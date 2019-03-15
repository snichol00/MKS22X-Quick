import java.util.Arrays;
import java.util.Random;
import java.lang.Math;

public class Quick{
  public static int partition(int [] data, int start, int end){
  //choose random pivot
  Random r = new Random();
  int pIdx = 0;
  int pVal = 0;

  if (data.length == 0){
    throw new IllegalArgumentException();
  }
  if (start == end){
    return start;
  }

  if (data[start] > data[end] && data[start] < data[(end+start)/2] || data[start] < data[end] && data[start] > data[(end+start)/2]){
    pVal = data[start];
    pIdx = start;
  }else if (data[end] > data[start] && data[end] < data[(end+start)/2] || data[end] < data[start] && data[end] > data[(end+start)/2]){
    pVal = data[end];
    pIdx = end;
  }else{
    pIdx = (end+start)/2; //refrain from using 0s and data.length - 1, bc in quicksort we do subsets not whole array
    pVal = data[pIdx];
  }
  //swap pivot to be first int
    data[pIdx] = data[start];
    data[start] = pVal;
    pIdx = start;
    start ++;

  //System.out.println(Arrays.toString(data));
  //once start and end are equal, array has been partitioned
  while (start != end){
    int flip = -1;

    //50% chance of flipping to other side
    if (pVal == data[start]){
      flip = r.nextInt(2);
    }

    if (data[start] > pVal || flip == 0){
      //swap start and end
      int temp = data[start];
      data[start] = data[end];
      data[end] = temp;
      end--;
    }
    else if (data[start] < pVal || flip == 1){
      start++;
    }
  }
  //System.out.println("loop finished");
  //System.out.println(Arrays.toString(data));
  //once sorted, you need to switch the pivot back into place

  if (pVal > data[start]){
    data[pIdx] = data[start];
    data[start] = pVal;
    return start;
  }
  else{
    data[pIdx] = data[start - 1];
    data[start - 1] = pVal;
    return start - 1;
  }
}


  public static int quickselect(int[] data, int k){
    //partitions array
    int p = partition(data, 0, data.length - 1);
    int start = p;
    int end = p;
    // will stop at kth val
    while (k != p){
      if (p < k){
        start = p + 1;
      }
      else{
        end = p - 1;
      }
      // partitions appropriate array
      p = partition(data, start, end);
    }
    //return kth value
    return data[k];
  }

  public static void quicksort(int[] data){
    quicksort(data, 0, data.length - 1);
  }

  public static void quicksort(int[] data, int low, int high){
    if (low >= high){
      return;
    }
    int p = partition(data, low, high);
    quicksort(data, low, p - 1);
    quicksort(data, p + 1, high);
  }
}
