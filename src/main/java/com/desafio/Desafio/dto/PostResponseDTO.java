package com.desafio.Desafio.dto;

import com.desafio.Desafio.controller.AutorDTO;
import com.desafio.Desafio.controller.AutorResponseDTO;
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
    public AutorResponseDTO autor;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataPost() {
        return dataPost;
    }

    public void setDataPost(LocalDateTime dataPost) {
        this.dataPost = dataPost;
    }

    public AutorResponseDTO getAutor() {
        return autor;
    }

    public void setAutor(AutorResponseDTO autor) {
        this.autor = autor;
    }
}
