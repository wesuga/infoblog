package com.wesju.infoblog.service;

import com.wesju.infoblog.dto.UserRegisterDto;
import com.wesju.infoblog.model.Role;
import com.wesju.infoblog.model.User;
import com.wesju.infoblog.repository.UserRepository;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
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

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException("Invalid email address");
    }

    return new org.springframework.security.core.userdetails
        .User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
  }

  private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
    return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
  }
}