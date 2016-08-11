package com.ink.user.api.service;

import com.ink.user.api.model.in.ExperienceGoldGrantBatchInput;
import com.ink.user.api.model.out.RetOutput;

/**
 * @Description: 体验金发放批量
 * @author wanghao^_^
 * @date 2016年6月13日 上午11:53:59
 * @version V1.0
 */
public interface IExperienceGoldGrantBatchService {
	public RetOutput exec(ExperienceGoldGrantBatchInput dto) throws Exception;
}
