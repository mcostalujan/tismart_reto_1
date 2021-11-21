package max.costa.reto1.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import max.costa.reto1.dao.GerenteRepository;
import max.costa.reto1.models.Gerente;
import max.costa.reto1.services.GerenteService;

@Service
public class GerenteServiceImpl implements GerenteService{

    private GerenteRepository gerenteRepository;

    public GerenteServiceImpl(GerenteRepository gerenteRepository){
        this.gerenteRepository = gerenteRepository;
    }

    @Override
    public List<Gerente> buscarTodosLosGerentes() {
        return this.gerenteRepository.findAll();
    }
    
}
