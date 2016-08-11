package com.ink.user.api.service;

import com.ink.user.api.model.in.ModifyUserBaseInfoInput;
import com.ink.user.api.model.out.RetOutput;

/**
 * 用户基本资料修改接口
 * @author yangchen
 * @date 2016年7月12日 下午7:36:33
 */
public interface IModifyUserBaseInfoService {
	public RetOutput exec(ModifyUserBaseInfoInput dto);
}
