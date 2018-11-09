package com.misterfat.framework.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;

public class RequestUtil {

	private final static Logger logger = LoggerFactory.getLogger(RequestUtil.class);

	public final static String METHOD_GET = "GET";
	public final static String METHOD_POST = "POST";
	public final static String METHOD_DELETE = "DELETE";
	public final static String METHOD_PUT = "PUT";
	public final static String METHOD_PATCH = "PATCH";
	public final static String APPLICATION_JSON = "application/json";
	public final static Map<String, String> DEFAULT_JSON_HEADERS = new HashMap<String, String>();
	public final static Map<String, String> DEFAULT_FORM_HEADERS = new HashMap<String, String>();

	public static final int BUFFER_SIZE = 1024;

	static {
		// DEFAULT_JSON_HEADERS.put("Accept", APPLICATION_JSON);
		DEFAULT_JSON_HEADERS.put("Content-Type", APPLICATION_JSON);
		DEFAULT_FORM_HEADERS.put("Content-Type", "application/x-www-form-urlencoded");
	}

	private RequestUtil() {
	}

	private static boolean isUnAvailableIp(String ip) {
		return (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip));
	}

	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	public static boolean isTimeout(long start, long timeout) {
		long current = System.currentTimeMillis();
		return current - start >= timeout;
	}

	private static Response request(String method, Map<String, Object> datas, Map<String, String> headers,
			HttpURLConnection conn) throws IOException, UnsupportedEncodingException {
		Iterator<Entry<String, String>> iterator = headers.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> next = iterator.next();
			conn.setRequestProperty(next.getKey(), next.getValue());
		}

		conn.setDoInput(true);
		conn.setDoOutput(true);
		// conn.connect();

		if (!METHOD_GET.equalsIgnoreCase(method) && datas != null && !datas.isEmpty()) {
			PrintWriter writer = new PrintWriter(conn.getOutputStream());
			String contentType = headers.get("Content-Type");
			if (contentType != null && contentType.toLowerCase().contains(APPLICATION_JSON)) {
				writer.write(JSON.toJSONString(datas));
			} else {
				writer.write(httpBuildQuery(datas));
			}
			writer.flush();
			writer.close();

		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream in = null;
		long byteCount = 0;
		try {
			in = conn.getInputStream();
		} catch (IOException e) {
			in = conn.getErrorStream();
		} finally {
			if (in != null) {
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = in.read(buffer)) != -1) {
					baos.write(buffer, 0, bytesRead);
					byteCount += bytesRead;
				}
				baos.flush();
			}
			logger.info("ResponseCode: {},ResponseMessage: {}", conn.getResponseCode(), conn.getResponseMessage());
		}
		return new Response(byteCount, baos.toByteArray(), conn.getResponseCode(), conn.getResponseMessage(), conn);
	}

	public static String resolve(String url) {
		if (url != null) {
			url = url.replace("\\", "/");
		}
		return url;
	}

	/**
	 * 
	 * 功能描述：获取请求信息
	 *
	 * @param request
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年7月25日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Map<String, Object> getRequestInfo(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		map.put("AuthType", request.getAuthType());
		map.put("CharacterEncoding", request.getCharacterEncoding());
		map.put("ContentLength", request.getContentLength());
		map.put("ContentType", request.getContentType());
		map.put("ContextPath", request.getContextPath());
		map.put("LocalAddr", request.getLocalAddr());
		map.put("LocalName", request.getLocalName());
		map.put("LocalPort", request.getLocalPort());
		map.put("Method", request.getMethod());
		map.put("PathInfo", request.getPathInfo());
		map.put("PathTranslated", request.getPathTranslated());
		map.put("Protocol", request.getProtocol());
		map.put("QueryString", request.getQueryString());
		map.put("RemoteAddr", request.getRemoteAddr());
		map.put("RemoteHost", request.getRemoteHost());
		map.put("RemotePort", request.getRemotePort());
		map.put("RemoteUser", request.getRemoteUser());
		map.put("RequestedSessionId", request.getRequestedSessionId());
		map.put("RequestURI", request.getRequestURI());
		map.put("Scheme", request.getScheme());
		map.put("ServerName", request.getServerName());
		map.put("ServerPort", request.getServerPort());
		map.put("ServletPath", request.getServletPath());
		map.put("isRequestedSessionIdFromCookie", request.isRequestedSessionIdFromCookie());
		map.put("isRequestedSessionIdFromURL", request.isRequestedSessionIdFromURL());
		map.put("isRequestedSessionIdFromURL", request.isRequestedSessionIdFromURL());
		map.put("isRequestedSessionIdValid", request.isRequestedSessionIdValid());
		map.put("isSecure", request.isSecure());

		Map<String, Object> headerMap = new HashMap<>();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			headerMap.put(headerName, request.getHeader(headerName));
		}
		map.put("headers", headerMap);
		return map;
	}

	/**
	 * 
	 * 功能描述：获取 HttpServletRequest
	 *
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年7月10日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static HttpServletRequest getHttpServletRequest() {
		try {
			ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest req = sra.getRequest();
			return req;
		} catch (Exception e) {
			logger.error("{}", e);
			return null;
		}
	}

	/**
	 * 获取HttpSession对象
	 * 
	 * @return
	 */
	public static HttpSession getHttpSession() {
		try {
			return getHttpServletRequest().getSession();
		} catch (Exception e) {
			logger.error("{}", e);
			return null;
		}
	}

	/**
	 * 获取ServletContext对象
	 * 
	 * @return
	 */
	public static ServletContext getServletContext() {
		try {
			return getHttpSession().getServletContext();
		} catch (Exception e) {
			logger.error("{}", e);
			return null;
		}
	}

	/**
	 * 
	 * 功能描述：判断是否是HTTPS
	 *
	 * @param url
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年4月25日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static boolean isHttps(String url) {
		return url.toLowerCase().startsWith("https:");
	}

	/**
	 * 
	 * 功能描述：判断是否是异步请求
	 *
	 * @param request
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年3月25日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static boolean isAjax(HttpServletRequest request) {
		return request.getHeader("x-requested-with") != null;
	}

	/**
	 * 
	 * 功能描述：判断是否是跨域
	 *
	 * @param request
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年5月18日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static boolean isCrossDomain(HttpServletRequest request) {
		String origin = request.getHeader("origin");
		return origin != null && !origin.contains(request.getHeader("host"));
	}

	/**
	 * 
	 * 功能描述：获得请求IP
	 *
	 * @param request
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年5月13日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String retrieveClientIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (isUnAvailableIp(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (isUnAvailableIp(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (isUnAvailableIp(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 参数编码
	 * 
	 * @param data
	 * @return
	 */
	public static String httpBuildQuery(Map<String, Object> data) {
		String ret = "";
		String k, v;
		Iterator<String> iterator = data.keySet().iterator();
		while (iterator.hasNext()) {
			k = iterator.next();
			v = data.get(k).toString();
			try {
				ret += URLEncoder.encode(k, "utf8") + "=" + URLEncoder.encode(v, "utf8");
			} catch (UnsupportedEncodingException e) {
			}
			ret += "&";
		}
		return ret.substring(0, ret.length() - 1);
	}

	/**
	 * 
	 * 功能描述：发送GET请求
	 *
	 * @param url
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年5月13日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Response get(String url) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		if (isHttps(url)) {
			return httpsRequest(METHOD_GET, url, null, null);
		}
		return httpRequest(METHOD_GET, url, null, null);
	}

	/**
	 * 
	 * 功能描述：发送GET请求
	 *
	 * @param url
	 * @param datas
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年4月25日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Response get(String url, Map<String, Object> datas)
			throws KeyManagementException, NoSuchAlgorithmException, IOException {
		if (isHttps(url)) {
			return httpsRequest(METHOD_GET, url, datas, null);
		}
		return httpRequest(METHOD_GET, url, datas, null);
	}

	/**
	 * 
	 * 功能描述：发送GET请求
	 *
	 * @param url
	 * @param datas
	 * @param headers
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年4月25日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Response get(String url, Map<String, Object> datas, Map<String, String> headers)
			throws KeyManagementException, NoSuchAlgorithmException, IOException {
		if (isHttps(url)) {
			return httpsRequest(METHOD_GET, url, datas, headers);
		}
		return httpRequest(METHOD_GET, url, datas, headers);
	}

	/**
	 * 
	 * 功能描述：发送PUT请求
	 *
	 * @param url
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年5月13日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Response put(String url) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		if (isHttps(url)) {
			return httpsRequest(METHOD_PUT, url, null, null);
		}
		return httpRequest(METHOD_PUT, url, null, null);
	}

	/**
	 * 
	 * 功能描述：发送PUT请求
	 *
	 * @param url
	 * @param datas
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年4月25日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Response put(String url, Map<String, Object> datas)
			throws KeyManagementException, NoSuchAlgorithmException, IOException {
		if (isHttps(url)) {
			return httpsRequest(METHOD_PUT, url, datas, null);
		}
		return httpRequest(METHOD_PUT, url, datas, null);
	}

	/**
	 * 
	 * 功能描述：发送PUT请求
	 *
	 * @param url
	 * @param datas
	 * @param headers
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年4月25日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Response put(String url, Map<String, Object> datas, Map<String, String> headers)
			throws KeyManagementException, NoSuchAlgorithmException, IOException {
		if (isHttps(url)) {
			return httpsRequest(METHOD_PUT, url, datas, headers);
		}
		return httpRequest(METHOD_PUT, url, datas, headers);
	}

	/**
	 * 
	 * 功能描述：发送POST请求
	 *
	 * @param url
	 * @param datas
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年5月13日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Response post(String url, Map<String, Object> datas)
			throws KeyManagementException, NoSuchAlgorithmException, IOException {
		if (isHttps(url)) {
			return httpsRequest(METHOD_POST, url, datas, null);
		}
		return httpRequest(METHOD_POST, url, datas, null);
	}

	/**
	 * 
	 * 功能描述：发送POST请求
	 *
	 * @param url
	 * @param datas
	 * @param headers
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年4月25日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Response post(String url, Map<String, Object> datas, Map<String, String> headers)
			throws KeyManagementException, NoSuchAlgorithmException, IOException {
		if (isHttps(url)) {
			return httpsRequest(METHOD_POST, url, datas, headers);
		}
		return httpRequest(METHOD_POST, url, datas, headers);
	}

	/**
	 * 
	 * 功能描述：发送请求
	 *
	 * @param method
	 * @param url
	 * @param datas
	 * @param headers
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年4月25日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Response request(String method, String url, Map<String, Object> datas, Map<String, String> headers)
			throws KeyManagementException, NoSuchAlgorithmException, IOException {
		if (isHttps(url)) {
			return httpsRequest(method, url, datas, headers);
		}
		return httpRequest(method, url, datas, headers);
	}

	/**
	 * http请求
	 * 
	 * @param url
	 * @param datas
	 * @return
	 * @throws IOException
	 */
	public static Response httpRequest(String method, String url, Map<String, Object> datas,
			Map<String, String> headers) throws IOException {
		url = resolve(url);
		HttpURLConnection conn;
		if (headers == null) {
			headers = DEFAULT_JSON_HEADERS;
		}
		try {

			if (METHOD_GET.equalsIgnoreCase(method) && datas != null) {
				url = buildGetUrl(url, datas);
				logger.info("请求方法:{},请求URL: {},请求头:{}", method, url, JSON.toJSONString(headers));
			} else {
				logger.info("请求方法:{},请求URL: {},请求数据:{},请求头:{}", method, url, JSON.toJSONString(datas),
						JSON.toJSONString(headers));

			}
			URL requestUrl = new URL(url);
			conn = (HttpURLConnection) requestUrl.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		conn.setRequestMethod(method);

		return request(method, datas, headers, conn);
	}

	/**
	 * 
	 * 功能描述：生成GET请求的URL
	 *
	 * @param url
	 * @param datas
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年7月11日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String buildGetUrl(String url, Map<String, Object> datas) {
		if (url.contains("?")) {
			return url + "&" + httpBuildQuery(datas);
		}
		return url + "?" + httpBuildQuery(datas);
	}

	/**
	 * 
	 * 功能描述：发送HTTPS请求
	 *
	 * @param url
	 * @param datas
	 * @return
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws KeyManagementException
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年2月16日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Response httpsRequest(String method, String url, Map<String, Object> datas,
			Map<String, String> headers) throws NoSuchAlgorithmException, IOException, KeyManagementException {
		url = resolve(url);
		if (headers == null) {
			headers = DEFAULT_JSON_HEADERS;
		}
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
		if (METHOD_GET.equalsIgnoreCase(method) && datas != null) {
			url = buildGetUrl(url, datas);
			logger.info("请求方法:{},请求URL: {},请求头:{}", method, url, headers);
		} else {
			logger.info("请求方法:{},请求URL: {},请求数据:{},请求头:{}", method, url, datas, headers);

		}
		URL console = new URL(url);
		HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
		conn.setRequestMethod(method);
		conn.setSSLSocketFactory(sc.getSocketFactory());
		conn.setHostnameVerifier(new TrustAnyHostnameVerifier());

		return request(method, datas, headers, conn);
	}

	/**
	 * 
	 * 功能描述：获取访问根路径
	 *
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年7月20日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getBaseUrl() {
		HttpServletRequest request = getHttpServletRequest();
		String path = request.getContextPath();
		int serverPort = request.getServerPort();
		String baseUrl = request.getScheme() + "://" + request.getServerName();
		if (80 != serverPort && 443 != serverPort) {
			baseUrl += (":" + serverPort);
		}
		baseUrl += path;
		return baseUrl;
	}

	/**
	 * 
	 * 功能描述：获取客户端IP
	 *
	 * @param request
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年7月30日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("x-real-ip");
		if (!StringUtils.hasText(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static class Response {

		private long byteCount;

		private byte[] data;

		private int code;

		private String message;

		private HttpURLConnection connection;

		public byte[] getData() {
			return data;
		}

		public void setData(byte[] data) {
			this.data = data;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public HttpURLConnection getConnection() {
			return connection;
		}

		public void setConnection(HttpURLConnection connection) {
			this.connection = connection;
		}

		public long getByteCount() {
			return byteCount;
		}

		public void setByteCount(long byteCount) {
			this.byteCount = byteCount;
		}

		public String reponseText(String charsetName) throws UnsupportedEncodingException {
			return new String(data, charsetName);
		}

		public String reponseText() throws UnsupportedEncodingException {
			return new String(data, "utf-8");
		}

		public Response(long byteCount, byte[] data, int code, String message, HttpURLConnection connection) {
			super();
			this.byteCount = byteCount;
			this.data = data;
			this.code = code;
			this.message = message;
			this.connection = connection;
		}

	}

}