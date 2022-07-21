<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Login do administrador</h1>
    <h2>${msgErro}</h2>
    <form method="post" action="/adm/logar">
        E-mail: <input type="text" name="email"/><br>
        Senha: <input type="password" name="senha"/><br>
        <input type="submit" value="Login" /><br>
    </form>
</body>
</html>