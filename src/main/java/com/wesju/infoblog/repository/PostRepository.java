package com.wesju.infoblog.repository;

import com.wesju.infoblog.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}