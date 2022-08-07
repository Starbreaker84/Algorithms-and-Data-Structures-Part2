import java.util.*;

class Heap
{
    public int [] HeapArray; // хранит неотрицательные числа-ключи

    public Heap() {
        HeapArray = null;
    }

    public void MakeHeap(int[] a, int depth) {
        // создаём массив кучи HeapArray из заданного
        // размер массива выбираем на основе глубины depth
        HeapArray = new int[(int) Math.pow(2, (double) depth + 1) - 1];
        Arrays.fill(HeapArray, -1);
        for (int num : a) {
            Add(num);
        }
    }

    private int getFirstNotEmptyIndex(){
        for (int i = HeapArray.length - 1; i >= 0; i--){
            if (HeapArray[i] != -1){
                return i;
            }
        }
        return -1;
    }

    private void removeMax(int index){
        if (2 * index + 1 < HeapArray.length && HeapArray[index] < HeapArray[2 * index + 1] ){
            int temp = HeapArray[index];
            HeapArray[index] = HeapArray[2 * index + 1];
            HeapArray[2 * index + 1] = temp;
            removeMax(2 * index + 1);
        }
        if (2 * index + 2 < HeapArray.length && HeapArray[index] < HeapArray[2 * index + 2]) {
            int temp = HeapArray[index];
            HeapArray[index] = HeapArray[2 * index + 2];
            HeapArray[2 * index + 2] = temp;
            removeMax(2 * index + 2);
        }
    }

    public int GetMax() {
        if (HeapArray == null) return -1;
        int index = getFirstNotEmptyIndex();
        if (index != -1) {
            int max = HeapArray[0];
            HeapArray[0] = HeapArray[index];
            HeapArray[index] = -1;
            removeMax(0);
            return max;
        }
        return -1; // если куча пуста
    }

    private int getFirstEmptyIndex(){
        for (int i = 0; i < HeapArray.length; i++){
            if (HeapArray[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    private boolean addElement(int index){
        if ((index - 1) / 2 >= 0 && HeapArray[(index - 1) / 2] < HeapArray[index]) {
            int temp = HeapArray[index];
            HeapArray[index] = HeapArray[(index - 1) / 2];
            HeapArray[(index - 1) / 2] = temp;
            return addElement((index - 1) / 2);
        }
        return true;
    }
    public boolean Add(int key) {
        if (HeapArray == null){
            return false;
        }
        // добавляем новый элемент key в кучу и перестраиваем её
        int index = getFirstEmptyIndex();
        if (index != -1){
            HeapArray[index] = key;
            return addElement(index);
        }
        return false; // если куча вся заполнена
    }

}
