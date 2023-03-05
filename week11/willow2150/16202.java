import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int cost;
        int vtxA;
        int vtxB;

        Edge(int cost, int vtxA, int vtxB) {
            this.cost = cost;
            this.vtxA = vtxA;
            this.vtxB = vtxB;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        StringTokenizer tokenizer;

        LinkedList<Edge> edgeList = new LinkedList<>();

        tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        for (int cost = 1; cost <= M; cost++) {
            tokenizer = new StringTokenizer(reader.readLine());
            edgeList.add(
                    new Edge(
                            cost,
                            Integer.parseInt(tokenizer.nextToken()),
                            Integer.parseInt(tokenizer.nextToken())
                    )
            );
        }

        LinkedList<Edge> MSTEdgeList = new LinkedList<>();
        int[] parentVtx = new int[N + 1];
        int prevMSTCost = 1;

        for (int round = 1; round <= K; round++) {
            if (prevMSTCost != 0) {
                int MSTCost = findMSTCost(edgeList, MSTEdgeList, parentVtx, N);
                if (MSTCost != 0) edgeList.remove(MSTEdgeList.pollFirst());
                prevMSTCost = MSTCost;
                MSTEdgeList.clear();
            }
            output.append(prevMSTCost).append(' ');
        }
        System.out.print(output);
    }

    public static int findMSTCost(LinkedList<Edge> edgeList,
                                  LinkedList<Edge> MSTEdgeList, int[] parentVtx, int N) {
        int MSTCost = 0;

        for (int vtx = 1; vtx <= N; vtx++)
            parentVtx[vtx] = vtx;

        for (Edge edge : edgeList) {
            int cost = edge.cost;
            int vtxA = edge.vtxA;
            int vtxB = edge.vtxB;
            if (findParentVtx(parentVtx, vtxA) == findParentVtx(parentVtx, vtxB))
                continue;
            union(parentVtx, vtxA, vtxB);
            MSTEdgeList.add(edge);
            MSTCost += cost;
            if (--N == 1) break;
        }
        return N == 1 ? MSTCost : 0;
    }

    public static int findParentVtx(int[] parentVtx, int vtx) {
        if (vtx == parentVtx[vtx]) return vtx;
        return parentVtx[vtx] = findParentVtx(parentVtx, parentVtx[vtx]);
    }

    public static void union(int[] parentVtx, int vtxA, int vtxB) {
        vtxA = parentVtx[vtxA];
        vtxB = parentVtx[vtxB];
        if (vtxA <= vtxB) parentVtx[vtxB] = vtxA;
        else parentVtx[vtxA] = vtxB;
    }
}
