package com.desafio.Desafio.controller;

import com.desafio.Desafio.dto.UserDTO;
import com.desafio.Desafio.dto.UserLoginDTO;
import com.desafio.Desafio.dto.UserResponseDTO;
import com.desafio.Desafio.model.UserModel;
import com.desafio.Desafio.services.UserServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping("{id}")
    public UserResponseDTO pegarUsuarioPorId(Long id){
        UserResponseDTO usuario = userServices.buscarPorId(id);
        return usuario;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO dto, HttpSession session){
        try{
            UserModel user = userServices.login(dto.getEmail(), dto.getSenha());
            session.setAttribute("usuario", user);
            return ResponseEntity.ok(Map.of("msg", "Login efetuado"));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("msg", e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody UserDTO dto){
       try{
           UserResponseDTO response = userServices.cadastrarUsuario(dto);
          return ResponseEntity.status(HttpStatus.CREATED).body(response);
       } catch (RuntimeException e) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("msg", e.getMessage()));
       }

    }

}
