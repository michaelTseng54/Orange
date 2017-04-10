package com.ifbk.project.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/PUBLIC")
public class PublicController extends BasicController {

	private static final Logger logger = Logger.getLogger(PublicController.class);

	public PublicController() {}

}