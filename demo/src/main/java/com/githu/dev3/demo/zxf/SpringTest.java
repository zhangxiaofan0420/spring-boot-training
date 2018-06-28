package com.githu.dev3.demo.zxf;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringTest {

	@RequestMapping("hello")
	public String hello() {
		return "Hello Spring poot";
	}
}
