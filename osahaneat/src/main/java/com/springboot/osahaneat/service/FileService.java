package com.springboot.osahaneat.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.osahaneat.service.imp.FileServiceImp;


@Service
public class FileService implements FileServiceImp {
	
	@Value("${fileUpload.rootPath}")
	private String rootPath;
	private Path root;
//	private Path root = Paths.get(rootPath);
	
	@Override
	public void init() {
		try {
			root = Paths.get(rootPath);
			if(Files.notExists(root)) {
				Files.createDirectories(root);
			}
		} catch (Exception e) {
			System.out.println("Couldn't create folder root " + e.getMessage());
		}
	}

	@Override
	public boolean save(MultipartFile file) {
//		Config / or \ depend on user application
//		this.root.resolve(file.getOriginalFilename()
		init();
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			return true;
		} catch (Exception e) {
			System.out.println("Couldn't save file " + e.getMessage());
		}
		return false;
	}

	@Override
	public Resource load(String filename) {
		
		init();
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			
			if(resource.exists() || resource.isReadable()) {
				return resource;
			}
			
		} catch (Exception e) {
			System.out.println("Couldn't load file " + e.getMessage());
		}
		return null;
	}

}
