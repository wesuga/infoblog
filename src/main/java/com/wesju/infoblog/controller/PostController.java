package com.wesju.infoblog.controller;

import com.wesju.infoblog.model.Post;
import com.wesju.infoblog.model.User;
import com.wesju.infoblog.service.PostService;
import com.wesju.infoblog.service.UserService;
import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {
  private final PostService postService;
  private final UserService userService;

  public PostController(PostService postService, UserService userService) {
    this.postService = postService;
    this.userService = userService;
  }

  @GetMapping("/new")
  public String showPostForm(Model model, Principal principal) {
    User user = userService.findByEmail(principal.getName());

    Post post = new Post();
    post.setUser(user);
    model.addAttribute("post", post);
    return "postform";
  }

  @PostMapping("/new")
  public String createPost(@ModelAttribute("post") Post post) {
    postService.save(post);
    return "redirect:/";
  }
}
