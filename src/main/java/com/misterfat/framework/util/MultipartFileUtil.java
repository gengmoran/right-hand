package com.misterfat.framework.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.misterfat.framework.exception.GenericException;

public class MultipartFileUtil {

	private final static Logger logger = LoggerFactory.getLogger(MultipartFileUtil.class);

	/**
	 * 下载文件
	 * 
	 * @param fileName
	 * @param content
	 * @param response
	 * @throws Exception
	 */
	public static void download(String fileName, String contentType, byte[] content, HttpServletResponse response)
			throws IOException {
		response.reset();
		response.setContentType(contentType);
		if (!StringUtils.hasText(fileName)) {
			fileName = "无标题";
		}
		HttpServletRequest request = RequestUtil.getHttpServletRequest();
		if (request == null) {
			throw new GenericException("request is null");
		}
		response.setHeader("Content-Disposition", "attachment;filename="
				+ DownloadUtil.encodeDownloadFilename(fileName, request.getHeader("user-agent")));

		InputStream inStream = new ByteArrayInputStream(content);
		OutputStream outStream = response.getOutputStream();
		int len = 0;
		byte[] bytes = new byte[1024];
		while ((len = inStream.read(bytes)) != -1) {
			outStream.write(bytes, 0, len);
		}
		inStream.close();
		outStream.close();
	}

	/**
	 * 预览
	 * 
	 * @param content
	 *            文件内容 byte[]类型
	 * @param fileType
	 *            文件类型
	 * @param response
	 * @throws IOException
	 */
	public static void view(String contentType, byte[] content, HttpServletResponse response) throws IOException {
		InputStream inStream = new ByteArrayInputStream(content);
		response.reset();
		response.setContentType(contentType);
		OutputStream outStream = response.getOutputStream();
		int len = 0;
		byte[] bytes = new byte[1024];
		while ((len = inStream.read(bytes)) != -1) {
			outStream.write(bytes, 0, len);
		}
		inStream.close();
		outStream.close();
	}

	// 文件保存目录路径
	private static String savePath0 = RequestUtil.getServletContext().getRealPath("/") + "resources/";

	// 文件保存目录URL
	private static String saveUrl0 = RequestUtil.getHttpServletRequest().getContextPath() + "/resources/";

	// 定义允许上传的文件扩展名
	private static HashMap<String, String> extMap = new HashMap<String, String>();

	static {
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,txt,pdf");
		extMap.put("fileAndImage", "gif,jpg,jpeg,png,bmp,doc,docx,xls,xlsx,ppt,txt,pdf");
	}

	/**
	 * 上传文件到服务器
	 * 
	 * @param file
	 * @param dirName
	 * @return
	 * @throws Exception
	 */
	public static String uploadFile(MultipartFile file, String fileType, String dirName) throws GenericException {

		// 检查目录
		File uploadDir = new File(savePath0);
		if (!uploadDir.isDirectory()) {
			FileUtil.createDir(savePath0);
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			throw new GenericException("上传目录没有写权限。");
		}

		if (dirName == null) {
			throw new GenericException("目录名不正确。");
		}
		// 取得传进来的第一层目录，来标识传进来的是图片还是文件或者视频

		// 创建文件夹
		String savePath = savePath0, saveUrl = saveUrl0;
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		String fileName = file.getOriginalFilename();

		// 检查扩展名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if (!Arrays.<String>asList(extMap.get(fileType).split(",")).contains(fileExt)) {
			throw new GenericException("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(fileType) + "格式。");
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(10000) + "." + fileExt;
		try {
			File uploadedFile = new File(savePath, newFileName);
			file.transferTo(uploadedFile);
		} catch (Exception e) {
			logger.error("{}", e);
			throw new GenericException("上传文件失败。");
		}

		return saveUrl + newFileName;
	}

	/**
	 * 得到上传根目录
	 * 
	 * @return
	 */
	public static String getUploadRealPath() {
		return savePath0;
	}

	/**
	 * 得到网站根目录
	 * 
	 * @return
	 */
	public static String getRealPath() {
		return RequestUtil.getServletContext().getRealPath("/");
	}

}
