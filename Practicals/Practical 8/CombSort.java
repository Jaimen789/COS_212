public class CombSort extends Sorting {
    public CombSort(){
        this.name = "CombSort";
    }

    @Override
    public String[] sortAcs(Vertex[] array) {

        int length = array.length;
        int gap = length;
        boolean swapped = true;

        String [] arr = new String[length];

        for(int i = 0; i < length; i++){
            arr[i] = array[i].getVName();
        }

        while(gap != 1 || swapped == true){

            gap = getNextGap(gap);

            swapped = false;

            for(int i = 0; i < length - gap; i++){

                if(arr[i].compareTo(arr[i+gap]) > 0){

                    String temp = arr[i];
                    arr[i] = arr[i + gap];
                    arr[i + gap] = temp;

                    swapped = true;
                }
            }
        }

        return arr;

    }

    @Override
    public String[] sortDsc(Vertex[] array) {

        int length = array.length;
        int gap = length;
        boolean swapped = true;

        String [] arr = new String[length];

        for(int i = 0; i < length; i++){
            arr[i] = array[i].getVName();
        }

        while(gap != 1 || swapped == true){

            gap = getNextGap(gap);

            swapped = false;

            for(int i = 0; i < length - gap; i++){

                if(arr[i].compareTo(arr[i+gap]) < 0){

                    String temp = arr[i];
                    arr[i] = arr[i + gap];
                    arr[i + gap] = temp;

                    swapped = true;
                }
            }
        }

        return arr;
    }

    int getNextGap(int gap){

        gap = (gap * 10) / 13;

        if(gap < 1){
            return 1;
        }

        return gap;
    }
}
