package com.ink.trade.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.asile.core.manager.IAsileBankManager;
import com.ink.asile.core.po.AsileBank;
import com.ink.asile.core.po.AsileInfo;
import com.ink.asile.core.query.AsileBankQuery;
import com.ink.base.log.util.YinkerLogger;
import com.ink.job.AbstractBaseJob;
import com.ink.trade.api.model.in.AsileRouteInput;
import com.ink.trade.api.model.out.AsileRouteOutput;
import com.ink.trade.api.rule.IAsileRoute;
import com.ink.trade.core.cnst.TradeRespConstant;
import com.ink.trade.core.exception.TradeException;
import com.ink.trade.core.manager.IFailerLogManager;
import com.ink.trade.core.po.FailerLog;
import com.ink.trade.service.route.chain.AuthRouteExecutor;
import com.ink.trade.service.route.chain.RouteChainExecutor;
import com.ink.trade.util.CustomizedPropertyPlaceholderConfigurer;

/**
 * @author wanghao
 * @ClassName: AsileRouteImpl
 * @Description: 交易路由选择接口实现类
 * @date 2016年4月14日 下午3:00:14
 */
@Service
public class AsileRouteImpl extends AbstractBaseJob implements IAsileRoute {

    private static YinkerLogger log = YinkerLogger.getLogger(AsileRouteImpl.class);

    @Autowired
    private RouteChainExecutor routeChainExecutor;
    @Autowired
    private IAsileBankManager asileBankManager;
    @Autowired
    private IFailerLogManager failerLogManager;
    @Autowired
    private AuthRouteExecutor authRouteExecutor;
    @Autowired
    private CustomizedPropertyPlaceholderConfigurer propertyConfigurer;

    @Override
    public AsileRouteOutput getTradeAsile(AsileRouteInput asileRouteInput)
            throws Exception {
        log.info("交易路由选择,路由选择开始...");
        AsileRouteOutput output = new AsileRouteOutput();
        try {
            // 参数检查
            check(asileRouteInput);

            // 交易路由过滤
            AsileInfo info = routeChainExecutor.execute(asileRouteInput);
            if (null != info && null != info.getAsileCode()) {
                output.setReponseCode(TradeRespConstant.TRADE_SUCCESS);
                output.setReponseMsg(TradeRespConstant.TRADE_SUCCESS_MSG);
                output.setAsileCode(info.getAsileCode());
                log.info("交易路由选择,路由选择结束...通道选择={}",
                        (Object) info.getAsileCode());
            } else {
                output.setReponseCode(TradeRespConstant.NO_USEFUL_CHANNEL);
                output.setReponseMsg(TradeRespConstant.NO_USEFUL_CHANNEL_MSG);
                log.info("交易路由选择,路由选择结束...无符合规则通道");
            }
        } catch (TradeException e) {
            log.error("路由通道失败!", e);
            output.setReponseCode(e.getCode());
            output.setReponseMsg(e.getMessage());
        } catch (Exception e) {
            log.error("路由通道失败!", e);
            output.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
            output.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
        }
        return output;

    }

    @Override
    public AsileRouteOutput degradeAsile(String asileCode, String bankCode,String payType) {
        if (null == asileCode || "".equals(asileCode) || null == bankCode
                || "".equals(bankCode)) {
            log.error("交易路由选择,降级通道参数不符合！");
            throw new RuntimeException("交易路由选择,降级通道参数不符合！");
        }
        AsileBank asileBank = asileBankManager.findAsileBankByAsileCode(
                asileCode, bankCode,payType);
        if (asileBank == null) {
            log.error("交易路由选择,要降级的通道银行不存在！");
            throw new RuntimeException("交易路由选择,要降级的通道银行不存在！");
        }
        log.info("降级前路由"+asileBank.toString());
        //查询与降级相关路由
        AsileBankQuery query=new AsileBankQuery();
        query.setBankShort(bankCode);
        query.setAsilePayType(payType);
        List<AsileBank> asileBanks=asileBankManager.find(query);
        log.info("路由降级条件,银行："+bankCode+"支付方式："+payType+"降级影响数目"+asileBanks.size());
        asileBanks.remove(asileBank);
        Collections.sort(asileBanks);
        int initPriority=1;
        //重新设置路由级别
        for(AsileBank bank:asileBanks){
        	bank.setPriority(initPriority);
        	initPriority++;
        }
        //将不可用路由级别降到最小
        asileBank.setPriority(asileBanks.size()+1);
        log.info("降级后路由"+asileBank.toString());
        asileBanks.add(asileBank);
        //批量更新
        asileBankManager.updateList(asileBanks);
        AsileRouteOutput result = new AsileRouteOutput();
        result.setAsileCode(asileCode);
        return result;
    }

    @SuppressWarnings("static-access")
    @Override
    public void testFailRate() {
        // 扫描fail表，取最近time分钟的记录，如果相同通道相同银行的记录多过count条，则降级

        int time = Integer.valueOf((String) propertyConfigurer
                .getContextProperty("testFailRateTime"));
        int count = Integer.valueOf((String) propertyConfigurer
                .getContextProperty("testFailRateCount"));
        log.info("通道降级,读取配置文件,Time={},count={}", time, count);
        List<FailerLog> failerLogList = failerLogManager
                .findFailerLogByTime(time);
        log.info("通道降级,读取错误日志，长度为={}", failerLogList == null ? 0
                : failerLogList.size());
        Map<AsileBankKey, Integer> counter = new HashMap<AsileBankKey, Integer>();
        for (FailerLog log : failerLogList) {
            AsileBankKey key = new AsileBankKey(log.getBankCode(),
                    log.getAsileCode(),log.getRouteBusinessType());
            if (counter.containsKey(key)) {
                Integer num = counter.get(key);
                counter.put(key, num + 1);
            } else {
                counter.put(key, 1);
            }
        }
        for (AsileBankKey key : counter.keySet()) {//
            if (counter.get(key) > count) {
                // 通道降级
                AsileInfo record = new AsileInfo();
                record.setAsileCode(key.getAsileCode());
                degradeAsile(key.getAsileCode(), key.getBankCode(),key.getPayType());
            }
        }
    }

    @Override
    public AsileRouteOutput authRoute(AsileRouteInput input) {
        log.info("四要素认证路由选择,路由选择开始...");
        AsileRouteOutput output = new AsileRouteOutput();
        try {
            // 判断输入
            if (input == null || input.getMchId() == null) {
                log.error("四要素认证路由选择,AsileRouteInput参数不符合规则！");
                throw new TradeException(TradeRespConstant.TRADE_ERROR_0001,
                        "AsileRouteInput 路由规则参数不符合！");
            }

            String channelNo = authRouteExecutor.execute(input);

            if (channelNo == null) {
                output.setReponseCode(TradeRespConstant.NO_USEFUL_CHANNEL);
                output.setReponseMsg(TradeRespConstant.NO_USEFUL_CHANNEL_MSG);
                log.info("四要素认证路由选择,路由选择结束...无符合规则通道");
            } else {
                output.setReponseCode(TradeRespConstant.TRADE_SUCCESS);
                output.setReponseMsg(TradeRespConstant.TRADE_SUCCESS_MSG);
                output.setAsileCode(channelNo);
                log.info("四要素认证路由选择,路由选择结束...通道选择={}", channelNo);
            }


        } catch (TradeException e) {
            log.error("四要素认证路由通道失败!", e);
            output.setReponseCode(e.getCode());
            output.setReponseMsg(e.getMessage());
        } catch (Exception e) {
            log.error("四要素认证路由通道失败!", e);
            output.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
            output.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
        }
        return output;
    }

    public void check(AsileRouteInput asileRouteInput) throws Exception {
        // 判断输入
        if (asileRouteInput == null || asileRouteInput.getBankShort() == null
                || asileRouteInput.getAmt() == null || asileRouteInput.getMchId() == null) {
            log.error("交易路由选择,AsileRouteInput参数不符合规则！");
            throw new TradeException(TradeRespConstant.TRADE_ERROR_0001,
                    "AsileRouteInput 路由规则参数不符合！");
        }
    }

    @Override
    public void execute() throws Exception {
     this.testFailRate();

    }


    // 银行通道CODEPOJO
    class AsileBankKey {

        public AsileBankKey(String bankCode, String asileCode,String payType) {
            super();
            this.bankCode = bankCode;
            this.asileCode = asileCode;
            this.payType=payType;
        }

        private String bankCode;
        private String asileCode;
        private String payType;

        public String getPayType() {
			return payType;
		}

		public void setPayType(String payType) {
			this.payType = payType;
		}

		public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getAsileCode() {
            return asileCode;
        }

        public void setAsileCode(String asileCode) {
            this.asileCode = asileCode;
        }

        @Override
        public String toString() {
            return "AsileBankKey [bankCode=" + bankCode + ", asileCode="
                    + asileCode + "]";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result
                    + ((asileCode == null) ? 0 : asileCode.hashCode());
            result = prime * result
                    + ((bankCode == null) ? 0 : bankCode.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            AsileBankKey other = (AsileBankKey) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (asileCode == null) {
                if (other.asileCode != null)
                    return false;
            } else if (!asileCode.equals(other.asileCode))
                return false;
            if (bankCode == null) {
                if (other.bankCode != null)
                    return false;
            } else if (!bankCode.equals(other.bankCode))
                return false;
            return true;
        }

        private AsileRouteImpl getOuterType() {
            return AsileRouteImpl.this;
        }

    }


}
