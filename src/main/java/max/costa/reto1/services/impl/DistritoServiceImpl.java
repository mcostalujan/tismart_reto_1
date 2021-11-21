package max.costa.reto1.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import max.costa.reto1.dao.DistritoRepository;
import max.costa.reto1.models.Distrito;
import max.costa.reto1.services.DistritoService;

@Service
public class DistritoServiceImpl implements DistritoService{

    private DistritoRepository distritoRepository;

    public DistritoServiceImpl(DistritoRepository distritoRepository){
        this.distritoRepository = distritoRepository;
    }


    @Override
    public List<Distrito> buscarTodosLosDistritos() {
        return this.distritoRepository.findAll();
    }
    
}
