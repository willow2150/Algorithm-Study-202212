import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 다음역 : 시계방향
* 이전역 : 반시계방향
* 역 고유번호의 입력을 따로 정렬할 순 없음 - 저 순서로 노선이 나있기 때문에(sort 따로 하면 안됨)
*
* 각 명령어를 수행하면서 그냥 출력하면 되네
*
* 이전역과 다음역의 고유번호를 찾기 위해선 50만개의 데이터를 탐색해야하는데 이걸 150만번 하니 그냥 List 단순탐색으로 하면
* 무조껀 시간초과가 날듯? 이걸 어떻게 해결하면 좋을까
*
* LinkedList 자체로 해결해볼까?
* a to b를 a to c, c to b 로 만들면 되잖아
* 2 -> 7
* 7 -> 3
* 3 -> 5
* 이런 형식으로 만들껀데 변수를 어떻게 선언하면 좋을까? 100000까지 나오는데 배열해볼까
*
*
*
* */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] preStation = new int[1000001];
        int[] nextStation = new int[1000001];
        int[] station = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            station[i] = Integer.parseInt(st.nextToken());
        }

        // 5(last) 2 7 3 5 2(first) 앞뒤로 값 2개만 박으면 쉽게 처리할 수 있음
        int first = station[0];
        int last = station[N-1];
        for (int i = 0; i < N; i++) {
            if (i == 0){
                preStation[station[i]] = last;
                nextStation[station[i]] = station[i+1];
            }
            else if (i == N-1){
                preStation[station[i]] = station[i-1];
                nextStation[station[i]] = first;
            }
            else{
                preStation[station[i]] = station[i-1];
                nextStation[station[i]] = station[i+1];
            }
        }

//        System.out.println(preStation[5]);
//        System.out.println(nextStation[5]);

        for (int k = 0; k < M; k++) {
            st = new StringTokenizer(br.readLine());

            String command =st.nextToken();

            if (command.equals("BN")){
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());

//                System.out.println(nextStation[i]);
                sb.append(nextStation[i]+"\n");

                int temp = nextStation[i];
                nextStation[i] = j;
                nextStation[j] = temp;

                preStation[j] = i;
                preStation[temp] = j;
            }
            else if (command.equals("BP")){
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());

//                System.out.println(preStation[i]);
                sb.append(preStation[i]+"\n");
                int temp = preStation[i];
                preStation[i] = j;
                preStation[j] = temp;

                nextStation[j] = i;
                nextStation[temp] = j;

            }
            else if (command.equals("CP")){
                int i = Integer.parseInt(st.nextToken());
                int remove_station = preStation[i];

//                System.out.println(remove_station);
                sb.append(remove_station+"\n");

                int temp = preStation[remove_station];
                nextStation[temp] = i;
                preStation[i] = temp;

                nextStation[remove_station] = 0;
                preStation[remove_station] = 0;

            }
            else if (command.equals("CN")){
                int i = Integer.parseInt(st.nextToken());

                int remove_station = nextStation[i];

//                System.out.println(remove_station);
                sb.append(remove_station+"\n");

                int temp = nextStation[remove_station];
                nextStation[i] = temp;
                preStation[temp] = i;

                nextStation[remove_station] = 0;
                preStation[remove_station] = 0;

            }


        }

        System.out.println(sb.toString());

    }
}