package max.costa.reto1.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class GerenteDto {
    @Getter @Setter private Long idGerente;
    @Getter @Setter private String nombre;
    @Getter @Setter private Date fechaRegistro;
}
