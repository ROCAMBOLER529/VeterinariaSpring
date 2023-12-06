package veterinaria.repositories;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import veterinaria.SpringMVC.HibernateUtil;
import veterinaria.controllers.Controlador;
import veterinaria.entities.Productos;
import veterinaria.entities.Usuario;
import veterinaria.entities.Veterinario;

@Repository
public class RepoVeterinario {

    private final String dbFullURL;
    private final String dbUser;
    private final String dbPswd;

    
    private Veterinario veterinario;
    private List<Veterinario> veterinarios;
    private List<Veterinario> vetEsp;
    private List<Veterinario> vetHora;

    @Autowired
    private RepoUsuarios repoUsuarios;
    
    @Autowired
    private Controlador controlador;

    @Autowired
    public RepoVeterinario(
        @Qualifier("dbName") String dbName,
        @Qualifier("dbURL") String dbURL,
        @Qualifier("dbUser") String dbUser,
        @Qualifier("dbPswd") String dbPswd) {

        dbFullURL = "jdbc:mysql://" + dbURL + "/" + dbName;
        this.dbUser = dbUser;
        this.dbPswd = dbPswd;
    }

    public String altaVeterinario(int usuario_id,String especialidad,String dia1,String dia2,String dia3) {
        String d1 =dia1;
        String d2 =dia2;

        String nom = repoUsuarios.buscarUsuario(usuario_id).getNombre();
        System.out.printf("" +nom);        
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Veterinario v = Veterinario.getInstance();
                       
            v.setNombre(nom);
            v.setEspecialidad(especialidad);

            if((d1.equals(dia2)) || (d1.equals(dia3)) || (d2.equals(dia3))) { 
            // System.out.println("Repetido");
            return "No se pudo cargar eeor en días";
            }
                    
            v.setDia1(dia1);
            v.setDia2(dia2);
            v.setDia3(dia3);
            v.setUsuariovet_id(usuario_id);
            
            session.save(v);
            session.getTransaction().commit();
            session.close();
            return "OK";  
        } catch (Exception e) {
            return "Veterinario ya existente";
        }
    }

    public String modificarVeterinario(int veterinario_id,String dia1,String dia2,String dia3) {
        String d1 =dia1;
        String d2 =dia2;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Veterinario vet = buscarVeterinario(veterinario_id);
            
            if((d1.equals(dia2)) || (d1.equals(dia3)) || (d2.equals(dia3))) {
             
                System.out.println("Repetido");
                return "mostrarError(model)";
            }
                   
            vet.setDia1(dia1);
            vet.setDia2(dia2);
            vet.setDia3(dia3);
                        
            session.update(vet);
            session.getTransaction().commit();
            session.close();
            return "OK";
            
        } catch (Exception e) {
            return "No se pudo realizar el cambio";
        } 
    }

    public List<Veterinario>getTurnoVet(String especialidad) {
            try {
                Session session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();

                Query query = session.createQuery("from Veterinario u where" + " u.especialidad like '"+ especialidad + "'");
                List<Veterinario> vetEsp = query.getResultList();
                
                session.close();
                return vetEsp;
        } catch (Exception e) {
            return  FactoriaArrayList.getInstance();
        }
    }
    
    public List<Veterinario>getHoraVet(Integer veterinario_id) {    
            try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery("from Veterinario u where" + " u.veterinario_id like '"+ veterinario_id + "'");
            List<Veterinario> vetHora = query.getResultList();

            session.close();
            return vetHora;
        } catch (Exception e) {
            return  FactoriaArrayList.getInstance();
        }
    }   
      
    public List<Veterinario>getVetId() {
                  
            try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery("from Veterinario");
            List<Veterinario> vetId = query.getResultList();

            session.close();
            return vetId;
        } catch (Exception e) {
            return  FactoriaArrayList.getInstance();
        }
    }   

    public Veterinario buscarVeterinario(Integer veterinario_id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Veterinario vet = session.get(Veterinario.class, veterinario_id);
            session.close();
            return vet;
        } catch (Exception e) {
            return null;
        }
    }

    public Veterinario buscarVeterinarioUsuario(Integer usuariovet_id) {
        try {
            System.out.println("(1)");        
            Session session = HibernateUtil.getSessionFactory().openSession();
            System.out.println("(1.5):" + session);
            session.beginTransaction();
            System.out.println("(2): " + usuariovet_id);
            Veterinario vet = session.get(Veterinario.class, usuariovet_id);
            System.out.println("(3)");
            session.close();
            System.out.println("(4): " + vet);
            return vet;
        } catch (Exception e) {
            System.out.println("(E)");
            return null;
        }

    }
    
    public String borrarVeterinario(Integer usuario_id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Veterinario v = buscarVeterinario(usuario_id);
            session.delete(v);
            
            session.getTransaction().commit();
            session.close();
            return "Veterinario borrado";
        } catch (Exception e) {
            return "no encontrado";
        }
    }

    public List<Veterinario> getVeterinarios() {
        return veterinarios;
    }

    public Veterinario getVeterinarioValido() {
        return veterinario;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    } 
}
