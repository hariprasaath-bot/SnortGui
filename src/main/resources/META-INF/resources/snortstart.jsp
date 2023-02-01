<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Snort starter</title>
    <link href="style1.css" rel="stylesheet">
</head>
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
<div style='margin-top: 200px;'>
    <form action="/start" align="center" method="POST">
        <label for="snortmode">Choose a mode:</label>
        <select id="snortMode" name="snortMode">
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
        <select id="inface" name="inface">
            <option value="">Please select</option>
        </select>

        <input class="fsty" type="submit" value="Start the snort">
    </form>
    <form action="/searchAlert" method="POST" style='margin-top: 200px; margin-left: 700px;'>
        <label for="searchTerm">Search Term:</label>
        <input id="searchTerm" name="var1" type="Text">
        <input type="submit" value="Search alerts">
    </form>
    <div>
        <table class="fixed_header">
            <thead>
            <tr>
                <th>Col 1</th>
                <th>Col 2</th>
                <th>Col 3</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
    <div id="alerts"></div>
    <script>
            var data = "${networkinterface}";
            var Rows = "${rows}";
            var infacelist =  data.split(",");

            var selectElement = document.getElementById('inface');
            for (let i = 0; i < Rows; i++) {
                console.log(infacelist[i]);
                selectElement.add(new Option(infacelist[i]));
            }
		var data1 = "${data}";
		var list1 = data1.split("alertModel")
		var no_of_rows = list1.length;
		for (var i=1;i<no_of_rows;i++)
		{
			var p = document.createElement("p");
			var breakElement = document.createElement("br");
			var textnode = document.createTextNode(i+" "+list1[i])
			p.appendChild(textnode);
			var divElement = document.getElementById("alerts");
			divElement.appendChild(p);
			divElement.appendChild(breakElement);
		}

    </script>
    <p align="center" class='alert'>${AlertMessage}</p>
</div>
</body>
</html>