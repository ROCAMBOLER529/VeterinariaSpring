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
@Table(name="productos")
public class Productos implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_productos;
    @Column(updatable = true)
    private String descripcion;
    private String categoria;
    private float precio;

    public static Productos getInstance(){
        return new Productos();
    }     
}