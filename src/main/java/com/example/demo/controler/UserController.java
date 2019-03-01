package com.example.demo.controler;

import com.example.demo.model.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;




@RestController
@RequestMapping("users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping
	List<User> find(@RequestParam (required = false) String userName){
		return userService.find(userName);
	}




}
