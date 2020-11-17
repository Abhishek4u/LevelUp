// Ques started from 2ndSep
// SS6590 ownwards

import java.util.Stack;
public class questionsStack {

    // NEXT GREATER ON RIGHT
    // FOundation Approach
    public int[] ngor(int[] arr) {

        int n = arr.length;
        Stack <Integer> st = new Stack<> ();

        int[] ans = new int[n];
        for(int i = n - 1; i >= 0; i--) {

            while(st.size() != 0 && st.peek() <= arr[i]) st.pop();

            if(st.size() == 0) ans[i] = n;
            else ans[i] = st.peek();

            st.push(arr[i]);
        }

        return ans;
    }

    // --------------Sir's Approach (WORKS FOR ALL SMALLER,GREATER ON ANY DIRECTION)-------------- 
    
    // THINK LIKE STUDENTS HAD REGISTERED IN JOB AGENCY AND WANT GREATER PACKAGE THAN CURRENT PACKAGE
    // SO WHEN THERE ARE HIGHER PACKAGE JOBS AVAILABLE THEY WILL CONTACT STUDENT WHICH ARE HAVING 
    // SMALLER PACKAGE THAN THE COMING COMPANY

    // move in same right direction and keep storing the indexes 
    // in stack and when bigger elt occurs then pop the smaller
    // and update their greater elt = current elt, then push own index
    
    // in starting we have filled ans[] with max value and those elts which are
    // still present in stack does not have any bigger elt then themselves on right so 
    // ans[] array already stores the max value so we do not need to process them
    public int[] ngor(int[] arr) {

        int n = arr.length;
        Stack <Integer> st = new Stack<> ();
        // st.push(-1);

        int ans[] = new int[n];
        Arrays.fill(ans, n);
        // already fill for larger elts as if stack has not updated some 
        // elts data then those elts will already have larger index 

        for(int i = 0; i < n; i++) {

            while(st.size() != 0 && arr[st.peek()] < arr[i]) 
                ans[st.pop()] = arr[i];

            st.push(i);
        }
        return ans;
    }

    // NEXT GREATER ON LEFT
    public int[] ngol(int[] arr) {

        int n = arr.length;
        int ans[] = new int[n];

        Stack<Integer> st = new Stack<> ();
        Arrays.fill(ans,-1);

        for(int i = n - 1; i >= 0; i--) {

            while(st.size() != 0 && arr[st.peek()] < arr[i]) 
                ans[st.pop()] = arr[i];

            st.push(i);
        }
        return ans;
    }

    // NEXT SMALLER ON RIGHT
    public int nsor(int[] arr) {

        int n = arr.length;
        int ans[] = new int[n];

        Stack<Integer> st = new Stack<> ();

        Arrays.fill(ans, -1);

        for(int i = 0; i < n; i++) {

            while(st.size() != 0 && arr[st.peek()] > arr[i] )
                ans[st.pop()] = arr[i];

            st.push(i);
        }
        return ans;
    }

    // NEXT SMALLER ON LEFT
    public int nsol(int[] arr) {

        int n = arr.length;

        int ans[] = new int[n];

        Stack<Integer> st = new Stack<> ();

        Arrays.fill(ans, -1);

        for(int i = n-1; i >= 0; i--) {

            while(st.size() != 0 && arr[st.peek()] > arr[i] )
                ans[st.pop()] = arr[i];

            st.push(i);
        }
        return ans;
    }

    // 20. Valid Parentheses
    // https://leetcode.com/problems/valid-parentheses/
    // Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the 
    // input string is valid : Open brackets must be closed by the same type of brackets and in correct order.
    public boolean isValid(String s) {
        
        Stack<Character> st = new Stack<> ();        
        for(int i = 0; i < s.length(); i++) {
            
            char ch = s.charAt(i);
            
            if(ch == '(' || ch == '{' || ch == '[')
                st.push(ch);
            
            else{
                if(st.size() == 0) return false; 
                // more no of closing bracket 
                
                // Now check if pairs are different
                if(ch == ']' && st.peek() != '[' ) return false;
                else if(ch == ')' && st.peek() != '(' ) return false;
                else if(ch == '}' && st.peek() != '{' ) return false;
                
                else st.pop();
                //same pairs of parentheses
            }
        }
        if(st.size() != 0) return false; // extra open brackets
        return true;
    }

    // 1021. Remove Outermost Parentheses
    // https://leetcode.com/problems/remove-outermost-parentheses/

    // My Code    
    // FOR STARTING BRACKET : we will use ignore variable to ignore 1st bracket
    // FOR LAST BRACKET : when count of open bracket = close bracket then we will ignore that bracket bcz we do not need outer brackets
    //  and we will make ignore = true so that we can ignore next substring's open-bracket
    public String removeOuterParentheses(String S) {
        
        StringBuilder ans = new StringBuilder();        
        int open = 0, close = 0;
        
        boolean ignore = true; 
        // to ignore 1st open bracket
        
        for(int i = 0;i < S.length(); i++) {
            
            char ch = S.charAt(i);
            if(ch == '(') open++;
            else close++;
            
            if(open != close) {
                if(ignore) ignore = false;
                else ans.append(ch); 
            } 
            
            if(open == close) {
                // to ignore last closing bracket 
                // and also using ignore we will ignore next starting bracket
                ignore = true;
            }
        }
        return ans.toString();
    }

    // Sir's Code
    public String removeOuterParentheses(String s) {

        StringBuilder sb = new StringBuilder();

        int ob = 0;
        for(int  i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if(ch == '(' && ob++ > 0 ) sb.append('(');
            // to leave 1st open bracket (when ob is 0 before incrementing)
            else if(ch == ')' && ob-- > 1) sb.append(')');
            // to leave last close bracket (when ob = 1 before decrementing)
        }
        return sb.toString();
    }

    // 503. Next Greater Element II
    // https://leetcode.com/problems/next-greater-element-ii/
   
    // for circular array we will use loop twice for elts can 
    // become greater for elts which are present in stack
    public int[] nextGreaterElements(int[] nums) {
        
        int n = nums.length;
        Stack<Integer> nge = new Stack<> ();// next greater elt
        
        int ans[] = new int[n];
        Arrays.fill(ans, -1);
        // for greater elts
        
        for(int i = 0; i < n*2; i++) {
            
            int idx = i%n;
            while(nge.size() != 0 && nums[nge.peek()] < nums[idx]) {
                ans[nge.pop()] = nums[idx];
            }            
            if(i < n) nge.push(idx);
            // will put elts obly once in stack 
        }        
        return ans;
    }

    // 921. Minimum Add to Make Parentheses Valid
    // https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/

    // My Code
    // when close brackets increases means we need one more open bracket so
    // increment count and make bracket = 0
    // at last when open brackets are left means we have to count bracket in answer
    // for number of close brackets required
    public int minAddToMakeValid(String S) {
        
        int ans = 0;        
        int bracket = 0;
        // ( -> ++ , ) -> --
        
        for(int i = 0; i < S.length(); i++) {            
            char ch = S.charAt(i);
            
            if(ch == '(') bracket++;
            else if(ch == ')') bracket--;
            
            if(bracket < 0) {
                // demand for opening brackets
                bracket = 0;
                ans++;
            }
        }        
        ans += bracket; 
        // demand for close brackets
        
        return ans;
    }

    // Sir's Code
    public int minAddToMakeValid(String str) {

        int obr = 0; // opening bracket requirement
        int cbr = 0; // closing bracket requirement

        for(int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            if(ch == '(') cbr++;
            // closing brackets needed
            else if(ch == ')' && cbr > 0) cbr--;
            // if closing brackets is there and we need closingBracket then we can use this bracket
            else if(ch == ')') obr++;
            // if closing is there and no closing bracket requirement is there
            // then we need opening brackets requirements
        }
        return obr + cbr;
        // return opening requirement and closing requirement
    }

    // Using Stack
    // Left out brackets in stack will be ans
    public int minAddToMakeValid(String str) {

        Stack<Character> st = new Stack<> ();
        
        for(int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            if(ch == '(')
                st.push(ch);
            else if(st.size() != 0 && st.peek() == '(' && ch == ')' )
                st.pop();
        }
        return st.size();
    }

    // Leetcode 32
    // https://leetcode.com/problems/longest-valid-parentheses/
    // SS6697
    public int longestValidParentheses(String s) {
        
        Stack<Integer> st = new Stack<> ();  
        st.push(-1); 
        // if string from starting is valid then 
        // stack top will have -1 and then we can calculate ans       
        int ans = 0;
        
        for(int i = 0;i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if(ch == '(') {
                st.push(i);
                
            } else if(ch == ')') {
                
                if(st.peek() != -1 && s.charAt(st.peek()) == '(') {
                    st.pop(); // pop the bracket
                    // REMOVE INTERMEDIATE B 
                    
                    // now count max length of solved string
                    // ie. the idx in stack to current idx is valid substring
                    // so update ans
                    ans = Math.max(ans, i - st.peek()); 
                    // CALCULATE USING A AND C ONLY
                    
                } else st.push( i );
                  // no open bracket in stack found so this is extra bracket
                  // and we have to push its idx in stack
            }
        }
        return ans;
    }

    // 1249. Minimum Remove to Make Valid Parentheses
    // https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
    // SS6753

    // Using Char[]
    public String minRemoveToMakeValid(String s) {
        
        Stack<Integer> st = new Stack<> ();
        
        boolean[] arr = new boolean[s.length()];
        Arrays.fill(arr,true);
        
        StringBuilder ans = new StringBuilder();
        
        for(int i = 0;i < s.length(); i++) {
            
            char ch = s.charAt(i);
            if( ch == '(' || ch == ')' ) {
                
                if(ch == '(') st.push(i);
                
                else if(ch == ')') {
                    
                    if(st.size() != 0 && s.charAt(st.peek()) == '(') st.pop();
                    else {
                        st.push(i);
                        arr[i] = false;
                        // wrong bracket found so it cannot be added in answer
                    }
                }
            }
        }
        // now make all left out brackets in stack as false bcz they
        // do not have their corresponding brackets
        while(st.size() != 0) arr[st.pop()] = false;
        
        // now make the answer
        for(int i = 0; i < s.length(); i++) {
            if(arr[i] == true)
                ans.append(s.charAt(i));
        }
        return ans.toString();
    }

    // Using StringBuilder
    public String minRemoveToMakeValid(String s) {
        
        Stack<Integer> st = new Stack<> ();
        int n = s.length();
        
        StringBuilder sb = new StringBuilder(s);
        
        for(int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            
            if(ch == '(') {
                st.push(i);
                
            } else if(ch == ')' ) {
                
                if(st.size() != 0 ) {
                    // no need to check stack peek elt bcz
                    // we only pus open brackets
                    st.pop();
                    
                } else sb.setCharAt(i, '#');
                // set different char to differentiate
            }            
        }
        
        while(st.size() != 0) {
            int i = st.pop();
            sb.setCharAt(i, '#');
            // leftout brackets are also not valid so mark them
        }
        
        StringBuilder ans = new StringBuilder();
        for(int i = 0;i < n; i++) {
            char ch = sb.charAt(i);
            if(ch != '#') ans.append(ch);
        }
        
        return ans.toString();
    }

    // 735. Asteroid Collision
    // https://leetcode.com/problems/asteroid-collision/

    // Cases : 
        // 1. <-     <-
        // 2. ->     ->
        // 3. <-     ->
        // 4. (i). ->(bigger)     <-(smaller)   will result as  ->(bigger)
         //  (ii). ->(smaller)    <-(bigger)    will result as  <-(bigger)
         // (iii). ->(equal)      <-(equal)     both will destroy
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<> ();
        
        for(int ele : asteroids) {
            
            // direction is (->)        
            if(ele >= 0) st.push(ele);
            // (2 and 3 case)
            
            else {         
                // direction is (<-)
                while(st.size() != 0 && st.peek() > 0 && st.peek() < -ele) st.pop();
                // 4(ii) case
                // it will remove all elts who are bigger in size and in coming direction(->)
                
                if(st.size() != 0 && st.peek() == -ele) st.pop();
                // eg: 5 & -5 will destroy (4(iii) case)
                else if(st.size() == 0 || st.peek() < 0) st.push(ele);
                // eg : if stack is empty or -ve elts present (1 case)
                else {
                    // asteroid will destroy as stack peek elt
                    // is bigger in size(4(i) case)
                }
            }
        }        
        int ans[] = new int[st.size()];
        
        for(int i = ans.length - 1; i >= 0; i--) {
            ans[i] = st.pop();
        }
        return ans;
    }

    // 84. Largest Rectangle in Histogram
    // https://leetcode.com/problems/largest-rectangle-in-histogram/
    // SS6805

    // O(n) space
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        
        int left[] = nextSmaller(heights, true);
        int right[] = nextSmaller(heights, false);
        
        int area = 0;
        int ans = 0;
        
        for(int i = 0; i < n; i++) {
            area = (right[i] - left[i] - 1) * heights[i];
            ans = Math.max(ans, area);
        }
        return ans;
    }
    
    public int[] nextSmaller(int arr[], boolean isLeft) {
        int n = arr.length;
        int ans[] = new int[n];
        
        if(isLeft) Arrays.fill(ans,-1);
        else Arrays.fill(ans, n);
        
        Stack<Integer> st = new Stack<> ();
        
        if(isLeft) {
            for(int i = n-1; i >= 0; i--) {
                while(st.size() != 0 && arr[st.peek()] > arr[i]) {
                    ans[st.pop()] = i;
                }
                st.push(i);
            }
        } else {
            for(int i = 0; i < n; i++) {
                while(st.size() != 0 && arr[st.peek()] > arr[i]) {
                    ans[st.pop()] = i;
                }
                st.push(i);
            }
        }        
        return ans;
    }

    // O(1) space
    public int largestRectangleArea(int[] arr) {
        int n = arr.length;
        
        Stack<Integer> st = new Stack<> ();
        st.push(-1);
        
        int area = 0;
        for(int i = 0; i < n; i++) {
            while(st.peek() != -1 && arr[st.peek()] > arr[i]) {
                int idx = st.pop();
                area = Math.max(area, (i - st.peek() - 1) * arr[idx]);                
            }            
            st.push(i);            
        }
        
        while(st.peek() != -1) {
            int idx = st.pop();
            
            area = Math.max(area, (n - st.peek() - 1) * arr[idx]);
        }
        return area;
    }

    // 85. Maximal Rectangle
    // https://leetcode.com/problems/maximal-rectangle/

    // Think like bars and keep increasing height of bars if upper col has 1 and
    // keep calculating largest area histogram for every row
    // eg :[["1","0","1","0","0"]            will     [["1","0","1","0","0"]
    //      ["1","1","1","1","1"]            become    ["2","1","2","1","1"]
    //      ["1","0","1","1","1"]            like      ["3","0","3","2","2"]
    //      ["1","1","0","1","0"]]           this      ["4","1","0","3","0"]]
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0 ) return 0;
        
        int n = matrix.length, m = matrix[0].length;
        
        int heights[] = new int[m];
        int area = 0;
        
        for(int i = 0;i < n; i++) {
            for(int j = 0; j< m; j++) {
                
                heights[j] = matrix[i][j] == '1' ? heights[j] + 1 : 0;
            }
            area = Math.max(area, largestRectangleArea(heights));
        }
        return area;
    }

    // 42. Trapping Rain Water
    // https://leetcode.com/problems/trapping-rain-water/
    // SS6940

    // O(2n) space using array
    public int trap(int[] height) {
        if(height.length == 0) return 0;
        
        int n = height.length;
        
        int left[] = new int[n];
        int right[] = new int[n];
        
        int prev = 0;
        for(int i = 0; i < n; i++) {
            left[i] = Math.max(prev, height[i]);
            prev = left[i];
        }
        
        prev = 0;
        for(int i = n - 1; i >= 0; i--) {
            right[i] = Math.max(prev, height[i]);
            prev = right[i];
        }
        
        int water = 0;
        for(int i = 0; i < n; i++) {
            int h = Math.min(left[i], right[i]);
            
            if(h > height[i]) water += h - height[i];
        }
        return water;        
    }

    // O(n) space using Stack
    public int trap(int[] height) {
        if(height.length == 0) return 0;
        int n = height.length;
        
        Stack<Integer> st = new Stack<> ();
        int water = 0;
        
        for(int i = 0;i < n; i++) {            
            while(st.size() != 0&& height[st.peek()] <= height[i]) {
                int h = height[st.pop()];
                
                if(st.size() == 0) break;
                // no left boundary so cannot store water here
                
                int minBound = Math.min(height[i], height[st.peek()]);
                // minimum boundary is current elt or elt present in stack
                
                int width = (i - st.peek() - 1); // width of water storage
                
                int ht = (minBound - h); // water height after storing
                // always give 0 or +ve value as we only process minimum elts in stack
                
                water +=  (ht * width); // area covered by water
            }
            st.push(i);
        }
        
        return water;
    }

    // O(1) space using 2pointers approach
    public int trap(int[] height) {
        if(height.length == 0) return 0;
        
        int n = height.length, water = 0;
        
        int lMax = 0, rMax = 0, li = 0, ri = n - 1;
        
        while(li < ri) {
            
            lMax = Math.max(lMax, height[li]);
            rMax = Math.max(rMax, height[ri]);
            
            if(lMax <= rMax) {
                // no need to check height[] is bigger or not as we always update 
                // lmax and rmax with height[] so we can add directly to water
                water += lMax - height[li++];
                
            }else water += rMax - height[ri--];
        }        
        return water;
    }

}