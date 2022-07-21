<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Cadastro de Prato</h1>

    <a href="/adm/inicialAdm">Voltar</a>

    <form method="post" action="/adm/cadastrarPrato">
        Nome: <input type="text" name="nome"/><br>
        Preco: <input type="number" name="preco" step="0.01"/><br>
        Descricao: <input type="text" name="descricao"/><br>
        <input type="submit" value="Cadastrar" /><br>

    </form>
</body>
</html>