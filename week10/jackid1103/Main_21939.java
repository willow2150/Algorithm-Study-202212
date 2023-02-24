import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main_21939 {
  static int N, M;
  static Map<Integer, Integer> pMap;
  static TreeSet<Point> tree;

  public static class Point implements Comparable<Point>{
		int pNum, level;
		
		Point(int p, int l){
			this.pNum = p;
			this.level = l;
		}
		
		@Override
		public int compareTo(Point p) {
      // 난이도가 같으면 번호순으로 정렬
			if(this.level == p.level) {
				return this.pNum - p.pNum;
			}
			else {
				return this.level - p.level;
			}
		}
	}

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    pMap = new HashMap<>();
    tree = new TreeSet<>();

    N = Integer.parseInt(br.readLine());
    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());

      int pNum = Integer.parseInt(st.nextToken());
      int level = Integer.parseInt(st.nextToken());

      pMap.put(pNum, level);
      tree.add(new Point(pNum, level));
    }
    
    M = Integer.parseInt(br.readLine());

    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      String command = st.nextToken();

      if(command.equals("add")) {
        int pNum = Integer.parseInt(st.nextToken());
        int level = Integer.parseInt(st.nextToken());

        pMap.put(pNum, level);
        tree.add(new Point(pNum, level));
      } 
      
      else if(command.equals("recommend")) {
        int temp = Integer.parseInt(st.nextToken());

        if(temp == 1) System.out.println(tree.last().pNum);
        else System.out.println(tree.first().pNum);
      } 
      
      else { // solved
        int pNum = Integer.parseInt(st.nextToken());
        int level = pMap.get(pNum);

        pMap.remove(pNum);
        tree.remove(new Point(pNum, level));
      }
    }
  }
  
}
