import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.Stack;

public class QuickSort {

    public static void main(String[] args) {
        String pastaEntrada = "../input/";
        String pastaSaida = "../output/quickSort/";
        File diretorio = new File(pastaEntrada);
        String csvFile = "../output/quickSort/grafico.csv";
        String delimiter = ",";

        if (diretorio.isDirectory()) {
            File[] arquivos = diretorio.listFiles();

            if (arquivos != null) {
                try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
                    writer.println("Arquivo" + delimiter + "ms"); // Escrever cabeçalho do CSV

                    for (File arquivo : arquivos) {
                        if (arquivo.isFile()) {
                            File saida = new File(pastaSaida + arquivo.getName());
                            long startTime = System.currentTimeMillis();
                            int[] array = readDataFromFile(pastaEntrada + arquivo.getName());
                            quickSort(array);
                            writeDataToFile(array, pastaSaida + arquivo.getName());
                            long endTime = System.currentTimeMillis();
                            writer.println(arquivo.getName() + delimiter + (endTime - startTime)); // Escrever dados
                        }
                    }
                    
                    System.out.println("Arquivo CSV criado com sucesso!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("O caminho especificado não é uma pasta de entrada válida.");
        }
    }


    private static int[] readDataFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int index = 0;
            int[] array = new int[getFileSize(filePath)];

            while ((line = reader.readLine()) != null) {
                array[index] = Integer.parseInt(line);
                index++;
            }

            return array;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new int[0];
    }

    private static int getFileSize(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int size = 0;
            while (reader.readLine() != null) {
                size++;
            }
            return size;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private static void quickSort(int[] array) {
        Stack<Integer> stack = new Stack<>();
        int low = 0;
        int high = array.length - 1;
        stack.push(low);
        stack.push(high);

        while (!stack.isEmpty()) {
            high = stack.pop();
            low = stack.pop();

            int pivotIndex = partition(array, low, high);

            if (pivotIndex - 1 > low) {
                stack.push(low);
                stack.push(pivotIndex - 1);
            }

            if (pivotIndex + 1 < high) {
                stack.push(pivotIndex + 1);
                stack.push(high);
            }
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void writeDataToFile(int[] array, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int value : array) {
                writer.write(String.valueOf(value));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
