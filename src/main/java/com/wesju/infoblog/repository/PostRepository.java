package com.wesju.infoblog.repository;

import com.wesju.infoblog.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PostRepository extends CrudRepository<Post, Long> {
    Collection<Post> findAllByOrderByDateCreatedDesc();
}