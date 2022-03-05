package com.example.MyPetProject.repo;

import com.example.MyPetProject.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
