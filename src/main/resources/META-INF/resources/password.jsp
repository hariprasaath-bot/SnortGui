<!DOCTYPE html>
<html lang="en" dir = "ltr">
    <head>
        <meta charset = "utf-8">
        <title> password change</title>
        <meta name="viewport" conten="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href = "style1.css">
    </head>
    <body>
       <nav>
        <a href="home"><img class='zoom' src="snortlogo.jpg" align="left" width=150 height=80></img></a>
        <label class="logo">SNORT password change</label>
        <ul>
            <div class="dropdown">
    		<button class="dropbtn">${regname}<i class="fa fa-caret-down"></i></button>
    		<div class="dropdown-content">
      			<a href="logout">logout</a>
      			<a href="changepass">change password</a>
    		</div>
  			</div> 
            <li><a href="rulegen">Generate snort rule</a></li>
            <li><a href='/rulefetch'>View snort rule</a></li>
            <li><a href='/snortstart'>starting snort</a></li>
        </ul>
       </nav>
       <p>${userinfo}</p>
       <form action="newpass" method="POST" style="width: 700px; padding: 10px;margin-left: 500px">
           <label for="currentpass">Current password:</label>
           <input type="password" name="currentpass" id="currentpass"><br><br>
           <label for="newpass">New password:</label>
           <input type="password" name="newpass" id="newpass"><br><br>
           <input type='submit' value='change password' style='margin-left:50px; padding: 10px;'>
       </form>
       <p>${newpassack}</p>
       <p>${wrongpass}</p>
    </body>
</html>