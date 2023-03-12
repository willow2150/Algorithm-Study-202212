import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 아, 티켓 정보대로 빠른순서로 입장해야되는데 사람들이 무작위로 몰리니까 티켓정보대로 입장 시키려고 하는거네
* 첫 번째 줄 맨 앞사람 : 이 사람만 이동 가능, 콘서트장 or 대기공간에서 다시 대기
* 한 줄(5명)이 모두 이동을 했다면 그 다음줄로 이동
*
* 대기공간(Stack)
* 마지막에 들어온 사람부터 나갈 수 있음
* -> 첫 번째 줄 맨 앞사람이 콘서트장에 입장해야되는데 티켓 정보대로 입장해야되니 대기공간에 있다가 가장 마지막에 들어온 사람부터 나가라는 의미임
* 나갈경우 바로 입장해야하고 다시 줄로 돌아갈 수 없음
* -> 대기공간에서 나간애는 무조껀 콘서트장으로 가야한다는 의미니까 pop 한번 시킬 때 잘하라는거네
*
* 핵심
* 입력으로 주어진 케이스가 되냐 안되냐만 판단하면 됨.
* 1. 입력으로 주어진거 문자랑 숫자로 정렬
* 2. 정렬시킨순서대로 나가야하니까 약간 투포인터 개념으로 지금 콘서트 가야할 티켓이 나올때까지 push시키고
* peek or nextNode 를 보면서 계속 index 늘려나간다음 stack에 남아있는애들로 정답 판단하면 될듯?
*
* */
public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        List<Node> data = new ArrayList<>();
        List<Node> data_sort = new ArrayList<>();

        for (int i = 0; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                String[] temp = st.nextToken().split("-");
                data.add(new Node(temp[0].charAt(0), Integer.parseInt(temp[1])));
                data_sort.add(new Node(temp[0].charAt(0), Integer.parseInt(temp[1])));
            }
        }

        data_sort.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.column == o2.column)
                    return o1.row - o2.row;
                return o1.column - o2.column;
            }
        });

//        System.out.println(data.toString());
//        System.out.println(data_sort.toString());

        Stack<Node> stack = new Stack<>();
        int index = 0 ;

        for (int i = 0; i <5*N ; i++) {

//            System.out.println("들어가야할 노드 : " + data_sort.get(index).toString());
//            System.out.println("탐색중인 노드 : " + data.get(i).toString());
//            if (stack.size() > 0)
//            System.out.println("스택 맨위에 있는 노드 : " + stack.peek().toString());

            if ((data.get(i).column == data_sort.get(index).column) && (data.get(i).row == data_sort.get(index).row)){
                index++;
            }
            else if (stack.size() > 0 && (stack.peek().column == data_sort.get(index).column) && (stack.peek().row== data_sort.get(index).row)){
                stack.pop();
                index++;
                i--;
            }
            else{
                stack.push(data.get(i));
            }


        }


        boolean flag = true;

        while (stack.size() > 0){
            Node current = stack.pop();
            if ((current.column == data_sort.get(index).column) && (current.row == data_sort.get(index).row)){
                index++;
            }
            else{
                flag = false;
                break;
            }
        }

        if (flag)
            System.out.println("GOOD");
        else
            System.out.println("BAD");



    }
}

class Node{
    char column;
    int row;

    public Node(char column, int row){
        this.column = column;
        this.row = row;
    }

    @Override
    public String toString(){
        return column + " " + row;
    }

}