package veterinaria.repositories;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.SQLQuery;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import veterinaria.SpringMVC.HibernateUtil;
import veterinaria.controllers.Controlador;
import veterinaria.entities.Productos;
import veterinaria.entities.Usuario;

@Repository
public class RepoUsuarios {

    private final String dbFullURL;
    private final String dbUser;
    private final String dbPswd;

    private Usuario usuario;
    private List<Usuario> usuarios;
    private List<Usuario> veteri;

    @Autowired
    public RepoUsuarios(
            @Qualifier("dbName") String dbName,
            @Qualifier("dbURL") String dbURL,
            @Qualifier("dbUser") String dbUser,
            @Qualifier("dbPswd") String dbPswd) {
        dbFullURL = "jdbc:mysql://" + dbURL + "/" + dbName;
        this.dbUser = dbUser;
        this.dbPswd = dbPswd;
    }

   
    public Usuario buscarUsuario(Integer usuario_id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Usuario usu = session.get(Usuario.class, usuario_id);
            session.close();
            return usu;
        } catch (Exception e) {
            return null;
        }
    }
    
    public String borrarUsuario(Integer usuario_id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
           Usuario us = buscarUsuario(usuario_id);
            session.delete(us);
            
            session.getTransaction().commit();
            session.close();
            return "Usuario borrado";
        } catch (Exception e) {
            return "no encontrado";
        }
    }

    public void validarUsuario(String nombre,String clave) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Usuario as usuario where" + " usuario.nombre like '" + nombre + "' and usuario.clave like '" + clave + "'");
            usuario = (Usuario) query.getSingleResult();
            
            session.close();
        } catch (Exception e) {
            usuario = Usuario.getInstance();
            usuario.setNombre("");
            usuario.setClave("");
            usuario.setTipo("");
        }
    }

    public void consultarUsuarios() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Usuario");
            usuarios = query.getResultList();
            session.close();
            
            System.out.print(usuario);
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public String altaUsuario(String nombre, String clave,String tipo){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Usuario usu = Usuario.getInstance();
            usu.setNombre(nombre);
            usu.setClave(clave);          
            usu.setTipo(tipo);

            session.save(usu);
            session.getTransaction().commit();
            session.close();
            return "OK";
            
        } catch (Exception e) {
            return "Usuario ya existente";
        }
    }

    public Usuario getUsuarioValido() {
        return usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Usuario> getVeterinario() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Usuario u where" + " u.tipo like 'veterinario'");
            List<Usuario> veteri = query.getResultList();
            session.close();
            return veteri;
        } catch (Exception e) {
            return  FactoriaArrayList.getInstance();
        }
    }
}
