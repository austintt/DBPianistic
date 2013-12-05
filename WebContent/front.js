		var request; function getInfo(){
			var type = document.getElementById("type").value;
			var url = "http://localhost:8080/DBPianistic/DBServlet?type="+type;
			if(window.ActiveXObject){ request = new ActiveXObject("Microsoft.XMLHTTP"); }
			else if(window.XMLHttpRequest){ request = new XMLHttpRequest(); } request.onreadystatechange = showResult;
			request.open("POST",url,true);
			request.send();
		}
	function showResult(){
		if(request.readyState == 4){
		var response = request.responseXML;
		var Pianos = response.getElementsByTagName("Piano");
		var Piano = Pianos[0];
		document.getElementById("Location1").innerHTML = student.getElementsByTagName("Locaiton")[0].text;
		document.getElementById("ID1").innerHTML = student.getElementsByTagName("ID")[0].text;
		document.getElementById("Condition1").innerHTML = student.getElementsByTagName("Condition")[0].text;
		}
	}
