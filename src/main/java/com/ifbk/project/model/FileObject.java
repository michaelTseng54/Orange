package com.ifbk.project.model;

import org.springframework.web.multipart.MultipartFile;

public class FileObject {

	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
