package max.costa.reto1.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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


    @Getter @Setter private String nombre;


    @Getter @Setter private int antiguedad;


    @Getter @Setter private float area;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
        CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sede")
    @Getter @Setter private Sede sede;
    
    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
        CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gerente")
    @Getter @Setter private Gerente gerente;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
        CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_condicion")
    @Getter @Setter private Condicion condicion;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
        CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_distrito")
    @Getter @Setter private Distrito distrito;


    @Getter @Setter private Date fechaRegistro;

    

}