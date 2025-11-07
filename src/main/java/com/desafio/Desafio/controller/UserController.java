package com.desafio.Desafio.controller;

import com.desafio.Desafio.dto.UserDTO;
import com.desafio.Desafio.dto.UserResponseDTO;
import com.desafio.Desafio.model.UserModel;
import com.desafio.Desafio.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping
    public List<UserResponseDTO> pegarUsuarios(){
        List<UserResponseDTO> usuarios = userServices.listarUsuarios();
        return userServices.listarUsuarios();
    }

    @GetMapping("{id}")
    public UserResponseDTO pegarUsuarioPorId(Long id){
        UserResponseDTO usuario = userServices.buscarPorId(id);
        return usuario;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> cadastrarUsuario(@RequestBody UserDTO dto){
       UserResponseDTO response = userServices.cadastrarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
