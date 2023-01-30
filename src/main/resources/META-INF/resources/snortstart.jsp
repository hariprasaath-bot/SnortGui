<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Snort starter</title>
    <link href="style1.css" rel="stylesheet">
</head>
<body>
<nav>
    <a href="home"><img align="left" class='zoom' height=80 src="snortlogo.jpg" width=150></img></a>
    <label class="logo">SNORT Homepage</label>
    <ul>
        <li><p>${regname}</p></li>
        <li><a href="rulegen">Generate snort rule</a></li>
        <li><a href='/rulefetch'>View snort rule</a></li>
        <li><a href='/snortstart'>starting snort</a></li>
    </ul>
</nav>

<div align='center' style="margin-top: 200px;">
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
    <p align="center" class='alert'>${AlertMessage}</p>
</div>
</body>
</html>