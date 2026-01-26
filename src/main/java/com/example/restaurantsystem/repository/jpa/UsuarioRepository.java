package com.example.restaurantsystem.repository.jpa;

import com.example.restaurantsystem.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM usuarios WHERE ST_DWithin(localizacao, :ponto, :distancia)", nativeQuery = true)
    List<Usuario> buscarPorProximidade(@Param("ponto") Point ponto, @Param("distancia") double distancia);

}
