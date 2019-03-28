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

  if (data[start] > data[end] && data[start] < data[(end + start) / 2] || data[start] < data[end] && data[start] > data[(end + start) / 2]){
    pVal = data[start];
    pIdx = start;
  }
  else if (data[end] > data[start] && data[end] < data[(end + start) / 2] || data[end] < data[start] && data[end] > data[(end + start) / 2]){
    pVal = data[end];
    pIdx = end;
  }
  else{
    pIdx = (end + start) / 2;
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

    if (data[start] < pVal || flip == 0){
      start++;
    }
    else if (data[start] > pVal || flip == 1){
      //swap start and end
      int temp = data[start];
      data[start] = data[end];
      data[end] = temp;
      end--;
    }
  }

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
    int start = 0;
    int end = data.length - 1;
    // will stop at kth val
    while (k != p){
      if (p < k){
        start = p + 1;
      }
      if (p > k){
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
    //base case
    if (low >= high){
      //end call
      return;
    }
    if (data.length < 47){
      insertionsort(data, low, high);
    }
    else{

    int p = partition(data, low, high);

    //call sort on new partitions
    quicksort(data, low, p - 1);
    quicksort(data, p + 1, high);
  }
}

public static void insertionsort(int[] data, int lo, int hi){
  for (int x = lo + 1; x <= hi; x++){
    int xval = data[x];
    int y = x - 1;
    for (; y >= lo && xval < data[y]; y--){
      data[y+1] = data[y];
    }
    data[y+1] = xval;
  }
}
}
