package com.desafio.Desafio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserResponseDTO {
    public Long id;
    public String nome;
    public String email;
    public String senha;
}
