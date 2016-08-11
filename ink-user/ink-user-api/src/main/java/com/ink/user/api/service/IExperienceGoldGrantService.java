package com.ink.user.api.service;

import com.ink.user.api.model.in.ExperienceGoldGrantInput;
import com.ink.user.api.model.out.RetOutput;

/**
 * @Description: 体验金发放
 * @author wanghao^_^
 * @date 2016年6月13日 上午11:26:46
 * @version V1.0
 */
public interface IExperienceGoldGrantService {
	public RetOutput exec(ExperienceGoldGrantInput dto) throws Exception;
}
