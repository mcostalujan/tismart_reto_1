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

@Table(name = "hospital")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Hospital {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
    @Getter @Setter private Long idHospital;
    
    @Getter @Setter private Distrito distrito;
    @Getter @Setter private String nombre;
    @Getter @Setter private int antiguedad;
    @Getter @Setter private float area;
    @Getter @Setter private Sede sede;
    
    @Getter @Setter private Gerente gerente;
    @Getter @Setter private Condicion condicion;
    @Getter @Setter private Date fechaRegistro;

    

}