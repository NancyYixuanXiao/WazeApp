# WazeApp

## What is it

 WazeApp is an independent side project.
 
It is an interactive web application which displays California road network and enables k shortest paths between two locations.
 
Demo video link: https://drive.google.com/open?id=0B80WQs3npy5tTDAxck9sOFM5NDA.
 
## Features and Functionalites

1. Shows or hides all location markers (marker clusters) by clicking the button of "markers".

2. Shows an info-window and adjacency edges of a location when clicking on this location marker.

3. By typing in location number in the start and end text boxes and the number of shortest paths k, it computes k shortest paths between these two locations and draws the routes on the map.

4. Reference the implementation of fibonacci heap from Stanford - Keith Schwarz. Time and space complexity to compute the shortest path is VlogV + E (V: number of nodes, E: number of edges).


## Technologies userd

Googlemap api, java, jsp, java servlet, jQuery, html, css, javascript, Mysql, Tomcat

 
*cal.cnode.txt* provides nodes (locations) with altitude, longitude and node number. *cal.cedge.txt* provides edges (roads) with starting node number, end node number and distance. I load these two files to Mysql database as nodes and edges tables, and query database to get data of nodes and edges.

In the src folder, the Demo.java runs the program locally without frontend. If the tomcat server and Mysql are setted up, then the index.html is the main page of this project.


