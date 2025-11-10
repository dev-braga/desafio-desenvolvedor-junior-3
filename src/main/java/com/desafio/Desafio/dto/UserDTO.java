package com.desafio.Desafio.dto;

import com.desafio.Desafio.model.PostsModel;
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
    public List<PostsModel> postagens;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<PostsModel> getPostagens() {
        return postagens;
    }

    public void setPostagens(List<PostsModel> postagens) {
        this.postagens = postagens;
    }
}