package com.wesju.infoblog.service;

import com.wesju.infoblog.dto.UserRegisterDto;
import com.wesju.infoblog.model.Role;
import com.wesju.infoblog.model.User;
import com.wesju.infoblog.repository.UserRepository;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Autowired
  private UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public User save(UserRegisterDto userRegisterDto) {
    User user = new User(userRegisterDto.getFirstName(), userRegisterDto.getLastName(),
        userRegisterDto.getEmail(), passwordEncoder.encode(userRegisterDto.getPassword()),
        Collections.singletonList(new Role("ROLE_USER")));

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