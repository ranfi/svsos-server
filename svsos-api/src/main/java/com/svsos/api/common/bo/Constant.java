package com.svsos.api.common.bo;

import java.io.File;
import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;

public class Constant {

	public enum NotifyTime {

		RECRUITENDNOTIFICATION(3, "招募结束前通知时间"),
		
		RELATIONSHIPEXPIREDNOTIFICATION(2, "招募结束前通知时间"),
		
		RELATIONSHIPKEEP(3*24, "招募结束前通知时间"),
		
		NoExecutedActivityTaskNotification(2, "活动未开始前２小时通知"),

		NoCommentExpiredActivityTaskNotification(12, "活动任务结束后１２小时未评价"),
		;

		public final int hours;
		public final long time;
		public final String name;

		NotifyTime(int hours, String name) {
			this.hours = hours;
			this.time = hours * 3600 * 1000;
			this.name = name;
		}
	}

	public enum Gender {
		MALE(1, "男"), FEMALE(2, "女");

		public final int value;
		public final String name;

		Gender(int value, String name) {
			this.value = value;
			this.name = name;
		}
	}

	public enum RecruitStatus {
		NORECRUIT(0, "没有招募"), RECRUITING(1, "招募中"), RECRUITFINISH(2, "已完成"), EXPIRED(3, "过期");

		public final int value;
		public final String name;

		RecruitStatus(int value, String name) {
			this.value = value;
			this.name = name;
		}
	}

	public enum RequestStatus {
		REQUESTING(0, "申请中"), AGREE(1, "同意"), REFUSED(2, "拒绝"), EXPIRED(3, "过期"), IGNORE(-1, "忽略");

		public final int value;
		public final String name;

		RequestStatus(int value, String name) {
			this.value = value;
			this.name = name;
		}
	}

	public enum RequestType {
		MSRELATIONSHIPREQUEST(1, "主仆关系，仆人申请"), MASTERREQUEST(1, "女主邀请仆人");

		public final int value;
		public final String name;

		RequestType(int value, String name) {
			this.value = value;
			this.name = name;
		}
	}

	public enum MessageType {
		TEXT(1, "文本"), IMAGE(2, "图像"), AUDIO(3, "音频"), VIDEO(4, "视频"), CHATREWARDANDPUNISH(5, "奖励惩罚");

		public final int value;
		public final String name;

		MessageType(int value, String name) {
			this.value = value;
			this.name = name;
		}
	}

	public enum RelationshipStatus {
		NORNAL(1, "正常"), LIFT(2, "解除关系"), EXPIRED(3, "过期"), DELETED(-1, "删除");

		public final int value;
		public final String name;

		RelationshipStatus(int value, String name) {
			this.value = value;
			this.name = name;
		}
	}

	public enum TaskStatus {
		RELEASE(1, "发布中"), ACCEPTED(2, "接受"), ACCEPTBYOTHERS(3, "被他人接受"), REFUSED(4, "拒绝"), FINASHED(5, "完成");

		public final int value;
		public final String name;

		TaskStatus(int value, String name) {
			this.value = value;
			this.name = name;
		}
	}

	public enum TaskType {
		MASTERTASKS(1, "女主任务"), PLEASETASKS(2, "讨好任务");

		public final int value;
		public final String name;

		TaskType(int value, String name) {
			this.value = value;
			this.name = name;
		}
	}

	public enum UserMsStatus {
		NONEMS(0, "无主或无仆"), SINGLE(1, "有主或仆人未满"), FULLED(2, "有主或仆人已满");

		public final int value;
		public final String name;

		UserMsStatus(int value, String name) {
			this.value = value;
			this.name = name;
		}
	}

	public enum FlowerType {
		EARNINGS(1, "收益"), EXPENDITURE(2, "支出");

		public final int value;
		public final String name;

		FlowerType(int value, String name) {
			this.value = value;
			this.name = name;
		}
	}

	/**
	 * 奖赏和惩罚定义
	 * 
	 * @author ranfi
	 *
	 */
	public enum RewardPunishmentCategory {
		REWARD(1, "奖赏"), PUNISHMENT(2, "惩罚");

		public final int value;
		public final String name;

		RewardPunishmentCategory(int value, String name) {
			this.value = value;
			this.name = name;
		}
	}

	/**
	 * 惩罚标签类型定义
	 * 
	 * @author ranfi
	 *
	 */
	public enum PunishmentTagType {
		TAG(1, "打标签"), AVATAR(2, "头像"), NICKNAME(3, "昵称"), MIXTAG(4, "混合标签");

		public final int value;
		public final String name;

		PunishmentTagType(int value, String name) {
			this.value = value;
			this.name = name;
		}
	}

	/**
	 * 奖赏标签类型定义
	 * 
	 * @author ranfi
	 *
	 */
	public enum RewardTagType {
		TAG(1, "打标签"), FLOWERS(2, "送鲜花");

		public final int value;
		public final String name;

		RewardTagType(int value, String name) {
			this.value = value;
			this.name = name;
		}
	}

	public enum DefaultContent {
		Registration("签到成功,获取{0}朵玫瑰");

		public final String text;

		DefaultContent(String text) {
			this.text = text;
		}

		public String getText(Object... args) {
			return MessageFormat.format(text, args);
		}
	}

	public enum SystemTime {
		MsRelationTime(3 * 24 * 60 * 60 * 1000, "主仆关系存活时间");

		public final long value;
		public final String name;

		SystemTime(long value, String name) {
			this.value = value;
			this.name = name;
		}
	}

	public enum MsgAcceptStatus {

		NOTREAD(0, "未读消息"), ACCEPTED(1, "收到消息");

		public final int value;
		public final String name;

		MsgAcceptStatus(int value, String name) {
			this.value = value;
			this.name = name;
		}
	}

	public enum RegistrationStatus {

		NOTREGISTED(0, "未签到"), REGISTED(1, "签到");

		public final int value;
		public final String name;

		RegistrationStatus(int value, String name) {
			this.value = value;
			this.name = name;
		}
	}

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
