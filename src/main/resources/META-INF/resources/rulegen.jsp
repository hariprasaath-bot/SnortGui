<!DOCTYPE html>
<html lang="en" dir = "ltr">
    <head>
        <meta charset = "utf-8">
        <title> Rule generator </title>
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href = "style1.css">
    </head>
    <body>
       <nav>
        <a href="home"><img class='zoom' src="snortlogo.jpg" align="left" width=150 height=80></img></a>
        <label class="logo">SNORT RULE GENERATOR</label>
        <ul>
        	<div class="dropdown">
    		<button class="dropbtn">${regname}<i class="fa fa-caret-down"></i></button>
    		<div class="dropdown-content">
      			<a href="logout">logout</a>
    		</div>
  			</div> 
            <li><a href="rulegen">Generate snort rule</a></li>
            <li><a href='rulefetch'>View snort rule</a></li>
            <li><a href='snortstart'>starting snort</a></li>
        </ul>
       </nav> 
       <h4> Just for single application need well-defined UI</h4>
       <form action="/rulepost" method="POST"; style="padding: 10px;margin-left: 500px";>
        <!--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />-->
        <label for="protocol">Enter protocol: </label>
        <input type="text" name="protocol" id="protocol">
        <label  class='rightlabel' for="srcip">Enter src ip: </label>
        <input type="text" name="srcip" id="srcip"><br><br>
        <label for="src_port">Enter src port: </label>
        <input type="text" name="src_port" id="src_port">
        <label class='rightlabel' for="dst_ip">Enter dst ip: </label>
        <input type="text" name="dst_ip" id="dst_ip"><br><br>
        <label for="dst_port">Enter dst port: </label>
        <input type="text" name="dst_port" id="dst_port">
        <label class='rightlabel' for="message">Enter alert message: </label>
        <input type="text" name="message" id="message" required><br><br>
        <label style='margin-top:20px' for="num_pkts">Enter no. of packets: </label>
        <input type="text" name="num_pkts" id="num_pkts" required>
        <input style='margin-left:20px' class='rigthlabel' type="submit" value="Set Snort rule"><br><br>
    </form>
    <form action='repoSave' method="POST"> <input type='submit' value='Save to Database' style='margin-left:50px;' class='savebtn'></form>
    <form action="rulefile" method="POST"><input type="submit" value="Add rule to file"  style='margin-left:50px; margin-right:50px' class='savebtn'></form>
    <p class='acknmsg'>${generatedrule}</p>
    <p class='acknmsg'>${fileackn}</p>
    <p class='acknmsg'>${repoAckn}</p>
    </body>

</html>