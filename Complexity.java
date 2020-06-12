//Name：Zhenhang Ji CWID：10445682
public class Complexity {
	//O(n^2)
	public static void method1(int n){
		int counter = 0;
		for (int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				System.out.println("Operation" + counter);
				counter++;
			}
		}
	}
	//O(n^3)
	public static void method2(int n){
		int counter = 0;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				for(int k = 0; k < n; k++){
					System.out.println("Operation" + counter);
					counter++;
				}
			}
		}
	}
	//O(log n)
	public static void method3(int n){
		int counter = 0;
		for(int i = 1; i < n; i = 2 * i){
			System.out.println("Operation" + counter);
			counter++;
		}
	}
	//O(nlogn)
	public static void method4(int n){
		int counter = 0;
		for(int i = 0; i < n; i++){
			for(int j = 1; j < n; j = 2 * j){
				System.out.println("Operation" + counter);
				counter++;
			}
		}
	}
	//O(loglogn)
	public static void method5(int n){
		int counter = 0;
		for(int i = 2; i < n; i = (int) Math.pow(i, 2)){
			//for(int j = 1; j < i; j = 2 * j){
				System.out.println("Operation" + counter);
				counter++;
		}
	}	
//}
	//O(2^n)
	public static int method6(int n){
		int counter = 0;
		if(n >= 1){
			//counter++;
			System.out.println("Operation" + counter++);
			return method6(n - 1) + method6(n - 2);
		}else{ return 0;
		}	
	}
	public static void main(String[] args){
		//System.out.println("method5");
		//method5();
		//System.out.println("method6");
		//method6();
	}
}
		
	
