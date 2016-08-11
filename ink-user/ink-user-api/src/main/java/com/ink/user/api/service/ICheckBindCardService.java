package com.ink.user.api.service;

import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.model.in.CheckBindCardInput;

/**
 * 绑卡检查接口
 * @author yangchen
 * @date 2016年4月21日 下午4:44:47
 */
public interface ICheckBindCardService {
	public RetOutput exec(CheckBindCardInput dto) throws Exception;
}
