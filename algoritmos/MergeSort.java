import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class MergeSort {

    public static void main(String[] args) {
            String pastaEntrada = "../input/";
            String pastaSaida = "../output/mergeSort/";
            File diretorio = new File(pastaEntrada);
            String csvFile = "../output/mergeSort/grafico.csv";
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
                                mergeSort(array);
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

    private static void mergeSort(int[] array) {
        int n = array.length;

        if (n <= 1) {
            return;
        }

        int mid = n / 2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, n - mid);

        mergeSort(left);
        mergeSort(right);

        merge(array, left, right);
    }

    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        int leftSize = left.length;
        int rightSize = right.length;

        while (i < leftSize && j < rightSize) {
            if (left[i] <= right[j]) {
                array[k] = left[i];
                i++;
            } else {
                array[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            array[k] = left[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            array[k] = right[j];
            j++;
            k++;
        }
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
