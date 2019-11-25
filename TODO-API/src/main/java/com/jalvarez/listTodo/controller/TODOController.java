package com.jalvarez.listTodo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jalvarez.listTodo.exception.TODONotFoundException;
import com.jalvarez.listTodo.model.TODO;
import com.jalvarez.listTodo.payload.UploadFileResponse;
import com.jalvarez.listTodo.repository.TODORepository;
import com.jalvarez.listTodo.service.FileService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class TODOController {

	private static final Logger logger = LoggerFactory.getLogger(TODOController.class);
	
	@Autowired
	TODORepository repository;
	@Autowired
	FileService fileServie;
	
	// Get All TODO
	@GetMapping("/todos")
	public List<TODO> getTODOList(String description, String status) {
		List<TODO> todos = repository.findAll();
		
		if(description != null && description != "") {
			todos = todos.stream()
				.filter(todo -> todo.getDescription().matches(".*" + description + ".*"))
				.collect(Collectors.toList());
		}
		if(status != null && status != "") {
			todos = todos.stream()
				.filter(todo -> todo.getStatus().equals(status))
				.collect(Collectors.toList());
		}
		
		return todos;
	}
	
	// Create a new TODO
	@PostMapping("/todos")
	public TODO createTODO(@RequestParam String description, @RequestParam String status, @RequestParam(required = false) MultipartFile file) {
		TODO todo = new TODO();
		todo.setDescription(description);
		todo.setStatus(status);
		todo = repository.save(todo);
		
		if(file != null) {
			String fileName = fileServie.saveFile(todo.getId(), file);
			String fileDownLoadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/api/todos/" + todo.getId() + "/file/").path(fileName).toUriString();
			todo.setFile(fileDownLoadUri);
			todo = repository.save(todo);
		}
		return todo;
	}
	
	@PostMapping("/todos/{id}/file")
	public UploadFileResponse uploadFile(@PathVariable(value = "id") Long todoId, @RequestParam MultipartFile file) {
		String fileName = fileServie.saveFile(todoId, file);
		String fileDownLoadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/todos/" + todoId + "/file/").path(fileName).toUriString();
		
		return new UploadFileResponse(fileName, fileDownLoadUri, file.getContentType(), file.getSize());
	}
	
	@GetMapping("/todos/{id}/file/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable(value = "id") Long todoId, @PathVariable String fileName, HttpServletRequest request) {
		Resource resource = fileServie.loadFileAsResource(todoId, fileName);
		String contentType = null;
		
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (Exception e) {
			logger.info("No se pudo determinar el tipo de archivo");
		}
		
		if(contentType == null) {
			contentType = "application/octet-stream";
		}
		
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	// Get a single TODO
	@GetMapping("/todos/{id}")
	public TODO getTODOById(@PathVariable(value = "id") Long todoId) {
		return repository.findById(todoId).orElseThrow(() -> new TODONotFoundException("TODO", "id", todoId));
	}
	
	// Update a TODO
	@PutMapping("/todos/{id}")
	public TODO updateTODO(@PathVariable(value = "id") Long todoId, @Valid @RequestBody TODO todoBody) {
		TODO todo = repository.findById(todoId).orElseThrow(() -> new TODONotFoundException("TODO", "id", todoId));
		
		//todo.setDescription(todoBody.getDescription());
		todo.setStatus(todoBody.getStatus());
		//todo.setFile(todoBody.getFile());
		
		todo = repository.save(todo);
		return todo;
	}
}
