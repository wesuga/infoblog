package com.wesju.infoblog.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
  @GetMapping("/login")
  public String login() {
    if (isAuthenticated()) {
      return "redirect:/";
    }
    return "login";
  }

  static boolean isAuthenticated() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null
        || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
      return false;
    }
    return authentication.isAuthenticated();
  }
}
