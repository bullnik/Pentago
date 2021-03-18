import java.util.ArrayList;

public class Matrix<T> {
    private final T[][] array;
    private final int size;

    public Matrix(int size, T[][] array) throws Exception {
        for (T[] arr : array) {
            if (arr.length != size) {
                throw new Exception("Matrix must be square");
            }
        }

        this.array = array;
        this.size = size;
    }

    public void getRows(ArrayList<ArrayList<T>> lists) throws Exception {
        if (lists.size() != 0) {
            throw new Exception("List must be empty");
        }

        for (int y = 0; y < 6; y++) {
            lists.add(new ArrayList<>());
            ArrayList<T> list = lists.get(y);
            for (int x = 0; x < 6; x++) {
                list.add(getValue(x, y));
            }
        }
    }

    public void getColumns(ArrayList<ArrayList<T>> lists) throws Exception {
        if (lists.size() != 0) {
            throw new Exception("List must be empty");
        }

        for (int x = 0; x < 6; x++) {
            lists.add(new ArrayList<>());
            ArrayList<T> list = lists.get(x);
            for (int y = 0; y < 6; y++) {
                list.add(getValue(x, y));
            }
        }
    }

    public void getDiagonals(ArrayList<ArrayList<T>> lists) {

    }

    public int getSize() {
        return size;
    }

    public void setValue(int x, int y, T value) {
        array[y][x] = value;
    }

    public T getValue(int x, int y) {
        return array[y][x];
    }
}
