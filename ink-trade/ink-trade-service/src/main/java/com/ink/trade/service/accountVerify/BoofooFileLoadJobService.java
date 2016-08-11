package com.ink.trade.service.accountVerify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.channel.core.boofoopay.service.BoofooFileLoadService;
import com.ink.job.AbstractBaseJob;

@Service("boofooFileLoadJobService")
public class BoofooFileLoadJobService extends AbstractBaseJob{
	
	@Autowired
	private BoofooFileLoadService boofooFileLoadService;
	@Override
	public void execute() {
		boofooFileLoadService.fileLoadFiRequest();//出款
		boofooFileLoadService.fileLoadFoRequest();//收款
	}
	
	
}
