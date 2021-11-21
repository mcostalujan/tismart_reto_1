package max.costa.reto1.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import max.costa.reto1.dao.PerfilRepository;
import max.costa.reto1.models.Perfil;
import max.costa.reto1.services.PerfilService;

@Service
public class PerfilServiceImpl implements PerfilService{

    private PerfilRepository perfilRepository;

    @Autowired
    public PerfilServiceImpl(PerfilRepository perfilRepository){
        this.perfilRepository = perfilRepository;
    }

    @Override
    public void guardar(Perfil perfil) {
        this.perfilRepository.save(perfil);
        
    }

    @Override
    public List<Perfil> buscarTodas() {
        return this.perfilRepository.findAll();
    }

    @Override
    public Perfil buscarPorId(Long idPerfil) {
        return this.perfilRepository.findByIdPerfil(idPerfil);
    }

    @Override
    public void eliminar(Long idPerfil) {
        this.perfilRepository.deleteById(idPerfil);
        
    }

    @Override
    public Perfil buscarPorNombre(String nombre) {
        return this.perfilRepository.findByNombre(nombre);
    }
    
}
