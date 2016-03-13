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

	public void sort() {
		this.arrayT = this.mergesort(this.arrayT, this.start, this.stop);
	}

	private T[] mergesort(T[] arrayT, int start, int stop) {

		if (start == stop) {
			@SuppressWarnings("unchecked")
			T[] value = (T[]) new Comparable[1];
			value[0] = arrayT[start];
			return value;
		}

		int mid = Math.floorDiv(start + stop, 2);
		//System.out.printf("S:%d, M:%d, S:%d\n", start, mid, stop);
		T[] firstArrayT = mergesort(arrayT, start, mid);
		
		T[] secondArrayT = mergesort(arrayT, mid + 1, stop);
		//this.print(firstArrayT);
		//this.print(secondArrayT);
		return merge(firstArrayT, secondArrayT);
	}

	public void run() {
		this.sort();
		this.print(arrayT);
	}

	public void print(T[] arrayT) {
		for (int indexArray = 0; indexArray < arrayT.length; indexArray++) {
			System.out.printf("%s, ", arrayT[indexArray].toString());
		}
		System.out.println("");
	}

	@SuppressWarnings("unchecked")
	public T[] merge(T[] arrayT1, T[] arrayT2) {
		T[] newArray = (T[]) new Comparable[arrayT1.length + arrayT2.length];
		int indexArray1 = 0, indexArray2 = 0;
		T first, second;
		for (int indexArray = 0; indexArray < newArray.length; indexArray++) {
			if (indexArray1 < arrayT1.length && indexArray2 < arrayT2.length) {
				first = arrayT1[indexArray1];
				second = arrayT2[indexArray2];
				if (first.compareTo(second) > 0) { //if first is greater
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
		return newArray;
	}
}