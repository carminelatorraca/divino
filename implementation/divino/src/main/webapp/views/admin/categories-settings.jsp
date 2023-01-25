<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>

<%@ page import="com.dewine.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Users user = (Users) request.getSession().getAttribute("user");
    if (user == null || !user.getUserRole().equals("Admin")) {
        //response.sendRedirect(pageContext.getServletContext().getContextPath()+"/login.jsp");
        request.setAttribute("notAuthorized", Boolean.TRUE);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
%>
<% DataSource dataSource = (DataSource) pageContext.getServletContext().getAttribute("DataSource"); %>
<% CategoriesDAO setCategoriesDAO = new CategoriesDAO(dataSource); %>

<% ArrayList<String> errors = (ArrayList<String>) session.getAttribute("cat-errors"); %>

<!DOCTYPE html>
<html lang="it">
<head>
    <%@include file="/fragments/meta.jsp" %>

    <title>Gestione Categorie</title>
</head>
<body>
<%@include file="/fragments/header.jsp" %>

<div class="container">
    <%
        if (errors != null && errors.size() > 0) {
            for (String error : errors) {
    %>
    <div class="row justify-content-center">
        <div class="col-lg-12">
            <div class="alert alert-warning wine-errors" role="alert">
                <%=error%>
            </div>
        </div>
    </div>
    <%
            }
        }
    %>
    <div class="row">
        <div class="col-md-9">
            <h3>Categorie</h3>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Nome</th>
                    <th scope="col">AZIONE</th>
                </tr>
                </thead>
                <tbody>
                <%
                    Collection<?> allCategories = null;
                    try {
                        allCategories = setCategoriesDAO.getAll("categoryName");
                        Iterator<?> iterator = allCategories.iterator();
                        while (iterator.hasNext()) {
                            Categories category = (Categories) iterator.next();
                %>
                <tr>
                    <td><%=category.getCategoryId()%>
                    </td>
                    <td><%=category.getCategoryName()%>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/category?toDo=deleteCategory&categoryId=<%=category.getCategoryId()%>">
                            <button type="button" class="btn btn-danger">ELIMINA</button>
                        </a>
                    </td>
                    <!--<td>
                        <button type="button" class="btn btn-secondary"
                                onclick="updateCategory(//category.getCategoryId()%>)">MODIFICA
                        </button>
                    </td>-->
                </tr>
                <%
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } %>
                </tbody>
            </table>
        </div>
        <div class="col-md-3">
            <h3>Aggiungi categoria</h3>
            <form class="" method="get" action="${pageContext.request.contextPath}/category">
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="inputCategoryName" class="form-label">Nome Categoria</label>
                        <input class="form-control" type="text" id="inputCategoryName" required
                               placeholder="inserisci il nome della categoria" name="cName">
                    </div>
                </div>
                <br>
                <button type="submit" class="btn btn-primary">AGGIUNGI</button>
                <button type="reset" class="btn btn-secondary">CANCELLA</button>
                <br>
                <%
                    if (request.getAttribute("catError") != null) {
                %>
                <div class="alert alert-danger" role="alert" id="error-div">
                    Questa categoria esiste gi&agrave;
                </div>
                <% } %>
            </form>
        </div>
    </div>
</div>

<%@include file="/fragments/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js"
        integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js"
        integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy"
        crossorigin="anonymous"></script>
</body>
</html>
