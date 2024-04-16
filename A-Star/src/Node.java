public class Node {
	
	private int row, col, f, g, h, type;
	private Node parent;
	public static final int PATHABLE = 0, UNPATHABLE = 1;


	public Node(int r, int c, int t){
		row = r;
		col = c;
		type = t;
		parent = null;
		//type 0 is traverseable, 1 is not
	}
	
	//set values
	public void setF(){
		f = g + h;
	}
	public void setG(int value){
		g = value;
	}
	public void setH(int value){
		h = value;
	}
	public void setParent(Node n){
		parent = n;
	}
	
	//get values
	public int getF(){
		return f;
	}
	public int getG(){
		return g;
	}
	public int getH(){
		return h;
	}
	public Node getParent(){
		return parent;
	}
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
	public int getType() { return type; }


	public boolean equals(Object in){

		Node n = (Node) in;
		
		return row == n.getRow() && col == n.getCol();
	}
   
	public String toString(){
		return "Node: " + row + "_" + col;
	}
	
}
