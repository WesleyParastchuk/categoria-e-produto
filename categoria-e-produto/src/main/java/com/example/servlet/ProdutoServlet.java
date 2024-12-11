/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.servlet;

/**
 *
 * @author wesle
 */

import com.example.dao.ProdutoDAO;
import com.example.dao.CategoriaDAO;
import com.example.entity.Produto;
import com.example.entity.Categoria;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * @author wesle
 */
@WebServlet("/ProdutoServlet")
public class ProdutoServlet extends HttpServlet {

    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();  // Acesso ao DAO da Categoria

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            String nome = request.getParameter("nome");
            double preco = Double.parseDouble(request.getParameter("preco"));
            int quantidade = Integer.parseInt(request.getParameter("quantidade"));
            Long categoriaId = Long.parseLong(request.getParameter("categoriaId")); // ID da categoria

            Categoria categoria = categoriaDAO.buscarPorId(categoriaId); // Obtendo a categoria pelo ID

            Produto produto = new Produto();
            produto.setNome(nome);
            produto.setPreco(preco);
            produto.setQuantidade(quantidade);
            produto.setCategoria(categoria);  // Associando a categoria ao produto

            produtoDAO.salvar(produto);
        } else if ("update".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            String nome = request.getParameter("nome");
            double preco = Double.parseDouble(request.getParameter("preco"));
            int quantidade = Integer.parseInt(request.getParameter("quantidade"));
            Long categoriaId = Long.parseLong(request.getParameter("categoriaId")); // ID da categoria

            Categoria categoria = categoriaDAO.buscarPorId(categoriaId); // Obtendo a categoria pelo ID

            Produto produto = produtoDAO.buscarPorId(id);
            if (produto != null) {
                produto.setNome(nome);
                produto.setPreco(preco);
                produto.setQuantidade(quantidade);
                produto.setCategoria(categoria);  // Atualizando a categoria do produto
                produtoDAO.atualizar(produto);
            }
        } else if ("delete".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            produtoDAO.excluir(id);
        }

        response.sendRedirect("produtos.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produto> produtos = produtoDAO.listarTodos();
        request.setAttribute("produtos", produtos);
        
        List<Categoria> categorias = categoriaDAO.listarTodos();
        request.setAttribute("categorias", categorias);

        RequestDispatcher dispatcher = request.getRequestDispatcher("produtos.jsp");
        dispatcher.forward(request, response);
    }
}

