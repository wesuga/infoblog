package com.wesju.infoblog.repository;

import com.wesju.infoblog.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
