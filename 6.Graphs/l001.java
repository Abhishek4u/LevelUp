import java.util.ArrayList;
import java.util.LinkedList;
public class l001 {

    public static class Edge{

        int v = 0; // vertex
        int w = 0; // weight

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) {

        // solve();
        solve2();
    }

    public static void solve() {

        constructGraph();

        // removeVtx(1);

        boolean []vis = new boolean[N];
        // System.out.println(hasPath(0,6,vis));

        // allPath(0,6,vis,0,"");

        // pair ans = heavyWeightPath(0,6,vis);
        // System.out.println(ans.weight + " " + ans.path);

        // System.out.println(hamiltonianPath(0,0,0,vis,""));
        // origin source, start node,vtx traversed count,vis[], ans string

        System.out.println(GCC());
    }

    public static void solve2() {

        constructGraph();
        boolean vis[] = new boolean[N];
        // BFS_01(0,vis);
        BFS_Levelwise(0,vis);
    }

    public static void addEdge(int u, int v, int w) {

        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
    }

    static int N = 7;
    static ArrayList<Edge>[] graph = new ArrayList[N];

    public static void constructGraph() {

        for(int i = 0;  i < graph.length;i++) {
            graph[i] = new ArrayList<> ();
        }

        addEdge(0,1,10);
        addEdge(0,3,10);
        addEdge(1,2,10);
        addEdge(2,3,40);
        addEdge(3,4,2);
        addEdge(4,5,2);
        addEdge(5,6,3);
        addEdge(4,6,8);

        addEdge(0,6,1);
        addEdge(2,5,1);
    }

    public static int findEdge(int u, int v) {

        int idx = -1;

        for(int i = 0; i < graph[u].size() ;i++) {

            Edge e = graph[u].get(i);

            if(e.v == v) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    public static void removeEdge(int u, int v) {

        int idx = findEdge(u,v);

        if(idx != -1) {

            graph[u].remove(idx);
        }

        idx = findEdge(v,u);
        if(idx != -1) {

            graph[v].remove(idx);
        }
    }

    public static void removeVtx(int u) {

        // run reverse loop bcz shifting can haapen and all the elts
        // will not be removed
        while(graph[u].size() != 0) {

            Edge e = graph[u].get(graph[u].size() - 1);

            removeEdge(u,e.v);
        }
    }

    public static boolean hasPath(int src, int dest, boolean vis[]) {

        if(src == dest) return true;

        vis[src] = true;
        boolean res = false;
        for(Edge e : graph[src] ) {

            if(!vis[e.v]) {

                res = res || hasPath(e.v,dest, vis);
            }
        }

        return res;
    }

    public static int allPath(int src,int dest, boolean vis[], int weight,String ans) {

        if(src == dest) {

            System.out.println(ans + src + " @ " + weight);
            return 1;
        }

        vis[src] = true;
        int count = 0;
        for(Edge e : graph[src]) {

            if(!vis[e.v]) {

                count += allPath(e.v, dest, vis, weight + e.w, ans + src + " ");
            }
        }

        vis[src] = false;

        return count;
    }

    public static class pair{

        int weight = 0;
        String path = "";

        pair(int wt,String path) {
            this.weight = wt;
            this.path = path;
        }
    }

    public static pair heavyWeightPath(int src,int dest, boolean[] vis) {

        if(src == dest) {

            return new pair(0, src + "" );
        }

        int maxWt = Integer.MIN_VALUE;
        String path = "";
        
        vis[src] = true;
        for(Edge e: graph[src]) {

            if(!vis[e.v]) {

                pair p = heavyWeightPath(e.v,dest, vis);

                if(p.weight + e.w > maxWt) {
                    // compare by adding edge value to p node from src and 
                    // if val is max then update

                    path = src + " " + p.path; // add ownself in maxwt path
                    maxWt = p.weight + e.w;
                }
            }
        }
        vis[src] = false;

        return new pair(maxWt, path);
    }

    public static int hamiltonianPath(int osrc,int src,int vtxCount, boolean[] vis, String ans) {

        if(vtxCount == vis.length - 1) {

            int idx = findEdge(src,osrc);

            if(idx != -1) {
                System.out.println("Cycle found : " + ans + src);

            } else {
                System.out.println("Path found : " + ans + src);
            }
            return 1;
        }

        vis[src] = true;
        int count = 0;
        for(Edge e : graph[src]) {

            if(!vis[e.v]) {

                count += hamiltonianPath(osrc, e.v, vtxCount + 1,vis, ans + src + " ");
            }
        }

        vis[src] = false;

        return count;
    }

    public static int dfs(int src,boolean[] vis) {

        vis[src] = true;
        int count = 0;
        for(Edge e : graph[src]) {

            if(!vis[e.v]) {

                count += dfs(e.v,vis);
            }
        }

        return count + 1; // +1 bcz own vtx count
    }

    // Get Connected Components
    public static int GCC() {

        boolean vis[] = new boolean[N];
        int count = 0;

        int totalArea = 0;
        for(int i = 0 ; i < vis.length; i++) {

            if(!vis[i]) {
                count++; // count the component
                totalArea += dfs(i,vis);
            }

        }
        System.out.println(totalArea);
        return count; // no of different components in graph
    }

    
    //-----------------------------------BFS---------------------------

    public static void BFS_01(int src, boolean[]vis) {

        LinkedList<Integer> que = new LinkedList<>();

        que.addLast(src);

        while(que.size() > 0) {

            int vtx = que.removeFirst();

            if(vis[vtx]) {
                System.out.println("Cycle found at: " + vtx);
                continue; 
            //     // if you add again it will form cycle again
            }
            // System.out.print(vtx + " ");

            vis[vtx] = true; // mark the elt

            for(Edge e : graph[vtx]) {

                if(!vis[e.v]) {
                    que.addLast(e.v);
                }
            }

        }
    }

    public static void BFS_Levelwise(int src, boolean[] vis) {

        LinkedList<Integer> que = new LinkedList<> ();
        que.addLast(src );

        int level = 0;
        while(que.size() != 0) {

            int size = que.size();
            System.out.print("Level " + level + " : ");

            while(size-- > 0) {

                int vtx = que.removeFirst();

                if(vis[vtx]) {
                    // for not visiting cycle loop
                    continue;
                }
                System.out.print(vtx + " ");

                vis[vtx] = true; // mark

                for(Edge e: graph[vtx]) {

                    if(!vis[e.v]) {

                        que.addLast(e.v);
                    }
                }
            }
            level++;
            System.out.println();
        }
    }


}