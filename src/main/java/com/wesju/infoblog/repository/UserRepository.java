package com.wesju.infoblog.repository;

import com.wesju.infoblog.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
  @EntityGraph(value = "graph.User.roles")
  User findByEmail(String email);
}
