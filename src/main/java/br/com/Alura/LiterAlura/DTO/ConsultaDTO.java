package br.com.Alura.LiterAlura.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ConsultaDTO(@JsonAlias("results")List<LivroDTO> livros) {
}
