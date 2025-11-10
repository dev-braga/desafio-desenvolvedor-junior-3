package com.desafio.Desafio.controller;

import com.desafio.Desafio.dto.PostDTO;
import com.desafio.Desafio.dto.PostResponseDTO;
import com.desafio.Desafio.services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostServices postServices;

    @PostMapping("/postar")
    public ResponseEntity<PostResponseDTO> postar(@RequestBody PostDTO postDTO, @AuthenticationPrincipal
    UserDetails userDetails){
        PostResponseDTO dto = postServices.publicarPost(postDTO, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
    @GetMapping("/posts")
    public List<PostResponseDTO> listarPosts(){
        List<PostResponseDTO> posts = postServices.listarPosts();
        return postServices.listarPosts();
    }
}
