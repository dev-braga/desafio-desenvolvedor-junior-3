package com.desafio.Desafio.controller;

import com.desafio.Desafio.dto.PostDTO;
import com.desafio.Desafio.dto.PostResponseDTO;
import com.desafio.Desafio.model.UserModel;
import com.desafio.Desafio.services.PostServices;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<?> postar(@RequestBody PostDTO postDTO, HttpSession session){
        UserModel usuario = (UserModel) session.getAttribute("usuario");
        if(usuario == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Faça login primeiro");
        }

        PostResponseDTO post = postServices.publicarPost(postDTO, usuario.getEmail());
        return ResponseEntity.ok(post);
    }
    @GetMapping
    public ResponseEntity<?> listarPosts(HttpSession session){
        UserModel usuario = (UserModel) session.getAttribute("usuario");

        if(usuario == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Faça login primeiro!");
        }
        List<PostResponseDTO> posts = postServices.listarPosts();

        return ResponseEntity.ok(posts);
    }
}
