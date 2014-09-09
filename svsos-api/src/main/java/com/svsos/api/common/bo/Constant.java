package com.svsos.api.common.bo;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

public class Constant {

	public enum Picture {

		SIZE100("100x100", ""), SIZE200("200x200", "");
		public final static String PICTURE_SUFFIX_NAME = ".jpg";
		public final String value;
		public final String name;

		Picture(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public static String getAvatarUrl(String avatarId) {
			if (StringUtils.isNoneBlank(avatarId)) {
				return SystemGlobal.IMAGE_DOMAIN + File.separator + StringUtils.substring(avatarId, 0, 1)
						+ File.separator + StringUtils.substring(avatarId, 1, 3) + File.separator
						+ StringUtils.substring(avatarId, 3, 5) + File.separator + avatarId + "_" + SIZE100.value
						+ PICTURE_SUFFIX_NAME;
			}
			return "";
		}
	}

	public enum FileType {

		IMAGE("img", "图片"), AUDIO("audio", "音频"), VIDEO("video", "视频");
		public final static String INPUT_PICTURE_SUFFIX_NAME = "jpg,png";
		public final static String INPUT_AUDIO_SUFFIX_NAME = "amr,aac";
		public final static String INPUT_VIDEO_SUFFIX_NAME = "3gp,mp4";

		public final static String OUTPUT_PICTURE_SUFFIX_NAME = ".jpg";
		public final static String OUTPUT_AUDIO_SUFFIX_NAME = ".amr";
		public final static String OUTPUT_VIDEO_SUFFIX_NAME = ".mp4";
		public final String value;
		public final String name;

		FileType(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public static String checkFileType(String suffixName) {
			if (StringUtils.contains(INPUT_PICTURE_SUFFIX_NAME, suffixName)) {
				return FileType.IMAGE.value;
			} else if (StringUtils.contains(INPUT_AUDIO_SUFFIX_NAME, suffixName)) {
				return FileType.AUDIO.value;
			} else {
				return FileType.VIDEO.value;
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(Picture.getAvatarUrl("09jkjdk33333"));
	}

}
