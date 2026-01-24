package com.example.restaurantsystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "carrinhos")
public class Carrinho implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private List<ItemCardapio> listaItens = new ArrayList<>();

    private Double valorTotal = 0.0;
}
