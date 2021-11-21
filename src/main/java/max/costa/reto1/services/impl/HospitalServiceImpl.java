package max.costa.reto1.services.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import max.costa.reto1.dao.CondicionRepository;
import max.costa.reto1.dao.DistritoRepository;
import max.costa.reto1.dao.GerenteRepository;
import max.costa.reto1.dao.HospitalRepository;
import max.costa.reto1.dao.SedeRepository;
import max.costa.reto1.dto.HospitalDto;
import max.costa.reto1.models.Condicion;
import max.costa.reto1.models.Distrito;
import max.costa.reto1.models.Gerente;
import max.costa.reto1.models.Hospital;
import max.costa.reto1.models.Sede;
import max.costa.reto1.services.HospitalService;
import max.costa.reto1.utility.Utility;

@Service
public class HospitalServiceImpl implements HospitalService {

    private HospitalRepository hospitalRepository;
    private CondicionRepository condicionRepository;
    private SedeRepository sedeRepository;
    private GerenteRepository gerenteRepository;
    private DistritoRepository distritoRepository;
    private Utility utility;

    @Autowired
    public HospitalServiceImpl(HospitalRepository hospitalRepository, CondicionRepository condicionRepository,
            SedeRepository sedeRepository, GerenteRepository gerenteRepository, DistritoRepository distritoRepository,
            Utility utility) {
        this.hospitalRepository = hospitalRepository;
        this.condicionRepository = condicionRepository;
        this.sedeRepository = sedeRepository;
        this.gerenteRepository = gerenteRepository;
        this.distritoRepository = distritoRepository;
        this.utility = utility;
    }

    @Override
    public List<Hospital> buscarTodosLosHospitales() {
        return this.hospitalRepository.findAll();
    }

    @Override
    public void saveNewHospital(HospitalDto hospitalDto) throws ParseException {
        Hospital hospital = new Hospital();
        Condicion condicion = this.condicionRepository.findByIdCondicion(Long.valueOf(hospitalDto.getIdCondicion()));
        Sede sede = this.sedeRepository.findByIdSede(Long.valueOf(hospitalDto.getIdSede()));
        Gerente gerente = this.gerenteRepository.findByIdGerente(Long.valueOf(hospitalDto.getIdGerente()));
        Distrito distrito = this.distritoRepository.findByIdDistrito(Long.valueOf(hospitalDto.getIdDistrito()));
        if (!hospitalDto.getIdHospital().isEmpty())
            hospital.setIdHospital(Long.valueOf(hospitalDto.getIdHospital()));
        hospital.setArea(Float.parseFloat(hospitalDto.getArea()));
        hospital.setAntiguedad(Integer.parseInt(hospitalDto.getAntiguedad()));
        hospital.setFechaRegistro(this.utility.convertStringToDateWithTimeZone(hospitalDto.getFechaRegistro()));
        hospital.setNombre(hospitalDto.getNombre());
        hospital.setCondicion(condicion);
        hospital.setSede(sede);
        hospital.setGerente(gerente);
        hospital.setDistrito(distrito);
        this.hospitalRepository.save(hospital);
    }

    @Override
    public HospitalDto buscarHospitalDtoPorId(String idHospital) {
        Hospital hospitalEncontrado = this.hospitalRepository.findByIdHospital(Long.parseLong(idHospital));
        if (hospitalEncontrado != null) {
            HospitalDto hospitalAEnviar = new HospitalDto();
            hospitalAEnviar.setIdHospital(String.valueOf(hospitalEncontrado.getIdHospital()));
            hospitalAEnviar.setAntiguedad(String.valueOf(hospitalEncontrado.getAntiguedad()));
            hospitalAEnviar.setArea(String.valueOf(hospitalEncontrado.getArea()));
            hospitalAEnviar.setFechaRegistro(
                    this.utility.convertirDateToStringWithFormat(hospitalEncontrado.getFechaRegistro()));
            hospitalAEnviar.setNombre(hospitalEncontrado.getNombre());
            hospitalAEnviar.setIdCondicion(String.valueOf(hospitalEncontrado.getCondicion().getIdCondicion()));
            hospitalAEnviar.setIdDistrito(String.valueOf(hospitalEncontrado.getDistrito().getIdDistrito()));
            hospitalAEnviar.setIdGerente(String.valueOf(hospitalEncontrado.getGerente().getIdGerente()));
            hospitalAEnviar.setIdSede(String.valueOf(hospitalEncontrado.getSede().getIdSede()));
            return hospitalAEnviar;
        }
        return null;
    }

    @Override
    public void eliminar(Long idHospital) {
        this.hospitalRepository.deleteById(idHospital);	
    }

    @Override
    public Hospital buscarHospitalPorId(String idHospital) {
        return this.hospitalRepository.findByIdHospital(Long.valueOf(idHospital));
    }

    @Override
    public List<Hospital> buscarHospitalesPorSede(String sedeNombre) {
        return this.hospitalRepository.findAllBySedeNombreContainingIgnoreCase(sedeNombre);
    }



}
