import java.text.DecimalFormat;
import java.util.Arrays;

public class Interface {

	private Node origin;

	private String floatFormatter(float value){
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(value);
	}

	//DO NOT CHANGE THE ABOVE FUNCTION
	//Place your code below

	public Interface() {

		this.origin = new Node(new Origin(), 0, 0);
	}

	public Node getOrigin() {

		return origin;
	}

	public float calculateValue(Function function, int v1, int v2) {

		if(function == null){
			return Float.NaN;
		}

		return function.calculate(v1, v2);
	}

	public Interface(Node[] arr) {
		
	}
	

	public float addPoint(Function function, int v1, int v2) {

		if (v1 == 0 && v2 == 0) {
			return Float.NaN;
		}

		Node newNode = new Node(function, v1, v2);

		float calculation = newNode.getValue();

		//insert on axis
		// pos - x1 => v1 > 0 and v2 == 0 -> right
		// pos - y1 => v1 == 0 and v2 > 0 -> up
		// neg - x2 => v1 < 0 and v2 == 0 -> left
		// neg - y2 => v1 == 0 and v2 < 0 -> down

		//insert in quadrants
		// pos v1, pos v2 : q1 => v1 > 0 and v2 > 0 -> up and right
		// pos v1, neg v2 : q2 => v1 > 0 and v2 < 0 -> down and right
		// neg v1, pos v2 : q3 => v1 < 0 and v2 < 0 -> down and left 
		// neg v1 ,neg v2 : q4 => v1 < 0 and v2 > 0 -> up and left

		//insert on the positive x - axis
		if(v1 > 0 && v2 == 0){
			if(origin.right == null){
				origin.right = newNode;
				newNode.left = origin;

			}else{

				Node curr = origin;

				if(curr != null && curr.getVariables()[0] <= v1){
					
					while(curr.right != null && curr.right.getVariables()[0] <= v1){

						curr = curr.right;
					}

					if(curr.getVariables()[0] == v1){

						curr.nextVal = newNode;
						newNode.prevVal = curr;

					}else if(curr.right == null){

						curr.right = newNode;
						newNode.left = curr;
		
					}else{
							
						newNode.right = curr.right;
						curr.right.left = newNode;
	
						curr.right = newNode;
						newNode.left = curr;
					}
				}	
	
			}
		}

		//insert on the positive y 
		if(v1 == 0 && v2 > 0){
			if(origin.up == null){
				origin.up = newNode;
				newNode.down = origin;

			}else{

				Node curr = origin;

				if(curr != null && curr.getVariables()[1] <= v2){
					
					while(curr.up != null && curr.up.getVariables()[1] <= v2){

						curr = curr.up;
					}

					if(curr.getVariables()[1] == v2){
						curr.nextVal = newNode;
						newNode.prevVal = curr;

					}else if(curr.up == null){
						curr.up = newNode;
						newNode.down = curr;
		
					}else{
							
						newNode.up = curr.up;
						curr.up.down = newNode;
	
						curr.up = newNode;
						newNode.down = curr;
					}
				}	
	
			}
		}


		//insert on the negative x - axis
		if(v1 < 0 && v2 == 0){
			if(origin.left == null){
				origin.left = newNode;
				newNode.right = origin;

			}else{

				Node curr = origin;

				if(curr != null && curr.getVariables()[0] >= v1){
					
					while(curr.left != null && curr.left.getVariables()[0] >= v1){

						curr = curr.left;
					}

					if(curr.getVariables()[0] == v1){

						curr.nextVal = newNode;
						newNode.prevVal = curr;

					}else if(curr.left == null){

						curr.left = newNode;
						newNode.right = curr;
		
					}else{
							
						newNode.left = curr.left;
						curr.left.right = newNode;
	
						curr.left = newNode;
						newNode.right = curr;
					}
				}	
	
			}
		}

		//insert on the negative y - axis
		if(v1 == 0 && v2 < 0){
			if(origin.down == null){
				origin.down = newNode;
				newNode.up = origin;

			}else{

				Node curr = origin;

				if(curr != null && curr.getVariables()[1] >= v2){
					
					while(curr.down != null && curr.down.getVariables()[1] >= v2){

						curr = curr.down;
					}

					if(curr.getVariables()[1] == v2){
						curr.nextVal = newNode;
						newNode.prevVal = curr;

					}else if(curr.down == null){
						curr.down = newNode;
						newNode.up = curr;
		
					}else{
							
						newNode.down = curr.down;
						curr.down.up = newNode;
	
						curr.down = newNode;
						newNode.up = curr;
					}
				}	
	
			}
		}
		

		//insert into quadrant 1
		if (v1 > 0 && v2 > 0) {
			if (origin.up == null && origin.right == null) {
				// Case 1: Both x-axis and y-axis are empty, create nodes on x and y axes
				Node newX = new Node(function, v1, 0);
				Node newY = new Node(function, 0, v2);
		
				// Connect nodes on x-axis
				origin.right = newX;
				newX.left = origin;
		
				// Connect nodes on y-axis
				origin.up = newY;
				newY.down = origin;
		
				// Connect the new node to nodes on x-axis and y-axis
				newX.up = newNode;
				newNode.down = newX;
		
				newY.right = newNode;
				newNode.left = newY;
			}else {

				Node tempX = origin;
				Node existX = null;
				Node prevX = null;
				boolean x_axis = false;

				

				while(tempX.right != null && tempX.getVariables()[0] <= v1){

					
					if(tempX.getVariables()[0] == v1){
						existX = tempX;
						x_axis = true; 
					}

					prevX = tempX;
					tempX = tempX.right;
				}


				Node tempY = origin;
				Node existY = null;
				Node prevY = null;
				boolean y_axis = false;


				while(tempY.up != null && tempY.getVariables()[1] <= v2){

					
					if(tempY.up.getVariables()[1] == v2){
						existY = tempY;
						y_axis = true; 
					}

					prevY = tempY;
					tempY = tempY.up;
				}

				if(x_axis  == true && y_axis == true){

					Node nodeOnXAxis = prevX;
					

					while(nodeOnXAxis.up != null){

						if(nodeOnXAxis.getVariables()[0] == v1 && nodeOnXAxis.getVariables()[1] == v2){

							nodeOnXAxis.nextVal = newNode;
							newNode.prevVal = nodeOnXAxis;
						}

						nodeOnXAxis = nodeOnXAxis.up;
					}

					if(nodeOnXAxis.up == null && nodeOnXAxis.getVariables()[1] < v2){
						newNode.down = nodeOnXAxis;
						nodeOnXAxis.up = newNode;
					}else{

						nodeOnXAxis.down = newNode;
						newNode.up = nodeOnXAxis;

						prevX.up = newNode;
						newNode.down = prevX;
					}

					Node nodeOnYAxis = prevY;

					while(nodeOnYAxis.right != null){
						nodeOnYAxis = nodeOnYAxis.right;
					}

					if(nodeOnYAxis.right == null && nodeOnYAxis.getVariables()[0] < v1){
						newNode.left = nodeOnYAxis;
						nodeOnYAxis.right = newNode;

					}else{
						//System.out.println("nodeOnYAxis: " + nodeOnYAxis.getVariables()[0] + "," +  nodeOnYAxis.getVariables()[1]);
						nodeOnYAxis.right = newNode;
						newNode.left = nodeOnYAxis;

						newNode.right = nodeOnYAxis;
						nodeOnYAxis.left = newNode;

						// nodeOnYAxis.left.right = newNode;
						// newNode.left = nodeOnYAxis.left;

						// newNode.right = nodeOnYAxis;
						// nodeOnYAxis.left = newNode;

					}


				}else if(x_axis  == true && y_axis == false){

					if(tempY.getVariables()[1] > v2){

						//insert inbetween

						Node newY = new Node(function, 0, v2);

						prevY.up = newY;
						newY.down = prevY;

						tempY.down = newY;
						newY.up = tempY;

						newY.right = newNode;
						newNode.left = newY;

					}else{

						Node newY = new Node(function, 0, v2);

						tempY.up = newY;
						newY.down = tempY;

						newY.right = newNode;
						newNode.left = newY;

					}

					Node tempXOnXAxis = prevX; 

					while(tempXOnXAxis.up != null){
						
						tempXOnXAxis = tempXOnXAxis.up;
					}
	
					
					if(tempXOnXAxis.up == null && tempXOnXAxis.getVariables()[1] < v2){
						newNode.down = tempXOnXAxis;
						tempXOnXAxis.up = newNode;

						

					}else{

						tempXOnXAxis.down = newNode;
						newNode.up = tempXOnXAxis;

						prevX.up = newNode;
						newNode.down = prevX;

						
					}
					

				}else if(x_axis  == false && y_axis == true){

					//System.out.println("x: f    y: t");

					if(tempX.getVariables()[0] > v1){

						//insert in between
						Node newX = new Node(function, v1, 0);

						newX.right = tempX;
						tempX.left = newX;

						prevX.right = newX;
						newX.left = prevX;

						newX.up = newNode;
						newNode.down = newX;

					}else{
						//insert after
						Node newX = new Node(function, v1, 0);

						tempX.right = newX;
						newX.left = tempX;

						newX.up = newNode;
						newNode.down = newX;
					}

					Node tempYOnAxis = tempY;

					while(tempY.right != null){
						
						tempY = tempY.right;
					}

					if(tempY.right == null && tempY.getVariables()[0] < v1){
						newNode.left = tempY;
						tempY.right = newNode;

					}else{

						tempYOnAxis.right = newNode;
						newNode.left = tempYOnAxis;

						newNode.right = tempY;
						tempY.left = newNode;

					}
					
				}else{

					if(tempX.getVariables()[0] > v1){

						//insert in between
						Node newX = new Node(function, v1, 0);

						newX.right = tempX;
						tempX.left = newX;

						prevX.right = newX;
						newX.left = prevX;

						newX.up = newNode;
						newNode.down = newX;

					}else{
						//insert after
						Node newX = new Node(function, v1, 0);

						tempX.right = newX;
						newX.left = tempX;

						newX.up = newNode;
						newNode.down = newX;
					}

					if(tempY.getVariables()[1] > v2){

						//insert inbetween

						Node newY = new Node(function, 0, v2);

						prevY.up = newY;
						newY.down = prevY;

						tempY.down = newY;
						newY.up = tempY;

						newY.right = newNode;
						newNode.left = newY;

					}else{

						Node newY = new Node(function, 0, v2);

						tempY.up = newY;
						newY.down = tempY;

						newY.right = newNode;
						newNode.left = newY;

					}


				}
					
			}
		}

		// insert in quadrant 2
		if (v1 > 0 && v2 < 0) {
			if (origin.down == null && origin.right == null) {
				// Case 1: Both x-axis and negative y-axis are empty, create nodes on x and negative y axes
				Node newX = new Node(function, v1, 0);
				Node newY = new Node(function, 0, v2);

				// Connect nodes on x-axis
				origin.right = newX;
				newX.left = origin;

				// Connect nodes on negative y-axis
				origin.down = newY;
				newY.up = origin;

				// Connect the new node to nodes on x-axis and negative y-axis
				newX.down = newNode;
				newNode.up = newX;

				newY.right = newNode;
				newNode.left = newY;

			} else {
				
				Node tempX = origin;
				Node existX = null;
				Node prevX = null;
				boolean x_axis = false;

				

				while(tempX.right != null && tempX.getVariables()[0] <= v1){

					
					if(tempX.right.getVariables()[0] == v1){
						existX = tempX;
						x_axis = true; 
					}

					prevX = tempX;
					tempX = tempX.right;
				}


				Node tempY = origin;
				Node existY = null;
				Node prevY = null;
				boolean y_axis = false;


				while(tempY.down != null && tempY.getVariables()[1] >= v2){

					
					if(tempY.down.getVariables()[1] == v2){
						existY = tempY;
						y_axis = true; 
					}

					prevY = tempY;
					tempY = tempY.down;
				}

				if(x_axis == true && y_axis == true){

					Node nodeOnXAxis = prevX;
					

					while(nodeOnXAxis.down != null){

						if(nodeOnXAxis.getVariables()[0] == v1 && nodeOnXAxis.getVariables()[1] == v2){

							nodeOnXAxis.nextVal = newNode;
							newNode.prevVal = nodeOnXAxis;
						}

						nodeOnXAxis = nodeOnXAxis.down;
					}

					if(nodeOnXAxis.down == null && nodeOnXAxis.getVariables()[1] < v2){

						nodeOnXAxis.up = newNode;
						newNode.down = nodeOnXAxis;

						prevX.down = newNode;
						newNode.up = prevX;
					}else{

						newNode.up = nodeOnXAxis;
						nodeOnXAxis.down = newNode;
					}

					Node nodeOnYAxis = prevY;

					while(nodeOnYAxis.right != null){
						nodeOnYAxis = nodeOnYAxis.right;
					}

					if(nodeOnYAxis.right == null && nodeOnYAxis.getVariables()[0] < v1){
						newNode.left = nodeOnYAxis;
						nodeOnYAxis.right = newNode;

					}else{
						nodeOnYAxis.right = newNode;
						newNode.left = nodeOnYAxis;

						newNode.right = nodeOnYAxis;
						nodeOnYAxis.left = newNode;

					}



				}else if(x_axis  == true && y_axis == false){

					if(tempY.getVariables()[1] < v2){

						//insert inbetween
						// System.out.println("here");

						Node newY = new Node(function, 0, v2);

						prevY.down = newY;
						newY.up = prevY;

						tempY.up = newY;
						newY.down = tempY;

						newY.right = newNode;
						newNode.left = newY;

					}else{

						Node newY = new Node(function, 0, v2);

						tempY.down = newY;
						newY.up = tempY;

						newY.right = newNode;
						newNode.left = newY;

					}

					Node tempXOnXAxis = prevX; 

					// System.out.println("prevX: " + prevX.getVariables()[0] + prevX.getVariables()[1]);
					// System.out.println("tempX: " + tempX.getVariables()[0] + tempX.getVariables()[1]);
					// System.out.println("tempXOnXAxis: " + tempXOnXAxis.getVariables()[0] + tempXOnXAxis.getVariables()[1]);

					while(tempXOnXAxis.down != null){
						
						tempXOnXAxis = tempXOnXAxis.down;
					}

					//System.out.println("tempXOnXAxis after: " + tempXOnXAxis.getVariables()[0] + tempXOnXAxis.getVariables()[1]);
	
					
					if(tempXOnXAxis.down == null && tempXOnXAxis.getVariables()[1] < v2){
						
						
						
						tempXOnXAxis.up = newNode;
						newNode.down = tempXOnXAxis;

						prevX.down = newNode;
						newNode.up = prevX;
						

					}else{

						tempXOnXAxis.down = newNode;
						newNode.up = tempXOnXAxis;

					}
				}else if(x_axis  == false && y_axis == true){

					if(tempX.getVariables()[0] > v1){

						//insert in between
						Node newX = new Node(function, v1, 0);
	
						newX.right = tempX;
						tempX.left = newX;
	
						prevX.right = newX;
						newX.left = prevX;
	
						newX.down = newNode;
						newNode.up = newX;
	
					}else{
						//insert after
						Node newX = new Node(function, v1, 0);
	
						tempX.right = newX;
						newX.left = tempX;
	
						newX.down = newNode;
						newNode.up = newX;
					}
	
					Node tempYOnAxis = tempY;
					Node prevYOnAxis = tempY;
	
					while(tempY.right != null){
						
						prevYOnAxis = tempY;
						tempY = tempY.right;
					}
	

					if(tempY.right == null && tempY.getVariables()[0] < v1){
						newNode.left = tempY;
						tempY.right = newNode;

					}else{	

						prevYOnAxis.right = newNode;
						newNode.left = prevY;

						newNode.right = tempY;
						tempY.left = newNode;

					}

				}else{
					// false false


					if(tempX.getVariables()[0] > v1){

						//insert in between
						Node newX = new Node(function, v1, 0);

						newX.right = tempX;
						tempX.left = newX;

						prevX.right = newX;
						newX.left = prevX;

						newX.down = newNode;
						newNode.up = newX;

					}else{
						//insert after
						Node newX = new Node(function, v1, 0);

						tempX.right = newX;
						newX.left = tempX;

						newX.down = newNode;
						newNode.up = newX;
					}

					if(tempY.getVariables()[1] > v2){

						//insert after

						Node newY = new Node(function, 0, v2);

						tempY.down = newY;
						newY.up = tempY;

						newY.right = newNode;
						newNode.left = newY;

					}else{

						//insert inbetween 
						Node newY = new Node(function, 0, v2);

						prevY.down = newY;
						newY.up = prevY;

						tempY.up = newY;
						newY.down = tempY;

						newY.right = newNode;
						newNode.left = newY;

					}

				}
				
			}
		}

		// insert in quadrant 3
		if (v1 < 0 && v2 < 0) {
			if (origin.down == null && origin.left == null) {
				// Case 1: Both negative x-axis and negative y-axis are empty, create nodes on negative x and negative y axes
				Node newX = new Node(function, v1, 0);
				Node newY = new Node(function, 0, v2);

				// Connect nodes on negative x-axis
				origin.left = newX;
				newX.right = origin;

				// Connect nodes on negative y-axis
				origin.down = newY;
				newY.up = origin;

				// Connect the new node to nodes on negative x-axis and negative y-axis
				newX.down = newNode;
				newNode.up = newX;

				newY.left = newNode;
				newNode.right = newY;

			}else{

				Node tempX = origin;
				Node existX = null;
				Node prevX = null;
				boolean x_axis = false;

				

				while(tempX.left != null && tempX.getVariables()[0] >= v1){

					
					if(tempX.getVariables()[0] == v1){
						existX = tempX;
						x_axis = true; 
					}

					prevX = tempX;
					tempX = tempX.left;
				}

				Node tempY = origin;
				Node existY = null;
				Node prevY = null;
				boolean y_axis = false;


				while(tempY.down != null && tempY.getVariables()[1] >= v2){

					
					if(tempY.down.getVariables()[1] == v2){
						existY = tempY;
						y_axis = true; 
					}

					prevY = tempY;
					tempY = tempY.down;
				}

				if(x_axis == true && y_axis == true){

					existY = prevY;

					//System.out.println("x: " + x_axis + " " + "y: " + y_axis);

					
					// System.out.println("prevX: " + prevX.getVariables()[0] + prevX.getVariables()[1]);
					// System.out.println("existX: " + existX.getVariables()[0] + existX.getVariables()[1]);

					

					Node nodeOnXAxis = existX;
					

					while(nodeOnXAxis.down != null){

						if(nodeOnXAxis.getVariables()[0] == v1 && nodeOnXAxis.getVariables()[1] == v2){

							nodeOnXAxis.nextVal = newNode;
							newNode.prevVal = nodeOnXAxis;
						}

						prevX = nodeOnXAxis;
						nodeOnXAxis = nodeOnXAxis.down;
					}

					if(nodeOnXAxis.down == null && nodeOnXAxis.getVariables()[1] > v2){

						//System.out.println("nodeOnXAxis: " + nodeOnXAxis.getVariables()[0] + nodeOnXAxis.getVariables()[1]);

						

						nodeOnXAxis.down = newNode;
						newNode.up = nodeOnXAxis;

					}else{

						

						prevX.down = newNode;
						newNode.up = prevX;

						nodeOnXAxis.up = newNode;
						newNode.down = nodeOnXAxis;
					}

					Node nodeOnYAxis = prevY;

					

					while(nodeOnYAxis.left != null){
						prevY = nodeOnYAxis;
						nodeOnYAxis = nodeOnYAxis.left;
					}

					if(nodeOnYAxis.left == null && nodeOnYAxis.getVariables()[0] > v1){
	
						newNode.right = nodeOnYAxis;
						nodeOnYAxis.left = newNode;

					}else{

						prevY.left = newNode;
						newNode.right = prevY;

						nodeOnYAxis.right = newNode;
						newNode.left =  nodeOnYAxis;

					}

					


				}else if(x_axis == false && y_axis == true){
					

					if(tempX.getVariables()[0] < v1){
						//insert in between
						Node newX = new Node(function, v1, 0);
	
						newX.left = tempX;
						tempX.right = newX;
	
						prevX.left = newX;
						newX.right = prevX;
	
						newX.down = newNode;
						newNode.up = newX;
	
					}else{
						//insert after

						Node newX = new Node(function, v1, 0);
	
						tempX.left = newX;
						newX.right = tempX;
	
						newX.down = newNode;
						newNode.up = newX;
					}

					Node prevYOnAxis = prevY;
	
					while(prevY.left != null){
						
						prevYOnAxis = prevY;
						prevY = prevY.left;
					}

					// System.out.println("tempY: " + tempY.getVariables()[0] + tempY.getVariables()[1]);
					// System.out.println("prevY: " + prevY.getVariables()[0] + prevY.getVariables()[1]);
					// System.out.println("prevYOnAxis: " + prevYOnAxis.getVariables()[0] + prevYOnAxis.getVariables()[1]);

					if(prevY.left == null && prevY.getVariables()[0] > v1){


						prevY.left = newNode;
						newNode.right = prevY;

					}else{	

						prevYOnAxis.left = newNode;
						newNode.right = prevYOnAxis;

						prevY.right = newNode;
						newNode.left = prevY;

					}
	

				}else if(x_axis == true && y_axis == false){

					//System.out.println("v1: " + v1 + " " + "v2: " + v2);
					
					// System.out.println("prevY: " + prevY.getVariables()[0] + prevY.getVariables()[1]);
					// System.out.println("tempY: " + tempY.getVariables()[0] + tempY.getVariables()[1]);

					if(tempY.getVariables()[1] < v2){
						//insert inbetween
						// System.out.println("here, in between");

						Node newY = new Node(function, 0, v2);

						prevY.down = newY;
						newY.up = prevY;

						tempY.up = newY;
						newY.down = tempY;

						newY.left = newNode;
						newNode.right = newY;

					}else{

						Node newY = new Node(function, 0, v2);

						tempY.down = newY;
						newY.up = tempY;

						newY.left = newNode;
						newNode.right = newY;

					}

					Node tempXOnXAxis = prevX; 

					while(tempXOnXAxis.down != null){
						
						tempXOnXAxis = tempXOnXAxis.down;
					}
	
					
					if(tempXOnXAxis.down == null && tempXOnXAxis.getVariables()[1] > v2){
						newNode.up = tempXOnXAxis;
						tempXOnXAxis.down = newNode;

						

					}else{

						tempXOnXAxis.up = newNode;
						newNode.down = tempXOnXAxis;

						prevX.down = newNode;
						newNode.up = prevX;

						
					}


				}else{
					//false false

					if(tempX.getVariables()[0] < v1){

						

						//insert in between
						Node newX = new Node(function, v1, 0);

						newX.left = tempX;
						tempX.right = newX;

						prevX.left = newX;
						newX.right = prevX;

						newX.down = newNode;
						newNode.up = newX;

					}else{
						//insert after
						
						Node newX = new Node(function, v1, 0);

						tempX.left = newX;
						newX.right = tempX;

						newX.down = newNode;
						newNode.up = newX;
					}

					if(tempY.getVariables()[1] > v2){

						//insert after
						Node newY = new Node(function, 0, v2);

						tempY.down = newY;
						newY.up = tempY;

						newY.left = newNode;
						newNode.right = newY;

					}else{

						//insert inbetween 
						Node newY = new Node(function, 0, v2);

						prevY.down = newY;
						newY.up = prevY;

						tempY.up = newY;
						newY.down = tempY;

						newY.left = newNode;
						newNode.right = newY;

					}

				}		

			}
		}

		
		// insert in quadrant 4
		if (v1 < 0 && v2 > 0) {
			if (origin.up == null && origin.left == null) {
				// Case 1: Both negative x-axis and y-axis are empty, create nodes on negative x and y axes
				Node newX = new Node(function, v1, 0);
				Node newY = new Node(function, 0, v2);

				// Connect nodes on negative x-axis
				origin.left = newX;
				newX.right = origin;

				// Connect nodes on y-axis
				origin.up = newY;
				newY.down = origin;

				// Connect the new node to nodes on negative x-axis and y-axis
				newX.up = newNode;
				newNode.down = newX;

				newY.left = newNode;
				newNode.right = newY;

			} else {
				
				
				Node tempX = origin;
				Node existX = null;
				Node prevX = null;
				boolean x_axis = false;

				

				while(tempX.left != null && tempX.getVariables()[0] >= v1){

					
					if(tempX.getVariables()[0] == v1){
						existX = tempX;
						x_axis = true; 
					}

					prevX = tempX;
					tempX = tempX.left;
				}

				Node tempY = origin;
				Node existY = null;
				Node prevY = null;
				boolean y_axis = false;


				while(tempY.up != null && tempY.getVariables()[1] <= v2){

					
					if(tempY.up.getVariables()[1] == v2){
						existY = tempY;
						y_axis = true; 
					}

					prevY = tempY;
					tempY = tempY.up;
				}

				if(x_axis == true && y_axis == true){

					existY = prevY;

					Node nodeOnXAxis = existX;
					

					while(nodeOnXAxis.up != null){

						if(nodeOnXAxis.getVariables()[0] == v1 && nodeOnXAxis.getVariables()[1] == v2){

							nodeOnXAxis.nextVal = newNode;
							newNode.prevVal = nodeOnXAxis;
						}

						prevX = nodeOnXAxis;
						nodeOnXAxis = nodeOnXAxis.up;
					}


					if(nodeOnXAxis.up == null && nodeOnXAxis.getVariables()[1] < v2){

						nodeOnXAxis.up = newNode;
						newNode.down = nodeOnXAxis;

					}else{

						
						
						prevX.up = newNode;
						newNode.down = prevX;

						nodeOnXAxis.down = newNode;
						newNode.up = nodeOnXAxis;
					}

					Node nodeOnYAxis = tempY;

					// System.out.println("nodeOnYAxis: " + nodeOnYAxis.getVariables()[0] + "," +  nodeOnYAxis.getVariables()[1]);
					// System.out.println("prevY: " + prevY.getVariables()[0] + "," +  prevY.getVariables()[1]);

					while(nodeOnYAxis.left != null && nodeOnYAxis.getVariables()[0] > v1){
						prevY = nodeOnYAxis;
						nodeOnYAxis = nodeOnYAxis.left;
					}


					if(nodeOnYAxis.left == null && nodeOnYAxis.getVariables()[0] > v1){

						nodeOnYAxis.left = newNode;
						newNode.right = nodeOnYAxis;
						

					}else{

						nodeOnYAxis.right = newNode;
						newNode.left = nodeOnYAxis;

						prevY.left = newNode;
						newNode.right = prevY;
						

					}

				}else if(x_axis == false && y_axis == true){

					if(tempX.getVariables()[0] < v1){
						//insert in between
						Node newX = new Node(function, v1, 0);
	
						newX.left = tempX;
						tempX.right = newX;
	
						prevX.left = newX;
						newX.right = prevX;
	
						newX.up = newNode;
						newNode.down = newX;
	
					}else{
						//insert after

						

						Node newX = new Node(function, v1, 0);
	
						tempX.left = newX;
						newX.right = tempX;
	
						newX.up = newNode;
						newNode.down = newX;
					}

					Node tempYOnAxis = prevY;

					while(tempYOnAxis.left != null){
						tempYOnAxis = prevY;
						tempYOnAxis = tempYOnAxis.left;
					}


					if(tempY.left == null && tempY.getVariables()[0] > v1){

						newNode.right = prevY;
						prevY.left = newNode;

					}else{

						tempYOnAxis.right = newNode;
						newNode.left = tempYOnAxis;

						tempY.left = newNode;
						newNode.right = tempY;
						

					}

				}else if(x_axis == true && y_axis == false){

					if(tempY.getVariables()[1] > v2){

						//insert inbetween

						Node newY = new Node(function, 0, v2);

						prevY.up = newY;
						newY.down = prevY;

						tempY.down = newY;
						newY.up = tempY;

						newY.left = newNode;
						newNode.right = newY;

					}else{

						Node newY = new Node(function, 0, v2);

						tempY.up = newY;
						newY.down = tempY;

						newY.left = newNode;
						newNode.right = newY;

					}

					Node tempXOnXAxis = prevX; 

					while(tempXOnXAxis.up != null){
						
						tempXOnXAxis = tempXOnXAxis.up;
					}
	
					
					if(tempXOnXAxis.up == null && tempXOnXAxis.getVariables()[1] < v2){
						newNode.down = tempXOnXAxis;
						tempXOnXAxis.up = newNode;

						

					}else{

						tempXOnXAxis.down = newNode;
						newNode.up = tempXOnXAxis;

						prevX.up = newNode;
						newNode.down = prevX;

						
					}

				}else{
					

					if(tempX.getVariables()[0] < v1){

						

						//insert in between
						Node newX = new Node(function, v1, 0);

						newX.left = tempX;
						tempX.right = newX;

						prevX.left = newX;
						newX.right = prevX;

						newX.up = newNode;
						newNode.down = newX;

					}else{
						//insert after
						
						Node newX = new Node(function, v1, 0);

						tempX.left = newX;
						newX.right = tempX;

						newX.up = newNode;
						newNode.down = newX;
					}

					if(tempY.getVariables()[1] > v2){

						//insert inbetween

						Node newY = new Node(function, 0, v2);

						prevY.up = newY;
						newY.down = prevY;

						tempY.down = newY;
						newY.up = tempY;

						newY.left = newNode;
						newNode.right = newY;

					}else{

						Node newY = new Node(function, 0, v2);

						tempY.up = newY;
						newY.down = tempY;

						newY.left = newNode;
						newNode.right = newY;

					}
					
				}


			}
		}
		

		
		return calculation;
	
	}
	


	public void printLinkedListOnPosXAxis() {
		Node current = origin;
	
		while (current != null) {
			System.out.println("Node: " + current.getVariables()[0] + ", " + current.getVariables()[1]);
			System.out.println("Links: " + Arrays.toString(current.getNodeLinks()));
			System.out.println("---");
			current = current.right; // Move to the next node on the positive x-axis
		}
	
		System.out.println("End of Linked List\n");
	}

	public void printLinkedListPosOnYAxis() {
		Node current = origin;
	
		while (current != null) {
			System.out.println("Node: " + current.getVariables()[0] + ", " + current.getVariables()[1]);
			System.out.println("Links: " + Arrays.toString(current.getNodeLinks()));
			System.out.println("---");
			current = current.up; // Move to the next node on the positive x-axis
		}
	
		System.out.println("End of Linked List\n");
	}

	public void printLinkedListNegOnXAxis() {
		Node current = origin;
	
		while (current != null) {
			System.out.println("Node: " + current.getVariables()[0] + ", " + current.getVariables()[1]);
			System.out.println("Links: " + Arrays.toString(current.getNodeLinks()));
			System.out.println("---");
			current = current.left; // Move to the next node on the positive x-axis
		}
	
		System.out.println("End of Linked List\n");
	}

	public void printLinkedListNegOnYAxis() {
		Node current = origin;
	
		while (current != null) {
			System.out.println("Node: " + current.getVariables()[0] + ", " + current.getVariables()[1]);
			System.out.println("Links: " + Arrays.toString(current.getNodeLinks()));
			System.out.println("---");
			current = current.down; // Move to the next node on the positive x-axis
		}
	
		System.out.println("End of Linked List\n");
	}

	

	public Node removePoint(int v1, int v2) {

		Node ret = getPoint(v1,v2);

		if(v1 > 0 && v2 > 0){

			Node tempX = origin;
			Node prevX = null;

			while(tempX != null && tempX.getVariables()[0] < v1){
				tempX = tempX.right;
			}

			while(tempX != null && tempX.getVariables()[1] <= v2 ){

				if(tempX.getVariables()[1] < v2){
					prevX = tempX;
				}
				
				tempX = tempX.up;
			}

			if(tempX == null){

				prevX.up = null;

			}else{
				
				prevX.up = tempX;
				tempX.down = prevX;
			}

			Node tempY = origin;
			Node prevY = null;

			while(tempY != null && tempY.getVariables()[1] < v2){
				tempY = tempY.up;
			}

			while(tempY.right != null && tempY.getVariables()[0] <= v1 ){

				if(tempY.getVariables()[0] < v1){
					prevY = tempY;
				}
				
				tempY = tempY.right;
			}

			if(tempY.right == null){
				prevY.right = null;

			}else{
				prevY.right = tempY;
				tempY.left = prevY;
			}

		}else if(v1 > 0 && v2 < 0){

			Node tempX = origin;
			Node prevX = null;

			while(tempX != null && tempX.getVariables()[0] < v1){
				tempX = tempX.right;
			}

			while(tempX != null && tempX.getVariables()[1] >= v2){

				if(tempX.getVariables()[1] > v2){
					prevX = tempX;
				}

				tempX = tempX.down;
			}

			if(tempX == null){
				prevX.down = null;

			}else{

				prevX.down = tempX;
				tempX.up = prevX;
			}

			

			Node tempY = origin;
			Node prevY = null;

			while(tempY != null && tempY.getVariables()[1] > v2){
				tempY = tempY.down;
			}

			while(tempY.right != null && tempY.getVariables()[0] <= v1 ){

				if(tempY.getVariables()[0] < v1){
					prevY = tempY;
				}				
				

				tempY = tempY.right;
			}

			if(tempY.right != null){
				
				prevY.right = tempY;
				tempY.left = prevY;

			}else{
				prevY.right = null;
			}

		}else if(v1 < 0 && v2 < 0){

			Node tempX = origin;
			Node prevX = null;

			while(tempX != null && tempX.getVariables()[0] > v1){
				tempX = tempX.left;
			}

			while(tempX != null && tempX.getVariables()[1] >= v2){

				if(tempX.getVariables()[1] > v2){
					prevX = tempX;
				}

				tempX = tempX.down;
			}

			if(tempX == null){
				prevX.down = null;

			}else{

				prevX.down = tempX;
				tempX.up = prevX;
			}


			Node tempY = origin;
			Node prevY = null;

			while(tempY != null && tempY.getVariables()[1] > v2){
				tempY = tempY.down;
			}

			while(tempY.left != null && tempY.getVariables()[0] >= v1 ){

				if(tempY.getVariables()[0] > v1){
					prevY = tempY;
				}				
				

				tempY = tempY.left;
			}

			if(tempY.left != null){
				
				prevY.left = tempY;
				tempY.right = prevY;

			}else{
				prevY.left = null;
			}


		}else{

			Node tempX = origin;
			Node prevX = null;
			
			while(tempX != null && tempX.getVariables()[0] > v1){
				tempX = tempX.left;
			}

			

			while(tempX != null && tempX.getVariables()[1] <= v2){

				if(tempX.getVariables()[1] < v2){
					prevX = tempX;
				}

				tempX = tempX.up;
				
			}

			if(tempX == null){
				prevX.up = null;

			}else{

				prevX.up = tempX;
				tempX.down = prevX;
			}

			Node tempY = origin;
			Node prevY = null;

			while(tempY != null && tempY.getVariables()[1] < v2){
				tempY = tempY.up;
			}

			while(tempY.left != null && tempY.getVariables()[0] >= v1 ){

				if(tempY.getVariables()[0] > v1){
					prevY = tempY;
				}				
				

				tempY = tempY.left;
			}

			if(tempY.left != null){
				
				prevY.left = tempY;
				tempY.right = prevY;

			}else{
				prevY.left = null;
			}
		}

		return ret;
	}

	public Node getPoint(int v1, int v2) {

		Node ret = null;

		if(v1 > 0 && v2 > 0){

			Node tempX = origin;

			while(tempX != null && tempX.getVariables()[0] < v1){
				tempX = tempX.right;
			}

			while(tempX != null && tempX.getVariables()[1] <= v2 ){

				if(tempX.getVariables()[1] == v2 && tempX.getVariables()[0] == v1){

					ret = tempX;
				}
				
				tempX = tempX.up;
			}

			if(ret == null){
				return null;
			}
			

		}else if(v1 > 0 && v2 < 0){

			Node tempX = origin;


			while(tempX != null && tempX.getVariables()[0] < v1){
				tempX = tempX.right;
			}

			while(tempX != null && tempX.getVariables()[1] >= v2){

				if(tempX.getVariables()[1] == v2 && tempX.getVariables()[0] == v1){
					ret = tempX;
				}

				tempX = tempX.down;
			}


			if(ret == null){
				return null;
			}


		}else if(v1 < 0 && v2 < 0){

			Node tempX = origin;

			while(tempX != null && tempX.getVariables()[0] > v1){
				tempX = tempX.left;
			}

			while(tempX != null && tempX.getVariables()[1] >= v2){

				if(tempX.getVariables()[1] == v2 && tempX.getVariables()[0] == v1){
					ret = tempX;
				}

				tempX = tempX.down;
			}

			if(ret == null){
				return null;
			}

		}else{

			Node tempX = origin;
			
			while(tempX != null && tempX.getVariables()[0] > v1){
				tempX = tempX.left;
			}

			

			while(tempX != null && tempX.getVariables()[1] <= v2){

				if(tempX.getVariables()[1] == v2 && tempX.getVariables()[1] == v2){
					ret = tempX;
				}

				tempX = tempX.up;
				
			}

			if(ret == null){
				return null;
			}
			
		}

		return ret;
	}



	public float findMaxValue() {

		float max = 0;

		Node tempXQ1 = origin;
		Node tempXOnAxisQ1 = origin;
		Node prevXQ1 = null;

		while(tempXOnAxisQ1 != null){
			
			while(tempXQ1.up != null && tempXQ1.getVariables()[0] != 0){

				prevXQ1 = tempXQ1;
				tempXQ1 = tempXQ1.up;
				
				if(tempXQ1.getValue() >= max){
					max = tempXQ1.getValue();
				}
				
			}

			tempXOnAxisQ1 = tempXOnAxisQ1.right;
			tempXQ1 = tempXOnAxisQ1;
			
		}

		Node tempXQ2 = origin;
		Node tempXOnAxisQ2 = origin;
		Node prevXQ2 = null;

		while(tempXOnAxisQ2 != null){

			
			
			
			while(tempXQ2.down != null && tempXQ2.getVariables()[0] != 0){

				prevXQ2 = tempXQ2;
				tempXQ2 = tempXQ2.down;

				// System.out.println("tempXQ2: " + tempXQ2.getVariables()[0] + " " + tempXQ2.getVariables()[1]);
				// System.out.println("prevXQ2: " + prevXQ2.getVariables()[0] + " " + prevXQ2.getVariables()[1]);

				if(tempXQ2.getValue() >= max){
					max = tempXQ2.getValue();
				}
				
			}

			tempXOnAxisQ2 = tempXOnAxisQ2.right;
			tempXQ2 = tempXOnAxisQ2;
			
		}

		Node tempXQ3 = origin;
		Node tempXOnAxisQ3 = origin;
		Node prevXQ3 = null;

		while(tempXOnAxisQ3 != null){
			
			while(tempXQ3.down != null && tempXQ3.getVariables()[0] != 0){

				prevXQ3 = tempXQ3;
				tempXQ3 = tempXQ3.down;
				

				if(tempXQ3.getValue() >= max){
					max = tempXQ3.getValue();
				}
				
			}

			tempXOnAxisQ3 = tempXOnAxisQ3.left;
			tempXQ3 = tempXOnAxisQ3;
			
		}

		Node tempXQ4 = origin;
		Node tempXOnAxisQ4 = origin;
		Node prevXQ4 = null;

		while(tempXOnAxisQ4 != null){
			
			while(tempXQ4.up != null && tempXQ4.getVariables()[0] != 0){

				prevXQ4 = tempXQ4;
				tempXQ4 = tempXQ4.up;
				

				if(tempXQ4.getValue() >= max){
					max = tempXQ4.getValue();
				}
				
			}

			tempXOnAxisQ4 = tempXOnAxisQ4.left;
			tempXQ4 = tempXOnAxisQ4;
			
		}

			

		return max;
			
	}

	public Node findMax() {

		Node max = origin;

		Node tempXQ1 = origin;
		Node tempXOnAxisQ1 = origin;
		Node prevXQ1 = null;

		while(tempXOnAxisQ1 != null){
			
			while(tempXQ1.up != null && tempXQ1.getVariables()[0] != 0){

				prevXQ1 = tempXQ1;
				tempXQ1 = tempXQ1.up;
				
				if(tempXQ1.getValue() >= max.getValue()){
					max = tempXQ1;
				}
				
			}

			tempXOnAxisQ1 = tempXOnAxisQ1.right;
			tempXQ1 = tempXOnAxisQ1;
			
		}

		Node tempXQ2 = origin;
		Node tempXOnAxisQ2 = origin;
		Node prevXQ2 = null;

		while(tempXOnAxisQ2 != null){

			
			
			
			while(tempXQ2.down != null && tempXQ2.getVariables()[0] != 0){

				prevXQ2 = tempXQ2;
				tempXQ2 = tempXQ2.down;

				// System.out.println("tempXQ2: " + tempXQ2.getVariables()[0] + " " + tempXQ2.getVariables()[1]);
				// System.out.println("prevXQ2: " + prevXQ2.getVariables()[0] + " " + prevXQ2.getVariables()[1]);

				if(tempXQ2.getValue() >= max.getValue()){
					max = tempXQ2;
				}
				
			}

			tempXOnAxisQ2 = tempXOnAxisQ2.right;
			tempXQ2 = tempXOnAxisQ2;
			
		}

		Node tempXQ3 = origin;
		Node tempXOnAxisQ3 = origin;
		Node prevXQ3 = null;

		while(tempXOnAxisQ3 != null){
			
			while(tempXQ3.down != null && tempXQ3.getVariables()[0] != 0){

				prevXQ3 = tempXQ3;
				tempXQ3 = tempXQ3.down;
				

				if(tempXQ3.getValue() >= max.getValue()){
					max = tempXQ3;
				}
				
			}

			tempXOnAxisQ3 = tempXOnAxisQ3.left;
			tempXQ3 = tempXOnAxisQ3;
			
		}

		Node tempXQ4 = origin;
		Node tempXOnAxisQ4 = origin;
		Node prevXQ4 = null;

		while(tempXOnAxisQ4 != null){
			
			while(tempXQ4.up != null && tempXQ4.getVariables()[0] != 0){

				prevXQ4 = tempXQ4;
				tempXQ4 = tempXQ4.up;
				

				if(tempXQ4.getValue() >= max.getValue()){
					max = tempXQ4;
				}
				
			}

			tempXOnAxisQ4 = tempXOnAxisQ4.left;
			tempXQ4 = tempXOnAxisQ4;
			
		}

		System.out.println("max: " + max.getVariables()[0] + " " + max.getVariables()[1]);

		return max;
	}

	public float findMinValue() {

		float min = findMaxValue();

		Node tempXQ1 = null;
		Node tempXOnAxisQ1 = null;

		if(origin.right != null){
			tempXQ1 = origin.right;
			tempXOnAxisQ1 = origin.right;
		}else{
			tempXQ1 = origin;
			tempXOnAxisQ1 = origin;
		}

		
		Node prevXQ1 = null;

		

		while(tempXOnAxisQ1 != null){
			
			while(tempXQ1.up != null && tempXQ1.getVariables()[0] != 0){

				prevXQ1 = tempXQ1;
				tempXQ1 = tempXQ1.up;
				
				if(tempXQ1.getValue() <= min){
					min = tempXQ1.getValue();
				}
				
			}

			tempXOnAxisQ1 = tempXOnAxisQ1.right;
			tempXQ1 = tempXOnAxisQ1;
			
		}

		Node tempXQ2 = null;
		Node tempXOnAxisQ2 = null;
		Node prevXQ2 = null;

		if(origin.right != null){
			tempXQ2 = origin.right;
			tempXOnAxisQ2 = origin.right;
		}else{
			tempXQ2 = origin;
			tempXOnAxisQ2 = origin;
		}

		while(tempXOnAxisQ2 != null){

			
			
			
			while(tempXQ2.down != null && tempXQ2.getVariables()[0] != 0){

				prevXQ2 = tempXQ2;
				tempXQ2 = tempXQ2.down;

				// System.out.println("tempXQ2: " + tempXQ2.getVariables()[0] + " " + tempXQ2.getVariables()[1]);
				// System.out.println("prevXQ2: " + prevXQ2.getVariables()[0] + " " + prevXQ2.getVariables()[1]);

				if(tempXQ2.getValue() <= min){
					min = tempXQ2.getValue();
				}
				
			}

			tempXOnAxisQ2 = tempXOnAxisQ2.right;
			tempXQ2 = tempXOnAxisQ2;
			
		}

		Node tempXQ3 = null;
		Node tempXOnAxisQ3 = null;
		Node prevXQ3 = null;

		if(origin.left != null){
			tempXQ3 = origin.left;
			tempXOnAxisQ3 = origin.left;
		}else{
			tempXQ3 = origin;
			tempXOnAxisQ3 = origin;
		}

		while(tempXOnAxisQ3 != null){
			
			while(tempXQ3.down != null && tempXQ3.getVariables()[0] != 0){

				prevXQ3 = tempXQ3;
				tempXQ3 = tempXQ3.down;
				

				if(tempXQ3.getValue() <= min){
					min = tempXQ3.getValue();
				}
				
			}

			tempXOnAxisQ3 = tempXOnAxisQ3.left;
			tempXQ3 = tempXOnAxisQ3;
			
		}

		Node tempXQ4 = null;
		Node tempXOnAxisQ4 = null;
		Node prevXQ4 = null;

		if(origin.left != null){
			tempXQ4 = origin.left;
			tempXOnAxisQ4 = origin.left;
		}else{
			tempXQ4 = origin;
			tempXOnAxisQ4 = origin;
		}

		while(tempXOnAxisQ4 != null){
			
			while(tempXQ4.up != null && tempXQ4.getVariables()[0] != 0){

				prevXQ4 = tempXQ4;
				tempXQ4 = tempXQ4.up;
				

				if(tempXQ4.getValue() <= min){
					min = tempXQ4.getValue();
				}
				
			}

			tempXOnAxisQ4 = tempXOnAxisQ4.left;
			tempXQ4 = tempXOnAxisQ4;
			
		}

			

		return min;
	}

	public Node findMin() {

		Node min = findMax();

		Node tempXQ1 = null;
		Node tempXOnAxisQ1 = null;

		if(origin.right != null){
			tempXQ1 = origin.right;
			tempXOnAxisQ1 = origin.right;
		}else{
			tempXQ1 = origin;
			tempXOnAxisQ1 = origin;
		}

		
		Node prevXQ1 = null;

		

		while(tempXOnAxisQ1 != null){
			
			while(tempXQ1.up != null && tempXQ1.getVariables()[0] != 0){

				prevXQ1 = tempXQ1;
				tempXQ1 = tempXQ1.up;
				
				if(tempXQ1.getValue() <= min.getValue()){
					min = tempXQ1;
				}
				
			}

			tempXOnAxisQ1 = tempXOnAxisQ1.right;
			tempXQ1 = tempXOnAxisQ1;
			
		}

		Node tempXQ2 = null;
		Node tempXOnAxisQ2 = null;
		Node prevXQ2 = null;

		if(origin.right != null){
			tempXQ2 = origin.right;
			tempXOnAxisQ2 = origin.right;
		}else{
			tempXQ2 = origin;
			tempXOnAxisQ2 = origin;
		}

		while(tempXOnAxisQ2 != null){

			
			
			
			while(tempXQ2.down != null && tempXQ2.getVariables()[0] != 0){

				prevXQ2 = tempXQ2;
				tempXQ2 = tempXQ2.down;

				// System.out.println("tempXQ2: " + tempXQ2.getVariables()[0] + " " + tempXQ2.getVariables()[1]);
				// System.out.println("prevXQ2: " + prevXQ2.getVariables()[0] + " " + prevXQ2.getVariables()[1]);

				if(tempXQ2.getValue() <= min.getValue()){
					min = tempXQ2;
				}
				
			}

			tempXOnAxisQ2 = tempXOnAxisQ2.right;
			tempXQ2 = tempXOnAxisQ2;
			
		}

		Node tempXQ3 = null;
		Node tempXOnAxisQ3 = null;
		Node prevXQ3 = null;

		if(origin.left != null){
			tempXQ3 = origin.left;
			tempXOnAxisQ3 = origin.left;
		}else{
			tempXQ3 = origin;
			tempXOnAxisQ3 = origin;
		}

		while(tempXOnAxisQ3 != null){
			
			while(tempXQ3.down != null && tempXQ3.getVariables()[0] != 0){

				prevXQ3 = tempXQ3;
				tempXQ3 = tempXQ3.down;
				

				if(tempXQ3.getValue() <= min.getValue()){
					min = tempXQ3;
				}
				
			}

			tempXOnAxisQ3 = tempXOnAxisQ3.left;
			tempXQ3 = tempXOnAxisQ3;
			
		}

		Node tempXQ4 = null;
		Node tempXOnAxisQ4 = null;
		Node prevXQ4 = null;

		if(origin.left != null){
			tempXQ4 = origin.left;
			tempXOnAxisQ4 = origin.left;
		}else{
			tempXQ4 = origin;
			tempXOnAxisQ4 = origin;
		}

		while(tempXOnAxisQ4 != null){
			
			while(tempXQ4.up != null && tempXQ4.getVariables()[0] != 0){

				prevXQ4 = tempXQ4;
				tempXQ4 = tempXQ4.up;
				

				if(tempXQ4.getValue() <= min.getValue()){
					min = tempXQ4;
				}
				
			}

			tempXOnAxisQ4 = tempXOnAxisQ4.left;
			tempXQ4 = tempXOnAxisQ4;
			
		}

		System.out.println("min: " + min.getVariables()[0] + " " + min.getVariables()[1]);

		return min;
		
	}	


	

	public int countNumberOfPoints(){

		int numPointsQ1 = 0;
		int numPointsQ2  = 0;
		int numPointsQ3 = 0;
		int numPointsQ4 = 0;

		Node tempXQ1 = null;
		Node tempXOnAxisQ1 = null;

		if(origin.right != null){
			tempXQ1 = origin.right;
			tempXOnAxisQ1 = origin.right;
		}else{
			tempXQ1 = origin;
			tempXOnAxisQ1 = origin;
		}

		
		Node prevXQ1 = null;

		

		while(tempXOnAxisQ1 != null){
			
			while(tempXQ1.up != null && tempXQ1.getVariables()[0] != 0){

				prevXQ1 = tempXQ1;
				tempXQ1 = tempXQ1.up;
				numPointsQ1++;
				
			}

			tempXOnAxisQ1 = tempXOnAxisQ1.right;
			tempXQ1 = tempXOnAxisQ1;
			
		}


		Node tempXQ2 = null;
		Node tempXOnAxisQ2 = null;
		Node prevXQ2 = null;

		if(origin.right != null){
			tempXQ2 = origin.right;
			tempXOnAxisQ2 = origin.right;
		}else{
			tempXQ2 = origin;
			tempXOnAxisQ2 = origin;
		}

		while(tempXOnAxisQ2 != null){

			
			
			
			while(tempXQ2.down != null && tempXQ2.getVariables()[0] != 0){

				prevXQ2 = tempXQ2;
				tempXQ2 = tempXQ2.down;
				numPointsQ2++;

				
			}

			tempXOnAxisQ2 = tempXOnAxisQ2.right;
			tempXQ2 = tempXOnAxisQ2;
			
		}

		Node tempXQ3 = null;
		Node tempXOnAxisQ3 = null;
		Node prevXQ3 = null;

		if(origin.left != null){
			tempXQ3 = origin.left;
			tempXOnAxisQ3 = origin.left;
		}else{
			tempXQ3 = origin;
			tempXOnAxisQ3 = origin;
		}

		while(tempXOnAxisQ3 != null){
			
			while(tempXQ3.down != null && tempXQ3.getVariables()[0] != 0){

				prevXQ3 = tempXQ3;
				tempXQ3 = tempXQ3.down;
				
				numPointsQ3++;
				
			}

			tempXOnAxisQ3 = tempXOnAxisQ3.left;
			tempXQ3 = tempXOnAxisQ3;
			
		}


		Node tempXQ4 = null;
		Node tempXOnAxisQ4 = null;
		Node prevXQ4 = null;

		if(origin.left != null){
			tempXQ4 = origin.left;
			tempXOnAxisQ4 = origin.left;
		}else{
			tempXQ4 = origin;
			tempXOnAxisQ4 = origin;
		}

		while(tempXOnAxisQ4 != null){
			
			while(tempXQ4.up != null && tempXQ4.getVariables()[0] != 0){

				prevXQ4 = tempXQ4;
				tempXQ4 = tempXQ4.up;
				
				numPointsQ4++;
				
			}

			tempXOnAxisQ4 = tempXOnAxisQ4.left;
			tempXQ4 = tempXOnAxisQ4;
			
		}

		return numPointsQ1 + numPointsQ2 + numPointsQ3 + numPointsQ4;
	}

	public int[] numPointsPerQuadrant(){

		int [] pointsPerQuadrant = new int[4];

		int numPointsQ1 = 0;
		int numPointsQ2  = 0;
		int numPointsQ3 = 0;
		int numPointsQ4 = 0;

		Node tempXQ1 = null;
		Node tempXOnAxisQ1 = null;

		if(origin.right != null){
			tempXQ1 = origin.right;
			tempXOnAxisQ1 = origin.right;
		}else{
			tempXQ1 = origin;
			tempXOnAxisQ1 = origin;
		}

		
		Node prevXQ1 = null;

		

		while(tempXOnAxisQ1 != null){
			
			while(tempXQ1.up != null && tempXQ1.getVariables()[0] != 0){

				prevXQ1 = tempXQ1;
				tempXQ1 = tempXQ1.up;
				numPointsQ1++;
				
			}

			tempXOnAxisQ1 = tempXOnAxisQ1.right;
			tempXQ1 = tempXOnAxisQ1;
			
		}


		Node tempXQ2 = null;
		Node tempXOnAxisQ2 = null;
		Node prevXQ2 = null;

		if(origin.right != null){
			tempXQ2 = origin.right;
			tempXOnAxisQ2 = origin.right;
		}else{
			tempXQ2 = origin;
			tempXOnAxisQ2 = origin;
		}

		while(tempXOnAxisQ2 != null){

			
			
			
			while(tempXQ2.down != null && tempXQ2.getVariables()[0] != 0){

				prevXQ2 = tempXQ2;
				tempXQ2 = tempXQ2.down;
				numPointsQ2++;

				
			}

			tempXOnAxisQ2 = tempXOnAxisQ2.right;
			tempXQ2 = tempXOnAxisQ2;
			
		}

		Node tempXQ3 = null;
		Node tempXOnAxisQ3 = null;
		Node prevXQ3 = null;

		if(origin.left != null){
			tempXQ3 = origin.left;
			tempXOnAxisQ3 = origin.left;
		}else{
			tempXQ3 = origin;
			tempXOnAxisQ3 = origin;
		}

		while(tempXOnAxisQ3 != null){
			
			while(tempXQ3.down != null && tempXQ3.getVariables()[0] != 0){

				prevXQ3 = tempXQ3;
				tempXQ3 = tempXQ3.down;
				
				numPointsQ3++;
				
			}

			tempXOnAxisQ3 = tempXOnAxisQ3.left;
			tempXQ3 = tempXOnAxisQ3;
			
		}


		Node tempXQ4 = null;
		Node tempXOnAxisQ4 = null;
		Node prevXQ4 = null;

		if(origin.left != null){
			tempXQ4 = origin.left;
			tempXOnAxisQ4 = origin.left;
		}else{
			tempXQ4 = origin;
			tempXOnAxisQ4 = origin;
		}

		while(tempXOnAxisQ4 != null){
			
			while(tempXQ4.up != null && tempXQ4.getVariables()[0] != 0){

				prevXQ4 = tempXQ4;
				tempXQ4 = tempXQ4.up;
				
				numPointsQ4++;
				
			}

			tempXOnAxisQ4 = tempXOnAxisQ4.left;
			tempXQ4 = tempXOnAxisQ4;
			
		}

		pointsPerQuadrant[0] = numPointsQ1;
		pointsPerQuadrant[1] = numPointsQ2;
		pointsPerQuadrant[2] = numPointsQ3;
		pointsPerQuadrant[3] = numPointsQ4;

		return pointsPerQuadrant;

	}

	public void clearAllData(){
		 
		origin.up = null;
		origin.down = null;
		origin.left = null;
		origin.right = null;
	}


	// public Node[] toArray() {

	// }

	// public String printFunctionValues(String functionName) {

	// }

	// public int removeAllFunctionPoints(String functionName){

	// }

	//ADD HELPER FUNCTIONS BELOW
}