public class questions2 {

    public class Edge {
        int v;
        int w;
        
        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    public class pair {
        int src = 0;
        int k = 0;
        int wsf = 0;
        
        pair(int src, int wsf,int k) {
            this.src = src;
            this.wsf = wsf;
            this.k = k;
        }
    }
    
    // Leetcode 787. Cheapest Flights Within K Stops 
    // https://leetcode.com/problems/cheapest-flights-within-k-stops/

    // ( Sep 24) Using Dijkstra
    // 1. Using pair and edge class
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    
        ArrayList<Edge>[] gp = new ArrayList[n];
        for(int i = 0 ;i < n;i++) gp[i] = new ArrayList<> ();
        
        for(int a[] : flights) {
            
            int u = a[0], v = a[1], w = a[2];    
            gp[u].add(new Edge(v,w));
        }
        PriorityQueue<pair> pq = new PriorityQueue<> ( (a,b) -> {
            return a.wsf - b.wsf;
        });
        pq.add(new pair(src,0,k+1));
        // src, price, k+1(no of edges required to reach dest)
        
        //DIJKSTRA (BFS)
        
        while(pq.size() > 0) {
            pair vtx = pq.poll();
                        
            if(vtx.k < 0) continue;
            // not possible through current path bcz k < 0
            
            if(vtx.src == dst) return vtx.wsf;
            // in dijkstra we get min weight path so return it no need to compare
            
            for(Edge e : gp[vtx.src]) {
                pq.add(new pair(e.v,vtx.wsf + e.w, vtx.k - 1));
            }
        }
        return -1;
        // no path found with at max k points
    }

    // 2. Using int[] only

    public int findCheapestPrice(int n, int[][] flights, int s, int dst, int K) {
        // u,v,w
        ArrayList<int[]>[] gp = new ArrayList[n];
        for(int i=0;i<n;i++) gp[i] = new ArrayList<>();

        for(int[] a: flights) gp[a[0]].add(new int[]{a[1],a[2]});

        // src, wsf , k
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            return a[1] - b[1];
        });

        pq.add(new int[]{s,0,K+1});
        while(pq.size()!=0){

            int[] vtx = pq.poll();
            int src = vtx[0];
            int wsf = vtx[1];
            int k = vtx[2]; 

            if(k < 0) continue;
            if(src == dst) return wsf;

            for(int[] e: gp[src]){
                pq.add(new int[]{e[0], wsf + e[1], k - 1});
            }
        }

        return -1;
    }

    // Leetcode 743. Network Delay Time
    // https://leetcode.com/problems/network-delay-time/

    // ( Sep 24) Using Dijkstra
    public int networkDelayTime(int[][] times, int N, int K) {
        
        // v,w
        ArrayList<int[]> [] gp = new ArrayList[N+1];// bcz vtx starts from 1
        for(int i = 0; i <= N; i++) gp[i] = new ArrayList<> ();
        
        // u , v, w
        for(int[] a: times) gp[a[0]].add(new int[] {a[1],a[2]});
        
        // src, time
        PriorityQueue<int[]> pq = new PriorityQueue<> ((a,b) -> {
            return a[1] - b[1]; // a/time
        });
        
        int []dis = new int[N+1]; // distance array
        Arrays.fill(dis,(int) 1e8);
        
        pq.add(new int[] {K,0});
        dis[K] = 0; // for starting vtx
        
        
        // DIJKSTRA
        while(pq.size() > 0) {
            
            int []vtx = pq.poll();
            int src = vtx[0];
            int time = vtx[1];
            
            for(int[] e : gp[src]) {
                if(time + e[1] < dis[e[0]]) {
                    
                    dis[e[0]] = time + e[1];
                    // distance of v is time + w(time)
                    pq.add(new int[] { e[0], time + e[1]});
                }
            }
        }
        
        // find max distance
        int maxTime = 0;
        for(int i = 1;i<=N;i++) {
            // start from 1 bcz 0 is source vtx
            int ele = dis[i];
            
            if(ele == (int) 1e8) return -1;
            // all vtx are not reachable (directed path and some vtx left if src is in center)
            
            maxTime = Math.max(maxTime, ele);
        }
        return maxTime;
    }

    /* for you: ------------------>> leetcode 886
    Do this------------------------------------------
    -----------------------------------------
    -----------------------------------------
    -------------------------------
    ------------------------------
    ------------------------------
    -
    - */

    //Leetcode : 778 (DO yourself)
    public int swimInWater(int[][] grid) {
        if(grid.length==0 || grid[0].length == 0) return 0;
        
        int n = grid.length;
        int m = grid[0].length;
        
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            return grid[a/m][a%m] - grid[b/m][b%m];
        });
        
        int maxTime = 0;
        pq.add(0);
        
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        boolean[][] vis = new boolean[n][m];
        vis[0][0] = true;
        
        while(pq.size()!=0){
        int val = pq.poll();
        
        maxTime = Math.max(maxTime,grid[val/m][val%m]);
        if(val/m == n-1 && val%m == m-1) return maxTime;
        
        for(int d=0;d<4;d++){
            int r = val/m + dir[d][0];
            int c = val%m + dir[d][1];
            
            if(r>=0 && c>=0 && r< n && c< m && !vis[r][c]){
                vis[r][c] = true;  
                pq.add(r*m + c);
            }
        }
        }
        
        return -1;
        
    }
}