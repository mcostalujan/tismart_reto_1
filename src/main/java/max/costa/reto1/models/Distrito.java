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

@Table(name = "distrito")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Distrito {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
    @Getter @Setter private Long idDistrito;
    

    @Getter @Setter private String nombre;
    @Getter @Setter private Date fechaRegistro;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
        CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_provincia")
    @Getter @Setter private Provincia provincia;
    

}
