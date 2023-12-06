package veterinaria.repositories;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import veterinaria.SpringMVC.HibernateUtil;
import veterinaria.entities.Productos;
import veterinaria.entities.Turnos;
import veterinaria.entities.Usuario;
import veterinaria.entities.Veterinario;
        
@Repository        
public class RepoTurnos {

    private final String dbFullURL;
    private final String dbUser;
    private final String dbPswd;

    
    private Turnos turno;
    private List<Turnos> turnos ;
    private List<Turnos> turnosRecep ;

    @Autowired
    public RepoTurnos(
        @Qualifier("dbName") String dbName,
        @Qualifier("dbURL") String dbURL,
        @Qualifier("dbUser") String dbUser,
        @Qualifier("dbPswd") String dbPswd) {

        dbFullURL = "jdbc:mysql://" + dbURL + "/" + dbName;
        this.dbUser = dbUser;
        this.dbPswd = dbPswd;
    }

    public String altaTurnos(String nom_mascota, String especialidad,String dia,String hora,String nom_dueño,int contacto){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Turnos tur = Turnos.getInstance();
            tur.setNom_mascota(nom_mascota);
            tur.setEspecialidad(especialidad);          
            tur.setDia(dia);
            tur.setHora(hora);
            tur.setNom_dueño(nom_dueño);
            tur.setTel_contacto(contacto);

            session.save(tur);
            session.getTransaction().commit();
            session.close();
            return "OK";
            
        } catch (Exception e) {
            return "Turno ya existente";
        }
    }

    public List<Turnos> getTurnosRecep() {    
        List<Turnos> turnosRecep;

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Turnos");
        turnosRecep = query.getResultList();
        session.close();
        return turnosRecep;
    }

    public List<Turnos> buscarTurnosVet(String especialidad, String dia1, String dia2, String dia3) {
          
        System.out.printf("" + especialidad + dia1 + dia2 + dia3);
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
          
            Query query = session.createQuery("from Turnos as t where"+ " t.especialidad like '" + especialidad + "' and t.dia like '" + dia1 + "'");
                           
            //no toma los tres hay que hacer de a uno
            List<Turnos> turnosVet = query.getResultList();
            session.close();
            return turnosVet;
        } catch (Exception e) {
            return  FactoriaArrayList.getInstance();
        }
    } 
    
    public String tomarTurno(Integer idTurno) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Turnos tur = buscarTurno(idTurno);
            session.delete(tur);
            
            session.getTransaction().commit();
            session.close();
            return "OK";
        } catch (Exception e) {
            return "ya existente";
        }
    }
     
    public Turnos buscarTurno(Integer idTurno) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Turnos tur = session.get(Turnos.class, idTurno);
            session.getTransaction().commit();
            session.close();
            return tur;
                    
        } catch (Exception e) {
            return null;
        }
    }

    public Turnos getTurnoValido() {
        return turno;
    }
    
    

    public List<Turnos> getTurnos() {
        return turnos;
    }
}