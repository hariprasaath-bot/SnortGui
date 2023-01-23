<!DOCTYPE html>
<html lang="en" dir = "ltr">
    <head>
        <meta charset = "utf-8">
        <title> Rule view </title>
        <meta name="viewport" conten="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href = "style1.css">
    </head>
    <body>
    <div >
       <nav>
        <a href="home"><img class='zoom' src="snortlogo.jpg" align="left" width=150 height=80></img></a>
        <label class="logo">SNORT RULE MANAGE</label>
        <ul>
            <li><a href="rulegen">Generate snort rule</a></li>
            <li><a href='/rulefetch'>View snort rule</a></li>
            <li><a href='/snortstart'>starting snort</a></li>
        </ul>
       </nav>
       </div>
       <div >
       <form align="center" style="margin-top: 100px" method= "POST" action="fetchrule">
         <label for="sid">search term</label>
         <input type="text" id="search term" name="search term">
          <input style="margin-left:30px" type="submit">
       </form>
        <p style="margin-top=5px"> ${rules} </p>
       </div>
       <div>
            <form action="validate" method="POST">
                <input type="submit" value="Snort rule validation">
            </form>
            <p style="font-size: 20px" align='center' class='alert'> ${acknowledge} </p>
       </div>
       <form action="saveToFile">
   			<input type="submit" value="Save to File">
		</form>
    </body>
</html>



