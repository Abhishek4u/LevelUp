public class l003_unionFind{

    public static class Edge{
        int v = 0;
        int w = 0;

        Edge(int v,int w){
            this.v = v;
            this.w = w;
        }
    }

    static int N = 7;
    static ArrayList<Edge>[] graph = new ArrayList[N];
    static ArrayList<Edge>[] unionGraph = new ArrayList[N];

    public static void addEdge(int u,int v,int w){
        
        unionGraph[u].add(new Edge(v,w));
        unionGraph[v].add(new Edge(u,w));
    }

    //=======================================================================

    static int[] par; // parent[]
    static int[] size; // size[] (Not needed in code)

    // PATH COMPRESSION AND FIND PARENT FXN
    public static int findPar( int u) {

        if(u == par[u]) {
            return u;
            // he is the president
        }

        else {
            // otherwise find the globalParent by querying in  parent stored in par[u]
            // and update your parent
            return par[u] = findPar(par[u]);
            // THIS LINE IS KNOWN AS PATH COMPRESSION
        }

    }  
    // graph input is like { {u,v,w}, ...}
    public static void unionFind(int[][] graph) {

        for(int i = 0;i < N;i++) unionGraph[i] = new ArrayList<>();
        // initializing graph

        for(int i = 0; i < N;i++) {

            par[i] = i;
            // firstly make all vtx as their own parent/president 
        }

        for(int[] a: graph) {

            int u = a[0];
            int v = a[1];
            int w = a[2];

            int p1 = findPar(u);
            int p2 = findPar(v);

            // merging condition
            if(p1 != p2) {

                addEdge(u,v,w);
                // to create the graph create when merging the vtxes

                par[p1] = p2;
                // do not check for size, simply update parent of 
                // any vertex to save time in writing the code
            }
        }
    }

    public static void  merge(int p1,int p2) {

        if(size[p2] > size[p1]) {

            par[p1] = p2; // change the parent of p1 to p2
            size[p2] += size[p1];

        } else if(size[p1] > size[p2]) {

            par[p2] = p1; // change the parent of p2 to p1
            size[p1] += size[p1];
        }
    }

    // if you want to use merge using comparing then use this 
    // otherwise upper on is smaller and better to understand
    public static void unionFind2_withMergeFxn(int[][] graph) {
    
        for(int i = 0;i < N;i++) unionGraph[i] = new ArrayList<>();
        // initializing graphfor(int i = 0;i < N;i++) graph[i] = new ArrayList<>();

        for(int i = 0; i < N;i++) {

            par[i] = i;
            // firstly make all vtx as their own parent/president 
        }

        for(int[] a: graph) {

            int u = a[0];
            int v = a[1];
            int w = a[2];

            int p1 = findPar(u);
            int p2 = findPar(v);

            // merging condition
            if(p1 != p2) {

                addEdge(u,v,w);
                
                merge(p1,p2); 
            }
        }
    }

    // {u,v,w}
    // Minimum No of Edges
    public static void kruskalAlgo(int[][] graph) {

        for(int i = 0;i < N;i++) unionGraph[i] = new ArrayList<>();
        // initializing graph

        Arrays.sort(graph, (a,b) -> {
            return a[2] - b[2]; // this - other,default, Increasing
            // return b[2]-a[2]; // other - this, reverse of default, Decreasing
        })
        // sorting a/weight

        for(int i = 0; i < N;i++) par[i] = i;

        for(int[] a : graph) {

            int p1 = findPar(u);
            int p2 = findPar(v);

            if(p1 != p2) {

                addEdge(a[0],a[1],a[2]);
                merge(p1,p2);
                // merging using comparing is imp in kruskal algo
            }

        }
    }
}