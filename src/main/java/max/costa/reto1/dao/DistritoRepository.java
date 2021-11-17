package max.costa.reto1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import max.costa.reto1.models.Distrito;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito,Long>{
    
	public Distrito findByIdDistrito(Long idDistrito);
	
}

