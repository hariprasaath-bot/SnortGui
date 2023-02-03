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
        <a href="login"><img class='zoom' src="snortlogo.jpg" align="left" width=150 height=80></img></a>
        <label class="logo">SNORT USER REGISTRATION</label>
       </nav>
	   <form action="userregpost" method="POST"; style="width: 700px; padding: 10px;margin-left: 500px";>
        <label for="email">E-Mail:</label>
        <input type="text" name="email" id="email"><br><br>
        <label for="username">User name:</label>
        <input type="text" name="username" id="username"><br><br>
        <label for="password">Password:</label>
        <input type="password" name="password" id="password"><br><br>
        <input type='submit' value='register' style='margin-left:50px; padding: 10px;' class='savebtn'>
        <p>${userregrepoAckn}</p>
        <a href='login'>return to login page</a>
       </form>
    </body>
</html>