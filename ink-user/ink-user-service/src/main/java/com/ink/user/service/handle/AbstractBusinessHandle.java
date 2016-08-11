package com.ink.user.service.handle;


public abstract class AbstractBusinessHandle {

	protected boolean checkOriInfo(String oriIdType, String oriIdNo,
			String oriMblNo, String dtoOriIdType, String dtoOriIdNo, String dtoOriMblNo) {
		if (oriMblNo == null || oriMblNo.trim().equals("")) {// 原手机号为空
			if (!dtoOriMblNo.trim().equals("")) {// 参数传入的原手机号不为空
				return false;
			}
		}

		if ((oriIdType == null || oriIdType.trim().equals(""))
				&& (dtoOriIdNo == null || dtoOriIdNo.trim().equals(""))) {// 原idType和idNo都是空
			if (!dtoOriIdNo.trim().equals("") || !dtoOriIdType.trim().equals("")) {// 传入参数的原idType或idNo不是空
				return false;
			}
		}
		if (!oriIdType.equals(dtoOriIdType) || !oriIdNo.equals(dtoOriIdNo)) {// 原idType或IdNo不匹配
			return false;
		}

		if (!oriMblNo.equals(dtoOriMblNo)) {// 原手机号不匹配
			return false;
		}

		return true;
	}

}
