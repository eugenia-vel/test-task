import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class task4 {
    private static int getSum(Integer[] array, int number) {
        int sum = 0;
        for (int el : array) {
            sum += Math.abs(el - number);
        }
        return sum;
    }
    public static void main(String[] args) {
        File inputFile = new File(args[0]);
        ArrayList<Integer> list = new ArrayList<>();
        try (Scanner reader = new Scanner(inputFile)) {
            while (reader.hasNextInt()) {
                list.add(reader.nextInt());
            }
            Integer[] array = list.toArray(new Integer[0]);
            Arrays.sort(array);
            int pointer = array.length/2;
            HashMap<Integer,Integer> hashMap = new HashMap<>();
            while (pointer > 0 || pointer < array.length) {
                int currentNumber = array[pointer];
                int prevPointer = pointer - 1;
                while (array[prevPointer] == currentNumber && prevPointer > 0) {
                    prevPointer -= 1;
                }
                int prevNumber = array[prevPointer];
                int nextPointer = pointer - 1;
                while (array[nextPointer] == currentNumber && nextPointer < array.length - 1) {
                    nextPointer += 1;
                }
                int nextNumber = array[nextPointer];
                if (!hashMap.containsKey(currentNumber)) {
                    hashMap.put(currentNumber, getSum(array, currentNumber));
                }
                if (!hashMap.containsKey(prevNumber)) {
                    hashMap.put(prevNumber, getSum(array, prevNumber));
                }
                if (!hashMap.containsKey(currentNumber)) {
                    hashMap.put(nextNumber, getSum(array, nextNumber));
                }
                if (hashMap.get(currentNumber) <= hashMap.get(nextNumber)
                        && hashMap.get(currentNumber) <= hashMap.get(nextNumber)) {
                    if (hashMap.get(currentNumber) > 20) {
                        System.out.print("20 ходов недостаточно для приведения всех элементов массива к одному числу");
                    } else {
                        System.out.print(hashMap.get(currentNumber));
                    }
                    break;
                } else if (hashMap.get(currentNumber) > hashMap.get(nextNumber)) {
                    pointer += 1;
                }
                else {
                    pointer -= 1;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
    }
}
