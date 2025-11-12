package com.desafio.Desafio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDTO {
    public String titulo;
    public String conteudo;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
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
