package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Objects;



@Data
@Builder
public class Marca {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nome")
    private String nome;

    public Marca(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Marca() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marca marca = (Marca) o;
        return id == marca.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
