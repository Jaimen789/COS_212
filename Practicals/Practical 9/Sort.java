// Name:
// Student number:
import java.util.Arrays;
public class Sort
{
	
	////// Implement the functions below this line //////
	
	/********** MERGE **********/
	public static <T extends Comparable<? super T>> void mergesort(T[] data, int first, int last, boolean debug)
	{
		// Your code here
		if(first < last){

			int middle = first + (last - first) / 2;

			mergesort(data, first, middle, debug);
			mergesort(data, middle + 1, last, debug);

			merge(data, first, last, debug);
			
		}
		
	}
     
	private static <T extends Comparable<? super T>> void merge(T[] data, int first, int last, boolean debug)
	{
		// Your code here
		int middle = first + (last - first) / 2;

		int n1 = middle - first + 1;
		int n2 = last - middle;

		T[] firstArray  = (T[]) new Comparable[n1];
		T[] lastArray  = (T[]) new Comparable[n2];

		for(int i = 0; i < n1; ++i){
			firstArray[i] = data[first + i];
		}

		for(int j = 0; j < n2; ++j){
			lastArray[j] = data[middle + 1 + j];
		}

		int i = 0; 
		int j = 0;

		int k = first;

		while(i < n1 && j < n2){
			if(firstArray[i].compareTo(lastArray[j]) <= 0){
				data[k] = firstArray[i];
				i++;

			}else{
				data[k] = lastArray[j];
				j++;
			}
			k++;
		}

		while(i < n1){
			data[k] = firstArray[i];
			i++;
			k++;
		}

		while(j < n2){
			data[k] = lastArray[j];
			j++;
			k++;
		}

		//DO NOT MOVE OR REMOVE!
		if (debug){
			System.out.println(Arrays.toString(data));
		}
	}
     
	/********** COUNTING **********/
	public static void countingsort(int[] inputArray, boolean debug) {

		int N = inputArray.length;
		int M = 0;
	
		for (int i = 0; i < N; i++) {
			M = Math.max(M, inputArray[i]);
		}
	
		int[] countArr = new int[M + 1];
	
		// Calculate cumulative count
		for (int i = 0; i < N; i++) {
			countArr[inputArray[i]]++;
		}
	
		// Update countArr to store the actual position of each element in outputArr
		for (int i = 1; i <= M; i++) {
			countArr[i] += countArr[i - 1];
		}
	
		int[] outputArr = new int[N];
	
		// Fill in the outputArr
		for (int i = N - 1; i >= 0; i--) {
			outputArr[countArr[inputArray[i]] - 1] = inputArray[i];
			countArr[inputArray[i]]--;
		}
	
		// Update the original array
		//System.arraycopy(outputArr, 0, inputArray, 0, N);

		for(int i = 0; i < inputArray.length; i++){
			inputArray[i] = outputArr[i];
		}
	
		// Optionally print the sorted array
		if (debug) {
			System.out.println(Arrays.toString(inputArray));
		}
	}
	

}