package com.svsos.core.utils.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.svsos.core.utils.JsonMapper;

/**
 * 
 * @ClassName: HttpUtils
 * @Description: http工具类,处理表单模拟上传等逻辑
 * @author ranfi
 * @date May 12, 2014 1:18:29 PM
 * 
 */
public class HttpUtils {

	/**
	 * 
	 * @param url
	 *            服务器地址
	 * @param in
	 *            文件流
	 * @param name
	 *            服务端获取的文件命名
	 * @return
	 */
	public static String uploadMultipart(String url, InputStream in, String name,String filename) {
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost httppost = new HttpPost(url);
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().setMode(
				HttpMultipartMode.BROWSER_COMPATIBLE);

		HttpResponse response = null;
		try {
			multipartEntityBuilder.addBinaryBody(name, in, ContentType.MULTIPART_FORM_DATA,filename);
			HttpEntity httpEntity = multipartEntityBuilder.build();
			httppost.setEntity(httpEntity);
			response = client.execute(httppost);
			HttpEntity resEntity = response.getEntity();
			return IOUtils.toString(resEntity.getContent());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httppost.abort();
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String uploadMultipart(String url, File file, String name) {
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost httppost = new HttpPost(url);
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().setMode(
				HttpMultipartMode.BROWSER_COMPATIBLE);
		HttpResponse response = null;
		try {
			multipartEntityBuilder.addBinaryBody(name, file);
			HttpEntity httpEntity = multipartEntityBuilder.build();
			httppost.setEntity(httpEntity);
			response = client.execute(httppost);
			HttpEntity resEntity = response.getEntity();
			return IOUtils.toString(resEntity.getContent());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String uploadMultipart(String url, Object param, File file, String fileName, String paramName) {
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost httppost = new HttpPost(url);
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().setMode(
				HttpMultipartMode.BROWSER_COMPATIBLE);
		HttpResponse response = null;
		try {

			multipartEntityBuilder.addBinaryBody(fileName, file);
			multipartEntityBuilder.addTextBody(paramName, JsonMapper.nonEmptyMapper().toJson(param),
					ContentType.APPLICATION_JSON);
			HttpEntity httpEntity = multipartEntityBuilder.build();
			httppost.setEntity(httpEntity);
			response = client.execute(httppost);
			HttpEntity resEntity = response.getEntity();
			return IOUtils.toString(resEntity.getContent());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String uploadMultipart(String url, Object param, Map<String, File> fileMap, String paramName) {
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost httppost = new HttpPost(url);
		
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().setMode(
				HttpMultipartMode.BROWSER_COMPATIBLE);
		HttpResponse response = null;
		try {
			for (Map.Entry<String, File> entry : fileMap.entrySet()) {
				multipartEntityBuilder.addBinaryBody(entry.getKey(), entry.getValue());
			}
			multipartEntityBuilder.addTextBody(paramName, JsonMapper.nonEmptyMapper().toJson(param),
					ContentType.APPLICATION_JSON);
			HttpEntity httpEntity =  multipartEntityBuilder.build();
			httppost.setEntity(httpEntity);
			response = client.execute(httppost);
			HttpEntity resEntity = response.getEntity();
			return IOUtils.toString(resEntity.getContent());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String post(String url, List<NameValuePair> parameters, String charset){
		
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost httppost = new HttpPost(url);
		HttpResponse response = null;
		try {
			
			httppost.setEntity(new UrlEncodedFormEntity(parameters, charset));
			response = client.execute(httppost);
			HttpEntity resEntity = response.getEntity();
			return IOUtils.toString(resEntity.getContent());
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	
	public static String post(String url, List<NameValuePair> parameters){
		return post(url, parameters, "UTF-8");
	}
	
	public static InputStream downloadFiles(String url){
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet httpget = new HttpGet(url);
		try {
			HttpResponse response = client.execute(httpget);
			HttpEntity resEntity = response.getEntity();
			return IOUtils.toBufferedInputStream(resEntity.getContent());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
