<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Iterator" %>

<%@ page import="com.dewine.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% Users user = (Users) request.getSession().getAttribute("user");
    if (user == null || !user.getUserRole().equals("Admin")) {
        //response.sendRedirect(pageContext.getServletContext().getContextPath()+"/login.jsp");
        request.setAttribute("notAuthorized", Boolean.TRUE);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
%>
<% DataSource dataSource = (DataSource) pageContext.getServletContext().getAttribute("DataSource"); %>
<% UsersDAO usersDAO = new UsersDAO(dataSource); %>

<!DOCTYPE html>
<html lang="it">
<head>
    <%@include file="/fragments/meta.jsp" %>

    <title>Gestione Clienti</title>
</head>
<body>
<%@include file="/fragments/header.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-12" style="overflow: scroll">
            <h3>Clienti</h3>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Username</th>
                    <th scope="col">Nome e Cognome</th>
                    <th scope="col">Ruolo</th>
                    <th scope="col">Email</th>
                    <th scope="col">Cellulare</th>
                    <th scope="col">Indirizzo spedizione</th>
                    <th scope="col">Creato il</th>
                </tr>
                </thead>
                <tbody>
                <%
                    Collection<?> customers;
                    try {
                        customers = usersDAO.getAll("userId");
                        if (customers != null) {
                            Iterator<?> iterator = customers.iterator();
                            while (iterator.hasNext()) {
                                Users users = (Users) iterator.next();
                %>
                <tr>
                    <td><%=users.getUserId()%>
                    </td>
                    <td><%=users.getUsername()%>
                    </td>
                    <td><%=users.getFirstName() + " " + users.getLastName()%>
                    </td>
                    <td><%=users.getUserRole()%>
                    </td>
                    <td><%=users.getEmail()%>
                    </td>
                    <td><%=users.getMobilePhone()%>
                    </td>
                    <td><%=users.getAddress() + " " + users.getCapCode() + " " + users.getCity()%>
                    </td>
                    <td><%=users.getCreatedAt()%>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/gestione?toDo=deleteUser&userId=<%=users.getUserId()%>">
                            <button type="button" class="btn btn-danger">ELIMINA ACCOUNT</button>
                        </a>
                    </td>
                </tr>
                <% }
                } else { %>
                <tr>
                    <td>nessun cliente disponibile</td>
                </tr>
                <%
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                %>
                </tbody>
            </table>
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
