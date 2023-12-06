package veterinaria.repositories;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import veterinaria.SpringMVC.HibernateUtil;
import veterinaria.entities.Productos;
import veterinaria.entities.Usuario;

@Repository
public class RepoProductos {

    private final String dbFullURL;
    private final String dbUser;
    private final String dbPswd;

    private Productos producto;
    private List<Productos> productos;
    private List<Productos> productosRecep;

    @Autowired
    public RepoProductos(
        @Qualifier("dbName") String dbName,
        @Qualifier("dbURL") String dbURL,
        @Qualifier("dbUser") String dbUser,
        @Qualifier("dbPswd") String dbPswd) {

        dbFullURL = "jdbc:mysql://" + dbURL + "/" + dbName;
        this.dbUser = dbUser;
        this.dbPswd = dbPswd;
    }

    public String altaProducto(String descripcion, String categoria,float precio){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Productos p = Productos.getInstance();
            p.setDescripcion(descripcion);
            p.setCategoria(categoria);
            p.setPrecio(precio);

            session.save(p);
            session.getTransaction().commit();
            session.close();
            return "OK";
            
        } catch (Exception e) {
            return "Producto ya existente";
        }
    }

    public String ventaProductoVet(Integer id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Productos prod = buscarProducto(id);
            session.delete(prod);
            
            session.getTransaction().commit();
            session.close();
            return "OK";
        } catch (Exception e) {
            return "ya existente";
        }
    }

    public Productos buscarProducto(Integer id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Productos prod = session.get(Productos.class, id);
            session.getTransaction().commit();
            session.close();

            return prod;
        } catch (Exception e) {
            return null;
        }
    }

    public void validarProductos(String nombre,String clave) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery("from Productos");
            productos = (List<Productos>) (Productos) query.getSingleResult();
            session.close();
        } catch (Exception e) {
            producto = Productos.getInstance();
            producto.getDescripcion();
            producto.getCategoria();
            producto.getPrecio();
        }
    }

    public Productos getProductoValido() {
        return producto;
    }

    public List<Productos> getProductos() {
        List<Productos> productos;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Productos ");
        productos = query.getResultList();

        session.close();
        return productos;
    }

    public List<Productos> getProductosRecep() {
        List<Productos> productosRecep;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Productos u where" + " u.categoria like 'regulares'");
        productosRecep = query.getResultList();

        session.close();
        return productosRecep;
    }    
}
