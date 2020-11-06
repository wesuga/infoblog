package com.wesju.infoblog.controller;

import com.wesju.infoblog.model.Post;
import com.wesju.infoblog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {
  private final PostService postService;

  public HomeController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping
  public String showAllPosts(@RequestParam(defaultValue = "1") int page, Model model) {
    Page<Post> pages = postService.findAllPaginated(page);

    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", pages.getTotalPages());
    model.addAttribute("posts", pages.getContent());
    return "home";
  }
}