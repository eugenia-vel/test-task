import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class task2 {
    private static int getPosition(double x, double y, double a, double b) {
        double equation = Math.pow(x, 2)/Math.pow(a, 2) + Math.pow(y, 2)/Math.pow(b, 2);
        if (equation == 1) {
            return 0;
        } else if (equation > 1) {
            return 2;
        } else {
            return 1;
        }
    }
    public static void main(String[] args) {
        File file1 = new File(args[0]);
        double[] center = new double[2];
        double[] radius = new double[2];
        try (Scanner scanner = new Scanner(file1)) {
            center[0] = scanner.nextDouble();
            center[1] = scanner.nextDouble();
            radius[0] = scanner.nextDouble();
            radius[1] = scanner.nextDouble();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
        File file2 = new File(args[1]);
        try (Scanner scanner = new Scanner(file2)) {
            while (scanner.hasNextDouble()) {
                double x = scanner.nextDouble() - center[0];
                double y = scanner.nextDouble() - center[1];
                System.out.println(getPosition(x, y, radius[0], radius[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
    }
}
