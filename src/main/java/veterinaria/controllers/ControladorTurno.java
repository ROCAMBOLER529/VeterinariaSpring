package veterinaria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import veterinaria.entities.Veterinario;
import veterinaria.repositories.RepoTurnos;
import veterinaria.repositories.RepoUsuarios;
import veterinaria.repositories.RepoVeterinario;

@Controller
public class ControladorTurno {
    
    @Autowired
    private RepoTurnos repoTurnos;
    @Autowired
    private Controlador controlador;
    
    @Autowired
    private RepoVeterinario repoVeterinario;
    
    
    @PostMapping("/altaTurnos")
    public String altaTurnos(
        Model model,
        @RequestParam(value = "nom_mascota", required = true)String nom_mascota,
        @RequestParam(value = "especialidad", required = true) String especialidad,
        @RequestParam(value = "dia", required = true) String dia,
        @RequestParam(value = "hora", required = true) String hora,
        @RequestParam(value = "nom_dueño", required = true) String nom_dueño,
        @RequestParam(value = "contacto", required = true) int contacto) {

        model.addAttribute("verificacionTurnoAlta", repoTurnos.altaTurnos(nom_mascota,especialidad, dia, hora,nom_dueño,contacto));
        controlador.cargarDatos(model);
        return controlador.mostrarMenuRecepcionista(model);
    }
    
    @PostMapping("/turnoHoraVete1")
    public String turnoHoraVet1(
        Model model,
        @RequestParam(value = "veterinario_id", required = true) Integer veterinario_id) {
             
        //System.out.printf("" + veterinario_id);
        String l = repoVeterinario.buscarVeterinario(veterinario_id).getEspecialidad();
        String d1 = repoVeterinario.buscarVeterinario(veterinario_id).getDia1();
        String d2 = repoVeterinario.buscarVeterinario(veterinario_id).getDia2();
        String d3 = repoVeterinario.buscarVeterinario(veterinario_id).getDia3();
        
        
        System.out.printf( ""+ l +d1 +d2+ d3 );
        controlador.cargarDatos(model);
        model.addAttribute("VeteriTurno", repoVeterinario.buscarVeterinario(veterinario_id));  
  
        //String t = repoVeterinario.getVeterinarioValido().getNombre();
        //  System.out.printf(t);
        //  model.addAttribute("turnosVeteri", repoTurnos.getTurnosVet(vetTur));
        //
        //controlador.cargarDatos(model);

        model.addAttribute("turnosVeteriEsp", repoTurnos.buscarTurnosVet(l,d1,d2,d3));
        return controlador.mostrarMenuVeterinario(model);
    }

    @PostMapping("/turnoHoraVete2")
    public String turnoHoraVet2(
        Model model,
        @RequestParam(value = "veterinario_id", required = true) Integer veterinario_id) {
             
        //System.out.printf("" + veterinario_id);
        String l = repoVeterinario.buscarVeterinario(veterinario_id).getEspecialidad();
        String d1 = repoVeterinario.buscarVeterinario(veterinario_id).getDia2();
        String d2 = repoVeterinario.buscarVeterinario(veterinario_id).getDia2();
        String d3 = repoVeterinario.buscarVeterinario(veterinario_id).getDia3();     
        
        System.out.printf( ""+ l +d1 +d2+ d3 );
        controlador.cargarDatos(model);
        
        model.addAttribute("VeteriTurno", repoVeterinario.buscarVeterinario(veterinario_id));    
        model.addAttribute("turnosVeteriEsp", repoTurnos.buscarTurnosVet(l,d1,d2,d3));
        return controlador.mostrarMenuVeterinario(model);
    }
    
    @PostMapping("/turnoHoraVete3")
    public String turnoHoraVet3(
        Model model,
        @RequestParam(value = "veterinario_id", required = true) Integer veterinario_id) {
             
        //System.out.printf("" + veterinario_id);
        String l = repoVeterinario.buscarVeterinario(veterinario_id).getEspecialidad();
        String d1 = repoVeterinario.buscarVeterinario(veterinario_id).getDia3();
        String d2 = repoVeterinario.buscarVeterinario(veterinario_id).getDia2();
        String d3 = repoVeterinario.buscarVeterinario(veterinario_id).getDia3();
        
        System.out.printf( ""+ l +d1 +d2+ d3 );
        controlador.cargarDatos(model);
        model.addAttribute("VeteriTurno", repoVeterinario.buscarVeterinario(veterinario_id));  
        model.addAttribute("turnosVeteriEsp3", repoTurnos.buscarTurnosVet(l,d1,d2,d3));
        return controlador.mostrarMenuVeterinario(model);
    }
    
    @PostMapping("/tomarTurno")
    public String tomarTurno(
        Model model,
        @RequestParam(value = "verTurno", required = true) String verTurno) {
                 
        int idTurno = Integer.parseInt(verTurno);     

        model.addAttribute("verificacionTurnoVet", repoTurnos.tomarTurno(idTurno));

        controlador.cargarDatos(model);
        return controlador.mostrarMenuVeterinario(model);
    } 
    
    @PostMapping("/tomarTurnoSolo")
    public String tomarTurnoSolo(
        Model model,
        @RequestParam(value = "veterinario_id", required = true) String veterinario_id,        
        @RequestParam(value = "verTurno", required = true) String verTurno) {
                 
        int idVet = Integer.parseInt(veterinario_id);    
        int idTurno = Integer.parseInt(verTurno);
                  
        String l = repoVeterinario.buscarVeterinario(idVet).getEspecialidad();
        String d1 = repoVeterinario.buscarVeterinario(idVet).getDia1();
        String d2 = repoVeterinario.buscarVeterinario(idVet).getDia2();
        String d3 = repoVeterinario.buscarVeterinario(idVet).getDia3();
        
        System.out.printf( "La informacion es "+ l +d1 +d2+ d3 );    
            
        //buscar tuno y especialidad
        if(repoTurnos.buscarTurno(idTurno).getEspecialidad().equals(l)) {
            if(repoTurnos.buscarTurno(idTurno).getDia().equals(d1)) {
                
                repoTurnos.tomarTurno(idTurno);
                controlador.cargarDatos(model);
                return controlador.mostrarMenuVeterinario(model); 

            } else if (repoTurnos.buscarTurno(idTurno).getDia().equals(d2)) {
                
                repoTurnos.tomarTurno(idTurno);
                controlador.cargarDatos(model);
                return controlador.mostrarMenuVeterinario(model); 
  
            } else if (repoTurnos.buscarTurno(idTurno).getDia().equals(d3)) {
           
                repoTurnos.tomarTurno(idTurno);
                controlador.cargarDatos(model);
                return controlador.mostrarMenuVeterinario(model);
       
            }
       
        } else

            controlador.cargarDatos(model);
            return controlador.mostrarError(model);
        
    } 
    
@PostMapping("/tomarTurnoUsuario")
     public String tomarTurnoUsuario(
            Model model,
            @RequestParam(value = "usuario_id", required = true) String usuario_id,        
            @RequestParam(value = "verTurno", required = true) String verTurno)    
                    
           
             {
                 
            int usuariovet_id = Integer.parseInt(usuario_id);    
            int idTurno = Integer.parseInt(verTurno);
          
                    
                 
                    
           String l  =repoVeterinario.buscarVeterinarioUsuario(usuariovet_id).getEspecialidad();
           String d1  =repoVeterinario.buscarVeterinarioUsuario(usuariovet_id).getDia1();
           String d2  =repoVeterinario.buscarVeterinarioUsuario(usuariovet_id).getDia2();
           String d3  =repoVeterinario.buscarVeterinarioUsuario(usuariovet_id).getDia3();
        
        
  System.out.printf( "La informacion es "+ l +d1 +d2+ d3 );    
            
  //buscar tuno y especialidad
   if(repoTurnos.buscarTurno(idTurno).getEspecialidad().equals(l))
   {
    
       if(repoTurnos.buscarTurno(idTurno).getDia().equals(d1))
           
       {
           
           repoTurnos.tomarTurno(idTurno);
           controlador.cargarDatos(model);
           return controlador.mostrarMenuVeterinario(model); 
  
       }else  if(repoTurnos.buscarTurno(idTurno).getDia().equals(d2))
           
       {
           
           repoTurnos.tomarTurno(idTurno);
           controlador.cargarDatos(model);
           return controlador.mostrarMenuVeterinario(model); 
  
       }else if(repoTurnos.buscarTurno(idTurno).getDia().equals(d3))
           
       {
           
           repoTurnos.tomarTurno(idTurno);
           controlador.cargarDatos(model);
           return controlador.mostrarMenuVeterinario(model);
       
       }
       
   }else
     
       controlador.cargarDatos(model);
   
   return controlador.mostrarError(model); 
     
       
    } 
     
     
     
     
     
     
    
    
    
}