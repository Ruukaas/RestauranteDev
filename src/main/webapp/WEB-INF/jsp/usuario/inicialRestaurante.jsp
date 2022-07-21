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
    <h1>Restaurante Dev - Faca seu pedido</h1>
    <a href="/">Voltar para tela inicial</a>
    <h3>Bem vindo, ${cliente}</h3>
    <h4>${msg}</h4>
    <a href="/usuario/pedidos/${clienteID}">Listar todos os pedidos</a>
    <table>
        <tr>
            <th>Nome</th>
            <th>Preco</th>
            <th>Descricao</th>

        </tr>
        <c:forEach items = "${pratos}" var="item">
            <tr>
                <td>${item.nome}</td>
                <td>${item.preco}</td>
                <td>${item.descricao}</td>
                <td><a href="/usuario/fazerPedido/${item.ID}/${clienteID}">Fazer Pedido</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>