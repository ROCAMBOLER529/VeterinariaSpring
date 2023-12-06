package veterinaria.controllers;

import veterinaria.repositories.RepoUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import veterinaria.repositories.RepoProductos;
import veterinaria.repositories.RepoTurnos;
import veterinaria.repositories.RepoVeterinario;

@Controller
public class Controlador {

    @Autowired
    private RepoUsuarios arrayUsuarios;
    
    @Autowired
    private RepoProductos repoProductos;
    
    @Autowired
    private RepoVeterinario repoVeterinario;
  
    @Autowired
    private RepoTurnos repoTurnos;

    // GetMapping seccion

    @GetMapping("/")
    public String mostrarInicio(Model model) {
        return "index";
    }
    
    @GetMapping("/vistaAdministrador")
    public String mostrarMenuAdmin(Model model) {    
        return "vistaAdministrador";
    }
   
    @GetMapping("/vistaRecepcionista")
    public String mostrarMenuRecepcionista(Model model) {
            return "vistaRecepcionista";    
    }

    @GetMapping("/vistaVeterinario")
    public String mostrarMenuVeterinario(Model model) {
        return "vistaVeterinario";
    }
   
    @GetMapping("/vistaError")
    public String mostrarError(Model model) {
        return "vistaError";
    }
    
    @GetMapping("/vistaErrorUsuario")
    public String mostrarErrorUsuario(Model model) {
        return "vistaErrorUsuario";
    }
    
    @GetMapping("/vistaErrorDias")
    public String mostrarErrorDias(Model model) {
        return "vistaErrorDias";
    }

    @PostMapping("/validarUsuario")
    public String validarUsuario(
        Model model, 
        @RequestParam(value = "nombre", required = true) String nombre, 
        @RequestParam(value = "clave", required = true) String clave
        ) {
        arrayUsuarios.validarUsuario(nombre,clave);
    
        cargarDatos(model);
        switch (arrayUsuarios.getUsuarioValido().getTipo()) {
            case "administrador":
              return mostrarMenuAdmin(model);
                
             case "recepcionista":
                return mostrarMenuRecepcionista(model);
                
            case "veterinario":
                return mostrarMenuVeterinario(model);    
                
           
            default:
                System.out.println(""+nombre + clave+arrayUsuarios.getUsuarioValido().getTipo());
                return mostrarErrorUsuario(model);
        }
    }

    public void cargarDatos(Model model) {
        arrayUsuarios.consultarUsuarios();
        model.addAttribute("usuario", arrayUsuarios.getUsuarioValido());
        model.addAttribute("usuarios", arrayUsuarios.getUsuarios());
        model.addAttribute("productos", repoProductos.getProductos());
        model.addAttribute("producto", repoProductos.getProductoValido());
        model.addAttribute("productoRecep", repoProductos.getProductosRecep());
        model.addAttribute("veterinarios", arrayUsuarios.getVeterinario());
        model.addAttribute("veterinario", repoVeterinario.getVeterinario());
        model.addAttribute("veterinariosL", repoVeterinario.getVetId());
        model.addAttribute("turnosRecep", repoTurnos.getTurnosRecep());
        model.addAttribute("veterinarioID", repoVeterinario.getVetId());
    }
}
