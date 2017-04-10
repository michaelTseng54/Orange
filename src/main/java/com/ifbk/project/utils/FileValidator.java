package com.ifbk.project.utils;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.ifbk.project.model.FileObject;


public class FileValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object uploadedFile, Errors errors) {
		FileObject file = (FileObject) uploadedFile;
		if (file.getFile().getSize() == 0) {
			errors.rejectValue("file", "uploadForm.selectFile",
					"Please select a file!");
		} else if (!file.getFile().getOriginalFilename().endsWith("xls") 
				&& !file.getFile().getOriginalFilename().endsWith("xlsx")) {
			errors.rejectValue("file", "uploadForm.selectFile",
					"Please select a excel file!");
		}
	}

}
