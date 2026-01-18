import java.util.concurrent.*;

public class task1 {
    public static class Path implements Callable<String> {
        public int n;
        public int m;
        public Path(int n, int m) {
            this.n = n;
            this.m = m;
        }
        public String call() {
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
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> rez1 = executorService
                .submit(new Path(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
        Future<String> rez2 = executorService
                .submit(new Path(Integer.parseInt(args[2]), Integer.parseInt(args[3])));
        String result1 = rez1.get();
        String result2 = rez2.get();
        System.out.print(result1);
        System.out.println(result2);
        executorService.shutdown();
    }
}
