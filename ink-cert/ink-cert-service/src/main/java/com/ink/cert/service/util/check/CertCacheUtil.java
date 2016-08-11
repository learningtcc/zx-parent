package com.ink.cert.service.util.check;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.redis.client.CacheObject;
import com.ink.base.redis.client.Reader;
import com.ink.base.redis.client.Yedis;
import com.ink.cert.api.constant.CacheConstant;
import com.ink.cert.api.constant.SysConstant;
import com.ink.cert.api.util.CertUtil;
import com.ink.cert.core.po.CertInfo;
import com.ink.cert.core.query.CertInfoQuery;
import com.ink.cert.core.service.ICertInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 证书缓存工具类
 * Created by aiyungui on 2016/6/22.
 */
@Component("certCacheUtil")
public class CertCacheUtil {

    private YinkerLogger yinkerLogger = YinkerLogger.getLogger(CertCacheUtil.class);
    @Autowired
    private ICertInfoManager certInfoManager;
    @Autowired
    private Yedis yedis;

    /**
     * 获取证书信息
     *
     * @param merchantCode 商户编号
     * @param certCode     证书编号
     * @return
     */
    public CertInfo getCertInfo(String merchantCode, String certCode) {
        CertInfo certInfo;
        try {

            final String finalMerchantCode = merchantCode;
            final String finalCertCode = certCode;
            CacheObject cacheObject = yedis.getObject(CacheConstant.MSG_CERT_KEY,
                    CertUtil.getCertCacheKey(merchantCode, certCode), CertInfo.class, new Reader<Object>() {
                        @Override
                        public Object readerFromDatabase() {
                            CertInfo cacheCertInfo = null;
                            CertInfoQuery query = new CertInfoQuery();
                            query.setMerchatCode(finalMerchantCode);
                            query.setCertCode(finalCertCode);
                            List<CertInfo> infoList = certInfoManager.find(query);
                            if (!(null == infoList || infoList.isEmpty())) {
                                cacheCertInfo = infoList.get(0);
                            }
                            return cacheCertInfo;
                        }
                    });
            certInfo = (CertInfo) cacheObject.getValue();
        } catch (Exception e) {
            yinkerLogger.error(SysConstant.MODULE_DATA_SECRET_CODE, "获取缓存信息异常：" + e.getMessage(), e);
            certInfo = null;
        }
        return certInfo;
    }
}
