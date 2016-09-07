# WazeApp

 WazeApp is the final project done for EC504 Advanced Data Structures.
 
This program can caculate k (integer) shortest paths between two arbitrary locations. The outputs are the distance of each path, and the paths representing in sequence of edge numbers.

*cal.cnode.txt* provides nodes (locations) with altitude, longitude and node number. *cal.cedge.txt* provides edges (roads) with starting node number, end node number and distance.

To test this program, run Demo.java and enter integers at each prompt. The first two entries are both node numbers. Given all nodes in *cal.cnode.txt*, the numbers should be in \[0, 21047]. Notice if the third entry is the number of shortest paths that you want to find, if is larger than 3, for certain points it may take some time to run.
