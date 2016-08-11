package com.ink.user.api.service;

import com.ink.user.api.model.in.ModifyUserIdInfoInput;
import com.ink.user.api.model.out.RetOutput;

/**
 * 用户证件信息修改接口
 * @author yangchen
 * @date 2016年7月12日 下午7:37:49
 */
public interface IModifyUserIdInfoService {
	public RetOutput exec(ModifyUserIdInfoInput dto);
}
