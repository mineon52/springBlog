package com.mineon.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpTestController {
	private static final String TAG = "HttpTestController";
	
	
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("sss").passward("123").email("aa@naver.com").build();
		
		return TAG+ ":lombok "+m.getId()+m.getUsername()+m.getEmail();
	}
	
	@GetMapping("/http/get")
	public String getTest(Member m) {
		m.setId(123);
		return TAG + ":get 요청-"+m.getId();
	}
	@GetMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		return TAG + ":post 요청-" +m.getId();
	}
	@GetMapping("/http/put")
	public String putTest() {
		return TAG + ":put 요청";
	}
	@GetMapping("/http/delete")
	public String deleteTest() {
		return TAG + ":delete 요청";
	}
}
