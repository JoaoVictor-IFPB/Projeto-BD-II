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

    private final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario salvarComLocalizacao(Usuario usuario, double latitude, double longitude) {
        Point ponto = geometryFactory.createPoint(new Coordinate(longitude, latitude));
        usuario.setLocalizacao(ponto);
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarProximos(double lat, double lon, double raioMetros) {
        Point pontoReferencia = geometryFactory.createPoint(new Coordinate(lon, lat));
        return usuarioRepository.buscarPorProximidade(pontoReferencia, raioMetros);
    }
}