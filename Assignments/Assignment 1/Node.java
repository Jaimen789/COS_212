import java.text.DecimalFormat;

public class Node {

	private int v1; // this is the first variable
	private int v2; // this is the second variable
	public Node left; // this is the node left of this node
	public Node right; // this is the node right of this node
	public Node up; // this is the node up of this node
	public Node down; // this is the node down of this node
	public Node nextVal; // this is the next value of the current node
	public Node prevVal; // this is the prev value of the current node
	private Function nodeFunction; // this is the function associated with the current node

	private String floatFormatter(float value){
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(value);
	}
	
	//DO NOT CHANGE THE ABOVE FUNCTION
	//Place your code below

	public Node(Function function, int var1, int var2) {

		this.v1 = var1;
		this.v2 = var2;

		left = null;
		right = null;
		up = null;
		down = null;

		nextVal = null;
		prevVal = null;
		
		nodeFunction = function.clone();
		
	}

	public Function setFunction(Function function) {

		Function oldFunction = nodeFunction;

		nodeFunction = function;

		return oldFunction;
	
	}

	public float getValue() {

		if(nodeFunction == null){
			return Float.NEGATIVE_INFINITY;
		}

		return nodeFunction.calculate(v1, v2);

	}

	public int[] getVariables() {

		int [] variables = new int[2];

		variables[0] = v1;
		variables[1] = v2;

		return variables;

	}

	public Function getFunction(){

		return nodeFunction;
	}

	public String[] getNodeLinks(){

		String[] nodeLink = new String [6];

		if(this.up == null){
			nodeLink[0] = "U[][]{}";
		}else{
			nodeLink[0] = "U[" + String.valueOf(this.up.v1) + "]" + "[" + String.valueOf(this.up.v2) + "]" + "{" + String.valueOf(floatFormatter(this.up.getValue())) + "}";
		}

		if(this.down == null){
			nodeLink[1] = "D[][]{}";
		}else{
			nodeLink[1] = "D[" + String.valueOf(this.down.v1) + "]" + "[" + String.valueOf(this.down.v2) + "]" + "{" + String.valueOf(floatFormatter(this.down.getValue())) + "}";
		}

		if(this.right == null){
			nodeLink[2] = "R[][]{}";
		}else{
			nodeLink[2] = "R[" + String.valueOf(this.right.v1) + "]" + "[" + String.valueOf(this.right.v2) + "]" + "{" + String.valueOf(floatFormatter(this.right.getValue())) + "}";
		}

		if(this.left == null){
			nodeLink[3] = "L[][]{}";
		}else{
			nodeLink[3] = "L[" + String.valueOf(this.left.v1) + "]" + "[" + String.valueOf(this.left.v2) + "]" + "{" + String.valueOf(floatFormatter(this.left.getValue())) + "}";
		}

		if(this.prevVal == null){
			nodeLink[4] = "P[][]{}";
		}else{
			nodeLink[4] = "P[" + String.valueOf(this.prevVal.v1) + "]" + "[" + String.valueOf(this.prevVal.v2) + "]" + "{" + String.valueOf(floatFormatter(this.prevVal.getValue())) + "}";
		}

		if(this.nextVal == null){
			nodeLink[5] = "N[][]{}";
		}else{
			nodeLink[5] = "N[" + String.valueOf(this.nextVal.v1) + "]" + "[" + String.valueOf(this.nextVal.v2) + "]" + "{" + String.valueOf(floatFormatter(this.nextVal.getValue())) + "}";
		}

		return nodeLink;
	}

}