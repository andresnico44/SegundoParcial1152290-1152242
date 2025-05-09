package co.edu.ufps.segundoparcial1152290.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.segundoparcial1152290.entities.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}