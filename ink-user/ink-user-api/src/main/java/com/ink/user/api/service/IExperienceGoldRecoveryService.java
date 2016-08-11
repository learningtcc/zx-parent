package com.ink.user.api.service;

import com.ink.user.api.model.in.ExperienceGoldRecoveryInput;
import com.ink.user.api.model.out.RetOutput;

/**
 * @Description: 体验金回收
 * @author wanghao^_^
 * @date 2016年6月13日 下午2:15:57
 * @version V1.0
 */
public interface IExperienceGoldRecoveryService {
	public RetOutput exec(ExperienceGoldRecoveryInput dto) throws Exception;
}
