package com.svsos.api.common.bo;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.svsos.api.common.bo.Constant.FileType;
import com.svsos.api.common.bo.Constant.Picture;

public class SystemGlobal {

	public final static String IMAGE_DOMAIN = "http://192.168.0.38:81";
	public final static String UPLOAD_FILE_API = IMAGE_DOMAIN + "/upload";

	/**
	 * 获取资源的服务器访问地址
	 * 
	 * @param id
	 *            资源的服务器存储hash ID
	 * @param fileType
	 *            文件类型,image、audio和video
	 * @param size
	 *            文件尺寸
	 * @return
	 */
	public static String getResourceUrl(String id, String fileType, String... size) {
		StringBuffer urlSb = new StringBuffer();
		String sizeUrl = size.length > 0 ? "_" + size[0] : "";
		if (StringUtils.isNoneBlank(id)) {
			urlSb.append(SystemGlobal.IMAGE_DOMAIN).append(File.separator).append(fileType).append(File.separator)
					.append(StringUtils.substring(id, 0, 1)).append(File.separator)
					.append(StringUtils.substring(id, 1, 3)).append(File.separator)
					.append(StringUtils.substring(id, 3, 5)).append(File.separator);

			if (fileType.equals(FileType.AUDIO.value)) {

				urlSb.append(id).append(sizeUrl).append(Constant.FileType.OUTPUT_AUDIO_SUFFIX_NAME);

			} else if (fileType.equals(FileType.VIDEO.value)) {

				urlSb.append(id).append(sizeUrl).append(Constant.FileType.OUTPUT_VIDEO_SUFFIX_NAME);

			} else {

				urlSb.append(id).append(sizeUrl).append(Picture.PICTURE_SUFFIX_NAME);

			}
		}
		return urlSb.toString();
	}

	/**
	 * 获取图片的服务器访问地址
	 * 
	 * @param id
	 *            图片的存储ID
	 * @param size
	 *            图片的尺寸
	 * @param suffixName
	 *            图片的后缀名
	 * @return
	 */
	public static String getPicUrl(String id, String size, String suffixName) {
		StringBuffer urlSb = new StringBuffer();
		if (StringUtils.isNoneBlank(id)) {
			urlSb.append(SystemGlobal.IMAGE_DOMAIN).append(File.separator).append(StringUtils.substring(id, 0, 1))
					.append(File.separator).append(StringUtils.substring(id, 1, 3)).append(File.separator)
					.append(StringUtils.substring(id, 3, 5)).append(File.separator).append(id).append(id).append("_")
					.append(size).append(".").append(suffixName);
		}
		return urlSb.toString();
	}
}
