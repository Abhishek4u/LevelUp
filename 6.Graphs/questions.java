import java.util.*;
public static class questions {


    // Leetcode 200. Number of Islands
    // https://leetcode.com/problems/number-of-islands/
    int n = 0;
    int m = 0;
    int dir[][] = { {0,1}, {0,-1}, {-1,0}, {1,0}};
    public void dfs(char[][] grid, int i, int j) {
        
        grid[i][j] = '0';
        
        for(int d[] : dir) {
            int r = i + d[0];
            int c = j + d[1];
            
            if(r>= 0 && c >= 0 &&  r < n && c < m && grid[r][c] == '1') {
                
                dfs(grid,r,c);
            } 
        }
    }
    
    public int numIslands(char[][] grid) {
        if(grid.length == 0  || grid[0].length == 0) return 0;
        
        n = grid.length;
        m = grid[0].length;
        int count = 0;
        for(int i = 0; i < n ;i++) {
            for(int j = 0 ; j < m ;j++) {
                
                if(grid[i][j] == '1') {
                    count++;
                    dfs(grid,i,j);
                    
                }
            }
        }
        
        return count;
    }

    // Leetcode 463 Island Perimeter
    // https://leetcode.com/problems/island-perimeter/

    // 1. My Approach
    public int islandPerimeter(int[][] grid) {
        if(grid.length == 0) return 0;
        
        int n = grid.length, m = grid[0].length;
        int maxPerimeter = 0;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0;j < m;j++) {
                if(grid[i][j] != 0) {
                    maxPerimeter = floodFill(grid,i,j);
                    return maxPerimeter;
                    // all islands are connected and value is calculated so return from here
                }
            }
        }
        return maxPerimeter;
        // no island found so return 0
    }
    
    public int floodFill(int[][] grid,int i,int j) {
        
        grid[i][j] = 2;
        int dir[][] = { {-1,0},{1,0},{0,-1},{0,1}};
        
        int count = 4;// default boundary count where the current island part is surrounded by water
        int overAllPerimeter = 0;
        for(int[] d :dir) {
            int r = i + d[0];
            int c = j + d[1];
            
            if(r >= 0 && r < grid.length && c >= 0 && c < grid[0].length) {
                
                if(grid[r][c] == 1) {
                    count--; 
                    // decrease count bcz it is connected with other part
                    // and no boundary could be installed here
                    overAllPerimeter += floodFill(grid,r,c);
                    
                } else if(grid[r][c] == 2) {
                    count--;
                    // decrease count you are connected to previous part 
                    // from where you are called in recursion
                }
            } 
        }
        return overAllPerimeter + count;
        // return calculated perimeter in recursion and self perimeter
    }

    // 2. Sirs Approach
    public int islandPerimeter(int[][] grid) {
        
        if(grid.length == 0 || grid[0].length == 0) return 0;
        
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        
        int count = 0, nbrs = 0;
        
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m; j++) {
                
                if(grid[i][j] == 1) {
                    count++;
                    
                    for(int d[] : dir) {
                        int r = i + d[0];
                        int c = j + d[1];
                        
                        if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                            nbrs++;
                            // count no of neighbours
                        } 
                    }
                }
            }
        }
        
        int totalPerimeter = count*4; // bcz for square we have 4 boundaries
        
        // Now subtract no of neighbours and it will give required perimeter
        
        return totalPerimeter - nbrs;
    }

    // Leetcode 695. Max Area of Island
    // https://leetcode.com/problems/max-area-of-island/

    // 1. My Approach
    public int maxAreaOfIsland(int[][] grid) {
        
        if(grid.length == 0) return 0;
        
        int n = grid.length, m = grid[0].length;
        
        int maxArea = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0;j < m;j++) {

                if(grid[i][j] != 0 && grid[i][j] != 2) {
                    
                    maxArea = Math.max(floodFill(grid,i,j), maxArea);
                }
            }
        }
        return maxArea;
    }
    
    public int floodFill(int[][] grid,int i,int j) {
        
        grid[i][j] = 2;
        int dir[][] = { {-1,0},{1,0},{0,-1},{0,1}};
        
        int count = 1;// count self too in area so make value 1
        for(int[] d :dir) {
            int r = i + d[0];
            int c = j + d[1];
            
            if(r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == 1) {
                
                count += floodFill(grid,r,c);
            } 
        }
        return count;
    }

    // Sir's Approach
    int n = 0;
    int m = 0;
    public int dfsIsland(int[][] grid, int i, int j) {
        
        grid[i][j] = 0;
        int count = 1;
        
        int dir[][] = {{0,1},{0,-1},{1,0},{-1,0}};
        
        for(int d[] : dir) {
            int r = i + d[0];
            int c = j + d[1];
            
            if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                
                count += dfsIsland(grid,r,c);
                // count no of islands
            }
        }
        return count;
    }
    
    public int maxAreaOfIsland(int[][] grid) {
        
        if(grid.length==0 || grid[0].length==0) return 0;
        
        n = grid.length;
        m = grid[0].length;
        
        int maxArea = 0;
        for(int i = 0;i < n;i++) {
            for(int j = 0; j < m; j++) {
                
                if(grid[i][j] == 1) {
                    
                    maxArea = Math.max(maxArea, dfsIsland(grid,i,j));
                }
            }
        }
        return maxArea;
    }

    // Leetcode 130. Surrounded Regions
    // https://leetcode.com/problems/surrounded-regions/
    public static void sorroundedRegion(int i, int j, char[][] board) {
        
        board[i][j] = '$';
        int n = board.length;
        int m = board[0].length;
        int dir[][] = {{0,-1},{0,1},{1,0},{-1,0}};
        
        for(int d[]:dir) {
            
            int r = i + d[0];
            int c = j + d[1];   
            if(r >= 0 && c >= 0 && r < n && c < m && board[r][c] == 'O') {
                
                sorroundedRegion(r,c,board);
            }
        }
    }
    
    public void solve(char[][] board) {
        
        if(board.length == 0 || board[0].length == 0) return;
        
        int n = board.length;
        int m = board[0].length;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                
                if(i == 0 || i == n-1 || j == 0 || j == m-1 ) {
                    // for boundary 0's flood fill them with another char
                    if(board[i][j] == 'O') {
                        sorroundedRegion(i,j,board);
                    }
                }
            }
        }
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < m;j++) {
                
                if(board[i][j] == 'O') { 
                    board[i][j] = 'X';
                    
                } else if(board[i][j] == '$') {
                    // correct again flood filled O's
                    board[i][j] = 'O';
                }
            }
        }
    }

    // Leetcode 785. Is Graph Bipartite
    // https://leetcode.com/problems/is-graph-bipartite/

    public boolean isBipartite_(int[][] graph,int prevColor[],int src) {
        
        // 0. Red ,1. Green
        
        ArrayDeque<Integer> que = new ArrayDeque<> ();
        que.addLast(src);
        
        int color = 0;
        while(que.size() > 0) {
            int size = que.size();
            
            while(size-- > 0) {
                
                int vtx = que.removeFirst();
                
                if(prevColor[vtx] != -1 && prevColor[vtx] != color) {
                    return false;
                }
                prevColor[vtx] = color; // for marking with current color
                
                for(int e: graph[vtx]) {
                    // traversing in 1d array of 2darray and getting elt from that array
                    if(prevColor[e] == -1) {
                        // add unvisited elts
                        que.addLast(e);
                    }
                }
            }
            color = (color + 1) % 2; // for toggling the color/level
        }
        return true;
    }
    
    public boolean isBipartite(int[][] graph) {
    
        // Run loop using vis[] bcz graph can have multiple components
        int n = graph.length;
        int prevColor[] = new int[n]; // visited and color marking array
        // -1 Not visited , 0 red color, 1 green color
        Arrays.fill(prevColor,-1);
        
        for(int i = 0 ;i < n; i++) {
            if(prevColor[i] == -1) {
                
                if(!isBipartite_(graph,prevColor,i)) {
                    // mismatch color found when traversed from diff paths
                    return false;
                }
            }
        }
        
        return true;
    }

    // Leetcode 994. Rotting Oranges
    // https://leetcode.com/problems/rotting-oranges/

    // 1. Using array
    public int orangesRotting(int[][] grid) {
        
        int n = grid.length;
        int m = grid[0].length;
        
        ArrayDeque<int[]> que = new ArrayDeque<> ();
        int freshOranges = 0;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0;j < m;j++) {
                
                if(grid[i][j] == 2) {
                    
                    que.addLast(new int[]{i,j});
                    
                } else if(grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }
       
        if(freshOranges == 0) return 0;
        // if no oranges are there then return from here otherwise it will
        // give error bcz we returned in if condition in below code
        
        int dir[][] = { {0,-1}, {0, 1}, {1,0}, {-1,0} };
        int time = 0;
        while(que.size() != 0) {
            
            int size = que.size();
            while(size-- > 0) {
                
                int vtx[] = que.removeFirst();
                
                int r = vtx[0];
                int c = vtx[1];
                
                for(int d[] : dir) {
                    
                    int x = r + d[0];
                    int y = c + d[1];
                    
                    if(x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1) {
                        
                        grid[x][y] = 2; // mark rotten
                        freshOranges--;
                       
                        que.addLast( new int[]{x,y});
                        
                        if(freshOranges == 0) {
                        
                            return time + 1;
                            // return +1 bcz we are returning from here  to save the
                            // time other wise put this return condition in last
                        }
                    }
                }
            }
            time++;
        }
        return -1;
        // some oranges are left so return -1
    }

    // 2. Using index
    public int orangesRotting(int[][] grid) {
        
        int n = grid.length, m = grid[0].length;
        
        ArrayDeque<Integer> que = new ArrayDeque<> ();
        int freshOranges = 0;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m;j++) {
                
                if(grid[i][j] == 2) {
                    que.add(i*m + j);
                    
                } else if(grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }
        
        if(freshOranges == 0) return 0;
        int time = 0;
        
        int dir[][] ={ {-1,0}, {0,1}, {1,0}, {0,-1}};
        while(que.size() != 0) {
            
            int size = que.size();
            
            while(size-- > 0) {
                
                int vtx = que.removeFirst();
                
                int r = vtx/m;
                int c = vtx%m;
                
                for(int d[]: dir) {
                    
                    int x = r + d[0];
                    int y = c + d[1];
                    
                    if(x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1) {
                        
                        grid[x][y] = 2;
                        freshOranges--;
                        
                        que.addLast(x*m + y);
                        
                    }
                }
            }
            time++;
        }
        
        return freshOranges == 0 ? time - 1 : -1;
    }

    // Leetcode 1091. Shortest Path in Binary Matrix
    // https://leetcode.com/problems/shortest-path-in-binary-matrix/

    // 1. My Solution

    public int shortestPathBinaryMatrix(int[][] grid) {
        // Corner cases
        if(grid.length == 0 || grid[0].length == 0) return 0;
        
        int n = grid.length, m = grid[0].length;
        
        if(grid[0][0] == 1 || grid[n-1][m-1] == 1) return -1;
        // if no path is possible to traverse
        
        if(grid.length == 1 && grid[0][0] == 0) return 1;
        // 1 step to take to reach destination
        
        // Generic code starts from here
        ArrayDeque<int[]> que = new ArrayDeque<> ();
        que.add(new int[]{0,0});
        
        
        int level = 1; 
        // start from 1 because you want to count steps till bottom right and
        // 1 signifies that we have taken step for (0,0) elt
        int dir[][] = { {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1,-1}, {0, -1}, {-1,-1} };
        
        while(que.size() > 0) {
            
            int size = que.size();
            level += 1;
            while(size-- > 0) {
                
                int vtx[] = que.removeFirst();
                
                int r = vtx[0];
                int c = vtx[1];
                
                for(int d[] : dir) {
                    
                    int x = r + d[0];
                    int y = c + d[1];
                    
                    if(x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 0) {
                        
                        grid[x][y] = 1;
                        que.addLast(new int[] {x,y});
                        
                        
                    }
                    if(x == n-1 && y == n-1) {
                            return level;
                            
                        }
                }
                
            }
        }
        return -1; // if no path found
    }

    // 2. Sir's Approach
    public int shortestPathBinaryMatrix(int[][] grid) {
        
        if(grid.length == 0 || grid[0].length == 0) return 0;
        
        int n = grid.length, m = grid[0].length;
        
        if(grid[0][0] == 1 || grid[n-1][m-1] == 1) return -1;
        // if no path is possible to traverse
        
        ArrayDeque<int[]> que = new ArrayDeque<> ();
        
        que.add(new int[]{0,0});
        grid[0][0] = 1; // 1st elt is visited
        
        int level = 1; 
        // start from 1 because we want to count steps till bottom right and
        // 1 signifies that we have taken step for (0,0) elt
        
        int dir[][] = { {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1,-1}, {0, -1}, {-1,-1} };
        
        while(que.size() > 0) {
            
            int size = que.size();
            while(size-- > 0) {
                
                int rvtx[] = que.removeFirst();
                
                int r = rvtx[0];
                int c = rvtx[1];
                
                if(r == n-1 && c == n-1) {
                    // when it reached 1st time return
                    return level;

                }
                for(int d[] : dir) {
                    
                    int x = r + d[0];
                    int y = c + d[1];
                    
                    if(x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 0) {
                        
                        grid[x][y] = 1;
                        que.addLast(new int[] {x,y});
                        // traverse bfs wise   
                    }  
                }  
            }
            level++;
        }
        return -1; // if no path found
    }

    // Leetcode 542
    // https://leetcode.com/problems/01-matrix/

    // 1. My Approach
    public int[][] updateMatrix(int[][] matrix) {
        
        if(matrix.length == 0 || matrix[0].length == 0) return matrix;

        int n = matrix.length, m = matrix[0].length;
        
        ArrayDeque<int[]> que = new ArrayDeque<> ();
        int ans[][] = new int[n][m];
        
        for(int i = 0; i< n; i++) {
            for(int j = 0;j < m ;j++) {
                
                if(matrix[i][j] == 0) {
                    que.add(new int[] {i,j});
                }
            }
        }
        int dir[][] = { {0,1}, {0,-1}, {1,0}, {-1,0} };
        
        int level = 1;
        while(que.size() > 0) {
            
            int size = que.size();
            while(size-- > 0) {
                
                int rVtx[] = que.removeFirst();
                
                int r = rVtx[0];
                int c = rVtx[1];
                
                for(int d[]: dir) {
                    
                    int x = r + d[0];
                    int y = c + d[1];
                    
                    if(x >= 0 && y >= 0 && x < n && y < m && matrix[x][y] == 1) {
                        
                        matrix[x][y] = 2; // put different value
                        ans[x][y] = level;
                        que.addLast(new int[]{x,y});
                    } 
                }
            }
            level++;
        }
        return ans;
    }

    // 2. Sir's Approach
    // Here we used ans(vis) array to fill 1 locations as -1 due to which we can save 
    // our original array

    public int[][] updateMatrix(int[][] matrix) {
        
        if(matrix.length == 0 || matrix[0].length == 0) return matrix;

        int n = matrix.length, m = matrix[0].length;
        
        ArrayDeque<int[]> que = new ArrayDeque<> ();
        
        int vis[][] = new int[n][m];
        for(int a[]: vis) {
            Arrays.fill(a,-1);
        }
       
        int countOnes = 0;
        
        for(int i = 0; i< n; i++) {
            for(int j = 0;j < m ;j++) {
                
                if(matrix[i][j] == 0) {
                    
                    que.add(new int[] {i,j});
                    vis[i][j] = 0; // make 0's 0 in vis[][]
                    
                } else if(matrix[i][j] == 1) {
                    countOnes++;
                }
            }
        }
        int dir[][] = { {0,1}, {0,-1}, {1,0}, {-1,0} };
        
        int level = 1;
        while(que.size() > 0) {
            
            int size = que.size();
            while(size-- > 0) {
                
                int rVtx[] = que.removeFirst();
                
                int r = rVtx[0];
                int c = rVtx[1];
                
                for(int d[]: dir) {
                    
                    int x = r + d[0];
                    int y = c + d[1];
                    
                    if(x >= 0 && y >= 0 && x < n && y < m && vis[x][y] == -1) {
                        
                        vis[x][y] = level;
                        
                        countOnes--;
                        // decrease count when you got number
                                             
                        que.addLast(new int[]{x,y});
                    } 
                }
            }
            level++;
            if(countOnes == 0) break;
            // will work efficiently
        }
        return vis;
    }


    // Leetcode 1020. Number of Enclaves
    // https://leetcode.com/problems/number-of-enclaves/

    // 1. Using DFS
    public  void dfsFill(int A[][], int i, int j) {
        
        A[i][j] = 2; // mark with false info 
        
        int dir[][] = { {0,1}, {0, -1}, {1,0}, {-1,0} };
        
        for(int d[] : dir){
            
            int x = i + d[0];
            int y = j + d[1];
            
            if(x >= 0 && y >= 0 && x < A.length && y < A[0].length && A[x][y] == 1) {
                
                dfsFill(A,x,y);
            }
        }
    }
    
    public int numEnclaves(int[][] A) {
        
        int n = A.length, m = A[0].length;
        
        for(int i = 0;i < n; i++) {
            for(int j = 0;j < m; j++) {
                if( (i == 0 || i == n-1 || j == 0 || j == m-1) && A[i][j] == 1) {
                    
                    A[i][j] = 2; 
                    // most important in bfs ans dfs if you do not mark
                    // this than recursion can go back again to this state 
                    
                    dfsFill(A,i,j);
                }
            }
        }
        int countOnes = 0;
        for(int i = 0;i < n;i++) {
            for(int j = 0; j< m;j++) {
                
                if(A[i][j] == 1) {
                    countOnes++;
                }
            }
        }
        
        return countOnes;
    }

    // 2. Using BFS
    public int numEnclaves(int[][] A) {
        
        int n = A.length, m = A[0].length;
        
        ArrayDeque<Integer> que = new ArrayDeque<> ();
        int ones = 0;
        
        for(int i = 0 ;i < n; i++) {
            for(int j = 0; j < m ;j++) {
                
                ones += A[i][j];
                // It will only plus one values because other values are 0 
                // and that will not make any effect on count value
                
                if((i == 0 || j == 0 || i == n-1 || j == m-1) && A[i][j] == 1) {
                    
                    A[i][j] = 0;
                    // most important in bfs ans dfs if you do not mark
                    // this than recursion can go back again to this state 
                    
                    que.addLast( i*m + j);  
                    ones --;
                    // remove the boundary ones
                }
            }
        }
        if(ones == 0) return 0;
        int dir[][] = { {0,1}, {0,-1}, {-1,0}, {1,0} };
        
        while(que.size() > 0) {
            int size = que.size();
            
            while(size-- > 0) {
                
                int rVtx = que.removeFirst();
                
                int r = rVtx / m;
                int c = rVtx % m;
                
                for(int d[] : dir) {
                    
                    int x = r + d[0];
                    int y = c + d[1];
                    
                    if(x >= 0 && y >= 0 && x < n && y < m && A[x][y] == 1) {
                        
                        A[x][y] = 0;
                        que.addLast( x*m + y);
                        
                        ones--;
                        // remove the boundary flood filled ones
                    }
                }
            }
            if(ones == 0) break;
            // if no one is left then break
        }
        
        return ones;
    }
}