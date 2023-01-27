<!DOCTYPE html>
<html lang="en" dir = "ltr">
    <head>
        <meta charset = "utf-8">
        <title> Rule view </title>
        <meta name="viewport" conten="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" href = "style1.css">
        <script>
            var data = "${rules}";
            var Rows= "${rows}";
            //alert(Rows);
            var ldata = data.split(",");
                                   /*alert(typeof(data));
                                   alert(data.length);
                                   alert(typeof(ldata));
                                   alert(ldata.length);*/
                                   //document.getElementById("demo").innerHTML = data;
            function tableCreate() {

            const body = document.body,
            tbl = document.createElement('table');
            tbl.style.width = '200px';
            tbl.style.cssText = 'margin-left: auto;margin-right: auto;'
            tbl.style.border = '2px solid black';
            var count = 0;
            const tr1 = tbl.insertRow();
            const td1 = tr1.insertCell();
            td1.appendChild(document.createTextNode("Sid"));
            td1.style.border = '1px solid black';
            const td2 = tr1.insertCell();
            td2.appendChild(document.createTextNode("Protocol"));
            td2.style.border = '1px solid black';
            const td3 = tr1.insertCell();
            td3.appendChild(document.createTextNode("Source IP"));
            td3.style.border = '1px solid black';
            const td4 = tr1.insertCell();
            td4.appendChild(document.createTextNode("Source Port"));
            td4.style.border = '1px solid black';
            const td5 = tr1.insertCell();
            td5.appendChild(document.createTextNode("Dest IP"));
            td5.style.border = '1px solid black';
            const td6 = tr1.insertCell();
            td6.appendChild(document.createTextNode("Dest Port"));
            td6.style.border = '1px solid black';
            const td7 = tr1.insertCell();
            td7.appendChild(document.createTextNode("Message"));
            td7.style.border = '1px solid black';
            const td8 = tr1.insertCell();
            td8.appendChild(document.createTextNode("Num_pkts"));
            td8.style.border = '1px solid black';
            const td9 = tr1.insertCell();
            td9.appendChild(document.createTextNode("Edit"));
            td9.style.border = '1px solid black';
            const td10 = tr1.insertCell();
            td10.appendChild(document.createTextNode("Delete"));
            td10.style.border = '1px solid black';

            for (let i = 0; i < Rows; i++) {
               const tr = tbl.insertRow();
               for (let j = 0; j < 8; j++)
               {
                   const td = tr.insertCell();
                   td.appendChild(document.createTextNode(ldata[count]));
                   td.style.border = '1px solid black';
                   count++;

                    }
                }
             body.appendChild(tbl);
            }
        </script>
    </head>
    <body>
    <div >
       <nav>
        <a href="home"><img class='zoom' src="snortlogo.jpg" align="left" width=150 height=80></img></a>
        <label class="logo">SNORT RULE MANAGE</label>
        <ul>
        	<li><p>${regname}</p></li>
            <li><a href="rulegen">Generate snort rule</a></li>
            <li><a href='/rulefetch'>View snort rule</a></li>
            <li><a href='/snortstart'>starting snort</a></li>
        </ul>
       </nav>
       </div>
       <div >
       <form  align="center" style="margin-top: 100px" method= "POST" action="fetchrule">
         <label for="sid">search term</label>
         <input type="text" id="search term" name="search term">
          <input style="margin-left:30px" type="submit">
       </form>
       </div>
       <div>
            <form action="validate" method="POST">
                <input type="submit" value="Snort rule validation">
            </form>
            <p style="font-size: 20px" align='center' class='alert'> ${acknowledge} </p>
       </div>
       <div>
            <form action="saveToFile">
   			    <input type="submit" value="Save to File">
		    </form>
		</div>
		<img src ${functioncall}>
	   <!-- <div>
	        <button type="button" id="myBtn" onclick="tableCreate()">Show table</button>
	    </div>-->
</body>
</html>



