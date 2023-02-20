import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(reader.readLine());
        int[] weightToParent = new int[(1 << (k + 1)) - 1];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        for (int node = 1; node < weightToParent.length; node++)
            weightToParent[node] = Integer.parseInt(tokenizer.nextToken());

        int sumOfWeights = 0;
        dfs(weightToParent, 0);
        for (int weight : weightToParent)
            sumOfWeights += weight;
        System.out.print(sumOfWeights);
    }

    public static int dfs(int[] weightToParent, int node) {
        if (weightToParent.length <= (node << 1) + 1) return weightToParent[node];
        int leftChildNode = (node << 1) + 1;
        int rightChildNode = (node << 1) + 2;
        int leftWeight = dfs(weightToParent, leftChildNode);
        int rightWeight = dfs(weightToParent, rightChildNode);
        if (leftWeight <= rightWeight) {
            weightToParent[leftChildNode] += rightWeight - leftWeight;
            return weightToParent[node] + rightWeight;
        } else {
            weightToParent[rightChildNode] += leftWeight - rightWeight;
            return weightToParent[node] + leftWeight;
        }
    }
}
