package max.costa.reto1.services;

import java.util.List;

import max.costa.reto1.models.Perfil;

public interface PerfilService {
    
    void guardar(Perfil perfil);
    List<Perfil> buscarTodas();
    Perfil buscarPorId(Long idPerfil);
    void eliminar(Long idPerfil);

}
