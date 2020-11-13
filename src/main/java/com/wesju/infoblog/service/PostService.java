package com.wesju.infoblog.service;

import com.wesju.infoblog.model.Post;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface PostService {
  Post save(Post post);

  Page<Post> findAllPaginated(int page);

  Optional<Post> findById(Long id);
}
