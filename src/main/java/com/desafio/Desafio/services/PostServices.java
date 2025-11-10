package com.desafio.Desafio.services;

import com.desafio.Desafio.dto.PostDTO;
import com.desafio.Desafio.dto.PostResponseDTO;
import com.desafio.Desafio.dto.UserDTO;
import com.desafio.Desafio.model.PostsModel;
import com.desafio.Desafio.model.UserModel;
import com.desafio.Desafio.repository.PostsRepository;
import com.desafio.Desafio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServices {

    @Autowired
    private PostsRepository postsRepository;
    private UserRepository userRepository;

    public List<PostResponseDTO> listarPosts(){
        return postsRepository.findAll()
              .stream()
              .map(this::toResponse)
              .collect(Collectors.toList());
    }
    // #### Médodo para cadastrar um Post
    public PostResponseDTO publicarPost(PostDTO postDTO, String userNameAutor){
        // Buscar o user logado
        UserModel autorLogado = userRepository.findByEmail(userNameAutor)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado!"));

        PostsModel posts = new PostsModel();
        posts.setAutor(autorLogado);

        posts = toEntity(postDTO);
        postsRepository.save(posts);
        return toResponse(posts);
    }

    // Conversoes
    public PostsModel toEntity(PostDTO postDTO){
        PostsModel postsModel = new PostsModel();
        postsModel.setTitulo(postDTO.titulo);
        postsModel.setConteudo(postDTO.conteudo);
        postsModel.setDataPost(LocalDateTime.now());

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
