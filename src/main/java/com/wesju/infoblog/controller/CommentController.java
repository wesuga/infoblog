package com.wesju.infoblog.controller;

import com.wesju.infoblog.model.Comment;
import com.wesju.infoblog.model.Post;
import com.wesju.infoblog.model.User;
import com.wesju.infoblog.service.CommentService;
import com.wesju.infoblog.service.PostService;
import com.wesju.infoblog.service.UserService;
import java.security.Principal;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
  private final CommentService commentService;
  private final PostService postService;
  private final UserService userService;

  public CommentController(CommentService commentService, PostService postService,
      UserService userService) {
    this.commentService = commentService;
    this.postService = postService;
    this.userService = userService;
  }

  @PostMapping("/create/comment")
  public String createComment(@Valid Comment comment, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "/commentform";
    } else {
      commentService.save(comment);
      return "redirect:/post/" + comment.getPost().getId();
    }
  }

  @GetMapping("/post/{id}/comment")
  public String showCommentForm(@PathVariable Long id, Principal principal, Model model) {
    Optional<Post> post = postService.findById(id);
    User user = userService.findByEmail(principal.getName());

    Comment comment = new Comment();
    comment.setUser(user);
    comment.setPost(post.get());
    model.addAttribute("comment", comment);
    return "/commentform";
  }
}
