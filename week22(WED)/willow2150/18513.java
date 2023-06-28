import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class HashTable {
		static class Node {
			Node next;
			int key;

			public Node(Node next, int key) {
				this.next = next;
				this.key = key;
			}
		}

		Node[] data;
		int size;

		public HashTable(int size) {
			this.size = size;
			this.data = new Node[size];
		}

		Node get(int key) {
			Node node = data[getHashCode(key)];
			while (node != null) {
				if (key == node.key) {
					return node;
				}
				node = node.next;
			}
			return null;
		}

		void put(int key) {
			int hashCode = getHashCode(key);
			data[hashCode] = new Node(data[hashCode], key);
		}

		int getHashCode(int key) {
			return (int)((long)Math.abs(key) * 31 % size);
		}
	}

	static class Spring {
		int position;

		public Spring(int position) {
			this.position = position;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		Queue<Spring> queue = new ArrayDeque<>();
		HashTable hashTable = new HashTable(1 << 19);

		int N = Integer.parseInt(tokenizer.nextToken());
		int K = Integer.parseInt(tokenizer.nextToken());

		tokenizer = new StringTokenizer(reader.readLine());
		for (int spring = 0; spring < N; spring++) {
			int position = Integer.parseInt(tokenizer.nextToken());
			queue.add(new Spring(position));
			hashTable.put(position);
		}

		System.out.print(calcMinSumOfMisfortune(queue, hashTable, K));
	}

	public static long calcMinSumOfMisfortune(
			Queue<Spring> queue, HashTable hashTable, int K
	) {
		long minSumOfMisfortune = 0L;
		for (int misfortune = 1; 0 < K; misfortune++) {
			int queueSize = queue.size();
			for (int i = 0; i < queueSize; i++) {
				Spring spring = queue.poll();
				long temp = minSumOfMisfortune;
				int position = spring.position;
				int keyA = position - misfortune;
				int keyB = position + misfortune;
				if (hashTable.get(keyA) == null) {
					hashTable.put(keyA);
					minSumOfMisfortune += misfortune;
					if (--K == 0) {
						break;
					}
				}
				if (hashTable.get(keyB) == null) {
					hashTable.put(keyB);
					minSumOfMisfortune += misfortune;
					if (--K == 0) {
						break;
					}
				}
				if (minSumOfMisfortune == temp) {
					continue;
				}
				queue.add(spring);
			}
		}
		return minSumOfMisfortune;
	}
}
