package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class CategoriaDTO {
    private long id;
    private String nome;
}