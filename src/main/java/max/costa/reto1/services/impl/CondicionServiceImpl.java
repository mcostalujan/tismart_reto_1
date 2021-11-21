package max.costa.reto1.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import max.costa.reto1.dao.CondicionRepository;
import max.costa.reto1.models.Condicion;
import max.costa.reto1.services.CondicionService;

@Service
public class CondicionServiceImpl implements CondicionService {

    private CondicionRepository condicionRepository;

    @Autowired
    public CondicionServiceImpl ( CondicionRepository condicionRepository){
        this.condicionRepository = condicionRepository;
    }

    @Override
    public List<Condicion> buscarTodasLasCondiciones() {
        return this.condicionRepository.findAll();
    }
    
}
