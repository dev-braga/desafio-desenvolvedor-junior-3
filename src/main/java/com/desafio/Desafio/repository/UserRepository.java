package com.desafio.Desafio.repository;

import com.desafio.Desafio.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
