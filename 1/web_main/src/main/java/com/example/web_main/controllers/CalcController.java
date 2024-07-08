package com.example.web_main.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CalcController {

	@GetMapping("/calc")
	public String home() {
		return "calc";
	}

}
