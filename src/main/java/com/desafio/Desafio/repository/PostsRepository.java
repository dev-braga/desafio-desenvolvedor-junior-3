package com.desafio.Desafio.repository;

import com.desafio.Desafio.model.PostsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<PostsModel, Long> {
}
