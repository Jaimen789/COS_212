public class SelectionSort extends Sorting {
    
    public SelectionSort(){
        this.name = "SelectionSort";
    }

    @Override
    public String[] sortAcs(Vertex[] array) {

        int length = array.length;

        String [] arr = new String[length];

        for(int i = 0; i < length; i++){
            arr[i] = array[i].getVName();
        }

        for(int i = 0; i < length - 1; i++){

            int min_index =  i;

            for(int j = i + 1; j < length; j++){
                if(arr[j].compareTo(arr[min_index]) < 0){
                    min_index = j;
                }

            }

            String temp = arr[min_index];
            arr[min_index] = arr[i];
            arr[i] = temp;

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

        for(int i = 0; i < length - 1; i++){

            int min_index =  i;

            for(int j = i + 1; j < length; j++){
                if(arr[j].compareTo(arr[min_index]) > 0){
                    min_index = j;
                }
            }

            String temp = arr[min_index];
            arr[min_index] = arr[i];
            arr[i] = temp;
        }

        return arr;
    }
}
