public class l002_directedGraph {

    // In directed graph you need only neighbour vtx as weight does not matter
    // So you do not need edge class here

    static int N = 7;
    static ArrayList<Integer>[] graph = new ArrayList[N];

    public static void addEdge(int u,int v){
        graph[u].add(v);
    }

    public static void display(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i < N;i++){
            sb.append(i + " -> ");
            for(int e: graph[i]){
                sb.append(e +",");
            }
            sb.append('\n');
        }

        sb.append('\n');
        System.out.println(sb.toString());
    }

    public static void topoDFS( int src, boolean vis[], ArrayList<Integer> ans) {

        vis[src] = true;

        for(int e : graph[src]) {
            if(!vis[e]) {

                topoDFS(e, vis,ans);
            }
        }

        ans.add(src); // add yourself in post order
    }

    public static void topologicalOrder() {

        boolean vis[] = new boolean[N];
        ArrayList<Integer> ans = new ArrayList<> ();

        for(int i = 0; i < N;i++) {

            if(!vis[i]) {

                topoDFS(i, vis, ans);
            }
        }
    }
    // {u,v,} in directed graph

    // Kahn's Algo for cycle detection 
    // WORKS ON INDEGREE

    // to calculate indegree you can use u vtx to increment V's indegree
    // bcz v depends on u so by traversing on u neighbours you can increment
    public static void topologicalOrder_KahnsAlgo() {

        int[] indegree[] = new int[N];

        for(int i = 0;i < N;i++) {

            for(int v: graph[i]) {
                indegree[v]++;
                // update indegree value of you neighbours
            }
        }

        ArrayDeque<Integer> que = new ArrayDeque<> ();
        ArrayList<Integer> ans = new Arraylist<> ();

        for(int i =0 ;i < N;i++) {
            if(indegree[i] == 0) que.addLast(i);
            // add starting points ie. whose indegree is 0 means
            // these vtx does not depend on other vtxs
        }

        while(que.size() > 0) {

            int vtx = que.removeFirst();

            ans.add(vtx);
            // add yourself in answer[]

            for(int v : graph[vtx]) {

                if( --indegree[v] == 0) {
                    // current vtx does not depend on any other vtx now 
                    // it can be resolved

                    que.addLast(v);
                }
            }
        }

        if(ans.size() != 0) {
            System.out.println("Cycle found");
            // because we cannot traverse all vtx bcz they depend on each other

        } else {
            System.out.println("No cycle : " + ans);
        }
    }
    
}