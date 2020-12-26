package com.wesju.infoblog.controller;

import com.wesju.infoblog.model.Post;
import com.wesju.infoblog.model.User;
import com.wesju.infoblog.service.PostService;
import com.wesju.infoblog.service.UserService;
import java.security.Principal;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/post/{id}")
  public String displayPostWithComments(@PathVariable Long id, Model model, Principal principal) {
    Optional<Post> optionalPost = postService.findById(id);

    Post post = optionalPost.get();

    model.addAttribute("post", post);
    if (isPrincipalOwnerOfPost(principal, post)) {
      model.addAttribute("username", principal.getName());
    }
    return "post";
  }

  @GetMapping("/post/{id}/update")
  public String updatePostWithId(@PathVariable Long id, Principal principal, Model model) {

    Optional<Post> optionalPost = postService.findById(id);

    if (optionalPost.isPresent()) {
      Post post = optionalPost.get();

      if (isPrincipalOwnerOfPost(principal, post)) {
        model.addAttribute("post", post);
        return "postform";
      }
    }
    return "error";
  }

  @DeleteMapping("/post/{id}")
  public String deletePost(@PathVariable Long id, Principal principal) {
    Optional<Post> optionalPost = postService.findById(id);

    if (optionalPost.isPresent()) {
      Post post = optionalPost.get();

      if (isPrincipalOwnerOfPost(principal, post)) {
        postService.delete(post);
        return "redirect:/";
      }
    }
    return "error";
  }

  private boolean isPrincipalOwnerOfPost(Principal principal, Post post) {
    return principal != null && principal.getName().equals(post.getUser().getEmail());
  }
}
