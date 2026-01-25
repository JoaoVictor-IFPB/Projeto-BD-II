package com.example.restaurantsystem.service;

import com.example.restaurantsystem.model.Usuario;
import com.example.restaurantsystem.repository.jpa.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // Instância única para evitar criar várias fábricas na memória
    private final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    public Usuario salvarComLocalizacao(Usuario usuario, double latitude, double longitude) {
        // No padrão GIS/PostGIS, a ordem é (Longitude, Latitude)
        Coordinate coord = new Coordinate(longitude, latitude);
        Point ponto = geometryFactory.createPoint(coord);

        // Define o ponto no objeto usuário antes de mandar para o banco
        usuario.setLocalizacao(ponto);

        return usuarioRepository.save(usuario);
    }
}
