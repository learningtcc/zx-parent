package com.ink.trade.service.check;

import org.springframework.beans.factory.annotation.Autowired;

import com.ink.base.log.util.YinkerLogger;
import com.ink.basic.core.manager.IBankcardBinManager;
import com.ink.basic.core.po.BankcardBin;
import com.ink.trade.core.exception.orderfail.BankUnsupportException;
import com.ink.trade.core.manager.IMchBankManager;

public class MchBankCheck extends TradeCheck {
	private YinkerLogger LOGGER=YinkerLogger.getLogger(MchBankCheck.class);
	@Autowired
	private IMchBankManager mchBankManager;
	@Autowired
	private IBankcardBinManager bankCardBinManager;

	@Override
	public void operateCheck(Order order) {
		BankcardBin bankcardBin = bankCardBinManager.getByCardBin(order.getCardNo());
		if (bankcardBin == null) {
			LOGGER.error("根据cardbin将银行卡解析银行简码失败，卡号："+order.getCardNo());
              throw new BankUnsupportException();
		}
		//查询商户银行列表
		if (!mchBankManager.isBankSupport(order.getMerchantId(), bankcardBin.getBankSimpleCode())) {
			throw new BankUnsupportException();
		}
	}

	public IMchBankManager getMchBankManager() {
		return mchBankManager;
	}

	public void setMchBankManager(IMchBankManager mchBankManager) {
		this.mchBankManager = mchBankManager;
	}

}
