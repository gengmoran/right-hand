package com.misterfat.framework.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * 下载文件工具类
 * 
 * 依赖jar包 spring-web-3.2.3.RELEASE.jar sun jre rt.jarO
 * 
 * 
 */
@SuppressWarnings("all")
public class DownloadUtil {

	/**
	 * 下载文件
	 * 
	 * @param fileName
	 *            文件名(包含后缀)
	 * @param content
	 *            文件内容
	 * @param response
	 * @throws IOException
	 */
	public static void download(String fileName, byte[] content, HttpServletResponse response) throws IOException {

		setDownloadFileName(fileName, response);

		InputStream inStream = new ByteArrayInputStream(content);

		OutputStream outStream = response.getOutputStream();

		download(inStream, outStream);

	}

	/**
	 * 
	 * 下载文件
	 * 
	 * @param inStream
	 * @param outStream
	 * @throws IOException
	 */
	public static void download(InputStream inStream, OutputStream outStream) throws IOException {
		try {
			int len = 0;
			byte[] bytes = new byte[1024];
			while ((len = inStream.read(bytes)) != -1) {
				outStream.write(bytes, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inStream != null) {
				inStream.close();
			}
			if (outStream != null) {
				outStream.close();
			}
		}
	}

	/**
	 * 
	 * @param fileType
	 *            文件类型，图片/文本/视频
	 * @param fileName
	 *            文件名称，应该包含文件后缀
	 * @param content
	 *            文件内容 java.sql.Blob类型
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void download(String fileType, String fileName, Blob content, HttpServletResponse response)
			throws SQLException, IOException {

		setDownloadFileName(fileName, response);
		InputStream inStream = content.getBinaryStream();
		OutputStream outStream = response.getOutputStream();
		download(inStream, outStream);
	}

	/**
	 * 下载文件时，针对不同浏览器，进行附件名的编码
	 * 
	 * @param filename
	 *            下载文件名
	 * @param agent
	 *            客户端浏览器
	 * @return 编码后的下载附件名
	 * @throws IOException
	 */
	public static String encodeDownloadFilename(String filename, String agent) throws IOException {
		try {
			boolean isFireFox = (agent != null && agent.toLowerCase().indexOf("firefox") != -1);
			if (isFireFox) {
				filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
			} else {
				filename = toUtf8String(filename);
				if ((agent != null && agent.indexOf("MSIE") != -1)) {
					// see http://support.microsoft.com/default.aspx?kbid=816868
					if (filename.length() > 150) {
						// 根据request的locale 得出可能的编码
						filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			filename = "untitle" + filename.substring(filename.lastIndexOf("."), filename.length());
		}
		return filename;
	}

	public static String toUtf8String(String s) {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 获得下载文件的response
	 * 
	 * @param fileName
	 *            文件名(包含后缀)
	 * @param response
	 * @return
	 */
	public static HttpServletResponse setDownloadFileName(String fileName, HttpServletResponse response) {

		if (fileName == null || fileName == "") {
			fileName = "无标题";
		}

		HttpServletRequest request = getHttpServletRequest();
		response.reset();
		try {
			response.setHeader("Content-Disposition",
					"attachment;filename=" + encodeDownloadFilename(fileName, request.getHeader("user-agent")));
			response.setHeader("Content-Type", getContentType(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * 
	 * 功能描述：获取ContentType
	 *
	 * @param fileName
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2016年9月1日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getContentType(String fileName) {
		String suffix = FileUtil.getSuffix(fileName);
		return FileUtil.MIMES.get(suffix.toLowerCase());
	}

	/**
	 * 获得HttpServletRequest对象
	 * 
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest() {

		HttpServletRequest req = null;

		try {
			ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			req = sra.getRequest();
		} catch (Exception e) {
			req = null;
			e.printStackTrace();
		}
		return req;
	}

}
