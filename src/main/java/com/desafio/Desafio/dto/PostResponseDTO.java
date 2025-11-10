package com.desafio.Desafio.dto;

import com.desafio.Desafio.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDTO {
    public String titulo;
    public String conteudo;
    public LocalDateTime dataPost;
    public UserModel autor;
}
