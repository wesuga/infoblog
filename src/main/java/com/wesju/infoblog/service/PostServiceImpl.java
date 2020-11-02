package com.wesju.infoblog.service;

import com.wesju.infoblog.model.Post;
import com.wesju.infoblog.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
  private final PostRepository postRepository;

  public PostServiceImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public Post save(Post post) {
    return postRepository.save(post);
  }

  @Override
  public Page<Post> findAllPaginated(int page) {
    return postRepository
        .findAll(PageRequest.of(subtractPageByOne(page),
            3, Sort.by("dateCreated").descending()));
  }

  public int subtractPageByOne(int page) {
    return (page < 1) ? 0 : page - 1;
  }
}