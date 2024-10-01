<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultar TRM Colombia</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Estilos personalizados */
        body {
            background-color: #eef2f7;
            font-family: 'Montserrat', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            text-align: center;
        }

        h1 {
            font-size: 2em;
            color: #ff7f50;
            margin-bottom: 20px;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        label {
            font-weight: bold;
            color: #333;
        }

        .btn-primary {
            background-color: #6c63ff;
            border-color: #6c63ff;
            margin-top: 15px;
        }

        .btn-primary:hover {
            background-color: #574b90;
        }

        .result-box {
            background-color: #f8f9fa;
            border-radius: 8px;
            padding: 15px;
            margin-top: 20px;
        }

        .footer {
            margin-top: 20px;
            font-size: 0.8em;
            color: #999;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Consultar TRM de Colombia</h1>
    <form method="get" action="trm">
        <div class="form-group">
            <label for="date">Fecha (YYYY-MM-DD):</label>
            <input type="date" class="form-control" id="date" name="date" required>
        </div>
        <button type="submit" class="btn btn-primary btn-block">Consultar TRM</button>
    </form>

    <hr>

    <%
        if (request.getAttribute("trm") != null) {
            String trm = (String) request.getAttribute("trm");
            String fecha = (String) request.getAttribute("fecha");
    %>
    <div class="result-box">
        <h2>Resultado de la TRM:</h2>
        <p><strong>Fecha:</strong> <%= fecha %></p>
        <p><strong>TRM:</strong> <%= trm %> COP</p>
    </div>
    <%
    } else if (request.getAttribute("error") != null) {
    %>
    <div class="alert alert-danger" role="alert">
        Error: <%= request.getAttribute("error") %>
    </div>
    <%
        }
    %>
</div>

<div class="footer">
    &copy; 2024 Consultar TRM de Colombia. Todos los derechos reservados.
</div>

<!-- Scripts de Bootstrap -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
