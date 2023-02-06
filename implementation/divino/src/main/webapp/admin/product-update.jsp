<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="account.AccountEntity" %>
<%@ page import="catalog.CatalogEntity" %>
<%@ page import="catalog.ProductEntity" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.nio.file.FileSystemNotFoundException" %>
<% ServletContext context = request.getServletContext();
    AccountEntity user = (AccountEntity) request.getSession().getAttribute("user");
    if (user == null || !user.getRole().equals(AccountEntity.Role.MANAGERUSER)) {
        String errors = "non sei autorizzato";
        session.setAttribute("ow-errors", errors);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
    Integer product_id = Integer.valueOf(request.getParameter("up_product"));
    System.out.println(product_id);
    CatalogEntity c = (CatalogEntity) context.getAttribute("catalog");
    HashSet<ProductEntity> catalog = (HashSet<ProductEntity>) c.getCatalogProducts();
    //ProductEntity product = new ProductEntity();



%>

<!DOCTYPE html>
<html lang="it">
<head>
    <%@include file="/fragments/meta.jsp" %>

    <title>Update Prodotto</title>
</head>
<body>
<%@include file="/fragments/header.jsp" %>
<%
    for (ProductEntity product : catalog){
        if(product.getProductId().equals(product_id)){%>
<div class="container">
    <div class="row justify-content-center">
        <h3>Upload prodotti</h3>
        <div class="col-lg-6">
            <form id="update-form" action="${pageContext.request.contextPath}/catalog" method="post">
                <input hidden="hidden" name="mode" value="updateProduct">
                <input hidden="hidden" name="p_productID" value="<%out.println(product_id);%>">
                <div class="form-group row">
                    <div class="col-lg-6">
                        <label for="inputBrand" class="form-label">Marca</label>
                        <input class="form-control" type="text" id="inputBrand" required
                               placeholder="marca" name="p_brand" maxlength="255" value="<%out.println(product.getProductBrand());%>">
                    </div>
                    <div class="col-lg-6">
                        <label for="inputModel" class="form-label">Formato</label>
                        <input class="form-control" type="text" id="inputModel" required
                               placeholder="modello" name="p_format" maxlength="255" value="<%out.println(product.getProductFormat());%>">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-6">
                        <label for="inputTitle" class="form-label">Promo 1/0</label>
                        <input class="form-control" type="text" id="inputTitle" required
                               placeholder="titolo annuncio" name="p_issales" maxlength="255" value="<%out.println(product.isSales());%>">

                        <label for="inputPriceSales" class="form-label">Prezzo Promo</label>
                        <input class="form-control" type="text" id="inputPriceSales" required
                               placeholder="prezzo" name="p_price_sales" value="<%out.println(product.getSalesPrice());%>">
                    </div>
                    <div class="col-md-3">
                        <label for="inputPrice" class="form-label">Prezzo</label>
                        <input class="form-control" type="text" id="inputPrice" required
                               placeholder="prezzo" name="p_price" value="<%out.println(product.getProductPrice());%>">
                    </div>
                    <div class="col-md-3">
                        <label for="inputVat" class="form-label">Aliquota</label>
                        <select class="form-select" aria-label="Seleziona categoria" name="p_vat" id="inputVat"
                                required>
                            <option selected>Seleziona l'Iva</option>
                            <option value="4">Aliquota 4%</option>
                            <option value="5">Aliquota 5%</option>
                            <option value="0">Aliquota 10%</option>
                            <option value="2">Aliquota 22%</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="inputDescription" class="form-label">Descrizione</label>
                        <input class="form-control" type="text" id="inputDescription" required
                               placeholder="descrizione prodotto" name="p_description" maxlength="255" value="<%out.println(product.getProductDescription());%>">
                    </div>
                </div>
                <div class="gy-2 form-group row align-items-center">
                    <div class="col-lg-9">
                        <label for="inputStock" class="form-label">Disponibilità</label>
                        <input class="form-control" type="number" id="inputStock" required
                               placeholder="quantità stock" name="p_availability" min="0" value="<%out.println(product.getProductAvailability());%>">
                    </div>
                    <div class="col-lg-3">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="p_active" id="inputActive" value="1">
                            <label class="form-check-label" for="inputActive">Attivo</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="p_active" id="inputActive2" checked
                                   value="0">
                            <label class="form-check-label" for="inputActive2">Disattivato</label>
                        </div>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="inputImages" class="form-label">Immagine prodotto</label>
                    <input class="form-control" type="file" accept="image/jpeg" id="inputImages" name="p_images">
                </div>
                <br>
                <button type="submit" class="btn btn-primary">Modifica Prodotto</button>
                <button type="reset" class="btn btn-secondary">Annulla</button>
            </form>
        </div>
    </div>
</div>
<%}
}%>
<%@include file="/fragments/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js"
        integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk"
        crossorigin="anonymous">
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js"
        integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy"
        crossorigin="anonymous">
</script>
</body>
</html>
