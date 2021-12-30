package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import lombok.*;

import java.util.Objects;
@Data
@Builder
@NoArgsConstructor
public class MarcaDTO {


    public MarcaDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    private Long id;
    private String nome;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
