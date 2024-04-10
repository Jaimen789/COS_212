public class InsertSort extends Sorting {

    public InsertSort(){
        this.name = "InsertSort";
    }

    @Override
    public String[] sortAcs(Vertex[] array) {
        int length = array.length;

        String [] arr = new String[length];

        for(int i = 0; i < length; i++){
            arr[i] = array[i].getVName();
        }

        for(int i = 0; i < length; ++i){
            String key = arr[i];

            int j = i - 1;

            while(j >= 0 && arr[j].compareTo(key) > 0){
                arr[j + 1] = arr[j];
                j = j - 1;
            }

            arr[j+ 1] = key;
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

        for(int i = 1; i < length; ++i){
            String key = arr[i];

            int j = i - 1;

            while(j >= 0 && arr[j].compareTo(key) < 0){
                arr[j + 1] = arr[j];
                j = j - 1;
            }

            arr[j+ 1] = key;
        }

        return arr;
    }
}
