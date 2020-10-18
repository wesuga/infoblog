package com.wesju.infoblog.service;

import com.wesju.infoblog.dto.UserRegisterDto;
import com.wesju.infoblog.model.Role;
import com.wesju.infoblog.model.User;
import com.wesju.infoblog.repository.UserRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
  private UserRepository userRepository;

  @Autowired
  private UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User save(UserRegisterDto userRegisterDto) {
    User user = new User(userRegisterDto.getFirstName(), userRegisterDto.getLastName(),
                      userRegisterDto.getEmail(), userRegisterDto.getPassword(),
                      Arrays.asList(new Role("ROLE_USER")));

    return userRepository.save(user);
  }
}