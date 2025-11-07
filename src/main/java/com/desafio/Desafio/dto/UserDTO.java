package com.desafio.Desafio.dto;

import com.desafio.Desafio.model.PostsModel;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    public String nome;
    public String email;
    public List<PostsModel> postagens;
}