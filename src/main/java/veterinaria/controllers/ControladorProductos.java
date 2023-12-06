package veterinaria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import veterinaria.repositories.RepoProductos;

@Controller
public class ControladorProductos {
    
    @Autowired
    private Controlador controlador;
    
    @Autowired
    private RepoProductos repoProductos;
    
    @PostMapping("/altaProductos")
    public String altaProductos(
        Model model,
        @RequestParam(value = "descripcion", required = true) String descripcion,
        @RequestParam(value = "categoria", required = true) String categoria,
        @RequestParam(value = "precio", required = true) float precio) {

        model.addAttribute("verificacionProductosAlta", repoProductos.altaProducto(descripcion, categoria, precio));
        controlador.cargarDatos(model);
        return controlador.mostrarMenuAdmin(model);
    }
    
    @PostMapping("/ventaProdVet")
    public String ventaProdVet(
        Model model,
        @RequestParam(value = "prod", required = true) String prod) {
                 
        int id = Integer.parseInt(prod);     

        model.addAttribute("verificacionProductoVet", repoProductos.ventaProductoVet(id));
        controlador.cargarDatos(model);
        return controlador.mostrarMenuVeterinario(model);
    } 
    
    @PostMapping("/ventaProdRecep")
    public String ventaProdRecep(
        Model model,
        @RequestParam(value = "prod", required = true) String prod) {
                 
        int id = Integer.parseInt(prod);     

        model.addAttribute("verificacionProductoVet", repoProductos.ventaProductoVet(id));
        controlador.cargarDatos(model);
        return controlador.mostrarMenuRecepcionista(model);
    } 
}
