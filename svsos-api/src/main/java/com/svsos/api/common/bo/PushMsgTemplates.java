package com.svsos.api.common.bo;

import java.text.MessageFormat;

public enum PushMsgTemplates {
	
	MsRequest("{0}申请成为您的仆人", 100),
	TaskRequest("{0}创建了一个新的任务,赶快同ta一起完成吧", 101),
	TaskAccept("{0}接受了你的任务", 102),
	TaskRefused("{0}拒绝了你的任务", 103),
	TaskFinashed("{0}完成了任务，快去看看评论吧", 104),
	AcceptMsRequest("{0}同意了您的仆人申请", 105),
	BreakMsRelation("{0}与您断绝关系", 106),
	RenewalMsRelation("{0}希望与您续约", 107),
	AgreeMsRenewal("{0}同意与您续约", 108),
	RejectedRenewal("{0}拒绝与您续约", 109),
	MasterRequestServant("{0}邀请您成为她的仆人", 110),
	AgreeMasterRequest("{0}同意成为您的仆人", 111),
	RejectedMasterRequest("{0}拒绝成为您的仆人", 112),
	Punish("{0}使用道具【{1}】惩罚了您", 113),
	Reward("{0}使用道具【{1}】奖励了您", 114),
	SendFlows("{0}赠送了您{1}朵玫瑰花", 115),
	RecruitExpiredNotification("主人，你的招募信息还有{0}小时就过期了，您要看看有什么合适的仆人就赶紧确认吧", 116),
	NoCommentExpiredActifyTaskNotification("对仆人印象怎么样，去做个评价吧",117),
	NoExecutedActivityTaskNotification("还有两个小时，你和主人的【{0}】活动就要开始了，请记得参加哦！",118),
	RelationshipExpiredNotification("主人，你与仆人{0}的主仆关系还有{0}小时就过期了，快去问问设计该怎么提示", 119),
	RelationshipExpiredNotificationByServant("小仆，你与主人{0}的主仆关系还有{0}小时就过期了，快去问问设计该怎么提示", 120),
	RelationshipExpiredNotificationLift("主人，你与仆人{0}的主仆关系已经过期了，快去问问设计该怎么提示", 121),
	RelationshipExpiredNotificationLiftByServant("主人，你与仆人{0}的主仆关系已经过期了，快去问问设计该怎么提示", 121),
	
	;
	
	public final String value;
	public final int type;
	
	private PushMsgTemplates(String value, int type){
		this.value = value;
		this.type = type;
	}
	
	public int getType(){
		return type;
	}
	
	public String getTemplates(Object... args){
		return MessageFormat.format(value, args);
	}

}
