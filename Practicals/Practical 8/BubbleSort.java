public class BubbleSort extends Sorting {
    public BubbleSort(){
        this.name = "BubbleSort";
    }

    @Override
    public String[] sortAcs(Vertex[] array) {

        int length = array.length;

        String [] arr = new String[length];

        for(int i = 0; i < length; i++){
            arr[i] = array[i].getVName();
        }

        int i, j;
        String temp;
        boolean swapped;

        for(i = 0; i < length - 1; i++){
            swapped = false;

            for(j = 0; j < length - i -1; j++){
                if(arr[j].compareTo(arr[j+1]) > 0){

                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if(swapped == false){
                break;
            }
        }

        return arr;

    }

    @Override
    public String[] sortDsc(Vertex[] array) {

        int length = array.length;

        String [] arr = new String[length];

        for(int i = 0; i < length; i++){
            arr[i] = array[i].getVName();
        }

        int i, j;
        String temp;
        boolean swapped;

        for(i = 0; i < length - 1; i++){
            swapped = false;

            for(j = 0; j < length - i -1; j++){
                if(arr[j].compareTo(arr[j+1]) < 0){

                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if(swapped == false){
                break;
            }
        }

        return arr;
    }

}
