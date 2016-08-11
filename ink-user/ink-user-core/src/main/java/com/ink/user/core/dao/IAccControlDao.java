//package com.ink.user.core.dao;
//
//import org.apache.ibatis.annotations.Param;
//
//import com.ink.base.EntityDao;
//import com.ink.user.core.po.AccControl;
//
///**
// * 
// * @author yangchen
// * @date 2016年5月24日 下午6:11:04
// */
//public interface IAccControlDao extends EntityDao<AccControl, Integer>{
//	
//	int deleteByPrimaryKey(Long id);
//
//    int insert(AccControl record);
//
//    int insertSelective(AccControl record);
//
//    AccControl selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(AccControl record);
//
//    int updateByPrimaryKey(AccControl record);
//    
//    /**
//     * @FunctionName selectByMchIdAndTxnCode
//     * @Description 该商户可以操作那些交易控制
//     * @author guojie.gao
//     * @date 2015年12月9日 下午4:18:22
//     * @version 1.0
//     * @history guojie.gao, 2015年12月9日 下午4:18:22 create
//     * 
//     * @param mchId
//     * @param txnCode
//     * @return
//     */
//    public AccControl selectByMchIdAndTxnCode(@Param("mchId")String mchId, @Param("txnCode")String txnCode);
//    
//    public boolean checkControl(String mchId, String txnCode);
//
//    public int updateAccControl(AccControl accControl);
//
//    public int insertAccControl(AccControl accControl);
//
//    public int insertAccControlSelective(AccControl accControl);
//
//}
