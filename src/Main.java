
public class Main {
	
	public static void main(String[] args){
		Integer[] intarr = {1,3,2,1,4,2,24,5};
		Thread mt = new Thread (new Mergesort<Integer>(intarr, 0, intarr.length-1));
		mt.start();
		try {
			mt.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		print(intarr);
	}
	public static void print(Integer[] intarr) {
		for (int indexArray = 0; indexArray < intarr.length; indexArray++) {
			System.out.printf("%s, ", intarr[indexArray].toString());
		}
		System.out.println("");
	}
}
