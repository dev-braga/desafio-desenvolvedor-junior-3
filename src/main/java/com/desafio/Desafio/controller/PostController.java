package com.desafio.Desafio.controller;

import com.desafio.Desafio.dto.PostDTO;
import com.desafio.Desafio.dto.PostResponseDTO;
import com.desafio.Desafio.dto.PostUpdateDTO;
import com.desafio.Desafio.model.PostsModel;
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
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostServices postServices;

    @PostMapping
    public ResponseEntity<?> postar(@RequestBody PostDTO postDTO, HttpSession session){
        UserModel usuario = (UserModel) session.getAttribute("usuario");
        if(usuario == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Faça login primeiro");
        }

        PostResponseDTO post = postServices.publicarPost(postDTO, session);
        return ResponseEntity.ok(post);
    }

    // Metodo para retornar os posts pelo ID! ######
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPosts(HttpSession session, @PathVariable Long id, @RequestBody PostUpdateDTO dto){
        UserModel usuario = (UserModel) session.getAttribute("usuario");
        if(usuario == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Necessario autenticacao.");
        }
        PostsModel postAtualizado = postServices.atualizarPost(id, dto);

        return ResponseEntity.ok(postServices.toResponse(postAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPost(HttpSession session, @PathVariable Long id){
        UserModel usuario = (UserModel) session.getAttribute("usuario");
        if(usuario == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Necessario autenticacao."));
        }
        postServices.deletarPost(id);
        return  ResponseEntity.ok(Map.of("messagem", "Post deletado."));
    }

    // Metodo para retornar todos os posts! ######
    @GetMapping
    public ResponseEntity<?> listarPosts(
            HttpSession session,
            @RequestParam(required = false, defaultValue = "desc") String ordem,
            @RequestParam(required = false, defaultValue = "false") boolean meus
    ){
        UserModel usuario = (UserModel) session.getAttribute("usuario");

        if(usuario == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Necessario autenticacao.");
        }
        List<PostResponseDTO> posts = postServices.listarPosts(ordem, meus, usuario);
        return ResponseEntity.ok(posts);
    }
}
