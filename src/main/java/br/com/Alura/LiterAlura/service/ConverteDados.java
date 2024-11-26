package br.com.Alura.LiterAlura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteDados implements IConverteDados {
    //   Comando para conversão entre objetos Java e JSON.
    private ObjectMapper mapper = new ObjectMapper();

    //    Conversão do Json para a classe
    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            //Utiliza o ObjectMapper (mapper) para converter a string JSON (json) em um objeto do tipo classe.
            // O metodo readValue é onde ocorre a deserializacaoo.
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
