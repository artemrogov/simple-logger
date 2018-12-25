package strategy;

public class LearnStrategy {
    public static void main(String[] args) {

        //сортировка пузырьком:
        int[] arr = {12,2,0,7,5,1,6,8,10,9,11};
        StrategyClient bubbleSort = new StrategyClient();
        bubbleSort.setStrategy(new BubbleSort());

        bubbleSort.executeSort(arr);

        // сортировка вставкой:
        System.out.println();

        StrategyClient insertSort = new StrategyClient();

        insertSort.setStrategy(new InsertSort());

        insertSort.executeSort(arr);

    }
}

class StrategyClient {

    private Sorting strategy;

    public void setStrategy(Sorting strategy) {
        this.strategy = strategy;
    }

    public void executeSort(int[] arr) {
        strategy.sort(arr);
    }
}
interface Sorting {

    void sort(int[] arr);
}

class BubbleSort implements Sorting {

    @Override
    public void sort(int[] arr) {
        int j,t;
        int size = arr.length;

        for (int i = 1; i<size; i++){

            for(j = size - 1; j>=i; j--){
                if (arr[j-1] > arr[j]){
                    t = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = t;
                }
            }
        }
        System.out.println("Отсортированный массив: ");

        for (int i = 1; i<size; i++){
            System.out.print(i + " ");
        }

    }
}

class InsertSort implements Sorting {
    @Override
    public void sort(int[] arr) {
        int counter = 0;
        int size = arr.length;

        for (int p = 1; p<size; p++){
            for(int j1 = p; j1>0 && arr[j1 - 1]>arr[j1]; j1--){
                 counter++;
                int tmp = arr[j1 - 1];
                arr[j1 - 1] = arr[j1];
                arr[j1] = tmp;
            }
        }
        System.out.println("Сортировка вставкой - Отсортированный массив: ");

        for (int i = 0; i<size; i++){
            System.out.print(i + " ");
        }

    }
}
