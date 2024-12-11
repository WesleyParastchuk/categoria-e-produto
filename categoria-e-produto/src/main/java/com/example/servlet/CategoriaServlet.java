/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author wesle
 */
package com.example.servlet;

import com.example.dao.CategoriaDAO;
import com.example.entity.Categoria;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/CategoriaServlet")
public class CategoriaServlet extends HttpServlet {

    private CategoriaDAO dao = new CategoriaDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            String nome = request.getParameter("nome");

            Categoria categoria = new Categoria();
            categoria.setNome(nome);

            dao.salvar(categoria);
        } else if ("update".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            String nome = request.getParameter("nome");

            Categoria categoria = dao.buscarPorId(id);
            if (categoria != null) {
                categoria.setNome(nome);
                dao.atualizar(categoria);
            }
        } else if ("delete".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            dao.excluir(id);
        }

        response.sendRedirect("categorias.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categorias", dao.listarTodos());
        RequestDispatcher dispatcher = request.getRequestDispatcher("categorias.jsp");
        dispatcher.forward(request, response);
    }
}

