class Solution {
    public boolean isValid(String s) {
        Map<Character,Character> map = new HashMap();
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');
        Stack<Character> stack = new Stack<>();
        for(char ch : s.toCharArray()){
            if(map.containsKey(ch)){
                char topEle = (stack.isEmpty()) ? '#' : stack.pop();
                if(map.get(ch) != topEle) return false;
            }
            else{
              stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}