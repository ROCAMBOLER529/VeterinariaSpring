package veterinaria.controllers;

import java.time.LocalDate;
import veterinaria.repositories.RepoUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControladorUsuarios {

    @Autowired
    private RepoUsuarios repoUsuarios;
    @Autowired
    private Controlador controlador;
    
    @PostMapping("/altaUsuario")
    public String altaUsuario(
        Model model,
        @RequestParam(value = "nombre", required = true) String nombre,
        @RequestParam(value = "clave", required = true) String clave,
        @RequestParam(value = "tipo", required = true) String tipo) {

            model.addAttribute("verificacionUsuarioAlta", repoUsuarios.altaUsuario(nombre,clave,tipo));
            controlador.cargarDatos(model);
            return controlador.mostrarMenuAdmin(model);

    }
    
    @PostMapping("/bajaUsuario")
    public String bajaUsuario(
        Model model,
        @RequestParam(value = "usuario_id", required = true) int usuario_id) {

        model.addAttribute("baja", repoUsuarios.borrarUsuario(usuario_id));
        controlador.cargarDatos(model);
        return controlador.mostrarMenuAdmin(model);
    } 
}
