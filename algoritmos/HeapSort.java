import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
public class HeapSort {

    public static void main(String[] args) {
            String pastaEntrada = "../input/";
            String pastaSaida = "../output/heapSort/";
            File diretorio = new File(pastaEntrada);
            String csvFile = "../output/heapSort/grafico.csv";
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
                                heapSort(array);
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

    private static void heapSort(int[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
    }

    private static void heapify(int[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            heapify(array, n, largest);
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
