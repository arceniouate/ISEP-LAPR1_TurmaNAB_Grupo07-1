/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr1_turmanab_grupo07;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import static lapr1_turmanab_grupo07.Utils.clearConsole;
import static lapr1_turmanab_grupo07.Utils.showImg;

/**
 *
 *
 */
public class InputFile {

     /**
     * método para ler o ficheiro
     * @return
     * @throws IOException
     * @throws IndexOutOfBoundsException 
     */
    public static int[][] readFile(String args) throws IOException, IndexOutOfBoundsException {

        int nLinhas = 0;
        String linha;
        String dados[];
        int[][] matrix = null;
        int first=0;
        
        Scanner flnCol = new Scanner(new File(args));
        int count=0;
        
            while(flnCol.hasNext()){
                linha = flnCol.nextLine();
                linha = linha.replace(" ", "");
                if (linha.length() > 0) {
                    char start = linha.charAt(0);
                    if (Character.isDigit(start)) {
                        count++;
                    }
                }
            }
            flnCol.close();

        Scanner fln = new Scanner(new File(args));
        try {
            while (fln.hasNext()) {
                linha = fln.nextLine();

                if (linha.length() > 0) {
                    char start = linha.charAt(0);
                    if (Character.isDigit(start)) {
                        linha = linha.replace(" ", "");

                        dados = linha.trim().split(",");

                        if (first == 0) {
                            matrix = new int[count][dados.length];
                            first = 1;
                            nLinhas = 0;
                        }
                        for (int i = 0; i < dados.length; i++) {
                            matrix[nLinhas][i] = Integer.parseInt(dados[i]);
                        }
                    }
                    nLinhas++;
                }
            }
        } catch (final Exception IndexOutOfBoundsException) {
            clearConsole();
            System.out.println("\nFicheiro contém Erros!\n\nTente novamente!");
            System.out.println();
            System.out.println("(Pressione Enter para continuar)");
            System.in.read();
            System.exit(0);
        }
        fln.close();

        return matrix;
    }
    
    
    public static void saveToFile(String filename, int[][] matrix) throws IOException {

        BufferedWriter file = new BufferedWriter(new FileWriter( filename + ".txt"));

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                file.write(matrix[i][j] + ((j == matrix[i].length - 1) ? "" : ","));
            }
            file.newLine();
        }
        file.flush();
    }
    
    public static void saveCompressed(String filename, double[][][] matrix) throws IOException {

        BufferedWriter file = new BufferedWriter(new FileWriter( filename + ".txt"));

        for (int i = 0; i < matrix.length; i++) {//D
            file.write(matrix[i] + "");
            file.newLine();
            for (int j = 0; j < matrix[i].length; j++) {//U
                file.write(matrix[i][j] + ((j == matrix[i].length - 1) ? "" : ","));
                file.newLine();
                for(int k=0;k<matrix[i][j].length;k++){//V
                    file.write(matrix[i][j][k] + ((k == matrix[i][j].length - 1) ? "" : ","));
                    file.newLine();
                }
            }
        }
        file.flush();
    }
}
