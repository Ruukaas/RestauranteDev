<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    link
    <title>Document</title>
</head>
<body>
    <h1>Cadastro do usuário</h1>
    <a href="/">Voltar</a>

    <form method="post" action="/usuario/cadastrar">
        Nome: <input type="text" name="nome"/><br>
        E-mail: <input type="text" name="email"/><br>
        Senha: <input type="password" name="senha"/><br>
        Telefone: <input type="tel" name="telefone"/><br>

        <input type="submit" value="Cadastrar" /><br>

    </form>
</body>
</html>