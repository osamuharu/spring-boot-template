package com.osamuharu.user.application.service;

import com.osamuharu.user.application.mapper.UserAppMapper;
import com.osamuharu.user.application.usecase.CreateUserUseCase;
import com.osamuharu.user.application.usecase.DeleteUseUseCase;
import com.osamuharu.user.application.usecase.UpdateUserUseCase;
import com.osamuharu.user.domain.repository.UserRepository;
import com.osamuharu.user.presentation.dto.request.CreateUserDto;
import com.osamuharu.user.presentation.dto.request.UpdateUserDto;
import com.osamuharu.user.presentation.dto.response.UserDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final CreateUserUseCase createUserUseCase;
  private final UpdateUserUseCase updateUserUseCase;
  private final DeleteUseUseCase deleteUseUseCase;
  private final UserRepository userRepository;
  private final UserAppMapper mapper;

  public List<UserDto> getAllUsers() {
    return userRepository.findAll()
        .stream()
        .map(mapper::toDto)
        .toList();
  }

  public UserDto getUserById(Long id) {
    return userRepository.findById(id)
        .map(mapper::toDto)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }

  public UserDto getUserByUsername(String username) {
    return userRepository.findByUsername(username)
        .map(mapper::toDto)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }

  public UserDto createUser(CreateUserDto dto) {
    return mapper.toDto(createUserUseCase.execute(mapper.toDomain(dto)));
  }

  public UserDto updateUser(Long id, UpdateUserDto dto) {
    return mapper.toDto(updateUserUseCase.execute(id, mapper.toDomain(dto)));
  }

  public void deleteUser(Long id) {
    deleteUseUseCase.execute(id);
  }
}
