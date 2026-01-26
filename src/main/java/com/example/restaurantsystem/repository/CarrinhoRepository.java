package com.example.restaurantsystem.repository;

import com.example.restaurantsystem.model.Carrinho;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrinhoRepository extends MongoRepository<Carrinho, String> {


    default void salvar(Carrinho carrinho) {
        save(carrinho);
    }

    default Carrinho buscar(String id) {
        return findById(id).orElse(null);
    }

    default void deletar(String id) {
        deleteById(id);
    }
}