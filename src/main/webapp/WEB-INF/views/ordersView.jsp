<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

            <head>
                <meta charset="utf-8">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
                <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
                <title>Orders</title>
            </head>
            <body">

                <div class="container-fluid text-center">
                    <div class="row content">
                        <div class="col-sm-2 sidenav">
                        </div>

                        <div class="col-sm-8 text-left">
                            <div id="history" class="panel-group">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3>Orders</h3>
                                        <button 
                                                onclick="location.href='order?new=true'"
                                                type="button" class="btn btn-warning">
                                            Add new ...
                                        </button>
                                    </div>
                                    <div class="panel-body">
                                        <form>
                                            <table class="table">
                                                <tr>
                                                    <th>
                                                        id
                                                    </th>
                                                    <th>
                                                        userId
                                                    </th>
                                                    <th>
                                                        createdAt
                                                    </th>
                                                    <th>
                                                        startAt
                                                    </th>
                                                    <th>
                                                        endAt
                                                    </th>
                                                    <th>
                                                        price
                                                    </th>
                                                    <th>
                                                        routeDiscount
                                                    </th>
                                                    <th>
                                                        userDiscount
                                                    </th>
                                                    <th>
                                                        cash
                                                    </th>
                                                    <th>

                                                    </th>
                                                </tr>
                                                <c:forEach items="${orders}" var="o">
                                                    <tr>
                                                        <td>${o.id}</td>
                                                        <td>${o.userId}</td>
                                                        <td>${o.createdAt}</td>
                                                        <td>${o.startAt}</td>
                                                        <td>${o.endAt}</td>
                                                        <td>${o.price}</td>
                                                        <td>${o.routeDiscount}</td>
                                                        <td>${o.userDiscount}</td>
                                                        <td>${o.cash}</td>
                                                        <td> <a href="order?id=${o.id}">Edit...</a> </td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-2 sidenav">
                        </div>
                    </div>
                </div>
                </body>