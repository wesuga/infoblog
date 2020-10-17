package com.wesju.infoblog.repository;

import com.wesju.infoblog.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}