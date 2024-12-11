<%@ page import="com.example.entity.Produto" %>
<%@ page import="com.example.entity.Categoria" %>
<%@ page import="java.util.List" %>

<%
    List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");  // Listando as categorias
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CRUD de Produtos</title>
</head>
<body>
    <h1>Gerenciar Produtos</h1>

    <!-- Formulário para Adicionar ou Editar Produto -->
    <form action="ProdutoServlet" method="post">
        <input type="hidden" name="action" value="<%= request.getParameter("editId") != null ? "update" : "create" %>">
        <input type="hidden" name="id" value="<%= request.getParameter("editId") != null ? request.getParameter("editId") : "" %>">

        <label for="nome">Nome:</label>
        <input type="text" name="nome" value="<%= request.getParameter("editNome") != null ? request.getParameter("editNome") : "" %>" required>

        <label for="preco">Preço:</label>
        <input type="number" step="0.01" name="preco" value="<%= request.getParameter("editPreco") != null ? request.getParameter("editPreco") : "" %>" required>

        <label for="quantidade">Quantidade:</label>
        <input type="number" name="quantidade" value="<%= request.getParameter("editQuantidade") != null ? request.getParameter("editQuantidade") : "" %>" required>

        <label for="categoria">Categoria:</label>
        <select name="categoriaId" required>
            <%
                for (Categoria categoria : categorias) {
            %>
                <option value="<%= categoria.getId() %>" <%= request.getParameter("editCategoriaId") != null && request.getParameter("editCategoriaId").equals(String.valueOf(categoria.getId())) ? "selected" : "" %>>
                    <%= categoria.getNome() %>
                </option>
            <%
                }
            %>
        </select>

        <button type="submit">Salvar</button>
    </form>

    <hr>

    <!-- Tabela de Produtos -->
    <h2>Lista de Produtos</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Preço</th>
            <th>Quantidade</th>
            <th>Categoria</th>
            <th>Ações</th>
        </tr>
        <%
            if (produtos != null) {
                for (Produto produto : produtos) {
        %>
        <tr>
            <td><%= produto.getId() %></td>
            <td><%= produto.getNome() %></td>
            <td><%= produto.getPreco() %></td>
            <td><%= produto.getQuantidade() %></td>
            <td><%= produto.getCategoria().getNome() %></td>
            <td>
                <!-- Botão para Editar -->
                <form action="ProdutoServlet" method="get" style="display:inline;">
                    <input type="hidden" name="editId" value="<%= produto.getId() %>">
                    <input type="hidden" name="editNome" value="<%= produto.getNome() %>">
                    <input type="hidden" name="editPreco" value="<%= produto.getPreco() %>">
                    <input type="hidden" name="editQuantidade" value="<%= produto.getQuantidade() %>">
                    <input type="hidden" name="editCategoriaId" value="<%= produto.getCategoria().getId() %>">
                    <button type="submit">Editar</button>
                </form>
                
                <!-- Botão para Excluir -->
                <form action="ProdutoServlet" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="<%= produto.getId() %>">
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
