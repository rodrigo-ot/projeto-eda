import java.io.*;
import java.io.File;
import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        String pastaEntrada = "../input/";
        String pastaSaida = "../output/countingSort/";
        File diretorio = new File(pastaEntrada);
        String csvFile = "../output/countingSort/grafico.csv";
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
                            countingSortCSV(pastaEntrada+arquivo.getName(), pastaSaida+arquivo.getName());
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

    private static void countingSortCSV(String inputPath, String outputPath) {
        int[] values = readCSV(inputPath);
        countingSort(values);
        writeCSV(outputPath, values);
    }

    private static int[] readCSV(String filePath) {
        int[] values = new int[0];

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                values = Arrays.copyOf(values, values.length + 1);
                values[values.length - 1] = Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return values;
    }

    private static void writeCSV(String filePath, int[] values) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int value : values) {
                writer.write(Integer.toString(value));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void countingSort(int[] values) {
        if (values.length <= 1) {
            return;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int value : values) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }

        int[] countArray = new int[max - min + 1];

        for (int value : values) {
            countArray[value - min]++;
        }

        int k = 0;
        for (int i = 0; i < countArray.length; i++) {
            while (countArray[i] > 0) {
                values[k] = i + min;
                k++;
                countArray[i]--;
            }
        }
    }
}
