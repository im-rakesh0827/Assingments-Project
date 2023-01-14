import java.util.Arrays;

public class Main{
    public static void main(String[] args) {
        int [] arr = {-4, 1, 3, -2, -1};
        findTerms(arr);
    }

    public static void findTerms(int[] arr) {
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