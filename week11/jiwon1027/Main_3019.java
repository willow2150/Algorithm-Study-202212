package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 와 너무 귀찮고
* */

public class Main_3019 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int[] height = new int[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        int res = 0;

        switch (P){
            case 1:
                res += C;
                for (int i = 0; i < C-3; i++) {
                    if ((height[i] == height[i + 1]) && (height[i + 1] == height[i + 2]) && (
                            height[i + 2] == height[i + 3])) {
                        res += 1;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < C-1; i++) {
                    if (height[i] == height[i+1])
                        res+=1;
                }
                break;
            case 3:
                for (int i = 0; i < C-2; i++) {
                    if ((height[i] == height[i + 1]) && (height[i + 1] == height[i + 2] - 1)) {
                        res += 1;
                    }
                }
                for (int i = 0; i < C-1; i++) {
                    if (height[i] == height[i + 1] + 1) {
                        res += 1;
                    }
                }
                break;
            case 4:
                for (int i = 0; i < C-2; i++) {
                    if ((height[i] == height[i + 1]+1) && (height[i + 1] == height[i + 2])) {
                        res += 1;
                    }
                }
                for (int i = 0; i < C-1; i++) {
                    if (height[i] == height[i + 1] - 1) {
                        res += 1;
                    }
                }
                break;
            case 5:
                for (int i = 0; i < C-2; i++) {
                    if ((height[i] == height[i + 1]) && (height[i + 1] == height[i + 2])) {
                        res += 1;
                    }
                    if ((height[i] == height[i + 1] + 1) && (height[i + 1] == height[i + 2] - 1)) {
                        res += 1;
                    }
                }
                for (int i = 0; i < C-1; i++) {
                    if (height[i] == height[i + 1] - 1) {
                        res += 1;
                    }
                    if (height[i] == height[i + 1] + 1) {
                        res+=1;
                    }
                }
                break;
            case 6:
                for (int i = 0; i < C-2; i++) {
                    if ((height[i] == height[i + 1]) && (height[i + 1] == height[i + 2])) {
                        res += 1;
                    }
                    if ((height[i] == height[i + 1] - 1) && (height[i + 1] == height[i + 2])) {
                        res += 1;
                    }
                }
                for (int i = 0; i < C-1; i++) {
                    if (height[i] == height[i + 1]) {
                        res += 1;
                    }
                    if (height[i] == height[i + 1] + 2) {
                        res += 1;
                    }
                }
                break;
            case 7:
                for (int i = 0; i < C-2; i++) {
                    if ((height[i] == height[i + 1]) && (height[i + 1] == height[i + 2])) {
                        res += 1;
                    }
                    if ((height[i] == height[i + 1]) && (height[i + 1] == height[i + 2]+1)) {
                        res += 1;
                    }
                }
                for (int i = 0; i < C-1; i++) {
                    if (height[i] == height[i + 1]-2) {
                        res += 1;
                    }
                    if (height[i] == height[i + 1]) {
                        res += 1;
                    }
                }
                break;

        }
        System.out.println(res);

    }

}
