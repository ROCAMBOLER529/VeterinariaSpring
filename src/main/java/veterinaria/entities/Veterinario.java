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
@Table(name="veterinario")
public class Veterinario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer veterinario_id;
    @Column(updatable = true)
    private String nombre;
    private String especialidad;
    private String dia1;
    private String dia2;
    private String dia3;
    private int usuariovet_id;

    public static Veterinario getInstance(){
        return new Veterinario();
    }

    public String getEspecialidad() {
        System.out.println("(6)");
        return especialidad;
    }

    public String getDia1() {
        System.out.println("(6)");
        return dia1;
    }

    public String getDia2() {
        System.out.println("(6)");
        return dia2;
    }

    public String getDia3() {
        System.out.println("(6)");
        return dia3;
    }
}

