package edu.bh.cosumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.bh.cosumer.FeignCallerService;

@RestController
public class FnUserGenerator {

	@Autowired
	private FeignCallerService callerService;
	
	@GetMapping("/fnlbusers")
	public List<Object> getUsers(){
		
		return callerService.getUsers();
	}
	
	@PostMapping("/fnladduser")
	public Object addUser(@RequestBody Object body) {
		return callerService.addUser(body);
	}
}
