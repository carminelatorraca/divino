<!DOCTYPE html>
<html>
<nav class="navbar navbar-expand-md navbar-dark bg-black wine-header">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/shop.jsp">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="logo" width="50" height="50">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
                aria-controls="offcanvasNavbar" style="border: transparent">
            <span class="material-symbols-outlined">menu</span>
        </button>
        <div class="offcanvas offcanvas-end bg-black" tabindex="-1" id="offcanvasNavbar"
             aria-labelledby="offcanvasNavbarLabel">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasNavbarLabel"></h5>
                <button type="button" class="btn-close bg-white" data-bs-dismiss="offcanvas"
                        aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                           href="${pageContext.request.contextPath}/index.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/shop.jsp">Shop</a>
                    </li>
                    <%
                        if (session.getAttribute("user") != null) {
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/account/account.jsp">Il mio
                            account</a>
                    </li>
                    <%
                    } else {
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/login.jsp">Il mio account</a>
                    </li>
                    <%
                        }
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/cart.jsp">
                            <span class="material-symbols-outlined">shopping_bag</span>
                        </a>
                    </li>
                    <%
                        if (session.getAttribute("user") != null) {
                    %>
                    <a class="nav-link" href="${pageContext.request.contextPath}/logout">
                        <span class="material-symbols-outlined">logout</span>
                    </a>
                    <%
                        }
                    %>
                </ul>
                <br>
                <!--form di ricerca
                <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="cerca" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Cerca</button>
                </form>
                -->
            </div>
        </div>
    </div>
</nav>
</html>