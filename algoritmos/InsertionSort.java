import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
public class InsertionSort {


        public static void main(String[] args) {
            String pastaEntrada = "C:/Users/Computador/Desktop/PROJETO EDA/input/";
            String pastaSaida = "C:/Users/Computador/Desktop/PROJETO EDA/output/insertionSort/";
            File diretorio = new File(pastaEntrada);
            String csvFile = "C:/Users/Computador/Desktop/PROJETO EDA/output/insertionSort/grafico.csv";
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
                                insertionSort(array);
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

    private static void insertionSort(int[] array) {
        int n = array.length;

        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
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