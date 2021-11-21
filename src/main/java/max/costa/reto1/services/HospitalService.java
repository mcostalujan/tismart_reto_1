package max.costa.reto1.services;

import java.text.ParseException;
import java.util.List;

import max.costa.reto1.dto.HospitalDto;
import max.costa.reto1.models.Hospital;

public interface HospitalService {

    void saveNewHospital(HospitalDto hospitalDto) throws ParseException;

    HospitalDto buscarHospitalDtoPorId(String idHospital);
    
    List<Hospital> buscarTodosLosHospitales();

    void eliminar(Long idHospital);

    Hospital buscarHospitalPorId(String idHospital);

    List<Hospital> buscarHospitalesPorSede(String sedeNombre);
}
