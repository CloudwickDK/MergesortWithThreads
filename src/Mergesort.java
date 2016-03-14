import java.util.Arrays;

/**
 * This class implements concurrent Mergesort
 * 
 * @author desp
 *
 * @param <T>
 */
public class Mergesort<T extends Comparable> implements Runnable {

	private T[] arrayT;
	int start, stop;

	public Mergesort(T[] arrayT, int start, int stop) {
		this.arrayT = arrayT;
		this.start = start;
		this.stop = stop;
	}

	public void sort() throws InterruptedException {
		this.mergesort(this.arrayT, this.start, this.stop);
	}

	private void mergesort(T[] arrayT, int start, int stop) throws InterruptedException {
		if (start == stop) {
			return;
		}
		int mid = Math.floorDiv(start + stop, 2);
		Thread mt = new Thread (new Mergesort<T>(arrayT, mid+1, stop));
		mt.start();
		//mergesort(arrayT, mid + 1, stop);
		mergesort(arrayT, start, mid);
		mt.join();
		merge(start, stop);
	}

	public void run() {
		try {
			this.sort();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.print(arrayT);
	}

	public void print(T[] arrayT) {
		for (int indexArray = 0; indexArray < arrayT.length; indexArray++) {
			System.out.printf("%s, ", arrayT[indexArray].toString());
		}
		System.out.println("");
	}

	public void update(T[] newArrayT, int start, int stop) {
		for (int indexArray = 0; indexArray < newArrayT.length; indexArray++) {
			this.arrayT[start + indexArray] = newArrayT[indexArray];
		}
	}

	@SuppressWarnings("unchecked")
	public void merge(int start, int stop) {
		
		int middle = Math.floorDiv(start + stop, 2);
		T[] arrayT1 = Arrays.copyOfRange(arrayT, start, middle+1);
		T[] arrayT2 = Arrays.copyOfRange(arrayT, middle + 1, stop+1);
		print(arrayT1);
		print(arrayT2);
		T[] newArray = (T[]) new Comparable[arrayT1.length + arrayT2.length];
		int indexArray1 = 0, indexArray2 = 0;
		T first, second;
		for (int indexArray = 0; indexArray < newArray.length; indexArray++) {
			if (indexArray1 < arrayT1.length && indexArray2 < arrayT2.length) {
				first = arrayT1[indexArray1];
				second = arrayT2[indexArray2];
				if (first.compareTo(second) > 0) { // if first is greater
					newArray[indexArray] = second;
					indexArray2++;
				} else {
					newArray[indexArray] = first;
					indexArray1++;
				}
			} else if (indexArray1 < arrayT1.length) {
				first = arrayT1[indexArray1];
				newArray[indexArray] = first;
				indexArray1++;
			} else {
				second = arrayT2[indexArray2];
				newArray[indexArray] = second;
				indexArray2++;
			}

		}
		update(newArray, start, stop);
	}
}