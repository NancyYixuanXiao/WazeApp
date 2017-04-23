// declare all global markers
var startMarker; // marker for the start location
var endMarker; // marker for the end location
var markers = []; // array of all the markers
var Coordinates = []; // array of coordinates to draw the polyline
var flightPaths = []; // array of the google map polylines
var distances = [];
var markerCluster; // google map map cluster containsing all markers
var markersShowed = true; // boolean value indicating whether the amrkers are visible on map
var colors = ['#4a9be8', '#ff4f4f', '#d81c83', '#f24fd7', '#4c40e8', '#9140e8', 
  	          '#40e8e8', '#db9004', '#c340e8', '#40e8bb'];// list of colors to draw polylines

//put a marker with icon on the start or end location if the user change the content in the 
//corresponding text box
function markerAnimate(elem) {
	var elem_id = elem.id;
	var marker_id = parseInt(document.getElementById(elem_id).value);
 map.setZoom(9);
 map.panTo(markers[marker_id].getPosition());
 var image = {
     url: 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/Flag_icon_red_4.svg/32px-Flag_icon_red_4.svg.png',
		size: new google.maps.Size(35, 36),
		origin: new google.maps.Point(0, 0),
		anchor: new google.maps.Point(0, 32)
		};
		if (elem_id == "start") {
		if (typeof(startMarker) == 'object') {
			startMarker.setMap(null);
		}
		startMarker = new google.maps.Marker({
		    position: markers[marker_id].getPosition(),
		    map: map,
		    label: 'S',
		        icon: image
		      });
		} else {
			if (typeof(endMarker) == 'object') {
			endMarker.setMap(null);
		}
		endMarker = new google.maps.Marker({
		    position: markers[marker_id].getPosition(),
		    map: map,
		    label: 'E',
         icon: image
       });
     }
}

//Show or hide the markers on map if the user click the 'markers' button
function showOrHideMarkers() {
	if (markersShowed) {
		markerCluster.setMap(null);
		markersShowed = false;
	} else {
		markerCluster.setMap(map);
		markersShowed = true;
	}
}

// send form data to WazeServlet
function sendFormToServlet() {
	Coordinates = [];
	removeLine();
	flightPaths = [];
	var url = "WazeServlet?startNodeId=" + document.getElementById("start").value
	           + "&endNodeId=" + document.getElementById("end").value
	           + "&numPaths=" + document.getElementById("k").value;
	var wazeRequest = window.ActiveXObject ? new ActiveXObject('Microsoft.XMLHTTP') :
        new XMLHttpRequest;
	try{  
		wazeRequest.onreadystatechange = function() {
			if(wazeRequest.readyState == 4) { 
				var pathsString = wazeRequest.responseText;
				var paths = pathsString.trim().split(",");
				for (var i = 0; i < paths.length; i++) {
					if (paths[i] == null || paths[i].length == 0) {
						continue;
					}
					var paths1 = paths[i].split("/");
					Coordinates.push([]);
					findLatLng(paths1[1]);	
					distances.push(parseFloat(paths1[0]));
				}
			}
		};  
		wazeRequest.open("GET", url, false);  
		wazeRequest.send();
		addLine();
	}
	catch(e) {
		alert("Unable to connect to server");
	} 
}

// given the node_id, return a list of adjacent nodes by search in edges table in database
function findAdjNodes(node_id) {
	Coordinates = [];
	removeLine();
	flightPaths = [];
	Coordinates.push([]);
	var url = "searchEdges.jsp?id=" + node_id;
	var requestEdge = window.ActiveXObject ? new ActiveXObject('Microsoft.XMLHTTP') :
        new XMLHttpRequest;
	try{  
		requestEdge.onreadystatechange = function() {
			if(requestEdge.readyState == 4) { 
				var nodes = requestEdge.responseText;
				alert(nodes);
				findLatLng(nodes);
			}
		};  
		requestEdge.open("GET", url, false);  
		requestEdge.send();
	}
	catch(e) {
		alert("Unable to connect to server");
	} 
  	addLine();
}

//given a list nodes, add their latitudes and longitudes to Coordinates by search the nodes table
function findLatLng(nodes) {
	var nodeslist = nodes.trim().split(" "); 
	for (var i = 0; i < nodeslist.length; i++) {
		if (nodeslist[i] == null || nodeslist[i].length == 0) {
			continue;
		}
		var url = "searchLatLng.jsp?node_id=" + nodeslist[i];
		var requestGeo = window.ActiveXObject ? 
				new ActiveXObject('Microsoft.XMLHTTP') : new XMLHttpRequest;
		try{  
			requestGeo.onreadystatechange = function() {
				if (requestGeo.readyState == 4) {
					var latlng = requestGeo.responseText.trim().split(" ");
					Coordinates[Coordinates.length - 1].push({lat: parseFloat(latlng[0]), 
						             lng: parseFloat(latlng[1])});
				}
			};
			requestGeo.open("GET", url, false);  
			requestGeo.send();
		}
		catch(e) {
			alert("Unable to connect to server");
		}
	}
}

// draw lines in flightPaths on map
function addLine() {
	for (var i = 0; i < Coordinates.length; i++) {
		var flightPath = new google.maps.Polyline({
	    	path: Coordinates[i],
	        strokeColor: colors[i],
	        strokeOpacity: 0.6,
	        strokeWeight: 5,
	        draggable: true
	  	    });
		flightPath.setMap(map);
  	    flightPaths.push(flightPath);
	}
	Array.prototype.forEach.call(flightPaths, function(flightPath, index) {
		google.maps.event.addListener(flightPath, 'mouseover', function(e) {
			document.getElementById('popup').innerHTML = "No." + (index + 1) + " shortest path \n length = " + distances[index].toFixed(4);
			document.getElementById('popup').style.display = 'block';
			for (var i = 0; i < flightPaths.length; i++) {
				if (i == index) {
					flightPaths[i].setVisible(true);
				} else {
					flightPaths[i].setVisible(false);
				}
			}
		    });
		google.maps.event.addListener(flightPath, 'mouseout', function(e) {
			document.getElementById('popup').style.display = 'none';
			for (var i = 0; i < flightPaths.length; i++) {
				flightPaths[i].setVisible(true);
			}
		    });
		});
}

//when need to draw a new line, remove the line currently on map
function removeLine() {
	Array.prototype.forEach.call(flightPaths, function(flightPath) {
		flightPath.setMap(null);
	});
}
