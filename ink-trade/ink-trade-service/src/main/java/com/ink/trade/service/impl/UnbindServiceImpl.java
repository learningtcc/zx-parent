package com.ink.trade.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.trade.api.model.in.UnbindInput;
import com.ink.trade.api.model.out.UnbindOutput;
import com.ink.trade.api.service.IUnbindService;
import com.ink.trade.core.cnst.TradeRespConstant;
import com.ink.trade.core.enums.IsDelete;
import com.ink.trade.core.enums.TradeStatus;
import com.ink.trade.core.manager.IAuthManager;
import com.ink.trade.core.po.Auth;
import com.ink.trade.core.query.AuthQuery;

@Service("unbindService")
public class UnbindServiceImpl implements IUnbindService {
	@Autowired
	private IAuthManager authManager;

	@Override
	public UnbindOutput unbind(UnbindInput unbindInput) {
		UnbindOutput unbindOutput = new UnbindOutput();
		unbindOutput.setCardNo(unbindInput.getCardNo());
		unbindOutput.setMerchantId(unbindInput.getMerchantId());
		unbindOutput.setPayType(unbindInput.getPayType());
		unbindOutput.setUserId(unbindInput.getUserId());
		unbindOutput.setReponseCode(TradeRespConstant.TRADE_SUCCESS);
		unbindOutput.setReponseMsg(TradeRespConstant.TRADE_SUCCESS_MSG);
		unbindOutput.setStatus(TradeStatus.SUCCESS.getValue());
		AuthQuery query = new AuthQuery();
		query.setCardNo(unbindInput.getCardNo());
		query.setMchId(unbindInput.getMerchantId());
		query.setUserId(unbindInput.getUserId());
		query.setIsDelete(IsDelete.NO.getValue());
		query.setMasterMark(true);// 查主库
		List<Auth> authList = authManager.find(query);
		if (CollectionUtils.isEmpty(authList)) {
			unbindOutput.setReponseCode(TradeRespConstant.CARD_NOT_BIND);
			unbindOutput.setReponseMsg(TradeRespConstant.CARD_NOT_BIND_MSG);
			unbindOutput.setStatus(TradeStatus.FAIL.getValue());
		}
		Auth auth = authList.get(0);
		auth.setIsDelete(IsDelete.YES.getValue());
		auth.setLastupdateTime(new Date());
		try {
			authManager.update(auth);
		} catch (Exception ex) {
			unbindOutput.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
			unbindOutput.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
			unbindOutput.setStatus(TradeStatus.FAIL.getValue());
		}
		return unbindOutput;
	}

}
