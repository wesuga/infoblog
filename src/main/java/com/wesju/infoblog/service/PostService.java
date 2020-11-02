package com.wesju.infoblog.service;

import com.wesju.infoblog.model.Post;
import org.springframework.data.domain.Page;

public interface PostService {
  Post save(Post post);

  Page<Post> findAllPaginated(int page);
}