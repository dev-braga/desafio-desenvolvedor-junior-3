package com.desafio.Desafio.services;

import com.desafio.Desafio.dto.AutorResponseDTO;
import com.desafio.Desafio.dto.PostDTO;
import com.desafio.Desafio.dto.PostResponseDTO;
import com.desafio.Desafio.dto.PostUpdateDTO;
import com.desafio.Desafio.model.PostsModel;
import com.desafio.Desafio.model.UserModel;
import com.desafio.Desafio.repository.PostsRepository;
import com.desafio.Desafio.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServices {

    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private UserRepository userRepository;

    // #### Médodo para Listar todos os posts
    public List<PostResponseDTO> listarPosts(){
        return postsRepository.findAll()
              .stream()
              .map(this::toResponse)
              .collect(Collectors.toList());
    }

    public PostsModel atualizarPost(Long id, PostUpdateDTO dto){
        // Verificar se o post existe
        PostsModel posts = postsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post nao encontrado"));

        if(dto.getTitulo() != null && !dto.getTitulo().isBlank()){
            posts.setTitulo(dto.getTitulo());
        }
        if(dto.getConteudo() != null && !dto.getConteudo().isBlank()){
            posts.setConteudo(dto.getConteudo());
        }

        return postsRepository.save(posts);
    }

    // #### Médodo para cadastrar um Post
    public PostResponseDTO publicarPost(PostDTO postDTO, String userNameAutor){

        UserModel autorLogado = userRepository.findByEmail(userNameAutor)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado!"));

        PostsModel posts = toEntity(postDTO);
        posts.setAutor(autorLogado);

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
        dto.setTitulo(postsModel.getTitulo());
        dto.setDataPost(postsModel.getDataPost());
        dto.setConteudo(postsModel.getConteudo());

        //Converter o autor do modelo para o DTO
        AutorResponseDTO autorDTO = new AutorResponseDTO();
        autorDTO.setNome(postsModel.getAutor().getNome());
        dto.setAutor(autorDTO);

        return dto;
    }
}
