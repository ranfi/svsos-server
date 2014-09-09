package com.svsos.api.upload;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import jersey.repackaged.com.google.common.collect.Maps;

import org.junit.Test;

import com.svsos.core.utils.http.HttpUtils;

public class ImageUploadTest {

	private static final String UPLOAD_URL = "http://sz.files.lianxi.com/upload?ftype=img";

	@Test
	public void uploadImage() {

		Map<String, File> fileMap = new HashMap<String, File>();
		fileMap.put("userAvatar", new File("/home/ranfi/Pictures/5974e53f56d11e96c1.jpg"));
		String jsonString = HttpUtils.uploadMultipart(UPLOAD_URL, null, fileMap, "userFile");
		System.out.println(jsonString);
	}

	@Test
	public void uploadDataAndImage() {

		Map<String, File> fileMap = new HashMap<String, File>();
		fileMap.put("userAvatar", new File("/home/ranfi/Pictures/38015546.jpg"));
		fileMap.put("audioFile", new File("/home/ranfi/Downloads/1407218497863.amr"));
//		Map<String, Object> param = Maps.newHashMap();
//		param.put("user", "ddddd");
		String jsonString = HttpUtils.uploadMultipart("http://localhost:8080/lianxi-api/upload", null, fileMap, "param");
		System.out.println(jsonString);
	}

}
