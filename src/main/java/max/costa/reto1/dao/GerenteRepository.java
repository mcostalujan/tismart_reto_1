package max.costa.reto1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import max.costa.reto1.models.Gerente;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente,Long>{
    
	public Gerente findByIdGerente(Long idGerente);
	
}

