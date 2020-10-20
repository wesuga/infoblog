package com.wesju.infoblog.controller;

import com.wesju.infoblog.dto.UserRegisterDto;
import com.wesju.infoblog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
  private UserService userService;

  public RegisterController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String register(Model model) {
    if (LoginController.isAuthenticated()) {
      return "redirect:/";
    }
    model.addAttribute("user", new UserRegisterDto());
    return "register";
  }

  @PostMapping
  public String registerUserAccount(@ModelAttribute("user") UserRegisterDto userRegisterDto) {
    userService.save(userRegisterDto);
    return "redirect:/login";
  }
}