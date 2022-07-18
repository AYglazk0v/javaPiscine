import java.util.Scanner;

public class Program{
	public static void main(String arg[]) {
		long subject;
		long prb = 2;
		Scanner obj = new Scanner(System.in);

		System.out.println("Enter subject int");
		subject = obj.nextInt();
		if (subject < 2){
			System.err.println("IllegalArgument");
			obj.close();
			System.exit(-1);
		}
		while(prb * prb <= subject) {
			if (subject % prb == 0) {
				System.out.println("False " + (prb - 1));
				
				return ;
			}
			prb++;
		}
		System.out.println("True " + (prb - 1));
		obj.close();
	}
}
