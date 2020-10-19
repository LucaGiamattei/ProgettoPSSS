/**
 * 
 */

$(document).ready(isDocente());

function isDocente(){
	
	console.log("isDocenteFunc");
	
	var myId = localStorage['myId'];
	
	if(myId == undefined){
		myId = "";
	}
		
	var request = new XMLHttpRequest();
	var params = "requesterId="+myId;
	var url = "../riservate/docente/utenteservlet";
	request.open('GET', url+"?"+params, true);
	
	//Send the proper header information along with the request
	request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	
	request.onreadystatechange = function() {//Call a function when the state changes.
		if(request.readyState == 4 && request.status == 200) { //request.responseXML
			if(request.responseXML.getElementsByTagName("risposta").length > 0) {
				var risultato = request.responseXML.getElementsByTagName("risposta")[0].childNodes[0].nodeValue;
				
				
				if(risultato == "utenteNonAutorizzato"){
					
					$( ".januscon_anchor" ).append('<li><a href="Upgrade.html" class="januscon">Upgrade Docente</a></li>');
					
					return null;
				}
			}
		}
	}
		request.send();
}


function logOut(){
	
	localStorage.removeItem('myId');
	localStorage.removeItem('username');
	localStorage.removeItem('tokenUtente');
	localStorage.removeItem('tokenDocente');
	localStorage.removeItem('nomeRoom');
	localStorage.removeItem('idprog');
	localStorage.removeItem('videoCallStarted');
	window.location.replace("../");
	
	
}
function clearTimeouts(){
	
	var keys1 = Object.keys(timeout1);
	
	for (var i = 0; i < keys1.length; i++) {
	    var val = timeout1[keys1[i]];
	    	clearTimeout(val);
	}
	
	var keys2 = Object.keys(timeout2);
	
	for (var i = 0; i < keys2.length; i++) {
	    var val = timeout2[keys2[i]];
	    	clearTimeout(val);
	}


}
