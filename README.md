# WazeApp

 WazeApp is an independent side project.
 
 It is a web map application which can display California road network and query k shortest paths between two location.
 
*cal.cnode.txt* provides nodes (locations) with altitude, longitude and node number. *cal.cedge.txt* provides edges (roads) with starting node number, end node number and distance. I load these two files to Mysql database as nodes and edges tables, and query database to get data of nodes and edges.

In the src folder, the Demo.java runs the program locally without frontend. If the tomcat server and Mysql are setted up, then the index.html is the main page of this project.


