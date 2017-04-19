package waze;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WazeApp waze = new WazeApp ();
		Scanner scan = new Scanner (System.in);
		System.out.println("Please input the start node Id (only integer allowed): ");
		int n1 = scan.nextInt();
		System.out.println("Please input the end node Id (only integer allowed): ");
		int n2 = scan.nextInt();
		System.out.println("Please enter the number of shortest path (only integer allowed): ");
		int k = scan.nextInt();
		String path = "";
		try {
			path = WazeApp.demo(n1, n2, k);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(path);
	}
}
