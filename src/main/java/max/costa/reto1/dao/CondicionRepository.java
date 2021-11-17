package max.costa.reto1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import max.costa.reto1.models.Condicion;

@Repository
public interface CondicionRepository extends JpaRepository<Condicion,Long>{
    
	public Condicion findByIdCondicion(Long idCondicion);
	
}
