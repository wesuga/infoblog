package com.wesju.infoblog.service;

import com.wesju.infoblog.model.Post;

import java.util.Collection;

public interface PostService {
  Post save(Post post);
  Collection<Post> getAll();
}