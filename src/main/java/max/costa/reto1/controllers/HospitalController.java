package max.costa.reto1.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import max.costa.reto1.dto.HospitalDto;
import max.costa.reto1.models.Hospital;
import max.costa.reto1.services.CondicionService;
import max.costa.reto1.services.DistritoService;
import max.costa.reto1.services.GerenteService;
import max.costa.reto1.services.HospitalService;
import max.costa.reto1.services.SedeService;

@Controller
@RequestMapping("/hospitales")
public class HospitalController {

    private HospitalService hospitalService;
    private CondicionService condicionService;
    private SedeService sedeService;
    private DistritoService distritoService;
    private GerenteService gerenteService;

    private Boolean editHospital;
    private List<String> modelAttributes;
    private List<String> views;
    private String msgSuccess;
    private String msgError;

    @Autowired
    public HospitalController(HospitalService hospitalService, CondicionService condicionService,
            SedeService sedeService, DistritoService distritoService, GerenteService gerenteService) {
        this.hospitalService = hospitalService;
        this.condicionService = condicionService;
        this.sedeService = sedeService;
        this.distritoService = distritoService;
        this.gerenteService = gerenteService;
        this.editHospital = false;
        this.modelAttributes = Arrays.asList("editHospitalFlag", "hospital", "hospitales", "guardarButtonHabilitado",
                "msgSuccess", "msgError");
        this.views = Arrays.asList("hospitales/formHospital", "hospitales/listHospitales");
        this.nullMsgs();
    }

    @GetMapping("/edit")
    public String editExistingHospital(Model model, String id) {
        this.nullMsgs();
        this.editHospital = true;
        model.addAttribute(this.modelAttributes.get(0), this.editHospital);
        if (id != null) {
            HospitalDto hospitalEncontrado = this.hospitalService.buscarHospitalDtoPorId(id);
            if (hospitalEncontrado != null) {
                model.addAttribute(this.modelAttributes.get(1), hospitalEncontrado);
                model.addAttribute(this.modelAttributes.get(3), true);
                model.addAttribute(this.modelAttributes.get(4), "Hospital cargado exitosamente.");
            } else {
                model.addAttribute(this.modelAttributes.get(1), new HospitalDto());
                model.addAttribute(this.modelAttributes.get(3), false);
                model.addAttribute(this.modelAttributes.get(5), "No se encontró ningún hospital con el ID ingresado.");
            }
        } else
            model.addAttribute(this.modelAttributes.get(1), new HospitalDto());

        return this.views.get(0);
    }

    @GetMapping("/index")
    public String mostrarHospitales(Model model, String searchBy, String idKeyword, String sedeKeyword) {
        this.nullMsgs();
        if (searchBy != null) {
            List<Hospital> hospitalesEncontrados = buscarHospitales(searchBy, idKeyword, sedeKeyword);
            if (hospitalesEncontrados.isEmpty())
                this.msgError = "No se encontraron registros.";
            else
                this.msgSuccess = "Se encontraron registros.";
            model.addAttribute(this.modelAttributes.get(2), hospitalesEncontrados);
        } else {
            if (Boolean.TRUE.equals(validarKeywords(idKeyword, sedeKeyword)))
                this.msgError = "No se seleccionó el tipo de filtro";
        }
        model.addAttribute(this.modelAttributes.get(4), this.msgSuccess);
        model.addAttribute(this.modelAttributes.get(5), this.msgError);
        return this.views.get(1);
    }

    private List<Hospital> buscarHospitales(String searchBy, String idKeyword, String sedeKeyword) {
        if (Boolean.TRUE.equals(validarKeywords(idKeyword, sedeKeyword))) {
            if ("id".equals(searchBy)) {
                Boolean esKeywordValido = this.validarKeywordConCriterio(idKeyword, searchBy);
                if (Boolean.TRUE.equals(esKeywordValido))
                    return this.obtenerListaHospitalEncontradoPorId(idKeyword);
                else
                    this.msgError = "Ingrese solo números.";
            } else if ("sede".equals(searchBy) && sedeKeyword != null) {
                String keywordAltered = sedeKeyword.replace(",", "");
                keywordAltered = keywordAltered.replace("+", " ");
                return this.hospitalService.buscarHospitalesPorSede(keywordAltered);
            }
        } else
            this.msgError = "No se ingresó palabra clave";
        return Collections.emptyList();
    }

    private Boolean validarKeywords(String idKeyword, String sedeKeyword) {
        return (idKeyword != null && !"".equals(idKeyword)) || (sedeKeyword != null && !"".equals(sedeKeyword));
    }

    private Boolean validarKeywordConCriterio(String keyword, String searchBy) {
        boolean keywordValido = true;
        if ("id".equals(searchBy)) {
            try {
                Integer.parseInt(keyword);
            } catch (NumberFormatException nfe) {
                keywordValido = false;
            }
        }
        return keywordValido;
    }

    private List<Hospital> obtenerListaHospitalEncontradoPorId(String keyword) {
        List<Hospital> hospitales = new ArrayList<>();
        Hospital hospitalEncontrado = this.hospitalService.buscarHospitalPorId(keyword);
        if (hospitalEncontrado != null)
            hospitales.add(hospitalEncontrado);
        return hospitales;
    }

    @GetMapping("/create")
    public String createNewHospital(Model model) {
        this.nullMsgs();
        this.editHospital = false;
        model.addAttribute(this.modelAttributes.get(0), this.editHospital);
        model.addAttribute(this.modelAttributes.get(1), new HospitalDto());
        model.addAttribute(this.modelAttributes.get(3), true);
        return this.views.get(0);
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute(value = "hospital") @Valid HospitalDto hospitalDto, Errors errors,
            RedirectAttributes attributes) throws ParseException {
        this.nullMsgs();
        if (errors.hasErrors()) {
            if (Boolean.TRUE.equals(editHospital)) {
                this.editHospital = true;
                model.addAttribute(this.modelAttributes.get(0), this.editHospital);
                model.addAttribute(this.modelAttributes.get(1), hospitalDto);
            } else {
                this.editHospital = false;
                model.addAttribute(this.modelAttributes.get(0), this.editHospital);
            }
            model.addAttribute(this.modelAttributes.get(3), true);
            return this.views.get(0);
        }

        this.hospitalService.saveNewHospital(hospitalDto);
        String customMessage = null;
        if (Boolean.TRUE.equals(this.editHospital))
            customMessage = "Hospital actualizado correctamente.";
        else
            customMessage = "Hospital guardado correctamente.";
        attributes.addFlashAttribute("customMessage", customMessage);
        return "redirect:/hospitales/index";
    }

    private void nullMsgs() {
        this.msgError = null;
        this.msgSuccess = null;
    }

    @GetMapping("/delete/{id}")
    public String deleteExistingHospital(@PathVariable("id") Long idHospital, RedirectAttributes attributes) {
        this.nullMsgs();
        this.hospitalService.eliminar(idHospital);
        attributes.addFlashAttribute(this.modelAttributes.get(4), "¡Registro eliminado exitosamente!");
        return "redirect:/hospitales/index";
    }

    @ModelAttribute
    public void setGenericos(Model model) {
        model.addAttribute("condiciones", this.condicionService.buscarTodasLasCondiciones());
        model.addAttribute("sedes", this.sedeService.buscarTodasLasSedes());
        model.addAttribute("gerentes", this.gerenteService.buscarTodosLosGerentes());
        model.addAttribute("distritos", this.distritoService.buscarTodosLosDistritos());
        model.addAttribute(this.modelAttributes.get(2), this.hospitalService.buscarTodosLosHospitales());
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));
        webDataBinder.setDisallowedFields("id");
    }

}
