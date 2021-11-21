package max.costa.reto1.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class HospitalDto {
    @Getter @Setter private String idHospital;

    @NotEmpty(message="No se ingresó nombre.")
    @Getter @Setter private String nombre;

    @NotEmpty(message="No se ingresó años de antiguedad.")
    @Getter @Setter private String antiguedad;

    @NotEmpty(message="No se ingresó área.")
    @Getter @Setter private String area;
    
    @NotEmpty(message="No se ingresó fecha de registro.")
    @Getter @Setter private String fechaRegistro;

    @NotEmpty(message="No se seleccionó sede.")
    @Min(value = 1, message = "No se seleccionó sede.")
    @Getter @Setter private String idSede;

    @NotEmpty(message="No se seleccionó gerente.")
    @Min(value = 1, message = "No se seleccionó gerente.")
    @Getter @Setter private String idGerente;

    @NotEmpty(message="No se seleccionó condición.")
    @Min(value = 1, message = "No se seleccionó condición.")
    @Getter @Setter private String idCondicion;

    @NotEmpty(message="No se seleccionó distrito.")
    @Min(value = 1, message = "No se seleccionó distrito.")
    @Getter @Setter private String idDistrito;
}
