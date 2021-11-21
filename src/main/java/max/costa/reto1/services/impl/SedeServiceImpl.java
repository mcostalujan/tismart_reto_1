package max.costa.reto1.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import max.costa.reto1.dao.SedeRepository;
import max.costa.reto1.models.Sede;
import max.costa.reto1.services.SedeService;

@Service
public class SedeServiceImpl implements SedeService{

    private SedeRepository sedeRepository;

    public SedeServiceImpl(SedeRepository sedeRepository){
        this.sedeRepository = sedeRepository;
    }

    @Override
    public List<Sede> buscarTodasLasSedes() {
        return this.sedeRepository.findAll();
    }
    
}
