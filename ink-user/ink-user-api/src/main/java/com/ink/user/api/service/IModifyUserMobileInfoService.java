package com.ink.user.api.service;

import com.ink.user.api.model.in.ModifyUserMobileInfoInput;
import com.ink.user.api.model.out.RetOutput;

/**
 * 用户手机号信息修改接口
 * @author yangchen
 * @date 2016年7月12日 下午7:38:06
 */
public interface IModifyUserMobileInfoService {
	public RetOutput exec(ModifyUserMobileInfoInput dto);
}
