

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

 }

body.appendChild(tbl);
}