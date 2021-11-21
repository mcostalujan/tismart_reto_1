package max.costa.reto1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import max.costa.reto1.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}
