package max.costa.reto1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import max.costa.reto1.models.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    public Perfil findByIdPerfil(Long idPerfil);
    public Perfil findByNombre(String nombre);
}
