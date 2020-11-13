package com.wesju.infoblog.repository;

import com.wesju.infoblog.model.Post;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
  Optional<Post> findById(Long id);
}
