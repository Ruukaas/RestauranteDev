<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Restaurante Dev - Lista de Clientes</h1>
    <a href="/adm/inicialAdm">Voltar</a>    
    <table>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Email</th>
            <th>Telefone</th>
        </tr>
        <c:forEach items = "${clientes}" var="item">
            <tr>
                <td>${item.ID}</td>
                <td>${item.nome}</td>
                <td>${item.email}</td>
                <td>${item.telefone}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>