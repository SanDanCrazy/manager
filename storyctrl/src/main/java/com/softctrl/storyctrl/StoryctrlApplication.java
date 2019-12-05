package com.softctrl.storyctrl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class StoryctrlApplication {

	@RequestMapping("/")
	public static String index(){
		return "hello boys";
	}

	public static void main(String[] args) {
		SpringApplication.run(StoryctrlApplication.class, args);
	}

}
