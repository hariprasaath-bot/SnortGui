<!DOCTYPE html>
<html lang="en" dir = "ltr">
    <head>
        <meta charset = "utf-8">
        <title> Rule generator </title>
        <meta name="viewport" conten="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href = "style1.css">
    </head>
    <body>
       <nav>
        <a href="home"><img class='zoom' src="snortlogo.jpg" align="left" width=150 height=80></img></a>
        <label class="logo">SNORT RULE GENERATOR</label>
        <ul>
            <li><a href="rulegen">Generate snort rule</a></li>
            <li><a href='rulefetch'>View snort rule</a></li>
            <li><a href='snortstart'>starting snort</a></li>
        </ul>
       </nav> 
       <h4> Just for single application need well-defined UI</h4>
       <form action="/rulepost" method="POST"; style="width: 700px; padding: 10px;margin-left: 500px";>
        <!--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />-->
        <label for="protocol">Enter protocol: </label>
        <input type="text" name="protocol" id="protocol"><br><br>
        <label for="srcip">Enter src ip: </label>
        <input type="text" name="srcip" id="srcip"><br><br>
        <label for="src_port">Enter src port: </label>
        <input type="text" name="src_port" id="src_port"><br><br>
        <label for="dst_ip">Enter dst ip: </label>
        <input type="text" name="dst_ip" id="dst_ip"><br><br>
        <label for="dst_port">Enter dst port: </label>
        <input type="text" name="dst_port" id="dst_port"><br><br>
        <label for="message">Enter alert message: </label>
        <input type="text" name="message" id="message" required><br><br>
        <label for="num_pkts">Enter no. of packets: </label>
        <input type="text" name="num_pkts" id="num_pkts" required><br><br>
        <input type="submit" value="Set Snort rule"><br><br>
    </form>
    <p>${generatedrule}</p>
    <form action="rulefile" method="POST"><input type="submit" value="Add rule to file"></form>
    </body>

</html>