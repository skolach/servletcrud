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
    <style>
        /* Remove the navbar's default margin-bottom and rounded borders */
        .navbar {
            margin-bottom: 0;
            border-radius: 0;
        }

        /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
        .row.content {
            height: 450px
        }

        /* Set gray background color and 100% height */
        .sidenav {
            padding-top: 20px;
            background-color: #f1f1f1;
            height: 100%;
        }

        /* Set black background color, white text and some padding */
        footer {
            background-color: #555;
            color: white;
            padding: 15px;
        }

        /* On small screens, set height to 'auto' for sidenav and grid */
        @media screen and (max-width: 767px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }

            .row.content {
                height: auto;
            }
        }
    </style>
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
                            <h3>Order</h3>
                        </div>
                        <div class="panel-body">
                            <form id="order-form" class="form" action="${pageContext.request.contextPath}/order" method="post">
                                <input type="number" name="id" value="${order.id}" id="id" hidden>
                                <div class="form-group">
                                    <label for="userId" class="text-info">userId:</label><br>
                                    <input type="text" name="userId" value="${order.userId}" id="userId" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="createdAt" class="text-info">createdAt:</label><br>
                                    <input type="datetime" name="createdAt" value="${order.createdAt}" id="createdAt" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="startAt" class="text-info">startAt:</label><br>
                                    <input type="datetime" name="startAt" value="${order.startAt}" id="startAt" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="endAt" class="text-info">endAt:</label><br>
                                    <input type="datetime" name="endAt" value="${order.endAt}" id="endAt" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="price" class="text-info">price:</label><br>
                                    <input type="number" name="price" value="${order.price}" id="price" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="routeDiscount" class="text-info">routeDiscount:</label><br>
                                    <input type="number" name="routeDiscount" value="${order.routeDiscount}" id="routeDiscount" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="userDiscount" class="text-info">userDiscount:</label><br>
                                    <input type="number" name="userDiscount" value="${order.userDiscount}" id="userDiscount" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="cash" class="text-info">cash:</label><br>
                                    <input type="number" name="cash" value="${order.cash}" id="cash" class="form-control">
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="form-control">Submit</button>
                                    <button type="submit" name="delete" value="${order.id}" class="form-control">Delete</button>
                                </div>
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