package com.osamuharu.user.presentation.controller;

import com.osamuharu.shared.annotation.ResponseMessage;
import com.osamuharu.user.application.service.UserService;
import com.osamuharu.user.presentation.dto.request.CreateUserDto;
import com.osamuharu.user.presentation.dto.request.UpdateUserDto;
import com.osamuharu.user.presentation.dto.response.UserDto;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/{version}/users", version = "v1")
@RequiredArgsConstructor
public class UserController {

  private final UserService service;

  @GetMapping
  @ResponseMessage("Get all users successfully")
  public List<UserDto> getAll() {
    return service.getAllUsers();
  }

  @GetMapping("/{id}")
  @ResponseMessage("Get user by id successfully")
  public UserDto getById(@PathVariable Long id) {
    return service.getUserById(id);
  }

  @GetMapping("/username/{username}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseMessage("Get user by username successfully")
  public UserDto getByUsername(@PathVariable String username) {
    return service.getUserByUsername(username);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  @ResponseMessage("Create user successfully")
  public UserDto create(@Valid @RequestBody CreateUserDto createUserDto) {
    return service.createUser(createUserDto);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseMessage("Update user successfully")
  public UserDto update(@PathVariable Long id, @Valid @RequestBody UpdateUserDto updateUserDto) {
    return service.updateUser(id, updateUserDto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseMessage("Delete user successfully")
  public Boolean delete(@PathVariable Long id) {
    service.deleteUser(id);
    return true;
  }
}
