<%@ page import="org.json.simple.JSONObject" %>
<!-- File: rickandmorty.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rick and Morty Character Information</title>
</head>
<body>
<h1>Search for a Rick and Morty Character</h1>
<form method="get" action="rickandmorty">
    <label for="characterName">Character Name:</label>
    <input type="text" id="characterName" name="characterName" required>
    <button type="submit">Search</button>
</form>

<hr>

<%
    if (request.getAttribute("character") != null) {
        JSONObject character = (JSONObject) request.getAttribute("character");

        String name = (String) character.get("name");
        String status = (String) character.get("status");
        String species = (String) character.get("species");
        String image = (String) character.get("image");
%>
<h2>Character Details:</h2>
<table border="1">
    <tr><th>Name</th><td><%= name %></td></tr>
    <tr><th>Status</th><td><%= status %></td></tr>
    <tr><th>Species</th><td><%= species %></td></tr>
    <tr><th>Image</th><td><img src="<%= image %>" alt="<%= name %>"/></td></tr>
</table>
<%
} else if (request.getAttribute("error") != null) {
%>
<p>Error: <%= request.getAttribute("error") %></p>
<%
    }
%>

</body>
</html>
