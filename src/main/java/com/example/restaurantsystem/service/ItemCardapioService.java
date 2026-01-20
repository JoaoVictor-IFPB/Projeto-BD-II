package com.example.restaurantsystem.service;

import com.example.restaurantsystem.model.ItemCardapio;
import com.example.restaurantsystem.repository.jpa.ItemCardapioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCardapioService {

    @Autowired
    private ItemCardapioRepository itemCardapioRepository;

    @Cacheable(value = "cardapio", key = "'all'")
    public List<ItemCardapio> listarTodos(){
        return itemCardapioRepository.findAll();
    }

    @CacheEvict(value = "cardapio", allEntries = true)
    public ItemCardapio salvar(ItemCardapio itemCardapio) {
        return itemCardapioRepository.save(itemCardapio);
    }

}
