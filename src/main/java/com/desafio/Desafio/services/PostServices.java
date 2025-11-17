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
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServices {

    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private UserRepository userRepository;

    public List<PostResponseDTO> listarPosts(String ordem, boolean meus, UserModel usuarioLogado){
       Sort sort = ordem.equalsIgnoreCase("asc")
               ? Sort.by("dataPost").ascending()
               : Sort.by("dataPost").descending();

       List<PostsModel> posts;
       if(meus && usuarioLogado != null){
           posts = postsRepository.findByAutor(usuarioLogado, sort);
       }else{
           posts = postsRepository.findAll(sort);
       }
       return posts.stream()
               .map(this::toResponse)
               .toList();
    }

    public PostsModel atualizarPost(Long id, PostUpdateDTO dto){
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
    public void deletarPost(Long id){
        PostsModel postAtual = postsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post nao localizado."));

        postsRepository.delete(postAtual);
    }

    public PostResponseDTO publicarPost(PostDTO postDTO, HttpSession session){

        UserModel usuario = (UserModel) session.getAttribute("usuario");
        if(usuario == null){
            throw new RuntimeException("Nao encontrado");
        }

        PostsModel posts = toEntity(postDTO);
        posts.setAutor(usuario);
        posts.setDataPost(LocalDateTime.now());

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
        dto.setId(postsModel.getId());
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
