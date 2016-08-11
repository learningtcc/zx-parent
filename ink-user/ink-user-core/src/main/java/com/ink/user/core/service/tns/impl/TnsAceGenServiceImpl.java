//package com.ink.user.core.service.tns.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.ink.user.api.exception.AtpBusinessException;
//import com.ink.user.common.constant.RespCode;
//import com.ink.user.core.dao.ITnsAceGenDao;
//import TnsAceGen;
//import ITnsAceGenService;
//
//@Service("tnsAceGenService")
//public class TnsAceGenServiceImpl implements ITnsAceGenService {
//
//    @Autowired
//    private ITnsAceGenDao tnsAceGenDao;
//
//    // @Autowired
//    // private TnsAceGenCacheService tnsAceGenCacheService;
//    @Override
//    public List<TnsAceGen> getTnsAceGenByTxnCodeAndAceGroup(String txnCode) {
//        // List<TnsAceGen> listAceGen = tnsAceGenCacheService.getTnsAceGenFromCache(txnCode);
//        // if(listAceGen == null || listAceGen.size() == 0){
//        // listAceGen = tnsAceGenDao.getTnsAceGenByTxnCodeAndAceGroup(txnCode);
//        // if(listAceGen != null && listAceGen.size() > 0){
//        // tnsAceGenCacheService.setTnsAceGenList(txnCode, listAceGen);
//        // }else{
//        // throw new AtpBusinessException(RespCode.RespCode_000005,
//        // RespCode.RespCode_000005Desc);
//        // }
//        // }
//        // return listAceGen;
//        List<TnsAceGen> listAceGen = tnsAceGenDao.getTnsAceGenByTxnCodeAndAceGroup(txnCode);
//        if (listAceGen == null || listAceGen.size() == 0) {
//            throw new AtpBusinessException(RespCode.RespCode_000005, RespCode.RespCode_000005Desc);
//        }
//        return listAceGen;
//    }
//
//    @Override
//    public List<TnsAceGen> getTnsAceGenDir(String txnCode) {
//        return tnsAceGenDao.getTnsAceGenDir(txnCode);
//    }
//
//    @Override
//    public int insertTnaAceGen(TnsAceGen tnsAceGen) {
//        return tnsAceGenDao.insert(tnsAceGen);
//    }
//
//    @Override
//    public int updateById(TnsAceGen tnsAceGen) {
//        return tnsAceGenDao.updateByPrimaryKeySelective(tnsAceGen);
//    }
//
//    @Override
//    public TnsAceGen selectById(long id) {
//        return tnsAceGenDao.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public int deleteById(Long id) {
//        return tnsAceGenDao.deleteByPrimaryKey(id);
//    }
//
//}
