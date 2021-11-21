package max.costa.reto1.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import max.costa.reto1.dao.PerfilRepository;
import max.costa.reto1.models.Perfil;
import max.costa.reto1.services.PerfilService;

@Service
public class PerfilServiceImpl implements PerfilService{

    private PerfilRepository perfilesRepository;

    @Autowired
    public PerfilServiceImpl(PerfilRepository perfilesRepository){
        this.perfilesRepository = perfilesRepository;
    }

    @Override
    public void guardar(Perfil perfil) {
        this.perfilesRepository.save(perfil);
        
    }

    @Override
    public List<Perfil> buscarTodas() {
        return this.perfilesRepository.findAll();
    }

    @Override
    public Perfil buscarPorId(Long idPerfil) {
        return this.perfilesRepository.findByIdPerfil(idPerfil);
    }

    @Override
    public void eliminar(Long idPerfil) {
        this.perfilesRepository.deleteById(idPerfil);
        
    }
    
}
