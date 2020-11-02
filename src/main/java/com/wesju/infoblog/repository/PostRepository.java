package com.wesju.infoblog.repository;

import com.wesju.infoblog.model.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
}