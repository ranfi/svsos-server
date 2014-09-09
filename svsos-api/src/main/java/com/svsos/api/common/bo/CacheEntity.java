package com.svsos.api.common.bo;

import java.io.Serializable;

/**
 * 定一些公用的缓存实体
 * 
 * @author ranfi
 *
 */
public interface CacheEntity {

	/**
	 * 用于招募信息过期推送的实体
	 * 
	 * @author ranfi
	 *
	 */
	public static class RecruitExpiredNotification implements Serializable {

		private static final long serialVersionUID = -9149742312361078605L;
		private Long recruitId;
		private Long masterUid;
		private Long expiredTime;

		public RecruitExpiredNotification() {
		}

		public RecruitExpiredNotification(Long recruitId, Long masterUid, Long expiredTime) {
			this.recruitId = recruitId;
			this.masterUid = masterUid;
			this.expiredTime = expiredTime;
		}

		public Long getRecruitId() {
			return recruitId;
		}

		public void setRecruitId(Long recruitId) {
			this.recruitId = recruitId;
		}

		public Long getMasterUid() {
			return masterUid;
		}

		public void setMasterUid(Long masterUid) {
			this.masterUid = masterUid;
		}

		public Long getExpiredTime() {
			return expiredTime;
		}

		public void setExpiredTime(Long expiredTime) {
			this.expiredTime = expiredTime;
		}

	}

	public static class RelationshipExpiredNotification implements Serializable {

		private static final long serialVersionUID = 4768459164749765383L;
		private Long msRelationshipId;
		private Long expiredTime;

		public Long getMsRelationshipId() {
			return msRelationshipId;
		}

		public void setMsRelationshipId(Long msRelationshipId) {
			this.msRelationshipId = msRelationshipId;
		}

		public Long getExpiredTime() {
			return expiredTime;
		}

		public void setExpiredTime(Long expiredTime) {
			this.expiredTime = expiredTime;
		}
	}

	public static class ActivityTaskNotification implements Serializable {
		private static final long serialVersionUID = 883447089390733526L;
		public long taskId;
		public long taskRecordId;
		public long masterUid;
		public long servantUid;
		public long expiredTime;

		public static class Builder {

			private long taskId;
			private long taskRecordId;
			private long masterUid;
			private long servantUid;
			private long expiredTime;

			public Builder() {
			}

			public Builder setTaskId(long taskId) {
				this.taskId = taskId;
				return this;
			}

			public Builder setTaskRecordId(long taskRecordId) {
				this.taskRecordId = taskRecordId;
				return this;
			}

			public Builder setMasterUid(long masterUid) {
				this.masterUid = masterUid;
				return this;
			}

			public Builder setServantUid(long servantUid) {
				this.servantUid = servantUid;
				return this;
			}

			public Builder setExpiredTime(long expiredTime) {
				this.expiredTime = expiredTime;
				return this;
			}

			public ActivityTaskNotification build() {
				return new ActivityTaskNotification(this);
			}

		}

		private ActivityTaskNotification(Builder builder) {
			this.taskId = builder.taskId;
			this.taskRecordId = builder.taskRecordId;
			this.masterUid = builder.masterUid;
			this.servantUid = builder.servantUid;
			this.expiredTime = builder.expiredTime;
		}
	}
}
