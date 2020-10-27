package com.wesju.infoblog.service;

import com.wesju.infoblog.model.Post;
import com.wesju.infoblog.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService{
  private final PostRepository postRepository;

  public PostServiceImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public Post save(Post post) {
    return postRepository.save(post);
  }
}