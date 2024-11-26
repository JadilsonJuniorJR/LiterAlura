package br.com.Alura.LiterAlura.repository;

import br.com.Alura.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNome(String nome);

    @Query(value = "SELECT * FROM autores a WHERE a.ano_de_nascimento <=:ano AND a.ano_de_morte >=:ano OR a.ano_de_morte IS NULL", nativeQuery = true)
    List<Autor> buscaPorAno(int ano);
}
