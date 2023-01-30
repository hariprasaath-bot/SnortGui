<!DOCTYPE html>
<html dir="ltr" lang="en">
<head>
    <meta charset="utf-8">
    <title> Rule view </title>
    <meta content="width=device-width,initial-scale=1.0" name="viewport">
    <link href="style1.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
            
            function edit_row()
			{	
				console.log("GHJK");
				var id = this.getAttribute("id");
				var no = id[5];
				//alert("Edit"+no);
				document.getElementById("iedit"+no).style.display="none";
 				document.getElementById("isave"+no).style.display="block";
 				var rid = document.getElementById("idata"+(no+"0")); 	
				var rid_data = rid.innerHTML;
				//alert("rid"+rid_data);
				rid.innerHTML = "<input type='text' class='textbox' id='rid_text"+no+"' value='"+rid_data+"'>";
	
				var protocol = document.getElementById("idata"+(no+"1")); 	
				var protocol_data = protocol.innerHTML;
				protocol.innerHTML = "<input type='text' class='textbox' id='protocol_text"+no+"' value='"+protocol_data+"'>";
	
				var sip = document.getElementById("idata"+no+"2"); 	
				var sip_data = sip.innerHTML;
				sip.innerHTML = "<input type='text' class='textbox' id='sip_text"+no+"' value='"+sip_data+"'>";
	
				var sport = document.getElementById("idata"+no+"3"); 
				var sport_data = sport.innerHTML;
				sport.innerHTML = "<input type='text' class='textbox' id='sport_text"+no+"' value='"+sport_data+"'>";
		
	
				var dip = document.getElementById("idata"+no+"4");
				var dip_data = dip.innerHTML;
				dip.innerHTML = "<input type='text' class='textbox' id='dip_text"+no+"' value='"+dip_data+"'>";
				
				var dport = document.getElementById("idata"+no+"5"); 	
				var dport_data = dport.innerHTML;
				dport.innerHTML = "<input type='text' class='textbox' id='dport_text"+no+"' value='"+dport_data+"'>";
				
				var msg = document.getElementById("idata"+no+"6"); 	
				var msg_data = msg.innerHTML;
				msg.innerHTML = "<input type='text' class='textbox' id=msg_text"+no+"' value='"+msg_data+"'>";
				
				var npkts = document.getElementById("idata"+no+"7"); 	
				var npkts_data = npkts.innerHTML;
				npkts.innerHTML = "<input type='text' class='textbox'  id='npkts_text"+no+"' value='"+npkts_data+"'>";
				
			}
			function save_row()
			{	
				console.log("GHJK");
				var id = this.getAttribute("id");
				var no = id[5];
				//alert("save"+no);
				
				var rid_val = document.getElementById("rid_text"+no).value;
				document.getElementById("idata"+(no+"0")).innerHTML = rid_val;
	
				var protocol_val = document.getElementById("protocol_text"+no).value;
				document.getElementById("idata"+(no+"1")).innerHTML = protocol_val;
	
				var sip_val = document.getElementById("sip_text"+no).value;
				document.getElementById("idata"+(no+"2")).innerHTML = sip_val;
	
				var sport_val = document.getElementById("sport_text"+no).value;
				document.getElementById("idata"+(no+"3")).innerHTML = sport_val;
	
				var dip_val = document.getElementById("dip_text"+no).value;
				document.getElementById("idata"+(no+"4")).innerHTML = dip_val;
				
				var dport_val = document.getElementById("dport_text"+no).value;
				document.getElementById("idata"+(no+"5")).innerHTML = dport_val;
				
				var msg_val = document.getElementById("msg_text"+no).value;
				document.getElementById("idata"+(no+"6")).innerHTML = msg_val;
				
				var npkts_val = document.getElementById("npkts_text"+no).value;
				document.getElementById("idata"+(no+"7")).innerHTML = npkts_val;
	
				document.getElementById("iedit"+no).style.display="block";
    			document.getElementById("isave"+no).style.display="none";
    			
    			var datav =
    			{   rid: String(rid_val),
     				protocol:String(protocol_val),
     				sip:String(sip_val),
     				sport:String(sport_val),
     				dip:String(dip_val),
     				dport:String(dport_val),
     				msg:String(msg_val),
     				npkts:String(npkts_val)
    			};	
    			
    			
    			$.ajax({
                    url: "/url", 
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    type: "POST",
                    dataType: "json",
                    data: JSON.stringify(datav)
                });
                
			
			}
			function delete_row()
			{	
				console.log("GHJK");
				var id = this.getAttribute("id");
				var no = id[7];
				var rid = document.getElementById("idata"+(no+"0"));
				var rid_data = rid.innerHTML;
				//alert(rid_data);

				document.getElementById("irow"+no+"").outerHTML="";
				var datav2=
				{
				rid:String(rid_data)
				}
				$.ajax({
                    url: "/url2",
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
            tbl.style.cssText = 'margin-left:500px; margin-top:100px;'
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
            td9.style.Align = 'center';
            td9.appendChild(document.createTextNode("Options"));
            td9.style.border = '1px solid black';

            for (let i = 0; i < Rows; i++) {
               const tr = tbl.insertRow();
               tr.setAttribute("class","crow"+i);
    			tr.setAttribute("id","irow"+i);
               for (let j = 0; j < 8; j++)
               {
               		var str = ldata[count];
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
      				else if( i == (Rows-1) && j == 7 )
      				{
       					var newstr1 = newstr.replace("]","");
      				}
      				else
      				{	
      					var newstr1 = newstr;
      				}
                   const td = tr.insertCell();
                   td.setAttribute("class",("cdata"+i)+j);
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
    			but2.setAttribute("class","csave"+i);
     			but2.setAttribute("id","isave"+i);
     			but2.onclick = save_row;
     			but2.appendChild(document.createTextNode("Save"));     
     			tds.appendChild(but2);
     	
     			const tdd = tr.insertCell();     
     			const but3 = document.createElement('button');
    			but3.setAttribute("class","cdelete"+i);
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
<div>
    <nav>
        <a href="home"><img align="left" class='zoom' height=80 src="snortlogo.jpg" width=150></img></a>
        <label class="logo">SNORT RULE MANAGE</label>
        <ul>
            <li><p>${regname}</p></li>
            <li><a href="rulegen">Generate snort rule</a></li>
            <li><a href='/rulefetch'>View snort rule</a></li>
            <li><a href='/snortstart'>starting snort</a></li>
        </ul>
    </nav>
</div>
<div>
    <form action="fetchrule" align="center" method="POST" style="margin-top: 100px">
        <label for="search term">search term</label>
        <input id="search term" name="search term" type="text">
        <input style="margin-left:30px" type="submit">
    </form>
    <div>
        <!--INTENTIONALLY LEFT WITHOUT SOURCE -->
        <img ${functioncall} src style="margin-top=50px;">
    </div>
</div>
<p align='center' class='acknmsg' style="font-size: 20px"> ${acknowledge} </p>
<p align='center' class='alert' style="font-size: 20px"> ${noRule} </p>
<div id="table_place">
    <form action="validate" method="POST"><input class='savebtn' style='font-size: 15px; margin-right:20px'
                                                 type="submit" value="Snort rule validation"></form>
    <form action="saveToFile" method="POST"><input class='savebtn' style='font-size: 15px; margin-right:20px'
                                                   type="submit" value="Save to File"></form>
    <form action="deletefile" method="POST"><input class='savebtn' style='font-size: 15px; margin-right:20px'
                                                   type="submit" value="Delete rule file"></form>
</div>

<!-- <div>
     <button type="button" id="myBtn" onclick="tableCreate()">Show table</button>
</div>-->
</body>
</html>



