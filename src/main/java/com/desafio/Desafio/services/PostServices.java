package com.desafio.Desafio.services;

import com.desafio.Desafio.dto.PostDTO;
import com.desafio.Desafio.dto.PostResponseDTO;
import com.desafio.Desafio.dto.UserDTO;
import com.desafio.Desafio.model.PostsModel;
import com.desafio.Desafio.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class PostServices {

    @Autowired
    private PostsRepository postsRepository;

    public List<PostResponseDTO> listarPosts(){
        return postsRepository.findAll()
              .stream()
              .map(this::toResponse)
              .collect(Collectors.toList());
    }
    // #### Médodo para cadastrar um Post
    public PostResponseDTO publicarPost(PostDTO postDTO){
        PostsModel posts = toEntity(postDTO);
        postsRepository.save(posts);
        return toResponse(posts);
    }

    // Conversoes
    public PostsModel toEntity(PostDTO postDTO){
        PostsModel postsModel = new PostsModel();
        postsModel.setTitulo(postDTO.titulo);
        postsModel.setConteudo(postDTO.conteudo);
        postsModel.setAutor(postDTO.autor);
        postsModel.setDataPost(postDTO.dataPost);

        return postsModel;
    }

    public PostResponseDTO toResponse(PostsModel postsModel){
        PostResponseDTO dto = new PostResponseDTO();
        dto.titulo = postsModel.getTitulo();
        dto.dataPost = postsModel.getDataPost();
        dto.conteudo = postsModel.getConteudo();
        dto.autor = postsModel.getAutor();

        return dto;
    }
}
