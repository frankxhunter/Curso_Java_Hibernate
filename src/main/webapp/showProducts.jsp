<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <jsp:include page="layout/header.jsp" />

        <h1>Productos</h1>

        <c:if test="${username.isPresent()}">
            <p class="alert alert-info">Hello
                <c:out value="${username.get()}" />
            </p>
        </c:if>

        <table class="table table-hover table-striped">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Category</th>
                <th>Register Date</th>
                <th>SKU</th>
                <c:if test="${username.isPresent()}">
                    <th>Price</th>
                    <th>Add to ShoppingCart</th>
                    <th>Delete from database</th>
                </c:if>
            </tr>

            <c:forEach items="${products}" var="p">
                <tr>
                    <td>
                        <c:out value="${p.getId()}" />
                    </td>
                    <td>
                        <c:out value="${p.getName()}" />
                    </td>
                    <td>
                        <c:out value="${p.getCategoria().getName()}" />
                    </td>
                    <td>
                        <c:out value="${p.getRegisterDate()}" />
                    </td>
                    <td>
                        <c:out value="${p.getSku()}" />
                    </td>

                    <c:if test="${username.isPresent()}">
                        <td>
                            <c:out value="${p.getPrice()}" />
                        </td>
                        <td>
                            <a class="btn btn-sm btn-primary"
                                href="${pageContext.request.getContextPath()}/cart/add?id=${p.getId()}">Add</a>
                        </td>
                        <td>
                            <a class="btn btn-sm btn-danger "
                                onclick="return confirm('Are you sure you want to delete this product?')"
                                href="${pageContext.request.getContextPath()}/product/delete?id=${p.getId()}">Delete</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>

        <c:if test="${username.isPresent()}">
            <a class="btn btn-danger" href="${pageContext.request.getContextPath()}/product/new">Crear nuevo
                producto</a>
        </c:if>

        <p>Message:
            <c:out value="${message}" />
        </p>
        <jsp:include page="layout/footer.jsp"/>