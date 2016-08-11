package com.ink.user.ext.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ink.user.ext.api.IDeleteFileService;

/**
 * 删除上传文件接口
 * @author wanghao
 */
@Service
public class IDeleteFileServiceImpl implements IDeleteFileService{

	@Override
	public void deleteFileByDate(List<String> paths) {
		for(String path : paths){
			if(path!=null && !path.equals("")){
				File file = new File(path);
				if(file.exists()){       
		            file.delete();
		        } 
			}
		}
	}
	
	
}
