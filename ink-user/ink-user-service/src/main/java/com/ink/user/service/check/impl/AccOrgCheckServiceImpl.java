//package com.ink.user.service.check.impl;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.ink.user.core.dao.IAccOrgDao;
//import Checker;
//
///**
// * 渠道检查
// * @author yangchen
// * @date 2016年5月24日 下午4:57:13
// */
//@Service("accOrgCheckService")
//public class AccOrgCheckServiceImpl extends AbstractCheck implements Checker{
//	@Autowired
//	private IAccOrgDao accOrgDao;
//	@Override
//	public Map<String, Object> check(String txnCode, Map<String, String> dtoMap, Map<String, Object> map)
//			throws Exception {
////		String id = (String)dtoMap.get("orgId");
////		if(id == null){
////			id = dtoMap.get("channelId");
////		}
////		Long orgId = Long.valueOf( id);
////		AccOrg accOrg = accOrgDao.checkAccOrg(orgId);
////		if (null == accOrg) {
////			logger.error("根据机构id查询机构信息失败！orgId=【" + orgId + "】");
////			throw new AtpBusinessException(RespCode.RespCode_400004,
////					RespCode.RespCode_400004Desc);
////		}
////		return putMap(map, Constants.AccOrg, accOrg);
//		return map;
//	}
//
//}
