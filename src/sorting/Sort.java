package sorting;

public class Sort {
    public static void main(String[] args) {
        int[] table = {18, 5, 1, 8, 2, 10, 6, 2, 13};
        printTable(table);
        bubleSort(table);
    }

    // sortowanie bąbelkowe
    public static void bubleSort(int[] table) {
        for (int i = 1; i <= table.length - 1; i++) {
            for (int j = table.length - 1; j >= i; j--) {
                if (table[j - 1] > table[j]) {
                    swap(table, j);
                }
                printTable(table);
            }
            System.out.println("iteracja " + i);
        }
    }

    public static void swap(int[] table, int j) {
        int temp = table[j - 1];
        table[j - 1] = table[j];
        table[j] = temp;
    }

    public static void printTable(int[] table) {
        System.out.println("");
        for (int element : table) {
            System.out.print(element + " ");
        }
    }
}
