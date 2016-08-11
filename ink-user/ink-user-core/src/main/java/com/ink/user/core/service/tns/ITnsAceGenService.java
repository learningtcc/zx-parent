package com.ink.user.core.service.tns;

import java.util.List;

import com.ink.user.core.po.TnsAceGen;
public interface ITnsAceGenService {

	List<TnsAceGen> getTnsAceGenByTxnCodeAndAceGroup(String txnCode);
	
	List<TnsAceGen> getTnsAceGenDir(String txnCode);
	public int insertTnaAceGen(TnsAceGen tnsAceGen);
    public int updateById(TnsAceGen tnsAceGen) ;
    public TnsAceGen selectById(long id);
    public int deleteById(Long id) ;
}
