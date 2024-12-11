/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

/**
 *
 * @author wesle
 */

import com.example.entity.Produto;
import com.example.entity.Categoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author wesle
 */
public class ProdutoDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("produtoPU");
    private EntityManager em = emf.createEntityManager();

    public void salvar(Produto produto) {
        em.getTransaction().begin();
        if (produto.getCategoria() != null) {
            produto.setCategoria(em.merge(produto.getCategoria()));  
        }
        em.persist(produto);
        em.getTransaction().commit();
    }

    public Produto buscarPorId(Long id) {
        return em.find(Produto.class, id);
    }

    public void atualizar(Produto produto) {
        em.getTransaction().begin();
        if (produto.getCategoria() != null) {
            produto.setCategoria(em.merge(produto.getCategoria()));
        }
        em.merge(produto);
        em.getTransaction().commit();
    }

    public void excluir(Long id) {
        Produto produto = buscarPorId(id);
        if (produto != null) {
            em.getTransaction().begin();
            em.remove(produto);
            em.getTransaction().commit();
        }
    }

    public List<Produto> listarTodos() {
        return em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
    }
}

