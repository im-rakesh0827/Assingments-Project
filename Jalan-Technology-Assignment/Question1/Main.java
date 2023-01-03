import java.util.ArrayList;
import java.util.Arrays;

public class Main{
    public static void main(String[] args) {
        int [] arr = {-4, 1, 3, -2, -1};
        findTerms4(arr);

    }


    // This is the second approach to fint the all the terms whose sum is zero :
    // This is quite optimized than the above one :
    public static int[] findTerms2(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (arr[i] + arr[j] + arr[k] == 0) {
                       return  new int[]{arr[i], arr[j], arr[k]};
                    }
                }
            }
        }
        return new int[]{0};

    }




    public static void findTerms4(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        boolean found = false;
        for(int i=0; i<n-1; i++){
            int start = i+1;
            int end = n-1;
            while(start<end){
                int sum = arr[i]+arr[start]+arr[end];
                if(sum==0){
                    System.out.println(arr[i]+" "+arr[start]+" "+arr[end]);
                    start++;
                    end--;
                    found=true;
                    return;
                }else if(sum<0){
                    start++;
                }else end--;
            }
        }
        if(!found) System.out.println("No Elements Found");
    }
}