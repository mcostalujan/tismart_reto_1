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

@Table(name = "gerente")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Gerente {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
    @Getter @Setter private Long idGerente;
    
    @Getter @Setter private String descuentoDistrito;
    @Getter @Setter private Date fechaRegistro;
    

}