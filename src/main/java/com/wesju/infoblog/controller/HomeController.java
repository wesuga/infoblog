package com.wesju.infoblog.controller;

import com.wesju.infoblog.model.Post;
import com.wesju.infoblog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/")
public class HomeController {
  private final PostService postService;

  public HomeController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping
  public String showAllPosts(Model model) {
    Collection<Post> posts = postService.getAll();
    model.addAttribute("posts", posts);
    return "home";
  }
}