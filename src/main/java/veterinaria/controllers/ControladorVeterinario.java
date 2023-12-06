package veterinaria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import veterinaria.repositories.RepoUsuarios;
import veterinaria.repositories.RepoVeterinario;


@Controller
public class ControladorVeterinario {

    @Autowired
    private RepoVeterinario repoVeterinario;
    @Autowired
    private Controlador controlador;

    @PostMapping("/altaVeterinario")
    public String altaVeterinario(
        Model model,
        @RequestParam(value = "usuario_id", required = true) int usuario_id,
        @RequestParam(value = "especialidad", required = true) String especialidad,
        @RequestParam(value = "dia1", required = true) String dia1,
        @RequestParam(value = "dia2", required = true) String dia2,
        @RequestParam(value = "dia3", required = true) String dia3) {

        System.out.printf("" + usuario_id);
        model.addAttribute("verificacionVetrinariosAlta", repoVeterinario.altaVeterinario(usuario_id, especialidad, dia1, dia2, dia3));
        controlador.cargarDatos(model);
        return controlador.mostrarMenuAdmin(model);
    }

    @PostMapping("/bajaVeterinario")
    public String bajaVeterinario(
        Model model,
        @RequestParam(value = "veterinario_id", required = true) int veterinario_id) {

        model.addAttribute("baja", repoVeterinario.borrarVeterinario(veterinario_id));
        controlador.cargarDatos(model);
        return controlador.mostrarMenuAdmin(model);  
    }
    
    @PostMapping("/modificarVeterinario")
    public String modificarVeterinario(
        Model model,
        @RequestParam(value = "veterinario_id", required = true) int veterinario_id,
        @RequestParam(value = "dia1", required = true) String dia1,
        @RequestParam(value = "dia2", required = true) String dia2,
        @RequestParam(value = "dia3", required = true) String dia3) {

        model.addAttribute("modifica", repoVeterinario.modificarVeterinario(veterinario_id, dia1, dia2, dia3));
        controlador.cargarDatos(model);
        return controlador.mostrarMenuAdmin(model);
    }    
}
