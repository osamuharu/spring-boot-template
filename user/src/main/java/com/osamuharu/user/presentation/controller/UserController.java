package com.osamuharu.user.presentation.controller;

import com.osamuharu.user.application.service.UserService;
import com.osamuharu.user.presentation.dto.request.CreateUserDto;
import com.osamuharu.user.presentation.dto.request.UpdateUserDto;
import com.osamuharu.user.presentation.dto.response.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/{version}/users", version = "v1")
@RequiredArgsConstructor
public class UserController {
	private final UserService service;
	
	@GetMapping
	public List<UserDto> getAll() {
		return service.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public UserDto getById(@PathVariable Long id) {
		return service.getUserById(id);
	}
	
	@GetMapping("/username/{username}")
	public UserDto getByUsername(@PathVariable String username) {
		return service.getUserByUsername(username);
	}
	
	@PostMapping
	public UserDto create(@Valid @RequestBody CreateUserDto createUserDto) {
		return service.createUser(createUserDto);
	}
	
	@PutMapping("/{id}")
	public UserDto update(@PathVariable Long id, @Valid @RequestBody UpdateUserDto updateUserDto) {
		return service.updateUser(id, updateUserDto);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.deleteUser(id);
	}
}
