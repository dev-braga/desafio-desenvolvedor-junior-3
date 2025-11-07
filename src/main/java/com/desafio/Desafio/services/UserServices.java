package com.desafio.Desafio.services;

import com.desafio.Desafio.dto.UserDTO;
import com.desafio.Desafio.dto.UserResponseDTO;
import com.desafio.Desafio.model.UserModel;
import com.desafio.Desafio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    // Busca todos os usuarios
    public List<UserResponseDTO> listarUsuarios(){
        return userRepository.findAll()
                .stream()
                .map(this::toResposeUserDTO)
                .collect(Collectors.toList());
    }
    // Busca por ID
    public UserResponseDTO buscarPorId(Long id){
        UserModel usuario = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nao achei!"));
        return toResposeUserDTO(usuario);
    }

    public UserResponseDTO cadastrarUsuario(UserDTO userDTO){
        UserModel userModel = toEntity(userDTO);
        userRepository.save(userModel);
        return toResposeUserDTO(userModel);
    }
    // Atualizar --- Depois melhoro as mensagens.
    public UserResponseDTO atualizaUsuario(Long id, UserDTO userDTO){
        UserModel usuario = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nao achei!"));

        usuario.setNome(userDTO.nome);
        usuario.setEmail(userDTO.email);

        return toResposeUserDTO(usuario);
    }

    // Deletar usuarios
    public void deletarUsuario(Long id){
        if(!userRepository.existsById(id)){
            new RuntimeException("Nao achei!");
        }
        userRepository.deleteById(id);
    }

    // Conversoes

    public UserModel toEntity(UserDTO userDTO){
        UserModel userModel = new UserModel();
        userModel.setNome(userDTO.nome);
        userModel.setEmail(userDTO.email);
        return userModel;
    }

    public UserResponseDTO toResposeUserDTO(UserModel userModel){
        UserResponseDTO dto = new UserResponseDTO();
        dto.id = userModel.getId();
        dto.nome = userModel.getNome();
        dto.email = userModel.getEmail();
        dto.senha = userModel.getSenha();

        return dto;
    }
}
