

/*  
	class Node
		public  int frequency; // the frequency of this tree
    	public  char data;
    	public  Node left, right;
    
*/ 

	void decode(String s, Node root) {
    Node current = root;
    StringBuilder result = new StringBuilder();
    
    for (char bit : s.toCharArray()) {
        current = (bit == '0') ? current.left : current.right;
        
        if (current instanceof HuffmanLeaf) {
            result.append(((HuffmanLeaf)current).data);
            current = root;
        }
    }
    
    System.out.println(result.toString());
}

