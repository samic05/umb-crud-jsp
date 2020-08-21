<%@ page import="com.umb.santiago.galvis.crud.modelo.Empleado" %>
<%@ page import="com.umb.santiago.galvis.crud.services.EmpleadosService" %>
<%@ page import="org.apache.commons.lang3.ObjectUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.StringJoiner" %>
<%--
    Document   : listar
    Created on : Aug 14, 2020, 1:06:34 PM
    Author     : samic
--%>
<%! public static final String SPACE = " ";

    EmpleadosService service = new EmpleadosService();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Umb Crud application</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <style>
        /* Remove the navbar's default margin-bottom and rounded borders */
        .navbar {
            margin-bottom: 0;
            border-radius: 0;
        }

        /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
        .row.content {height: 450px}

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
            .row.content {height:auto;}
        }
    </style>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="crud-app">UMB Employees System</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="crud-app">Administracion de empleados</a></li>
                <li class="active"><a href="crud-app?action=list">Listar</a></li>
                <li><a href="crud-app?action=add">Añadir</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="vistas/salida.jsp"><span class="glyphicon glyphicon-log-out"></span> Cerrar</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">
        </div>
        <div class="col-sm-8 text-left">
            <h1>Listar Empleados</h1> <br>
            <a href="crud-app?action=add" class="btn btn-warning">Agregar</a>
            <div>
                <%
                    Object attribute =  request.getAttribute("ErrorFindingEmployee");
                    Boolean showError = ObjectUtils.isNotEmpty(attribute);
                %>
                <%= (showError) ? " <a><h2 class=\"text-danger bg-danger\">Error encontrando usuario</h2></a>": ""%>
            </div>
            <hr>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Tipo de documento</th>
                    <th>Numero de documento</th>
                    <th>Nombre(s) y Apellidos</th>
                    <th>Departamento asignado</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <%
                    List<Empleado> empleados = service.getEmpleados();
                    for(Empleado empleado: empleados){
                %>
                <tbody>
                <tr>
                    <td><%= empleado.getDocType()%></td>
                    <td><%= empleado.getDocNumber()%></td>
                    <td><%= new StringJoiner(SPACE).add(empleado.getName()).add(empleado.getLastName())%></td>
                    <td><%= empleado.getDepartment()%></td>
                    <td>
                        <a class="btn btn-warning" href="crud-app?action=editView&id=<%= empleado.getId() %>">Editar</a>
                        <a class="btn btn-danger" href="crud-app?action=delete&id=<%= empleado.getId() %>">Remover</a>
                    </td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
        <div class="col-sm-2 sidenav">
        </div>
    </div>
</div>
</body>
<footer class="container-fluid text-center">
    <p>Creado por Santiago Galvis</p>
</footer>
</html>


