public class task1 {
    private static String getPath(int n, int m) {
        int pointer = 0;
        StringBuilder result = new StringBuilder();
        int[] array = new int[n];
        for (int i = 0; i < n; i++){
            array[i] = i + 1;
        }
        do {
            result.append(array[pointer]);
            pointer += m - 1;
            if (pointer >= n) { pointer -= n;}
        } while (pointer != 0);
        return result.toString();
    }
    public static void main(String[] args) {
        for (int i = 0; i < 4; i += 2) {
            System.out.print(getPath(Integer.parseInt(args[i]), Integer.parseInt(args[i+1])));
        }
    }
}
