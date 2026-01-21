package com.example.restaurantsystem.repository.jpa;

import com.example.restaurantsystem.model.ItemCardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Long> {
    @Query(value = "SELECT calcular_total_com_taxa(:preco, 5.0)", nativeQuery = true)
    Double obterPrecoComEntregaFixa(Double preco);
}
