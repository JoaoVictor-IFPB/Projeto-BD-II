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
        // 1. Busca o prato no Postgres (NeonDB)
        ItemCardapio item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        // 2. Busca o carrinho no MongoDB (ou cria um novo se não existir)
        Carrinho carrinho = carrinhoRepository.findById(usuarioId)
                .orElseGet(() -> {
                    Carrinho novo = new Carrinho();
                    novo.setId(usuarioId);
                    return novo;
                });

        // 3. Usa a sua função PL/pgSQL para calcular o preço com a taxa
        Double precoComTaxa = itemRepository.obterPrecoComEntregaFixa(item.getPreco());

        // 4. Atualiza a lista e o valor total
        carrinho.getListaItens().add(item);
        carrinho.setValorTotal(carrinho.getValorTotal() + precoComTaxa);

        // 5. Salva no MongoDB de forma definitiva
        return carrinhoRepository.save(carrinho);
    }

    public Carrinho buscarCarrinho(String usuarioId) {
        // Busca direta no MongoDB
        return carrinhoRepository.findById(usuarioId).orElse(null);
    }

    public void limparCarrinho(String usuarioId) {
        // Remove do MongoDB
        carrinhoRepository.deleteById(usuarioId);
    }
}