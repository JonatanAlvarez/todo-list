package com.jalvarez.listTodo.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jalvarez.listTodo.exception.FileException;
import com.jalvarez.listTodo.exception.FileNotFoundException;
import com.jalvarez.listTodo.model.FileTODO;

@Service
public class FileService {
	private final Path fileLocation;
	
	@Autowired
	public FileService(FileTODO file) {
		this.fileLocation = Paths.get(file.getUploadDir()).toAbsolutePath().normalize();
		
		try {
			Files.createDirectories(this.fileLocation);
		} catch (Exception e) {
			throw new FileException("No se a creado el directorio para guardar archivos porque ya existe.");
		}
	}
	
	public String saveFile(Long todoId, MultipartFile file) {
		String fileName = StringUtils.cleanPath(todoId + "/" + file.getOriginalFilename());
		
		try {
			if(fileName.contains("..")) {
				throw new FileException("Lo sentimos, el nombre del archivo contiene caracteres invalidos: " + fileName);
			}
			
			Files.createDirectories(this.fileLocation.resolve(StringUtils.cleanPath(todoId + "")));
			Path targetLocation = this.fileLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			return StringUtils.cleanPath(file.getOriginalFilename());
		} catch (Exception e) {
			throw new FileException("No se puedo guardar el archivo " + fileName + ". Por favor, intente nuevamente.");
		}
	}
	
	public Resource loadFileAsResource(Long todoId, String fileName) {
		try {
			Path filePath = this.fileLocation.resolve(todoId + "/" + fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			
			if(resource.exists()) {
				return resource;
			} else {
				throw new FileNotFoundException("Archivo no encontrado: " + fileName);
			}
		} catch (Exception e) {
			throw new FileNotFoundException("Archivo no encontrado: " + fileName, e);
		}
	}
}
