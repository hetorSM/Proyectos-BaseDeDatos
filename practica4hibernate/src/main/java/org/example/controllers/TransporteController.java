package org.example.controllers;

import org.example.HibernateUtil;
import org.example.models.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TransporteController {

    public TransporteController() {
    }

    /**
     * Comprueba si un usuario existe
     *
     * @param nombreUsuario El nombre del usuario
     * @return True si existe en caso contrario de no existir false
     */
    public boolean comprobarUsuario(String nombreUsuario) {
        return buscarUsuario(nombreUsuario) != null;
    }

    /**
     * Comprueba si un usuario tiene el mismo nombre y contraseña para saber si son correctos los datos
     *
     * @param usuario         El usuario con el que se compara
     * @param nombreUsuario   El nombre del usuario para comparar
     * @param passwordUsuario La contraseña del usuario para comparar
     * @return True si es correcto en caso contrario de ser incorrecto false
     */
    public boolean comprobarUsuario(Usuario usuario, String nombreUsuario, String passwordUsuario) {
        return usuario.getUsuarioNombre().equals(nombreUsuario) && usuario.getUsuarioPassword().equals(passwordUsuario);
    }

    /**
     * Comprueba si un usuario existe en la lista obtenida con getAllEntities.
     *
     * @param nombreUsuario El nombre del usuario que se desea verificar.
     * @return Si el usuario existe lo envia y en caso contrario null.
     */
    public Usuario buscarUsuario(String nombreUsuario) {
        List<Usuario> usuarios = getListEntidades(Usuario.class);
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuarioNombre().equals(nombreUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Retorna un objeto de la clase que se especifica por ID.
     *
     * @param entidadId   EL ID que se desea obterner.
     * @param entidadTipo La clase de la entidad que se desea obtener.
     * @param <T>         El tipo generico de la entidad.
     * @return El objeto a obtener
     */
    public <T> T obtenerEntidadID(int entidadId, Class<T> entidadTipo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        T entidad = session.get(entidadTipo, entidadId);
        session.close();
        return entidad;
    }

    /**
     * Actualiza una entidad existente en la base de datos.
     *
     * @param entidad La entidad que se desea actualizar en la base de datos.
     * @param <T>     El tipo genérico de la entidad.
     */
    public <T> void actualizarEntidad(T entidad) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(entidad);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Error al modificar la entidad: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    /**
     * Inserta una nueva entidad en la base de datos.
     *
     * @param entidad La entidad que se desea guardar en la base de datos.
     * @param <T>     El tipo generico de la entidad.
     */
    public <T> void insertarEntidad(T entidad) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(entidad);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Error al guardar la entidad: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Elimina una entidad existente de la base de datos.
     *
     * @param entidad La entidad que se desea eliminar de la base de datos.
     * @param <T>     El tipo generico de la entidad.
     */
    public <T> void borrarEntidad(T entidad) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(entidad);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Error al eliminar la entidad: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Obtiene todas las entidades de un tipo específico desde la base de datos.
     *
     * @param entidadTipo La clase de la entidad que se desea obtener.
     * @param <T>         El tipo genérico de las entidades.
     * @return Una lista de todas las entidades del tipo especificado.
     */
    public <T> List<T> getListEntidades(Class<T> entidadTipo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<T> listaEntidades = null;
        try {
            listaEntidades = session.createQuery("FROM " + entidadTipo.getSimpleName(), entidadTipo).list();
        } catch (Exception e) {
            System.out.println("Error al obtener las entidades: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return listaEntidades;
    }
}
