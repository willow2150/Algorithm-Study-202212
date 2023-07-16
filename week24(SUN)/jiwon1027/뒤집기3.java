import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 맨 왼쪽 문자부터 뒤집어야함
 *
 *
 * */


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        char[] str = br.readLine().toCharArray();
        int N = str.length;

        for (int i = 0; i < N - 1; i++) {
            if (str[i] > str[i+1] && str[i+1] <= str[0]) {
                reverse(0, i+1, str);
                if (str[i] >= str[i+1]) {
                    reverse(0, i+1 + 1, str);
                }
            }
        }

        for (char x : str) {
            System.out.print(x);
        }
    }
    private static void reverse(int left, int right, char[] arr) {
        while (left < right - 1) {
            char temp = arr[right - 1];
            arr[right - 1] = arr[left];
            arr[left] = temp;
            left++;
            right--;
        }
    }
}

