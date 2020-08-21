<%@ page import="com.umb.santiago.galvis.crud.modelo.Empleado" %><%--
    Document   : editar
    Created on : Aug 14, 2020, 1:06:21 PM
    Author     : samic
--%>
<%
    Empleado empleado = (Empleado) request.getAttribute("employeeToEdit");
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
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
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
                <li class="active"><a href="crud-app">Administracion de empleados</a></li>
                <li><a href="crud-app?action=list">Listar</a></li>
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
            <h1>Agregar Empleado</h1> <br>
            <form action="crud-app">
                Tipo de documento (Verificar): <br>
                <select name="docType" class="form-control" id="docType">
                    <optgroup label="Colombia">
                        <option value="CC">Cedula de ciudadania</option>
                        <option value="TI">Tarjeta de identidad</option>
                        <option value="PA">Pasaporte</option>
                    </optgroup>
                    <optgroup label="Otros">
                        <option value="CE">Cedula de extranjeria</option>
                        <option value="PR">Permisos de residencia</option>
                    </optgroup>
                </select> <br>
                Numero de documento: <br>
                <input type="text" name="docNum" class="form-control" value="<%= empleado.getDocNumber()%>"> <br>
                Nombre(s): <br>
                <input type="text" name="name" class="form-control" value="<%=empleado.getName()%>"> <br>
                Apellido: <br>
                <input type="text" name="lastName" class="form-control" value="<%=empleado.getLastName()%>" > <br>
                Departamento: <br>
                <input type="text" name="department" class="form-control" value="<%=empleado.getDepartment()%> " > <br>
                <input type="hidden" name="id" class="form-control" value="<%= empleado.getId()%>"> <br>
                <button type="submit" name="action" class="btn btn-success" value="editExistingOne">Editar Empleado</button>
                <button class="btn btn-danger" onclick="goBack()">Volver atras</button><br>
            </form>
            <br><br>
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

