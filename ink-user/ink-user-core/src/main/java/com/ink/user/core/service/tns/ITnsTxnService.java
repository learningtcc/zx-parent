package com.ink.user.core.service.tns;

import java.util.List;

import com.ink.base.IBaseManager;
import com.ink.user.core.po.TnsTxn;

/**
 * 
 * @ClassName: ITnsTxnService 
 * @Description: 交易代码信息 
 * @author guojie.gao 
 * @date 2015年10月21日 下午1:36:37 
 *
 */
public interface ITnsTxnService extends IBaseManager<TnsTxn, Long>{

	public TnsTxn checkTnsTxn(String txnCode);
	/**
     * 
     * @Title: insertTnsTxn
     * @Description: 新增联机交易代码表 创建人：guojie.gao , 2015年10月26日 下午6:59:58
     *               修改人：guojie.gao , 2015年10月26日 下午6:59:58
     * @param
     * @return
     * @throws
     *
     */
    public int insertTnsTxn(TnsTxn tnsTxn);
    public int updateTnsTxn(TnsTxn tnsTxn);
    public TnsTxn selectByPrimaryKey(Long id);
    List<TnsTxn> selectAll();
}
