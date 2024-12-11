/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author wesle
 */
package com.example.dao;

import com.example.entity.Categoria;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CategoriaDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("produtoPU");
    private EntityManager em = emf.createEntityManager();

    public void salvar(Categoria categoria) {
        em.getTransaction().begin();
        em.persist(categoria);
        em.getTransaction().commit();
    }

    public Categoria buscarPorId(Long id) {
        return em.find(Categoria.class, id);
    }

    public void atualizar(Categoria categoria) {
        em.getTransaction().begin();
        em.merge(categoria);
        em.getTransaction().commit();
    }

    public void excluir(Long id) {
        Categoria categoria = buscarPorId(id);
        if (categoria != null) {
            em.getTransaction().begin();
            em.remove(categoria);
            em.getTransaction().commit();
        }
    }

    public List<Categoria> listarTodos() {
        return em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
    }
}


