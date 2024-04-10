public class main {
    public static void main(String[] args) {
        Interface interface1 = new Interface();

        // System.out.println("insert on positive x-axis");
        // interface1.addPoint(new ExampleFunction1(), 2, 0);

        // interface1.addPoint(new ExampleFunction1(), 1, 0);

        // interface1.addPoint(new ExampleFunction1(), 4, 0);

        // interface1.addPoint(new ExampleFunction1(), 3, 0);

        // interface1.addPoint(new ExampleFunction1(), 2, 0);

        // interface1.printLinkedListOnPosXAxis();

        // System.out.println("insert on positive y-axis");
        // interface1.addPoint(new ExampleFunction1(), 0, 2);

        // interface1.addPoint(new ExampleFunction1(), 0, 1);

        // interface1.addPoint(new ExampleFunction1(), 0, 4);

        // interface1.addPoint(new ExampleFunction1(), 0, 3);

        // interface1.addPoint(new ExampleFunction1(), 0, 2);

        // interface1.printLinkedListPosOnYAxis();

        // System.out.println("insert on negative x-axis");
        // interface1.addPoint(new ExampleFunction1(), -1, 0);

        // interface1.addPoint(new ExampleFunction1(), -2, 0);

        // interface1.addPoint(new ExampleFunction1(), -3, 0);

        // interface1.addPoint(new ExampleFunction1(), -4, 0);

        // interface1.addPoint(new ExampleFunction1(), -2, 0);

        // interface1.printLinkedListNegOnXAxis();

        // System.out.println("insert on negative y-axis");
        // interface1.addPoint(new ExampleFunction1(), 0, -2);

        // interface1.addPoint(new ExampleFunction1(), 0, -1);

        // interface1.addPoint(new ExampleFunction1(), 0, -3);

        // interface1.addPoint(new ExampleFunction1(), 0, -4);

        // interface1.addPoint(new ExampleFunction1(), 0, -2);

        // interface1.printLinkedListNegOnYAxis();

        System.out.println("insert in quadrant 1");

        interface1.addPoint(new ExampleFunction1(), 3 , 3);
        interface1.addPoint(new ExampleFunction1(), 2, 3);

        interface1.addPoint(new ExampleFunction1(), 4, 3);
        interface1.addPoint(new ExampleFunction1(), 1, 3);

        //interface1.addPoint(new ExampleFunction1(), 2, 5);

        interface1.addPoint(new ExampleFunction1(), 1, 2);

        interface1.addPoint(new ExampleFunction1(), 3, 2);

        interface1.addPoint(new ExampleFunction1(), 2, 2);

        interface1.addPoint(new ExampleFunction1(), 6, 4);
        

        interface1.printLinkedListOnPosXAxis();
        interface1.printLinkedListPosOnYAxis();

        System.out.println("insert in quadrant 2");

        interface1.addPoint(new ExampleFunction1(), 3 , -3);

        interface1.addPoint(new ExampleFunction1(), 3 , -2);

        interface1.addPoint(new ExampleFunction1(), 3 , -4);

        interface1.addPoint(new ExampleFunction1(), 8, -4);

        // interface1.addPoint(new ExampleFunction1(), 10, -4);

        interface1.addPoint(new ExampleFunction1(),9, -4);

        // interface1.addPoint(new ExampleFunction1(),12, -1);

        // interface1.addPoint(new ExampleFunction1(),11, -5);

        interface1.addPoint(new ExampleFunction1(),8, -3);

        interface1.addPoint(new ExampleFunction1(),6, -2);

        interface1.addPoint(new ExampleFunction1(),4, -4);



        interface1.printLinkedListOnPosXAxis();
        interface1.printLinkedListNegOnYAxis();


        System.out.println("insert in quadrant 3: ");
        interface1.addPoint(new ExampleFunction1(), -3, -3);
        interface1.addPoint(new ExampleFunction1(), -2, -3);
        interface1.addPoint(new ExampleFunction1(), -2, -6);
        interface1.addPoint(new ExampleFunction1(), -2, -7);

        interface1.addPoint(new ExampleFunction1(), -4, -3);

        interface1.addPoint(new ExampleFunction1(), -3, -2);

        interface1.addPoint(new ExampleFunction1(), -3, -4);

        interface1.addPoint(new ExampleFunction1(), -2, -4);



        interface1.printLinkedListNegOnXAxis();
        interface1.printLinkedListNegOnYAxis();


        System.out.println("insert in quadrant 4: ");

        interface1.addPoint(new ExampleFunction1(), -3, 4);

        interface1.addPoint(new ExampleFunction1(), -2, 4);

        interface1.addPoint(new ExampleFunction1(), -5, 2);

        
        
        interface1.printLinkedListNegOnXAxis();
        interface1.printLinkedListPosOnYAxis();

        System.out.println("remove in q1: ");

        interface1.removePoint(6, 4);
        interface1.removePoint(2, 2);
        interface1.removePoint(1, 2);


        interface1.printLinkedListOnPosXAxis();
        interface1.printLinkedListPosOnYAxis();

        System.out.println("remove in q2: ");

        interface1.removePoint(3, -2);
        interface1.removePoint(8, -4);
 


        interface1.printLinkedListOnPosXAxis();
        interface1.printLinkedListNegOnYAxis();

        System.out.println("remove in q3: ");

        interface1.removePoint(-3, -2);
        //interface1.removePoint(-2, -6);
 


        interface1.printLinkedListNegOnXAxis();
        interface1.printLinkedListNegOnYAxis();

        System.out.println("remove in q4: ");

        interface1.removePoint(-3, 4);
        interface1.removePoint(-5, 2);
 


        interface1.printLinkedListNegOnXAxis();
        interface1.printLinkedListPosOnYAxis();


        System.out.println("testing get node: ");
        
        System.out.println(interface1.getPoint(3, 3).getValue());
        System.out.println(interface1.getPoint(3, -3).getValue());
        System.out.println(interface1.getPoint(-4, -3).getValue());
        System.out.println(interface1.getPoint(-2, 4).getValue());

        System.out.println();

        System.out.println("find max: ");
        System.out.println(interface1.findMaxValue());

        System.out.println();

        System.out.println("Get max node: ");
        interface1.findMax();

        System.out.println();

        System.out.println("find min: ");
        System.out.println(interface1.findMinValue());
        
        System.out.println();

        System.out.println("Get min node: ");
        interface1.findMin();

        System.out.println();

        System.out.println("count number of points: ");
        System.out.println(interface1.countNumberOfPoints());

        System.out.println();

        System.out.println("number of points pre quadrant: ");

        for(int i = 0; i < 4; i++){
            System.out.println(interface1.numPointsPerQuadrant()[i]);
        }
        



        // for(int i=-1; i <= 1; i++)
        // {
        //     for(int j=1; j >= -1; j--)
        //     {
        //         interface1.addPoint(new ExampleFunction1(), i, j);
        //     }
        // }

        // //System.out.println(interface1.printFunctionValues((new ExampleFunction1()).getFunctionName()));
        // for(int i=-2; i <= 2; i++)
        // {
        //     for(int j=-2; j <= 2; j++)
        //     {
        //         interface1.addPoint(new ExampleFunction2(), i, j);
        //     }
        // }
        // System.out.println(interface1.printFunctionValues((new ExampleFunction2()).getFunctionName()));
        // Node n1 = interface1.getPoint(1, 1);
        // for(int i=0; i < 6; i++)
        // {
        //     System.out.print(n1.getNodeLinks()[i]);
        // }
        // System.out.println();
        // Node n2 = interface1.getPoint(2, 2);
        // for(int i=0; i < 6; i++)
        // {
        //     System.out.print(n2.getNodeLinks()[i]);
        // }
        // System.out.println();
        // for(int i=0; i < 4; i++)
        // {
        //     System.out.println("Count in Q" + i + ": " + interface1.numPointsPerQuadrant()[i]);
        // }
        // System.out.println("Number of nodes/points: " + interface1.countNumberOfPoints());
        // System.out.println(n1.getFunction().getFunctionName());
        // System.out.println(n1.prevVal.getFunction().getFunctionName());
        // System.out.println(n2.getFunction().getFunctionName());
        // Node n3 = interface1.removePoint(1, 1);
        // if(n3 == n1)
        //     System.out.println("Correct");
        // else 
        //     System.out.println("Problem");
        // Node n4 = interface1.getPoint(1, 1);
        // if(n4 != n1)
        //     System.out.println("Correct");
        // else 
        //     System.out.println("Problem");
        // System.out.println(n4.getFunction().getFunctionName());
        // System.out.println(n4.getValue() + " == " + interface1.calculateValue((new ExampleFunction2()), 1, 1));
    }
}

class ExampleFunction1 extends Function{
    public ExampleFunction1(){
        functionName = "Example function 1";
    }

    public float calculate(int v1, int v2){
        return Math.abs(v1) + Math.abs(v2);
    }

    public Function clone(){
        return new ExampleFunction1();
    }
}

class ExampleFunction2 extends Function{
    public ExampleFunction2(){
        functionName = "Example function 2";
    }

    public float calculate(int v1, int v2){
        return Math.max(v1, v2);
    }

    public Function clone(){
        return new ExampleFunction2();
    }
}

/*
Expected output:
2;0;0;2
-2;-1;1;2;-1;-1;1;2;1;1;1;2;2;2;2;2
U[1][2]{2}D[1][0]{0}R[2][1]{2}L[0][1]{0}P[1][1]{1}N[][]{}
U[][]{}D[2][1]{2}R[][]{}L[1][2]{2}P[][]{}N[][]{}
Count in Q0: 5
Count in Q1: 5
Count in Q2: 5
Count in Q3: 5
Number of nodes/points: 20
Example function 1
Example function 2
Example function 2
Correct
Correct
Example function 2
1.0 == 1.0
*/
