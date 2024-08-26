<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>New Product</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
                crossorigin="anonymous">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
                crossorigin="anonymous"></script>
        </head>

        <body>
            <div class="container">

                <form action="${pageContext.request.getContextPath()}/product/new" method="post">
                    <div class="row mb-2">
                        <label class="col-form-label col-sm-2" for="name">Nombre: </label>
                        <div class="col-sm-4">
                            <input class="form-control" type="text" id="name" name="name">
                        </div>
                    </div>
                    <div class="row mb-2">
                        <label class="col-form-label col-sm-2" for="price">Precio: </label>
                        <div class="col-sm-4">
                            <input class="form-control" type="number" id="price" name="price">
                        </div>
                    </div>
                    <div class="row mb-2">
                        <label class="col-form-label col-sm-2" for="category">Precio: </label>
                        <div class="col-sm-4">
                            <select name="category" id="category" name="category" class="form-select">
                                <option value="" selected>Select a category</option>
                                <c:forEach items="${listCategory}" var="cat">
                                    <option value="${cat.getId()}">
                                        ${cat.getName()}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row mb-2">
                        <label class="col-form-label col-sm-2" for="sku">SKU: </label>
                        <div class="col-sm-4">
                            <input class="form-control" type="text" id="sku" name="sku">
                        </div>
                    </div>
                    <div class="row mb-2">
                        <label class="col-form-label col-sm-2" for="date">Fecha de registro: </label>
                        <div class="col-sm-4">
                            <input class="form-control" type="date" id="date" name="date">
                        </div>
                    </div>
                    <input class="form-control" type="submit" value="Create">
                </form>
            </div>

        </body>

        </html>