package com.example.restaurantsystem.service;

import com.example.restaurantsystem.model.Carrinho;
import com.example.restaurantsystem.model.ItemCardapio;
import com.example.restaurantsystem.repository.CarrinhoRepository; // O do Redis
import com.example.restaurantsystem.repository.jpa.ItemCardapioRepository; // O do Postgres
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ItemCardapioRepository itemRepository;

    public Carrinho adicionarItem(String usuarioId, Long itemId) {

        ItemCardapio item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));


        Carrinho carrinho = carrinhoRepository.findById(usuarioId)
                .orElseGet(() -> {
                    Carrinho novo = new Carrinho();
                    novo.setId(usuarioId);
                    return novo;
                });


        Double precoComTaxa = itemRepository.obterPrecoComEntregaFixa(item.getPreco());


        carrinho.getListaItens().add(item);
        carrinho.setValorTotal(carrinho.getValorTotal() + precoComTaxa);


        return carrinhoRepository.save(carrinho);
    }

    public Carrinho buscarCarrinho(String usuarioId) {

        return carrinhoRepository.findById(usuarioId).orElse(null);
    }

    public void limparCarrinho(String usuarioId) {

        carrinhoRepository.deleteById(usuarioId);
    }
}