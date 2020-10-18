package com.wesju.infoblog.service;

import com.wesju.infoblog.dto.UserRegisterDto;
import com.wesju.infoblog.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  User save(UserRegisterDto userRegisterDto);
}