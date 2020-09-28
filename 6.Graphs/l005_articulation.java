public class l005_articulation {

    static int low[];
    static int dis[];
    static int timeCount;
    static boolean vis[];
    static ArrayList<int[]>bridges;

    public static void DFS_AP(int src, int par) {

        low[src] = dis[src] = timeCount++;
        vis[src] = true;
        for(Edge e: graph[src]) {

            if(!vis[e.v]) {

                DFS_AP(e.v,src);
                low[src] = Math.min(low[src], low[e.v]); // update to lowest time known person from edges
                // if(dis[src] <= low[e.v ]) {
                //     AP[src] = true;
                // }

                if(dis[src] < low[e.v]) {
                    // vtx knows only src edge( which has smallest time edge)
                    // this is the only edge to vtx(critical edge)
                    bridges.add(new int[] {src,vtx});
                }
                
            } else if(e.v != par) {
                // for all edges except parent
                // update lowest(that arrived in less time) known person
                // by the discovery time of vtx edges (if they arrived 
                // before src then will be updated)
                low[src] = Math.min(low[src], dis[e.v]);
            }
        }
    }

    public static APoint_Bridges() {
        low = new int[N];
        dis = new int[N];
        vis = new boolean[N];
        bridges = new ArrayList<>();
        timeCount = 0;

        for(int i = 0;i < N;i++) {
            if(!vis[i]) {

                DFS_AP(i,-1);
            }
        }
    }
}