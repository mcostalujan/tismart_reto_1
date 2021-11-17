package max.costa.reto1.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "condicion")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Condicion {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
    @Getter @Setter private Long idCondicion;
    
    @Getter @Setter private String descuentoCondicion;
    @Getter @Setter private Date fechaRegistro;

}
