package com.svsos.api.common.bo;

import java.text.MessageFormat;

public enum RewardPunishmentTemplates {
	
	REWARDFLOWERS("{0}贈送{1}鮮花{2}朵"),
	PUNISHMENT("{0}使用道具【{1}】惩罚了{2}"),
	REWARD("{0}使用道具【{1}】奖励了{2}"),
	;
	
	public final String value;
	
	private RewardPunishmentTemplates(String value){
		this.value = value;
	}
	
	
	public String getTemplates(Object... args){
		return MessageFormat.format(value, args);
	}
}
