package max.costa.reto1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import max.costa.reto1.models.Provincia;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia,Long>{
    
	public Provincia findByIdProvincia(Long idProvincia);
	
}
