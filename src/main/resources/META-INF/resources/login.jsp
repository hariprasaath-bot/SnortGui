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
        <label class="logo">SNORT USER LOGIN</label>
       </nav>
	   <form action="userloginpost" method="POST" style="width: 700px; padding: 10px;margin-left: 500px";>
        <label for="username">User name:</label>
        <input type="text" name="username" id="username"><br><br>
        <label for="password">Password:</label>
        <input type="password" name="password" id="password"><br><br>
        <input type='submit' value='login' style='margin-left:50px; padding: 10px;' class='savebtn'>
        <a href='register'>register</a>
       </form>
       <p>${nousername}</p>
       <p>${wrongpassword}</p>
    </body>
</html>