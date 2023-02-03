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
        <input type='submit' value='login' style='margin-right:50px; margin-left:50px; padding: 10px;'>
        <a style='margin-left:50px;' href='register'>register</a>
       </form><br><br>
       <h4 style="padding: 10px;margin-left: 500px">Admin login</h4><br><br>
       <form action="adminlogin" method="POST" style="width: 700px; padding: 10px;margin-left: 500px";>
           <label for="adminname">User name:</label>
           <input type="text" name="adminname" id="adminname"><br><br>
           <label for="password">Password:</label>
           <input type="password" name="password" id="adminpassword"><br><br>
           <input type='submit' value='login' style='margin-left:50px; padding: 10px;'>
       </form>
       <p class="alert">${nousername}</p>
       <p class="alert">${wrongpassword}</p>
    </body>
</html>