package com.ink.user.ext.core.batch.linemapper;

import com.ink.user.ext.api.constant.UserLogConstant;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.ink.base.log.util.MDCLog;
import com.ink.base.log.util.YinkerLogger;
import com.ink.user.ext.core.batch.item.UserMsgItem;

/**
 * 行映射到bean
 * @author yangchen
 * @date 2016年6月22日 下午1:56:10
 */
public class UserMsgJobFieldSetMapper implements FieldSetMapper<UserMsgItem>{

	private YinkerLogger logger = YinkerLogger.getLogger(UserMsgJobFieldSetMapper.class);
	@Override
	public UserMsgItem mapFieldSet(FieldSet fieldSet) throws BindException {
		try{
			//清楚线程变量信息
			MDCLog.setThreadPoolLog();
			// 商户号
			Long mchId = fieldSet.readLong(0);
			// 手机号
			String phone = fieldSet.readString(1);
			// 客户号
			Long custId = fieldSet.readLong(2);
			// 姓名
			String name = fieldSet.readString(3);
			// 昵称
			String nickName = fieldSet.readString(4);
			// 短信通道
			String msgChannel = fieldSet.readString(5);
			// 短信模板
			String msgTemplate = fieldSet.readString(6);
			// 活动信息
			String eventInfo = fieldSet.readString(7);
			// 证件类型
			String idType = fieldSet.readString(8);
			// 证件号
			String idNo = fieldSet.readString(9);
			// 地址
			String address = fieldSet.readString(10);
			// 性别
			String sex = fieldSet.readString(11);
			// 邮箱
			String email = fieldSet.readString(12);
			// 邮编
			String zipcode = fieldSet.readString(13);
			
			UserMsgItem item = new UserMsgItem();
			item.setMchId(mchId);
			item.setPhone(phone);
			item.setCustId(custId);
			item.setName(name);
			item.setNickName(nickName);
			item.setMsgChannel(msgChannel);
			item.setMsgTemplate(msgTemplate);
			item.setEventInfo(eventInfo);
			item.setIdType(idType);
			item.setIdNo(idNo);
			item.setAddress(address);
			item.setSex(sex);
			item.setEmail(email);
			item.setZipcode(zipcode);
			logger.info(UserLogConstant.ModuleCode,UserLogConstant.SendMassMsg_BusCode,"群发短信读入模块，读入条目 ：" + item.toString());
			return item;
		}catch(Exception e){
			logger.error(UserLogConstant.ModuleCode, "群发短信读入模块，读入条目出错", e);
			throw e;
		}
	}

}
