package co.edu.ufps.segundoparcial1152290.services;

import co.edu.ufps.segundoparcial1152290.entities.Usuario;
import co.edu.ufps.segundoparcial1152290.entities.enums.EnrollmentStatusEnum;
import co.edu.ufps.segundoparcial1152290.entities.enums.RoleEnum;
import co.edu.ufps.segundoparcial1152290.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario registrarUsuario(String name, String email, String password, RoleEnum role) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new RuntimeException("El email ya está registrado");
        }

        return usuarioRepository.save(Usuario.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(role)
                .status(EnrollmentStatusEnum.ACTIVE)
                .enrollmentDate(LocalDate.now())
                .build());
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Transactional
    public Usuario actualizarUsuario(Long id, String name, String password) {
        Usuario usuario = obtenerUsuarioPorId(id);
        if (name != null) usuario.setName(name);
        if (password != null) usuario.setPassword(passwordEncoder.encode(password));
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void cambiarEstadoUsuario(Long id, EnrollmentStatusEnum status) {
        Usuario usuario = obtenerUsuarioPorId(id);
        usuario.setStatus(status);
        usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuariosPorRol(RoleEnum role) {
        return usuarioRepository.findAllByRole(role);
    }

    public List<Usuario> listarUsuariosPorEstado(EnrollmentStatusEnum status) {
        return usuarioRepository.findAllByStatus(status);
    }
}