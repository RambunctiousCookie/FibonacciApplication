package com.example.oracle.service;

import com.example.oracle.dto.FibonacciResponseDTO;
import lombok.Data;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.util.*;

@Service
@Data
public class FibonacciService {

    private final int last;
    private final BigInteger[] fibonacciMemo;
    private final BigInteger[][] fibonacciSortedMemo;

    public FibonacciService() {
        last = 100;     //takes in a numerical value between 1 to 100

        //immediately cache all values in service fields upon instantiation for speedy retrieval
        //      if it is not cached, the computational effort will be directly proportional to the number of api calls made

        fibonacciMemo = new BigInteger[last];
        initializeFibonacciMemo(last -1);    //use idx value

        fibonacciSortedMemo = new BigInteger[last][];
        for (int i = 0; i< last; i++){
            fibonacciSortedMemo[i] = new BigInteger[i+1];       //use idx value and +1 for length
        }
        initializeFibonacciSortedMemo();
    }

    private void initializeFibonacciMemo(int n){    //initialize
        //seed values
        fibonacciMemo[0] = BigInteger.valueOf(0);
        fibonacciMemo[1] = BigInteger.valueOf(1);

        for (int i=2;i< fibonacciMemo.length;i++)
            fibonacciMemo[i] = fibonacciMemo[i-2].add(fibonacciMemo[i-1]);
    }

    private void initializeFibonacciSortedMemo(){
        for (int i =0; i<100; i++){
            BigInteger[] temp = Arrays.copyOf(fibonacciMemo, fibonacciSortedMemo[i].length);
            mergeSort(temp);
            for (int j=0; j<temp.length; j++){
                fibonacciSortedMemo[i][j] = temp[j];
            }
        }
    }

    public FibonacciResponseDTO getFibonacciResultDTO(int elements){
        return new FibonacciResponseDTO(
                Arrays.copyOf(fibonacciMemo, elements),
                fibonacciSortedMemo[elements-1]);
    }

    public Mono<FibonacciResponseDTO> getFibonacciResult(int elements){
//        return Flux.just(new FibonacciResponseDTO(
//                Arrays.copyOf(fibonacciMemo, elements),
//                fibonacciSortedMemo[elements-1]));

        return Mono.just(new FibonacciResponseDTO(
                Arrays.copyOf(fibonacciMemo, elements),
                fibonacciSortedMemo[elements-1]));
    }


    //REASONING: The entire array is ALWAYS arranged in ascending order, hence:
    //      do not use Insertion Sort, resulting in O(n^2)
    //      quicksort has a case for lower space complexity
    //      do not reverse and then sort, would have resulted in worst time complexity
    //      for this application, use merge sort for consistent O(n*log n)
    public static void mergeSort(BigInteger[] array) {
        if (array.length <= 1) {     //base case for recursion
            return;
        }

        int mid = array.length / 2;
        BigInteger[] left = Arrays.copyOfRange(array,0, mid);
        BigInteger[] right = Arrays.copyOfRange(array, mid, array.length);

        mergeSort(left);
        mergeSort(right);

        //divide until it reaches base case, then it returns and commences the merge + sorting process
        merge(array, left, right);
    }

    private static void merge(BigInteger[] array, BigInteger[] left, BigInteger[] right) {
        int i = 0; // index for left list
        int j = 0; // index for right list
        int k = 0; // index for merged list

        while (i < left.length && j < right.length) {
            if (left[i].mod(BigInteger.TWO).equals(BigInteger.ZERO) && !right[j].mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
                array[k] = left[i];   //give even number on left list priority for merged
                i++;
            }
            else if (!left[i].mod(BigInteger.TWO).equals(BigInteger.ZERO) && right[j].mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
                array[k] = right[j];   //give even number on right list priority for merged
                j++;
            }
            else {
                if (left[i].compareTo(right[j]) >= 0) {
                    array[k] = left[i];       //give larger odd number on left list priority for merged
                    i++;
                }
                else {
                    array[k] = right[j];      //give larger odd number on right list priority for merged
                    j++;
                }
            }
            k++;
        }
        //at least one of the lists has been exhausted by this point
        while (i < left.length) {
            array[k] = left[i];   //loop to add remaining elements in left list to merged
            i++;
            k++;
        }
        while (j < right.length) {
            array[k] = right[j];   //loop to add remaining elements in right list to merged
            j++;
            k++;
        }
    }

}
