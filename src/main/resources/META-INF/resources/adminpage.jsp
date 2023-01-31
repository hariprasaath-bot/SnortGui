<!DOCTYPE html>
<html lang="en" dir = "ltr">
<head>
    <meta charset = "utf-8">
    <title> Homepage</title>
    <meta name="viewport" conten="width=device-width,initial-scale=1.0">
    <link rel="stylesheet" href = "style1.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
            var data = "${users}";
            var Rows= "${rows}";
            //alert(Rows);
            console.log(data)
            var ldata = data.split(",");

            function edit_row()
			{
			    //button.disabled = true;
				var id = this.getAttribute("id");
				var no = id[5];
			    alert("edit"+no);

				document.getElementById("iedit"+no).style.display="none";
 				document.getElementById("isave"+no).style.display="block";

 				var emailref = document.getElementById("idata"+(no+"0"));
				var email = emailref.innerHTML;
				alert(email);
				emailref.innerHTML = "<input type='text' class='textbox' id='email"+no+"' value='"+email+"'>";

				var usernameref = document.getElementById("idata"+(no+"1"));
				var username = usernameref.innerHTML;
				usernameref.innerHTML = "<input type='text' class='textbox' id='username"+no+"' value='"+username+"'>";

				var operatingsystemref = document.getElementById("idata"+no+"2");
				var operatingsystem = operatingsystemref.innerHTML;
				operatingsystemref.innerHTML = "<input type='text' class='textbox' id='operatingsystem"+no+"' value='"+operatingsystem+"'>";

			}

			function save_row()
			{
				var id = this.getAttribute("id");
				var no = id[5];
				//alert("save"+no);

				var email = document.getElementById("email"+no).value;
				document.getElementById("idata"+(no+"0")).innerHTML = email;

                var username = document.getElementById("username"+no).value;
				document.getElementById("idata"+(no+"1")).innerHTML = username;

				var operatingsystem = document.getElementById("operatingsystem"+no).value;
				document.getElementById("idata"+(no+"2")).innerHTML = operatingsystem;

				document.getElementById("iedit"+no).style.display="block";
    			document.getElementById("isave"+no).style.display="none";

    			var user =
    			{   email: String(email),
     				operatingsystem:String(operatingsystem),
     				username:String(username)
    			};


    			$.ajax({
                    url: "/edited",
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    type: "POST",
                    dataType: "json",
                    data: JSON.stringify(user)
                });


			}
			function delete_row()
			{
				var id = this.getAttribute("id");
				var no = id[7];
				var emailref = document.getElementById("idata"+(no+"0"));
				var email = emailref.innerHTML;
				document.getElementById("irow"+no+"").outerHTML="";
				var datav2=
				{
				email:String(email)
				}
				$.ajax({
                    url: "/deleteby",
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    type: "POST",
                    dataType: "json",
                    data: JSON.stringify(datav2)
                });

			}

            function tableCreate() {

            const body = document.body,
            tbl = document.createElement('table');
            tbl.style.width = '300px';
            tbl.style.cssText = 'margin-left:500px; margin-top:100px; padding:30px;'
            tbl.style.border = '2px solid black';
            var count = 0;
            const tr1 = tbl.insertRow();
            const td1 = tr1.insertCell();
            td1.appendChild(document.createTextNode("email"));
            td1.style.border = '1px solid black';
            const td2 = tr1.insertCell();
            td2.appendChild(document.createTextNode("username"));
            td2.style.border = '1px solid black';
            const td3 = tr1.insertCell();
            td3.appendChild(document.createTextNode("operating System"));
            td3.style.border = '1px solid black';
            const td4= tr1.insertCell();
            td4.style.Align = 'center';
            td4.appendChild(document.createTextNode("Options"));
            td4.style.border = '1px solid black';

            for (let i = 0; i < Rows; i++) {
               const tr = tbl.insertRow();
    			tr.setAttribute("id","irow"+i);
               for (let j = 0; j < 3; j++)
               {
               		var str = ldata[count];
               		console.log(str);
               		if(str.includes(" "))
               		{
               			var newstr = str.replace(" ","");
               		}
               		else
               		{
               			var newstr = str;
               		}

      				if( i == 0 && j == 0)
      				{
      					var newstr1 = newstr.replace("[","");
      				}
      				else if( i == (Rows-1) && j == 2 )
      				{
       					var newstr1 = newstr.replace("]","");
      				}
      				else
      				{
      					var newstr1 = newstr;
      				}
                   const td = tr.insertCell();
    			   td.setAttribute("id",("idata"+i)+j);
                   td.appendChild(document.createTextNode(newstr1));
                   td.style.border = '1px solid black';
                   count++;
                }
                 const tde = tr.insertCell();
     			const but1 = document.createElement('button')
     			but1.setAttribute("id","iedit"+i);
    			but1.onclick = edit_row;
   				but1.appendChild(document.createTextNode("Edit"));
    			tde.appendChild(but1);

    			const tds = tr.insertCell();
   				const but2 = document.createElement('button');
     			but2.setAttribute("id","isave"+i);
     			but2.onclick = save_row;
     			but2.appendChild(document.createTextNode("Save"));
     			tds.appendChild(but2);

     			const tdd = tr.insertCell();
     			const but3 = document.createElement('button');
     			but3.setAttribute("id","idelete"+i);
   				but3.onclick = delete_row;
     			but3.appendChild(document.createTextNode("Delete"));
     			tdd.appendChild(but3);
                }

             //body.appendChild(tbl);
             body.insertBefore(tbl, document.getElementById('table_place'));
            }
    </script>
</head>
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
<h3 align="center">USER DATA MANAGEMENT</h3>
<form action="fetchuser" align="center" method="POST" style="margin-top: 100px">
    <label for="search term">search term</label>
    <input id="search term" name="search term" type="text">
    <input style="margin-left:30px" type="submit">
</form>
<form action='viewall' method="POST"> <input type='submit' value='SHOW ALL' style='margin-left:50px;'></form>
<div id="table_place">
    <!--INTENTIONALLY LEFT WITHOUT SOURCE -->
    <img ${functioncall} src style="margin-top=50px;">

</div>
</body>
</html>



