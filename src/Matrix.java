import java.lang.reflect.Array;
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

    public void getDiagonals(ArrayList<ArrayList<T>> lists) throws Exception {
        if (lists.size() != 0) {
            throw new Exception("List must be empty");
        }

        for (int i = 0; i < 6; i++) {
            lists.add(new ArrayList<>());
        }

        for (int i = 0; i < 6; i++) {
            lists.get(0).add(getValue(i, i));
            lists.get(1).add(getValue(5 - i, i));
        }

        for (int i = 1; i < 6; i++) {
            int j = i - 1;
            lists.get(2).add(getValue(i, j));
            lists.get(3).add(getValue(j, i));
        }

        for (int i = 0; i < 5; i++) {
            int j = 4 - i;
            lists.get(4).add(getValue(i, j));
        }

        for (int i = 1; i < 6; i++) {
            int j = 6 - i;
            lists.get(5).add(getValue(i, j));
        }
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
