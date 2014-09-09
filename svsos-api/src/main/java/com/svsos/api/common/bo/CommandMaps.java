package com.svsos.api.common.bo;

import com.svsos.api.common.exception.ExceptionCode;
import com.svsos.api.common.exception.WebRequestException;


public enum CommandMaps {
	
	
	

	COMMAND_10001001("userDomain", "loginFromLianxi", 0),					//	联系内登录
	COMMAND_10001002("userNearby", "getUserOfNearbyList"),					//	附近的人
	COMMAND_10001003("userDomain", "getUserHomepage"),						//	用户个人主页查询
	COMMAND_10001004("userDomain", "registration"),							//	签到
	COMMAND_10001005("userDomain", "flowerManage"),							//	鲜花管理
	COMMAND_10001006("userDomain", "getMys"),								//	获取Mys
	COMMAND_10001007("userDomain", "getUserBrief"),							//	获取用户基本信息

	COMMAND_10001100("relationshipDomain", "getMsRelationship"),			//  我的主人或我的仆人列表
	COMMAND_10001101("relationshipDomain", "publishRecruit"),				//	发布招募信息
	COMMAND_10001102("relationshipDomain", "requestMs"),					//	应聘女主招募
	COMMAND_10001103("relationshipDomain", "requestMsByMaster"),			//	女主主动邀请仆人
	COMMAND_10001104("relationshipDomain", "ackRequestMs"),					//	同意建立主仆关系
	COMMAND_10001105("relationshipDomain", "agreeMasterRequest"),			//	仆人同意女主邀请
	COMMAND_10001106("relationshipDomain", "rejectedMasterRequest"),		//	仆人拒绝女主邀请
	COMMAND_10001107("relationshipDomain", "getNotSelectedServant"),		//	待选仆人列表
	COMMAND_10001108("relationshipDomain", "cancelMsRelation"),				//	解除关系
	COMMAND_10001109("relationshipDomain", "renewalMsRelation"),			//	发起续约
	COMMAND_10001110("relationshipDomain", "acceptRenewalMsRelation"),		//	同意续约
	COMMAND_10001111("relationshipDomain", "refusedRenewalMsRelation"),		//	拒绝续约
	COMMAND_10001112("relationshipDomain", "getMyRequest"),					//	拒绝续约
	
	COMMAND_10001400("pushMessageDomain", "getUnreadMessage"),				//  轮询抓取我的推送消息
	COMMAND_10001402("chatDomain", "chatText"),								//  文本聊天
	COMMAND_10001404("chatDomain", "ackReceive"),							//	确认消息收到
	COMMAND_10001405("chatDomain", "pollChat"),								//	轮询抓取我的聊天消息
	COMMAND_10001406("pushMessageDomain", "ackNotifyMessage"),				//	轮询抓取我的聊天消息
	
	COMMAND_10002000("msTaskDomain","getMyTaskList"),						//	我的任务列表
	COMMAND_10002001("msTaskDomain","publishTask"),							//	发布任务
	COMMAND_10002002("msTaskDomain","acceptTask"),							//	接受任务
	COMMAND_10002003("msTaskDomain","rejectedTask"),						//	拒绝任务
	COMMAND_10002004("msTaskDomain","commentTask"),							//	评论任务
	
	
	
	COMMAND_10001300("rewardPunishmentDomain","getRewardPunishmentTags"),	//	获取奖惩数据
	COMMAND_10001301("rewardPunishmentDomain","sendFlowers"),				//	送鲜花
	COMMAND_10001302("rewardPunishmentDomain","reward"),					//	奖励
	COMMAND_10001303("rewardPunishmentDomain","punish"),					//	惩罚
	COMMAND_10001304("rewardPunishmentDomain","consumeFlowers"),			//	消费鲜花
	
	;
	
	
	
	
	
	
	public final String beanName;
	public final String methodName; 
	public final Integer level; 		// 1默认级别 需要accessToken登录    0 开放接口 无账号信息
	
	
	private CommandMaps(String beanName, String methodName){
		this(beanName, methodName, 1);
	}
	
	private CommandMaps(String beanName, String methodName, Integer level){
		this.beanName = beanName;
		this.methodName = methodName;
		this.level = level;
	}
	
	public static CommandMaps getCommandMap(String command){
		try{
			return CommandMaps.valueOf("COMMAND_"+command);
		}catch(Exception e){
			throw new WebRequestException(ExceptionCode.METHODDOESNOTEXIST);
		}
		
	}
}
