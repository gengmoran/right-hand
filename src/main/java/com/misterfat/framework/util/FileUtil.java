package com.misterfat.framework.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {

	private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	protected static final int BUFFER_SIZE = 4096;

	protected final static Map<String, String> MIMES = new HashMap<String, String>();

	static {
		MIMES.put("323", "text/h323");
		MIMES.put("acx", "application/internet-property-stream");
		MIMES.put("ai", "application/postscript");
		MIMES.put("aif", "audio/x-aiff");
		MIMES.put("aifc", "audio/x-aiff");
		MIMES.put("aiff", "audio/x-aiff");
		MIMES.put("asf", "video/x-ms-asf");
		MIMES.put("asr", "video/x-ms-asf");
		MIMES.put("asx", "video/x-ms-asf");
		MIMES.put("au", "audio/basic");
		MIMES.put("avi", "video/x-msvideo");
		MIMES.put("axs", "application/olescript");
		MIMES.put("bas", "text/plain");
		MIMES.put("bcpio", "application/x-bcpio");
		MIMES.put("bin", "application/octet-stream");
		MIMES.put("bmp", "image/bmp");
		MIMES.put("c", "text/plain");
		MIMES.put("cat", "application/vnd.ms-pkiseccat");
		MIMES.put("cdf", "application/x-cdf");
		MIMES.put("cer", "application/x-x509-ca-cert");
		MIMES.put("class", "application/octet-stream");
		MIMES.put("clp", "application/x-msclip");
		MIMES.put("cmx", "image/x-cmx");
		MIMES.put("cod", "image/cis-cod");
		MIMES.put("cpio", "application/x-cpio");
		MIMES.put("crd", "application/x-mscardfile");
		MIMES.put("crl", "application/pkix-crl");
		MIMES.put("crt", "application/x-x509-ca-cert");
		MIMES.put("csh", "application/x-csh");
		MIMES.put("css", "text/css");
		MIMES.put("dcr", "application/x-director");
		MIMES.put("der", "application/x-x509-ca-cert");
		MIMES.put("dir", "application/x-director");
		MIMES.put("dll", "application/x-msdownload");
		MIMES.put("dms", "application/octet-stream");
		MIMES.put("doc", "application/msword");
		MIMES.put("dot", "application/msword");
		MIMES.put("dvi", "application/x-dvi");
		MIMES.put("dxr", "application/x-director");
		MIMES.put("eps", "application/postscript");
		MIMES.put("etx", "text/x-setext");
		MIMES.put("evy", "application/envoy");
		MIMES.put("exe", "application/octet-stream");
		MIMES.put("fif", "application/fractals");
		MIMES.put("flr", "x-world/x-vrml");
		MIMES.put("gif", "image/gif");
		MIMES.put("gtar", "application/x-gtar");
		MIMES.put("gz", "application/x-gzip");
		MIMES.put("h", "text/plain");
		MIMES.put("hdf", "application/x-hdf");
		MIMES.put("hlp", "application/winhlp");
		MIMES.put("hqx", "application/mac-binhex40");
		MIMES.put("hta", "application/hta");
		MIMES.put("htc", "text/x-component");
		MIMES.put("htm", "text/html");
		MIMES.put("html", "text/html");
		MIMES.put("htt", "text/webviewhtml");
		MIMES.put("ico", "image/x-icon");
		MIMES.put("ief", "image/ief");
		MIMES.put("iii", "application/x-iphone");
		MIMES.put("ins", "application/x-internet-signup");
		MIMES.put("isp", "application/x-internet-signup");
		MIMES.put("jfif", "image/pipeg");
		MIMES.put("jpe", "image/jpeg");
		MIMES.put("jpeg", "image/jpeg");
		MIMES.put("jpg", "image/jpeg");
		MIMES.put("js", "application/x-javascript");
		MIMES.put("latex", "application/x-latex");
		MIMES.put("lha", "application/octet-stream");
		MIMES.put("lsf", "video/x-la-asf");
		MIMES.put("lsx", "video/x-la-asf");
		MIMES.put("lzh", "application/octet-stream");
		MIMES.put("m13", "application/x-msmediaview");
		MIMES.put("m14", "application/x-msmediaview");
		MIMES.put("m3u", "audio/x-mpegurl");
		MIMES.put("man", "application/x-troff-man");
		MIMES.put("mdb", "application/x-msaccess");
		MIMES.put("me", "application/x-troff-me");
		MIMES.put("mht", "message/rfc822");
		MIMES.put("mhtml", "message/rfc822");
		MIMES.put("mid", "audio/mid");
		MIMES.put("mny", "application/x-msmoney");
		MIMES.put("mov", "video/quicktime");
		MIMES.put("movie", "video/x-sgi-movie");
		MIMES.put("mp2", "video/mpeg");
		MIMES.put("mp3", "audio/mpeg");
		MIMES.put("mpa", "video/mpeg");
		MIMES.put("mpe", "video/mpeg");
		MIMES.put("mpeg", "video/mpeg");
		MIMES.put("mpg", "video/mpeg");
		MIMES.put("mpp", "application/vnd.ms-project");
		MIMES.put("mpv2", "video/mpeg");
		MIMES.put("ms", "application/x-troff-ms");
		MIMES.put("mvb", "application/x-msmediaview");
		MIMES.put("nws", "message/rfc822");
		MIMES.put("oda", "application/oda");
		MIMES.put("p10", "application/pkcs10");
		MIMES.put("p12", "application/x-pkcs12");
		MIMES.put("p7b", "application/x-pkcs7-certificates");
		MIMES.put("p7c", "application/x-pkcs7-mime");
		MIMES.put("p7m", "application/x-pkcs7-mime");
		MIMES.put("p7r", "application/x-pkcs7-certreqresp");
		MIMES.put("p7s", "application/x-pkcs7-signature");
		MIMES.put("pbm", "image/x-portable-bitmap");
		MIMES.put("pdf", "application/pdf");
		MIMES.put("pfx", "application/x-pkcs12");
		MIMES.put("pgm", "image/x-portable-graymap");
		MIMES.put("pko", "application/ynd.ms-pkipko");
		MIMES.put("pma", "application/x-perfmon");
		MIMES.put("pmc", "application/x-perfmon");
		MIMES.put("pml", "application/x-perfmon");
		MIMES.put("pmr", "application/x-perfmon");
		MIMES.put("pmw", "application/x-perfmon");
		MIMES.put("pnm", "image/x-portable-anymap");
		MIMES.put("pot,", "application/vnd.ms-powerpoint");
		MIMES.put("ppm", "image/x-portable-pixmap");
		MIMES.put("pps", "application/vnd.ms-powerpoint");
		MIMES.put("ppt", "application/vnd.ms-powerpoint");
		MIMES.put("prf", "application/pics-rules");
		MIMES.put("ps", "application/postscript");
		MIMES.put("pub", "application/x-mspublisher");
		MIMES.put("qt", "video/quicktime");
		MIMES.put("ra", "audio/x-pn-realaudio");
		MIMES.put("ram", "audio/x-pn-realaudio");
		MIMES.put("ras", "image/x-cmu-raster");
		MIMES.put("rgb", "image/x-rgb");
		MIMES.put("rmi", "audio/mid");
		MIMES.put("roff", "application/x-troff");
		MIMES.put("rtf", "application/rtf");
		MIMES.put("rtx", "text/richtext");
		MIMES.put("scd", "application/x-msschedule");
		MIMES.put("sct", "text/scriptlet");
		MIMES.put("setpay", "application/set-payment-initiation");
		MIMES.put("setreg", "application/set-registration-initiation");
		MIMES.put("sh", "application/x-sh");
		MIMES.put("shar", "application/x-shar");
		MIMES.put("sit", "application/x-stuffit");
		MIMES.put("snd", "audio/basic");
		MIMES.put("spc", "application/x-pkcs7-certificates");
		MIMES.put("spl", "application/futuresplash");
		MIMES.put("src", "application/x-wais-source");
		MIMES.put("sst", "application/vnd.ms-pkicertstore");
		MIMES.put("stl", "application/vnd.ms-pkistl");
		MIMES.put("stm", "text/html");
		MIMES.put("svg", "image/svg+xml");
		MIMES.put("sv4cpio", "application/x-sv4cpio");
		MIMES.put("sv4crc", "application/x-sv4crc");
		MIMES.put("swf", "application/x-shockwave-flash");
		MIMES.put("t", "application/x-troff");
		MIMES.put("tar", "application/x-tar");
		MIMES.put("tcl", "application/x-tcl");
		MIMES.put("tex", "application/x-tex");
		MIMES.put("texi", "application/x-texinfo");
		MIMES.put("texinfo", "application/x-texinfo");
		MIMES.put("tgz", "application/x-compressed");
		MIMES.put("tif", "image/tiff");
		MIMES.put("tiff", "image/tiff");
		MIMES.put("tr", "application/x-troff");
		MIMES.put("trm", "application/x-msterminal");
		MIMES.put("tsv", "text/tab-separated-values");
		MIMES.put("txt", "text/plain");
		MIMES.put("uls", "text/iuls");
		MIMES.put("ustar", "application/x-ustar");
		MIMES.put("vcf", "text/x-vcard");
		MIMES.put("vrml", "x-world/x-vrml");
		MIMES.put("wav", "audio/x-wav");
		MIMES.put("wcm", "application/vnd.ms-works");
		MIMES.put("wdb", "application/vnd.ms-works");
		MIMES.put("wks", "application/vnd.ms-works");
		MIMES.put("wmf", "application/x-msmetafile");
		MIMES.put("wps", "application/vnd.ms-works");
		MIMES.put("wri", "application/x-mswrite");
		MIMES.put("wrl", "x-world/x-vrml");
		MIMES.put("wrz", "x-world/x-vrml");
		MIMES.put("xaf", "x-world/x-vrml");
		MIMES.put("xbm", "image/x-xbitmap");
		MIMES.put("xla", "application/vnd.ms-excel");
		MIMES.put("xlc", "application/vnd.ms-excel");
		MIMES.put("xlm", "application/vnd.ms-excel");
		MIMES.put("xls", "application/vnd.ms-excel");
		MIMES.put("xlt", "application/vnd.ms-excel");
		MIMES.put("xlw", "application/vnd.ms-excel");
		MIMES.put("xof", "x-world/x-vrml");
		MIMES.put("xpm", "image/x-xpixmap");
		MIMES.put("xwd", "image/x-xwindowdump");
		MIMES.put("z", "application/x-compress");
		MIMES.put("zip", "application/zip");

	}

	/**
	 * 
	 * 功能描述：计算文件的MD5值
	 * 
	 * @param file
	 * @return
	 * 
	 * @author 耿沫然
	 * 
	 * @since 2015年9月23日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getFileMD5(File file) {
		if (!file.isFile()) {
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in = null;
		byte buffer[] = new byte[8192];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer)) != -1) {
				digest.update(buffer, 0, len);
			}
			BigInteger bigInt = new BigInteger(1, digest.digest());
			return bigInt.toString(16);
		} catch (Exception e) {
			return null;
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (Exception e) {
				logger.warn("{}", e);
			}
		}

	}

	/**
	 * 
	 * 功能描述：计算文件的 SHA-1 值
	 * 
	 * @param file
	 * @return
	 * 
	 * @author 耿沫然
	 * 
	 * @since 2015年9月23日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getFileSha1(File file) {
		if (!file.isFile()) {
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in = null;
		byte buffer[] = new byte[8192];
		int len;
		try {
			digest = MessageDigest.getInstance("SHA-1");
			in = new FileInputStream(file);
			while ((len = in.read(buffer)) != -1) {
				digest.update(buffer, 0, len);
			}
			BigInteger bigInt = new BigInteger(1, digest.digest());
			return bigInt.toString(16);
		} catch (Exception e) {
			return null;
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (Exception e) {
				logger.warn("{}", e);
			}
		}
	}

	/**
	 * 
	 * 功能描述：创建文件及所有父文件夹
	 *
	 * @param filepath
	 * @throws IOException
	 * 
	 * @author 耿沫然
	 *
	 * @since 2015年11月28日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static void createFile(String filepath) throws IOException {

		File file = new File(filepath);
		if (!file.getParentFile().exists()) {
			createDir(file.getParent());
		}
		boolean createNewFile = file.createNewFile();
		if (!createNewFile) {
			throw new RuntimeException("create file fail");
		}
	}

	/**
	 * 
	 * 功能描述：创建文件夹及所有父文件夹
	 *
	 * @param dirpath
	 * 
	 * @author 耿沫然
	 *
	 * @since 2015年11月28日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static void createDir(String dirpath) {
		File file = new File(dirpath);
		if (!file.getParentFile().exists() || !file.getParentFile().isDirectory()) {
			createDir(file.getParent());
		}
		file.mkdir();

	}

	/**
	 * 
	 * 功能描述：获取文件后缀名
	 *
	 * @param fullFilename
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2016年9月1日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getSuffix(String fullFilename) {
		if (fullFilename != null) {
			return fullFilename.substring(fullFilename.lastIndexOf(".") + 1);
		}
		return "";
	}

	/**
	 * 
	 * 功能描述：获取不带后缀名的文件名
	 *
	 * @param fullFilename
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2016年9月1日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getFilename(String fullFilename) {
		if (fullFilename != null) {
			return fullFilename.substring(0, fullFilename.lastIndexOf("."));
		}
		return "";
	}

	/**
	 * 功能：Java读取txt文件的内容 步骤：1：先获得文件句柄 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
	 * 3：读取到输入流后，需要读取生成字节流 4：一行一行的输出。readline()。 备注：需要考虑的是异常情况
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static List<String> readTxtFile(String filePath, String encoding) throws IOException {

		InputStreamReader read = null;
		BufferedReader bufferedReader = null;
		List<String> list = null;
		try {
			list = new ArrayList<String>();
			File file = new File(filePath);
			read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
			bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				list.add(lineTxt);
			}
			read.close();
		} catch (IOException e) {
			throw e;
		} finally {
			if (read != null) {
				try {
					read.close();
				} catch (IOException e) {
				}
			}
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
					bufferedReader = null;
				} catch (IOException e) {
					logger.warn("{}", e);
				}
			}
		}
		return list;

	}

	public static enum FileType {
		IMAGE("image"), VIDEO("video"), TEXT("text"), AUDIO("audio");

		String fileType;

		FileType(String fileType) {
			this.fileType = fileType;
		}

	}

}
