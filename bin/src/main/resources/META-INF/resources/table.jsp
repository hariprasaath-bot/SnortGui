
<head>
	<title>Table View</title>
</head>
<body></body>
 


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
{	alert("edit");
	console.log("GHJK");
	var id = this.getAttribute("id");
	var no = id[5];
	document.getElementById("iedit"+no).style.display="none";
 	document.getElementById("isave"+no).style.display="block";
 	
 	
 	var rid = document.getElementById("idata"+(no+"0")); 	
	var rid_data = rid.innerHTML;
	alert(rid_data);
	rid.innerHTML = "<input type = 'text' id = 'rid_text"+no+"' value = '"+rid_data+"'>"; 
	
	var protocol = document.getElementById("idata"+(no+"1")); 	
	var protocol_data = protocol.innerHTML;
	protocol.innerHTML = "<input type = 'text' id = 'protocol_text"+no+"' value = '"+protocol_data+"'>";
	
	var sip = document.getElementById("idata"+no+"2"); 	
	var sip_data = sip.innerHTML;
	sip.innerHTML = "<input type = 'text' id = 'sip_text"+no+"' value = '"+sip_data+"'>";
	
	var sport = document.getElementById("idata"+no+"3"); 
	var sport_data = sport.innerHTML;
	sport.innerHTML = "<input type = 'text' id = 'sport_text"+no+"' value = '"+sport_data+"'>";	
	
	
	var dip = document.getElementById("idata"+no+"4"); 	
	var dip_data = dip.innerHTML;
	dip.innerHTML = "<input type = 'text' id = 'dip_text"+no+"' value = '"+dip_data+"'>";
	
	var dport = document.getElementById("idata"+no+"5"); 	
	var dport_data = dport.innerHTML;
	dport.innerHTML = "<input type = 'text' id = 'dport_text"+no+"' value = '"+dport_data+"'>";
	
	var msg = document.getElementById("idata"+no+"6"); 	
	var msg_data = msg.innerHTML;
	msg.innerHTML = "<input type = 'text' id = 'msg_text"+no+"' value = '"+msg_data+"'>";
	
	var npkts = document.getElementById("idata"+no+"7"); 	
	var npkts_data = npkts.innerHTML;
	npkts.innerHTML = "<input type = 'text' id = 'npkts_text"+no+"' value = '"+npkts_data+"'>";
	
	}
	
	function save_row()
	{
	var id = this.getAttribute("id");
	var no = id[5];
	alert(no);
	}
	function delete_row()
	{
	var id = this.getAttribute("id");
	var no = id[7];
	alert(no);
	}
function tableCreate() {

  const body = document.body,
        tbl = document.createElement('table');
  tbl.style.width = '100px';
  tbl.style.border = '1px solid black';
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
     const tde = tr.insertCell();
     const but1 = document.createElement('button');
     but1.onclick = edit_row;
     but1.appendChild(document.createTextNode("Edit"))
     tde.appendChild(but1);
     
     const tds = tr.insertCell();
     const but2 = document.createElement('button');
     but2.onclick = save_row;
     but2.appendChild(document.createTextNode("Save"))
     tds.appendChild(but2);
     
     const tdd = tr.insertCell();
     const but3 = document.createElement('button');
     but3.onclick = delete_row;
     but3.appendChild(document.createTextNode("Delete"))
     tdd.appendChild(but3);
     
      }
    
  
  body.appendChild(tbl);
}

tableCreate();

</script>