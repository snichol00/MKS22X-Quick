import java.util.Arrays;
import java.util.Random;
import java.lang.Math;

public class Quick{
    public static int partition(int [] data, int start, int end){
      //choose random pivot
      Random r = new Random();
      int pIdx = r.nextInt(data.length);
      int pVal = data[pIdx];

      if (data.length == 0){
        throw new IllegalArgumentException();
      }
      if (start == end){
        return start;
      }

      if (data[start] > data[end] &&
          data[start] < data[(end + start) / 2] || 
          data[start] < data[end] &&
          data[start] > data[(end + start) / 2]){
        pVal = data[start];
        pIdx = start;
      }
      else if (data[end] > data[start] &&
              data[end] < data[(end + start) / 2] ||
              data[end] < data[start] &&
              data[end] > data[(end + start) / 2]){
        pVal = data[end];
        pIdx = end;
      }
      else{
        pIdx = (end + start) / 2;
      }
      //swap pivot to be first int
      if (start != pIdx){
        data[pIdx] = data[start];
        data[start] = pVal;
        pIdx = start;
        start ++;
      }
      //System.out.println(Arrays.toString(data));
      //once start and end are equal, array has been partitioned
      while (start != end){
        if (data[start] > pVal || data[start] == pVal){
          //swap start and end
          int temp = data[start];
          data[start] = data[end];
          data[end] = temp;
          end--;
        }
        else{
          start++;
        }
      }
      //System.out.println("loop finished");
      //System.out.println(Arrays.toString(data));
      //once sorted, you need to switch the pivot back into place
      if (start >= data.length){
        start--;
      }

      if (data[start] >= pVal){
        data[0] = data[start - 1];
        data[start - 1] = pVal;
        return start - 1;
      }
      else{
        data[0] = data[start];
        data[start] = pVal;
        return start;
      }
    }

  public static int quickselect(int[] data, int k){
    int p = partition(data, 0, data.length - 1);
    int start = p;
    int end = p;
    while (k != p){
      if (p < k){
        start = p + 1;
      }
      else{
        end = p - 1;
      }
      p = partition(data, start, end);
    }
    return data[k];

  }
}
