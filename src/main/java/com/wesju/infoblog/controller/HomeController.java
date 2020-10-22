package com.wesju.infoblog.controller;

import com.wesju.infoblog.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
  @GetMapping
  public String showPosts(Model model) {
    model.addAttribute("post", new Post());
    return "home";
  }
}