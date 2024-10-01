<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consumo de APIs</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Estilos generales */
        body {
            font-family: 'Montserrat', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #eef2f7;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: white;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            padding: 40px;
            max-width: 600px;
            width: 100%;
            text-align: center;
        }

        /* Encabezado */
        .header {
            background-color: #ff7f50;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 30px;
            color: white;
            font-size: 1.8em;
            text-transform: uppercase;
            font-weight: bold;
            letter-spacing: 2px;
        }

        /* Botones de selección */
        .api-button {
            display: block;
            background-color: #6c63ff;
            color: white;
            padding: 15px;
            border-radius: 8px;
            text-decoration: none;
            font-size: 1.2em;
            margin: 15px 0;
            transition: background-color 0.3s, transform 0.3s;
        }

        .api-button:hover {
            background-color: #574b90;
            transform: translateY(-5px);
        }

        /* Pie de página */
        .footer {
            margin-top: 30px;
            font-size: 0.9em;
            color: #999;
        }

        /* Efectos adicionales */
        .api-icon {
            display: block;
            font-size: 3em;
            margin-bottom: 10px;
            color: #ff7f50;
        }

    </style>
</head>
<body>

<div class="container">
    <div class="header">Consumo de APIs</div>
    <p class="lead">Seleccione una API para comenzar:</p>

    <a href="http://localhost:8080/ImplementacionAPI_war_exploded/rickandmorty" class="api-button">
        <span class="api-icon">🚀</span>
        API de Rick and Morty
    </a>

    <a href="http://localhost:8080/ImplementacionAPI_war_exploded/weather" class="api-button">
        <span class="api-icon">☁️</span>
        API Clima
    </a>

    <a href="http://localhost:8080/ImplementacionAPI_war_exploded/trm" class="api-button">
        <span class="api-icon">💰</span>
        API TRM de Colombia
    </a>

    <div class="footer">
        &copy; Sebastian Ordoñez Giraldo 2024. Todos los derechos reservados.
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
