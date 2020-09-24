import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;
public class l004 {

    public static class Edge {

        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph,int u,int v,int w) {

        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
    }

    public static void display(int N,ArrayList<Edge>[] graph) {

        StringBuilder sb = new StringBuilder();

        for(int i = 0 ;i < N;i++) {

            sb.append(i + " -> ");
            for(Edge e : graph[i]) {

                sb.append("(" + e.v + ", " + e.w + ") ");
            }
            sb.append("\n");
        }

        sb.append("\n");
        System.out.println(sb.toString());
    }

    public static class primsPair implements Comparable<primsPair> {

        int vtx = 0;
        int par = 0; // par is parent (connecting vtx)
        int weight = 0;

        primsPair(int vtx, int par, int weight) {

            this.vtx = vtx;
            this.par = par;
            this.weight = weight;
        }

        @Override
        public int compareTo(primsPair other) {

            return this.weight - other.weight;  // default sorting behaviour
        }
    }

    // DFS with PriorityQueue is PRIMS
    // prims simple approach (Use this always)
    public static void prims(int src, int N,ArrayList<Edge>[] graph) {

        ArrayList<Edge> primsGraph[] = new ArrayList[N];
        for(int i = 0 ;i < N;i++) primsGraph[i] = new ArrayList<>();

        // priority queue for giving preference to weight
        PriorityQueue<primsPair> pq = new PriorityQueue<> ();
        pq.add( new primsPair(src,-1, 0));

        boolean vis[] = new boolean[N];

        int EdgeCount = 0;
        while(EdgeCount != N-1) {

            primsPair rPair = pq.remove(); // O(log(n))

            // cycle check
            if(vis[rPair.vtx]) continue;

            // graph generation
            if(rPair.par != -1) {

                // it will not add src edge bcz we used -1 parent in that which does not exists
                addEdge(primsGraph, rPair.vtx, rPair.par, rPair.weight);
                EdgeCount++;
            }

            // mark
            vis[rPair.vtx]  = true;

            // insert all unvisited nbrs
            for(Edge e : graph[rPair.vtx]) {

                if(!vis[e.v]) {
                    pq.add(new primsPair(e.v, rPair.vtx, e.w));
                }
            }
        }
        display(N,primsGraph);
    }

    // optimized one 
    // in this the same edges with higher weight are not added
    public static void prims_02(int src, int N, ArrayList<Edge>[] graph) {
        
        ArrayList<Edge>[] primsGraph = new ArrayList[N];
        for(int i=0;i<N;i++) primsGraph[i] = new ArrayList<>();
        
        boolean[] vis = new boolean[N];
        int[] costArray = new int[N];
        Arrays.fill(costArray, (int) 1e9);

        PriorityQueue<primsPair> pq = new PriorityQueue<> ();
        pq.add(new primsPair(src,-1,0));
        costArray[src] = 0;

        int EdgeCount = 0;
        while(EdgeCount != N-1) {

            primsPair rPair = pq.remove();

            // cycle check
            if(vis[rPair.vtx]) continue;

            if(rPair.par != -1) {

                addEdge(primsGraph, rPair.vtx, rPair.par, rPair.weight);
                EdgeCount++;
            }

            // mark 
            vis[rPair.vtx] = true;

            // insert all unvisited nbrs
            for(Edge e : graph[rPair.vtx]) {
                if(!vis[e.v] && e.w < costArray[e.v]) {
                    costArray[e.v] = e.w;

                    pq.add(new primsPair(e.v, rPair.vtx, e.w));
                }
            }
        }
        display(N, primsGraph);
    }


    // --------------------------------DIJKSTRA---------------------------
    public static class dijPair implements Comparable<dijPair> {

        int vtx = 0;
        int par = 0; // other connecting vtc
        int weight = 0;
        int wsf = 0; // weight so far

        dijPair(int vtx,int par, int weight, int wsf) {
            this.vtx = vtx;
            this.par = par;
            this.weight = weight;
            this.wsf = wsf;
        }

        @Override
        public int compareTo(dijPair other) {

            return this.wsf - other.wsf;
        }
    }

    // code is same as prims Code but only 1 condition changes (in dijkstra_2())
    public static void dijkstra(int src, int N, ArrayList<Edge>[] graph) {
        
        ArrayList<Edge>[] dijGraph = new ArrayList[N];
        for(int i = 0; i < N;i++) dijGraph[i] = new ArrayList<> ();

        boolean vis[] = new boolean[N];

        PriorityQueue<dijPair> pq = new PriorityQueue<> ();
        pq.add( new dijPair(src, -1, 0, 0));

        int EdgeCount = 0;

        while(EdgeCount != N-1) {

            dijPair rPair = pq.remove();

            // cycle check
            if(vis[rPair.vtx]) continue;

            if(rPair.par != -1) {

                addEdge(dijGraph, rPair.vtx, rPair.par, rPair.weight);
                EdgeCount++;
            }

            // mark
            vis[rPair.vtx] = true;

            // insert all unvisited nbrs
            for(Edge e : graph[rPair.vtx]) {

                if(!vis[e.v] ) {

                    pq.add(new dijPair(e.v, rPair.vtx, e.w, rPair.wsf + e.w));
                    // weight so far of current edge will be weight from where its coming + own weight
                }
            } 
        }
        display(N, dijGraph);
    }

    public static void dijkstra_02(int src, int N, ArrayList<Edge>[] graph) {
        
        ArrayList<Edge>[] dijGraph = new ArrayList[N];
        for(int i = 0; i < N;i++) dijGraph[i] = new ArrayList<> ();

        boolean vis[] = new boolean[N];
        int []costArray = new int[N];
        Arrays.fill(costArray, (int) 1e9);

        PriorityQueue<dijPair> pq = new PriorityQueue<> ();
        pq.add( new dijPair(src, -1, 0, 0));
        costArray[src] = 0;

        int EdgeCount = 0;

        while(EdgeCount != N-1) {

            dijPair rPair = pq.remove();

            // cycle check
            if(vis[rPair.vtx]) continue;

            if(rPair.par != -1) {

                addEdge(dijGraph, rPair.vtx, rPair.par, rPair.weight);
                EdgeCount++;
            }

            // mark
            vis[rPair.vtx] = true;

            // insert all unvisited nbrs
            for(Edge e : graph[rPair.vtx]) {

                if(!vis[e.v] && rPair.wsf + e.w < costArray[e.v]) {
                    // wsf from curr edge is less than stored weight in costArray[] then change
                    costArray[e.v] = rPair.wsf = e.w;

                    pq.add(new dijPair(e.v, rPair.vtx, e.w, rPair.wsf + e.w));
                }
            } 
        }
        display(N, dijGraph);
    }

    public static void solve() {

        int N = 11;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for(int i = 0; i < N;i++) graph[i] = new ArrayList<> ();

        addEdge(graph,0,1,4);
        addEdge(graph,0,7,8);
        addEdge(graph,1,2,8);
        addEdge(graph,1,7,11);
        addEdge(graph,2,8,2);
        addEdge(graph,2,5,4);
        addEdge(graph,2,3,7);
        addEdge(graph,8,6,6);
        addEdge(graph,8,7,7);
        addEdge(graph,6,5,2);
        addEdge(graph,5,3,14);
        addEdge(graph,5,4,10);
        addEdge(graph,3,4,9);
        addEdge(graph,7,6,1);
        
        addEdge(graph,9,10,2);
        addEdge(graph,4,10,1);

        display(N,graph);

        prims(0, N,graph);

        prims_02(0, N,graph);

        dijkstra(10, N, graph);
    }

    public static void main(String[] args) {

        solve();
    }

}