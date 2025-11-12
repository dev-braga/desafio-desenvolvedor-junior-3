package com.desafio.Desafio.repository;

import com.desafio.Desafio.model.PostsModel;
import com.desafio.Desafio.model.UserModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<PostsModel, Long> {
    List<PostsModel> findByAutor(UserModel autor, Sort sort);
}
