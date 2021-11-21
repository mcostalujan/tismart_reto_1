package max.costa.reto1.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class DistritoDto {

    @Getter @Setter private Long idDistrito;
    @Getter @Setter private String nombre;
    @Getter @Setter private Date fechaRegistro;
    @Getter @Setter private Long idProvincia;

}
