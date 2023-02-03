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
    </body>
</html>



