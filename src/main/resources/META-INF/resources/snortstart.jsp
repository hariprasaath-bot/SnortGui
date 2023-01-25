<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Snort starter</title>
    <link href="style1.css" rel="stylesheet">
</head>
<body>
<div>
    <div>
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
</div>
<div>
    <form  align="center" action="/start" method="POST">
        <label for="snortmode">Choose a mode:</label>
        <select class="fsty" id="snortMode" name="snortMode">
            <option value="logging">logging</option>
            <option value="sniffing">sniffing</option>
            <option value="IDS">IDS</option>
        </select>
        <label for="logmode">Logging mode</label>
        <select class="fsty" id="logMode" name="logMode">
            <option value="alerttoweb">alert to web</option>
            <option value="alerttoconsole">alert to console</option>
        </select>
        <label for="inface">Enter interface to monitor</label>
        <input class="fsty" type="Text" id="inface" name="inface">
        <input class="fsty" type="submit" value="Start the snort">
    </form>
    <!--<form align="center" action="/start" method="POST">
            <label for="snortmode">Choose a mode:</label>
            <input type="Text" class="fsty" id="snortMode" name="snortMode">

            <label for="logmode">Logging mode</label>
            <input type="Text" class="fsty" id="logMode" name="logMode">

            <label for="inface">Enter interface to monitor</label>
            <input class="fsty" type="Text" id="inface" name="inface">
            <input class="fsty" type="submit" value="Start the snort">
        </form>-->
    <p align="center" class='alert'>${AlertMessage}</p>
</div>
</body>
</html>