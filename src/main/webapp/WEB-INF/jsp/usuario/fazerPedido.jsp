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
        <h1>Restaurant Dev - Confirmacao do Pedido</h1>
        <a href="/usuario/inicialUsuario/${clienteID}">Voltar</a>
        <form method="post" action="/usuario/confirmaPedido/${prato.ID}/${clienteID}">
            <h3>${prato.nome}</h3>
            <h4>R$ ${prato.preco}</h3>
                <h4>Forma de Pagamento</h4>
                <select name="formaDePagamento">
                    <c:forEach items="${fp}" var="item">
                        <option value="${item.ID}">${item.nome}</option>
                    </c:forEach>
                </select>
                <h4>Observacao:</h4>
                <input type="text" name="observacao"><br>
                <input type="submit" value="Fazer Pedido" /><br>
        </form>


    </body>

    </html>