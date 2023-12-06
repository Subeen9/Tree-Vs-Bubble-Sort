class BubbleSort {
    private int swaps;
    private int[] array;
    public BubbleSort(int[] array) {
        this.array = array;
    }
    public void sort() {
        boolean swapped;
        for (int j = 0; j < array.length; j++) {
            swapped = false;
            for (int i = j + 1; i < array.length; i++) {
                if (array[i] < array[j]) {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
    public int[] getSortedArray() {
        return array;
    }
    public int getSwaps() {
        return swaps;
    }
}