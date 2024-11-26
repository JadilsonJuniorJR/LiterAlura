package br.com.Alura.LiterAlura.repository;

import br.com.Alura.LiterAlura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILivroRepository extends JpaRepository <Livro,Long>  {
   List<Livro> findByIdiomaIgnoreCase(String idioma);


}
