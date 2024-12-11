<%-- 
    Document   : categorias
    Author     : wesle
--%>

<%@ page import="com.example.entity.Categoria" %>
<%@ page import="java.util.List" %>

<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CRUD de Categorias</title>
</head>
<body>
    <h1>Gerenciar Categorias</h1>

    <!-- Formulário para Adicionar ou Editar Categoria -->
    <form action="CategoriaServlet" method="post">
        <input type="hidden" name="action" value="<%= request.getParameter("editId") != null ? "update" : "create" %>">
        <input type="hidden" name="id" value="<%= request.getParameter("editId") != null ? request.getParameter("editId") : "" %>">

        <label for="nome">Nome:</label>
        <input type="text" name="nome" value="<%= request.getParameter("editNome") != null ? request.getParameter("editNome") : "" %>" required>

        <button type="submit">Salvar</button>
    </form>

    <hr>

    <!-- Tabela de Categorias -->
    <h2>Lista de Categorias</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Ações</th>
        </tr>
        <%
            if (categorias != null) {
                for (Categoria categoria : categorias) {
        %>
        <tr>
            <td><%= categoria.getId() %></td>
            <td><%= categoria.getNome() %></td>
            <td>
                <!-- Botão para Editar -->
                <form action="CategoriaServlet" method="get" style="display:inline;">
                    <input type="hidden" name="editId" value="<%= categoria.getId() %>">
                    <input type="hidden" name="editNome" value="<%= categoria.getNome() %>">
                    <button type="submit">Editar</button>
                </form>

                <!-- Botão para Excluir -->
                <form action="CategoriaServlet" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="<%= categoria.getId() %>">
                    <button type="submit">Excluir</button>
                </form>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
