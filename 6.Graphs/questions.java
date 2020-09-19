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

    
}