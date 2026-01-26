package com.example.restaurantsystem.controller;

import com.example.restaurantsystem.model.Usuario;
import com.example.restaurantsystem.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario) {
        return usuarioService.salvar(usuario);
    }

    @PostMapping("/com-localizacao")
    public ResponseEntity<Usuario> salvarComLocalizacao(
            @RequestBody Usuario usuario,
            @RequestParam double lat,
            @RequestParam double lon) {

        Usuario salvo = usuarioService.salvarComLocalizacao(usuario, lat, lon);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/proximos")
    public List<Usuario> listarProximos(
            @RequestParam double lat,
            @RequestParam double lon,
            @RequestParam double raioMetros) {

        return usuarioService.buscarProximos(lat, lon, raioMetros);
    }
}
