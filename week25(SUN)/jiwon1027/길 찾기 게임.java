import java.util.*;

class ListNode {
    int x;
    int y;
    int index;
    
    ListNode left;
    ListNode right;
    
    public ListNode(int x, int y, int index){
        this.x = x;
        this.y = y;
        this.index = index;
    }
}

class Solution {
    static final int X = 0;
    static final int Y = 1;
    static int idx;
    
    public int[][] solution(int[][] nodeinfo) {
        
        List<ListNode> nodes = new ArrayList<>();
        for (int i=0; i<nodeinfo.length; i++) {
            ListNode node = new ListNode(nodeinfo[i][X], nodeinfo[i][Y], i+1);
            nodes.add(node);
        }
        
        nodes.sort((o1, o2) -> (o2.y - o1.y));
        
        ListNode root = nodes.get(0);
        for (int i=1; i<nodes.size(); i++) 
            insertNode(root, nodes.get(i));
        
        int[][] answer = new int[2][nodeinfo.length];
        idx = 0;
        preorder(root, answer[0]);
        idx = 0;
        postorder(root, answer[1]);
        
        return answer;
    }
    
    private void insertNode(ListNode parent, ListNode child) {
        if (child.x < parent.x) {
            if (parent.left == null) 
                parent.left = child;
            else 
                insertNode(parent.left, child);
        } else {
            if (parent.right == null) 
                parent.right = child;
            else 
                insertNode(parent.right, child);
        }
    }
    
    private void preorder(ListNode root, int[] arr) {
        if (root != null) {
            arr[idx++] = root.index;
            preorder(root.left, arr);
            preorder(root.right, arr);
        }
    }
    
    private void postorder(ListNode root, int[] arr) {
        if (root != null) {
            postorder(root.left, arr);
            postorder(root.right, arr);
            arr[idx++] = root.index;
        }
    }
}

