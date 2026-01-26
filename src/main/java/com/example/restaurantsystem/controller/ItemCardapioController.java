package com.example.restaurantsystem.controller;

import com.example.restaurantsystem.model.ItemCardapio;
import com.example.restaurantsystem.service.FileService;
import com.example.restaurantsystem.service.ItemCardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/itemCardapio")
public class ItemCardapioController {

    @Autowired
    private ItemCardapioService itemCardapioService;

    @Autowired
    private FileService fileService;

    @GetMapping
    public List<ItemCardapio> listarTodos(){
        return itemCardapioService.listarTodos();
    }

    @PostMapping
    public ItemCardapio salvar(@RequestBody ItemCardapio itemCardapio) {
        return itemCardapioService.salvar(itemCardapio);

    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ItemCardapio> criarComFoto(
            @RequestPart("item") ItemCardapio item,
            @RequestPart("foto") MultipartFile foto) throws Exception {

        String url = fileService.uploadImage(foto);

        item.setUrlFoto(url);

        return ResponseEntity.ok(itemCardapioService.salvar(item));
    }
}
