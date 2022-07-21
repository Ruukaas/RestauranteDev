<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Restaurant Dev - Todos os Pedidos</h1>
    <a href="/adm/inicialAdm">Voltar</a>    
    <table>
        <tr>
            <th>ID</th>
            <th>Dono do Pedido</th>
            <th>Prato</th>
            <th>Forma de pagamento</th>
            <th>Observacao</th>
        </tr>
        <c:forEach items = "${pedidos}" var="item">
            <tr>
                <td>${item.ID}</td>
                <td>${item.donoDoPedido.nome}</td>
                <td>${item.prato.nome}</td>
                <td>${item.formaDePagamento.nome}</td>
                <td>${item.observacao}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>