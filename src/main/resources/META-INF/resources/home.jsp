<!DOCTYPE html>
<html lang="en" dir = "ltr">
    <head>
        <meta charset = "utf-8">
        <title> Homepage</title>
        <meta name="viewport" conten="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href = "style1.css">
    </head>
    <body>
       <nav>
        <a href="home"><img class='zoom' src="snortlogo.jpg" align="left" width=150 height=80></img></a>
        <label class="logo">SNORT Homepage</label>
        <ul>
            <li><p>${regname}</p></li>
            <li><a href="rulegen">Generate snort rule</a></li>
            <li><a href='/rulefetch'>View snort rule</a></li>
            <li><a href='/snortstart'>starting snort</a></li>
        </ul>
       </nav>
       <form action="home" method="POST" style="width: 400px; padding: 10px;margin-left: 150px";>
       	<label for="name">Enter your name: </label>
        <input type="text" name="name" id="name"><br><br>
       	<input type="submit" value="submit">
       </form>
    </body>
</html>



