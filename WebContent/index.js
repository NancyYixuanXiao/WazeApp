// send form data to WazeServlet
function sendFormToServlet() {
	Coordinates = [];
	removeLine();
	flightPaths = [];
	var url = "WazeServlet?startNodeId=" + document.getElementById("start").value
	           + "&endNodeId=" + document.getElementById("end").value
	           + "&numPaths=" + document.getElementById("k").value;
	var requestEdge = window.ActiveXObject ? new ActiveXObject('Microsoft.XMLHTTP') :
        new XMLHttpRequest;
	try{  
		requestEdge.onreadystatechange = function() {
			if(requestEdge.readyState == 4) { 
				var pathsString = requestEdge.responseText;
				var paths = pathsString.trim().split(",");
				for (var i = 0; i < paths.length; i++) {
					Coordinates.push([]);
					findNodePairs(paths[i]);	
					alert("Path" + (i + 1) + " is ready.");
				}
			}
		};  
		requestEdge.open("GET", url, false);  
		requestEdge.send();
		addLine();
	}
	catch(e) {
		alert("Unable to connect to server");
	} 
}
   var startMarker;
	   var endMarker;
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
var markers = [];
var Coordinates = [];
var flightPaths = [];
var markerCluster;
var markersShowed = true;
var colors = ['#4a9be8', '#ff4f4f', '#d81c83', '#f24fd7', '#4c40e8', '#9140e8', 
  	          '#40e8e8', '#db9004', '#c340e8', '#40e8bb'];
function showOrHideMarkers() {
	if (markersShowed) {
		markerCluster.setMap(null);
		markersShowed = false;
	} else {
		markerCluster.setMap(map);
		markersShowed = true;
	}
}
// given the node_id, find adjacency edges by search in edges table in database
function findAdjEdges(node_id) {
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
				var val = requestEdge.responseText;
				findNodePairs(val);
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
// giving a list of edge id, find the start and end nodes of these edges
function findNodePairs(val) {
	var edges = val.trim().split(" "); 
	for (var i = 0; i < edges.length; i++) {
		var url = "searchNodes.jsp?edge_id=" + edges[i];
		var requestNode = window.ActiveXObject ? new ActiveXObject('Microsoft.XMLHTTP') :
                  new XMLHttpRequest;
		try{  
			requestNode.onreadystatechange = function() {
				if (requestNode.readyState == 4) {
					var nodePair = requestNode.responseText;
					findLatLng(nodePair);
				}
			};
			requestNode.open("GET", url, false);  
			requestNode.send();
		}
		catch(e) {
			alert("Unable to connect to server");
		}
	}
}
//given a pair of nodes, find the latitude and longitude by search the nodes table
function findLatLng(nodePair) {
	var nodes = nodePair.trim().split(" "); 
	for (var i = 0; i < nodes.length; i++) {
		var url = "searchLatLng.jsp?node_id=" + nodes[i];
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
function removeLine() {
	Array.prototype.forEach.call(flightPaths, function(flightPath) {
		flightPath.setMap(null);
	});
}
function addLine() {
	for (var i = 0; i < Coordinates.length; i++) {
		var flightPath = new google.maps.Polyline({
	    	path: Coordinates[i],
	        strokeColor: colors[i],
	        strokeOpacity: 0.8,
	        strokeWeight: 5,
	        draggable: true
	  	    });
		flightPath.setMap(map);
  	    flightPaths.push(flightPath);
	}
	Array.prototype.forEach.call(flightPaths, function(flightPath, index) {
		google.maps.event.addListener(flightPath, 'mouseover', function(e) {
			document.getElementById('popup').innerHTML = "No." + (index + 1) + " shortest path.";
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
