<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp" />
<h1>Shopping cart</h1>
<c:choose>
    <c:when test="${cart!= null && !cart.getItems().isEmpty()}">
        <table class="table table-hover table-striped">
            <tr>

                <th>id</th>
                <th>name</th>
                <th>price</th>
                <th>Quantity</th>
                <th>total</th>
            </tr>
            <c:forEach items="${cart.getItems()}" var="item">
                <tr>
                    <td>
                        ${item.getProduct().getId()}
                    </td>
                    <td>
                        ${item.getProduct().getName()}
                    </td>
                    <td>
                        ${item.getProduct().getPrice()}
                    </td>
                    <td>
                        ${item.getCant()}
                    </td>
                    <td>
                        ${item.getTotal()}
                    </td>
                </tr>
            </c:forEach>

            <tr>
                <td colspan="4" style="text-align: right;">Total</td>
                <td>
                    ${cart.getTotal()}
                </td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <div class="alert alert-warning"> Lo sentimos, no hay articulos en su carro de compras</div>
    </c:otherwise>
</c:choose>

<p>
    <a href="${pageContext.request.getContextPath()}/products">Seguir Comprando</a>
    <a href="${pageContext.request.getContextPath()}/index.html">Volver al inicio</a>
</p>
<jsp:include page="layout/footer.jsp"/>