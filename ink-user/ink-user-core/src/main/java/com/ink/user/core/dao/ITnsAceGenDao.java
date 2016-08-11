//package com.ink.user.core.dao;
//
//import java.util.List;
//
//import org.apache.ibatis.annotations.Param;
//
//import com.ink.base.EntityDao;
//import TnsAceGen;
//
//public interface ITnsAceGenDao extends EntityDao<TnsAceGen, Integer>{
//    int deleteByPrimaryKey(Long id);
//
//    int insert(TnsAceGen record);
//
//    int insertSelective(TnsAceGen record);
//
//    TnsAceGen selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(TnsAceGen record);
//
//    int updateByPrimaryKey(TnsAceGen record);
//
//    /**
//     * @FunctionName getTnsAceGenByTxnCodeAndAceGroup
//     * @Description 获取会计分录参照文件
//     * @author baiyu
//     * @date 2015年10月23日 下午3:52:28
//     * @version 1.0
//     * @history baiyu, 2015年10月23日 下午3:52:28 create
//     * 
//     * @param txnCode
//     * @param aceGroup
//     * @return
//     */
//    public List<TnsAceGen> getTnsAceGenByTxnCodeAndAceGroup(@Param("txnCode")String txnCode);
//    
//    //根据交易编号查询借贷方向
//    public List<TnsAceGen> getTnsAceDir(@Param("txnCode")String txnCode);
//    
//    public List<TnsAceGen> getTnsAceGenDir(String txnCode);
//
//}