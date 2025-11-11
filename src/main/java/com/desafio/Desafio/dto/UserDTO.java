package com.desafio.Desafio.dto;

import com.desafio.Desafio.model.PostsModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    public String nome;
    public String email;
    public String senha;
}