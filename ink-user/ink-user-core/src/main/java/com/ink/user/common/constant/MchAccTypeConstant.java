package com.ink.user.common.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 商户账户类型常量列表
 * @author yangchen
 * @date 2016年5月11日 下午4:25:07
 */
public class MchAccTypeConstant {
	public static final List<String> mchSacTypes = new ArrayList<String>();
	static {
		mchSacTypes.add("0011");//商户借款人账户
		mchSacTypes.add("0012");//商户过期利息回收账户
	}
}
