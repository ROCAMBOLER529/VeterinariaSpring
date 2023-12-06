package veterinaria.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="turnos")
public class Turnos  implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_turnos;
    @Column(updatable = true)
    private String nom_mascota;
    private String especialidad;
    private String dia;
    private String hora;
    private String nom_dueño;
    private int tel_contacto;
    
    public static Turnos getInstance(){
        return new Turnos();
    }   
}

