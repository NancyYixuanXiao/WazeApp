package waze;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner (System.in);
		System.out.println("Please input the start node Id (only integer allowed): ");
		int n1 = scan.nextInt();
		System.out.println("Please input the end node Id (only integer allowed): ");
		int n2 = scan.nextInt();
		
		System.out.println("The paths are: ");
		System.out.println(WazeApp.waze(n1, n2, k));
	}
}
