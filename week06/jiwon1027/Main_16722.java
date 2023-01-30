package pratice;

import java.io.*;
import java.util.*;

/*
* 그냥 뭐 구현문제네
*
* */

public class Main_16722 {

    public static class Card{
        String shape, color, background;

        public Card(String shape, String color, String background) {
            this.shape = shape;
            this.color = color;
            this.background = background;
        }
    }

    public static Card[] cards;
    public static int[] selected;
    public static HashSet<String> possibleSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        cards = new Card[10];

        for(int i = 1; i < 10; i++){
            st = new StringTokenizer(br.readLine());
            cards[i] = new Card(st.nextToken(), st.nextToken(), st.nextToken());
        }

        int n = Integer.parseInt(br.readLine());

        selected = new int[3];
        dfs(1, 0);

        int ans = 0;
        boolean flag = true;

        while(n-->0){
            st = new StringTokenizer(br.readLine());
            char comd = st.nextToken().charAt(0);

            if(comd == 'H'){
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                int max = Math.max(Math.max(a, b), c);
                int min = Math.min(Math.min(a, b), c);
                int mid = a + b + c - max - min;

                a = min;
                b = mid;
                c = max;

                StringBuilder sb = new StringBuilder();
                sb.append(a).append(b).append(c);

                if(checkShape(a, b, c) && checkColor(a, b, c) && checkBackground(a, b, c) && possibleSet.contains(sb.toString())){
                    ans++;
                    possibleSet.remove(sb.toString());
                }else ans--;
            }else{
                if(flag && possibleSet.size() == 0) {
                    ans += 3;
                    flag = false;
                }else{
                    ans--;
                }
            }
        }

        System.out.println(ans);
    }

    public static void dfs(int idx, int cnt){
        if(cnt == 3){
            check();
            return;
        }

        for(int i = idx; i < 10; i++){
            selected[cnt] = i;
            dfs(i+1, cnt+1);
        }
    }

    public static void check(){
        int a = selected[0];
        int b = selected[1];
        int c = selected[2];

        if(checkShape(a, b, c) && checkColor(a, b, c) && checkBackground(a, b, c)){
            StringBuilder sb = new StringBuilder();
            sb.append(a).append(b).append(c);
            possibleSet.add(sb.toString());
        }
    }

    public static boolean checkShape(int a, int b, int c){
        HashSet<String> set = new HashSet<>();
        set.add(cards[a].shape);
        set.add(cards[b].shape);
        set.add(cards[c].shape);

        return set.size() == 3 || set.size() == 1;
    }

    public static boolean checkColor(int a, int b, int c){
        HashSet<String> set = new HashSet<>();
        set.add(cards[a].color);
        set.add(cards[b].color);
        set.add(cards[c].color);

        return set.size() == 3 || set.size() == 1;
    }

    public static boolean checkBackground(int a, int b, int c){
        HashSet<String> set = new HashSet<>();
        set.add(cards[a].background);
        set.add(cards[b].background);
        set.add(cards[c].background);

        return set.size() == 3 || set.size() == 1;
    }

}
