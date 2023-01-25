<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>

<%@ page import="com.dewine.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% DataSource dataSource = (DataSource) pageContext.getServletContext().getAttribute("DataSource"); %>
<% CategoriesDAO categoriesDAO = new CategoriesDAO(dataSource); %>

<% Users user = (Users) request.getSession().getAttribute("user");
    if (user == null || !user.getUserRole().equals("Admin")) {
        request.setAttribute("notAuthorized", Boolean.TRUE);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <%@include file="/fragments/meta.jsp" %>

    <title>Upload Prodotti</title>
</head>
<body>
<%@include file="/fragments/header.jsp" %>

<div class="container">
    <div class="row justify-content-center">
        <h3>Upload prodotti</h3>
        <div class="col-lg-6">
            <form id="upload-form" action="${pageContext.request.contextPath}/productUpload" method="post"
                  enctype="multipart/form-data">
                <div class="form-group row">
                    <div class="col-lg-6">
                        <label for="inputBrand" class="form-label">Marca</label>
                        <input class="form-control" type="text" id="inputBrand" required
                               placeholder="marca" name="p_brand" maxlength="255">
                    </div>
                    <div class="col-lg-6">
                        <label for="inputModel" class="form-label">Modello</label>
                        <input class="form-control" type="text" id="inputModel" required
                               placeholder="modello" name="p_model" maxlength="255">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-6">
                        <label for="inputTitle" class="form-label">Titolo</label>
                        <input class="form-control" type="text" id="inputTitle" required
                               placeholder="titolo annuncio" name="p_title" maxlength="255">
                    </div>
                    <div class="col-md-3">
                        <label for="inputPrice" class="form-label">Prezzo</label>
                        <input class="form-control" type="text" id="inputPrice" required
                               placeholder="prezzo" name="p_price">
                    </div>
                    <div class="col-md-3">
                        <label for="inputVat" class="form-label">Aliquota</label>
                        <select class="form-select" aria-label="Seleziona categoria" name="p_vat" id="inputVat" required>
                            <option selected>Seleziona l'Iva</option>
                            <option value="1.04">Aliquota 4%</option>
                            <option value="1.05">Aliquota 5%</option>
                            <option value="1.10">Aliquota 10%</option>
                            <option value="1.22">Aliquota 22%</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="inputDescription" class="form-label">Descrizione</label>
                        <input class="form-control" type="text" id="inputDescription" required
                               placeholder="descrizione prodotto" name="p_description" maxlength="255">
                    </div>
                </div>
                <div class="gy-2 form-group row align-items-center">
                    <div class="col-lg-9">
                        <label for="inputStock" class="form-label">Quantità</label>
                        <input class="form-control" type="number" id="inputStock" required
                               placeholder="quantità stock" name="p_stock" min="0">
                    </div>
                    <div class="col-lg-3">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="p_active" id="inputActive" value="1">
                            <label class="form-check-label" for="inputActive">Attivo</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="p_active" id="inputActive2" checked value="0">
                            <label class="form-check-label" for="inputActive2">Disattivato</label>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="inputCategory" class="form-label">Seleziona la categoria</label>
                        <select class="form-select" aria-label="Seleziona categoria" name="p_category" id="inputCategory">
                            <option selected>Seleziona la categoria</option>
                            <%
                                Collection<?> allCategory = null;
                                try {
                                    allCategory = categoriesDAO.getAll("categoryName");
                                    if(allCategory != null){
                                        Iterator<?> iterator = allCategory.iterator();
                                        while (iterator.hasNext()){
                                            Categories category = (Categories) iterator.next();
                            %>
                                            <option value=<%=category.getCategoryId()%>><%=category.getCategoryName()%></option>
                            <%
                                        }
                                    } else {

                                        }
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            %>
                        </select>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="inputImages" class="form-label">Immagini prodotto</label>
                    <input class="form-control" type="file" accept="image/jpeg" id="inputImages" name="p_images"
                           multiple>
                </div>
                <br>
                <button type="submit" class="btn btn-primary">Inserisci Prodotto</button>
                <button type="reset" class="btn btn-secondary">Annulla</button>
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
