package max.costa.reto1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import max.costa.reto1.models.Sede;

@Repository
public interface SedeRepository extends JpaRepository<Sede,Long>{
    
	public Sede findByIdSede(Long idSede);
	
}