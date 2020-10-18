package com.wesju.infoblog.service;

import com.wesju.infoblog.dto.UserRegisterDto;
import com.wesju.infoblog.model.User;

public interface UserService {
  User save(UserRegisterDto userRegisterDto);
}