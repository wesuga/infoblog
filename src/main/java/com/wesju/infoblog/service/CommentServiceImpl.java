package com.wesju.infoblog.service;

import com.wesju.infoblog.model.Comment;
import com.wesju.infoblog.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
  private final CommentRepository commentRepository;

  public CommentServiceImpl(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  @Override
  public Comment save(Comment comment) {
    return commentRepository.save(comment);
  }
}
