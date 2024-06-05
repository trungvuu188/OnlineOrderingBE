package com.springboot.osahaneat.service.imp;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface FileServiceImp {
	
	void init();
	
	boolean save(MultipartFile file);
	
	Resource load(String filename);
	
}
