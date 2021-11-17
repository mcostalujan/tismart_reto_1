package max.costa.reto1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import max.costa.reto1.models.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long>{
    
	public Hospital findByIdHospital(Long idHospital);
	
}

