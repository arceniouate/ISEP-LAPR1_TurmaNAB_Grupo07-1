/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package lapr1_turmanab_grupo07;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.in;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static lapr1_turmanab_grupo07.ImageCompression.*;
/**
 *
 * 
 */
public class Utils {

    private static Scanner in = new Scanner(System.in);
    private static Formatter out = new Formatter(System.out);

    
    /**
     * Esta função permite ordenar um array em ordem crescente utilizando o
     * selctionSort, aprendido nas aulas de Algoritmo e Progrmação
     *
     * @param array
     * @return
     */
    public static int[] SortArray(int[] array) {
        int temp = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
        return array;
    }

    /**
     * showImg - Apresenta a matriz/imagem actual na consola. (em memória, não a
     * imagem original).
     *
     * @param matrix
     */
    public static void showImg(int matrix[][]) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * openImg - Procedimento para abertura de uma nova imagem/matriz.
     *
     * @param matrix
     */
    public static void openImg(int matrix[][]) {

    }

    /**
     * saveImg - Guarda em ficheiro *.txt a matriz/imagem em memória.
     *
     * @param matrix
     * @param fileName
     */
    public static void saveImg(int matrix[][], String fileName[]) {

    }

    /**
     * saveImg - Guarda
     *
     * @param matrix
     * @param fileName
     */
    public static int[][] saveImg(int matrix[][]) {
        return null;
    }

    /**
     * método para validar o tamanho
     *
     * @param matrix
     * @return
     */
    public static boolean isLengthValid(int[][] matrix) {
        int maxLength = 200;

        if (matrix.length > maxLength) {
            return false;
        }
        return true;
    }

    /**
     * método para validar se a matriz e quadrada
     *
     * @param matrix
     * @return
     */
    public static boolean isMatrixSquare(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        if (rows != cols) {
            return false;
        }

        return true;
    }

    /**
     * método para validar se o valor do pixel e maior que 0 e menor que 255
     *
     * @param matrix
     * @return
     */
    public static boolean isIndexValid(int[][] matrix) {
        int min = 0;
        int max = 255;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] < min || matrix[i][j] > max) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void showMatrix(int[][] m) {
        int i, j;
        System.out.println("\n Matrix");
        for (i = 0; i < m.length; i++) {
            for (j = 0; j < m[i].length; j++) {
                System.out.printf("%d ", m[i][j]);

            }

            System.out.println();
        }

    }

    /**
     * matrixWk - Retorna uma matriz Wk obtida a partir da matrix argumento
     *
     * @param fullMatrix - matrix original
     * @param i - coordenada central i
     * @param j - coordenada central j
     * @param k - tamanho da matriz máscara
     * @return - matriz Wk com tamanho k x k obtida a partir da posição i,j da
     * fullMatrix
     */
    public static int[][] matrixWk(int fullMatrix[][], int i, int j, int k) {
        int wkMatrix[][] = new int[k][k];
        int limit = (k - 1) / 2;
        int tam = fullMatrix.length;//matriz quadrada, fullMatrix[0].length é igual.

        //lógica 1
        for (int wl = 0, l = i - limit; l <= i + limit; l++, wl++) {
            for (int wc = 0, c = j - limit; c <= j + limit; c++, wc++) {
                if (l < 0 && c < 0) {
                    wkMatrix[wl][wc] = 0;
                }
                if (l > (tam - 1) && c > (tam - 1)) {
                    wkMatrix[wl][wc] = 0;
                }
                if (l < 0 && c > (tam - 1)) {
                    wkMatrix[wl][wc] = 0;
                }
                if (l < (tam - 1) && c < 0) {
                    wkMatrix[wl][wc] = 0;
                }

                if (l < 0 && c >= 0 && c < (tam)) {
                    wkMatrix[wl][wc] = fullMatrix[0][c];
                }
                if (l > (tam - 1) && c >= 0 && c < (tam)) {
                    wkMatrix[wl][wc] = fullMatrix[tam - 1][c];
                }
                if (c < 0 && l >= 0 && l < (tam)) {
                    wkMatrix[wl][wc] = fullMatrix[l][0];
                }
                if (c > (tam - 1) && l >= 0 && l < (tam)) {
                    wkMatrix[wl][wc] = fullMatrix[l][tam - 1];
                }

                if (l >= 0 && c >= 0 && l < (tam) && c < (tam)) {
                    wkMatrix[wl][wc] = fullMatrix[l][c];
                }
            }
        }
        return wkMatrix;
    }

    /**
     * findMax - Encontra e retorna o valor máximo de uma matriz
     *
     * @param matrix
     * @return
     */
    public static int findMax(int matrix[][]) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }
        return max;
    }

    /**
     * findMin - Encontra e retorna o valor mínimo de uma matriz
     *
     * @param matrix
     * @return
     */
    public static int findMin(int matrix[][]) {
        int min = 255;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                }
            }
        }
        return min;
    }

    public static int showMessage() {
        int op1;
        System.out.println("\n\t*** Deseja Guardar a matriz em ficheiro txt antes de sair? ***\n\n");
        System.out.println("\n\t*** Sim - 1 ou Não - 2? ***\n\n");
        op1 = in.nextInt();
        return op1;

    }

    public static int[][] confirmationImageSaving(boolean confirm, int[][] matrixChanged) {
        if (confirm == true) {
            int[][] matrixSaved = saveImg(matrixChanged);
            return matrixSaved;
        } else {
            return matrixChanged;
        }
    }

    public static int[][] imageSaving(int[][] matrix) {
        int[][] matrixSaved = null;

        return matrixSaved;

    }

    public static void clearConsole()
{
    try
    {
        final String os = System.getProperty("os.name");
        if (os.contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
    }
    catch (final Exception e)
    {
        
    }
}
    
    public static int[][] selectionSort(int matrix[][]) {

        //Método de Ordenação Selection Sort - Decrescente
        int n, x, pos;
        for (int col = 0; col < matrix[0].length; col++) {
            for (int i = 0; i < matrix.length - 1; i++) {
                int index = i;
                for (int j = i + 1; j < matrix.length; j++) {
                    if (matrix[j][col] > matrix[index][col]) {
                        index = j;
                    }
                }
                int maior = matrix[index][col];
                matrix[index][col] = matrix[i][col];
                matrix[i][col] = maior;
            }
        }
        return matrix;
    }
    
    public static int[][] bubbleSort(int matrix[][]) {

        //Método de Ordenação Bubble Sort - Decrescente
        for (int col = 0; col < matrix[0].length; col++) {
            int n = matrix.length - 1, temp2;
            boolean trocas = false;
            do {
                trocas = false;
                for (int i = 0; i < n; i++) {
                    if (matrix[i][col] < matrix[i + 1][col]) {
                        temp2 = matrix[i + 1][col];
                        matrix[i + 1][col] = matrix[i][col];
                        matrix[i][col] = temp2;
                        trocas = true;
                    }
                }
                n--;
            } while (trocas);
        }
        return matrix;
    }
    
    public static int[][] insertionSort(int matrix[][]) {

        //Método de Ordenação Insertion Sort - Decrescente
        for (int col = 0; col < matrix[0].length; col++) {
            for (int i = 0; i < matrix.length; i++) {
                int temp = matrix[i][col];
                int j = i;
                while (j > 0 && matrix[j - 1][col] < temp) {
                    matrix[j][col] = matrix[j - 1][col];
                    j--;
                }
                matrix[j][col] = temp;
            }
        }
        return matrix;
    }
     
    
    
    /**
     * Converte matriz inteira para float
     * @param matrix
     * @return 
     */
    public static float[][] intMatrixToFloat(int[][]matrix){
        float[][]matrixFloat=new float[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                matrixFloat[i][j]=(float)matrix[i][j];
            }
        }
        return matrixFloat;
    }
    
    /**
     * Converte matriz float para inteira
     * @param matrix
     * @return 
     */
    public static int[][] floatMatrixToInt(float[][]matrix){
        int[][]matrixInt=new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                matrixInt[i][j]=(int)matrix[i][j];
            }
        }
        return matrixInt;
    }
    
    /**
     * Verifica a compressão da imagem e retorna true ou false
     * @param ficheiro 
     */
    public static boolean checkCompression(Scanner ficheiro) {
        boolean compression = false;
        String linha;
        while (ficheiro.hasNext()) {
            linha = ficheiro.nextLine();
            if (linha.contains(".")) {
                compression = true;//return true;
            } else {
                compression = false;//return false;
            }
        }
        return compression;
    }
    
    public static int [][] uncompress(String [] args) throws FileNotFoundException, IOException{
        String linha;
        int trio = 0;
        int d = 0, u = 0, v = 0;

        Scanner ficheiro = new Scanner(new File(args[0]));
        //Dimensionar matrizes UV
        int dim = 0;
        while (ficheiro.hasNext()) {
            linha = ficheiro.nextLine();
            linha = linha.replace(" ", "");
            String dados[] = linha.trim().split(",");
            if (dados.length > dim) {
                dim = dados.length;
            }
        }
        ficheiro.close();
        
        //Contar o número de valores singulares
        int sval=0;
        Scanner ficheiro1 = new Scanner(new File(args[0]));
        while (ficheiro1.hasNext()){
            linha = ficheiro1.nextLine();
            if(linha.length()>0){
            //char start = linha.charAt(0);
            if(linha.length()>0&&Character.isDigit(linha.charAt(0))&&!linha.contains(",")){
                sval++;
            }
            }
        }
        ficheiro1.close();
        
        double[][] matD = new double[sval][sval];
        double[][] matU = new double[dim][sval];
        double[][] matV = new double[sval][dim];
        
        //Ler o ficheiro e preencher as matrizes DUV
        Scanner ficheiro3 = new Scanner(new File(args[0]));
        while (ficheiro3.hasNext()) {
            linha = ficheiro3.nextLine();

            if (linha.length() > 0) {
                //System.out.println("Linha maior do que 0");
                char start = linha.charAt(0);
                if (Character.isDigit(start)) {
                    //System.out.println("Linha começa por Dígito");
                    linha = linha.replace(" ", "");
                    String dados[] = linha.trim().split(",");

                    //Matriz D
                    if (!linha.contains(",")) {
                        //System.out.println("Linha não contém ,");
                        //matrix d
                        matD[trio][trio] = Double.parseDouble(dados[0]);
                        d = 1;
                        //System.out.println(matD[trio][trio]);
                        linha=ficheiro3.nextLine();
                        linha = linha.replace(" ", "");
                        dados = linha.trim().split(",");
                        //break;
                    }
                    //Matriz U
                    //if (d == 1 && v == 0) {
                        //System.out.println("Entrou no U");
                        for (int i = 0; i < dados.length; i++) {
                            matU[i][trio] = Double.parseDouble(dados[i]);
                            u = 1;
                        }
                        linha=ficheiro3.nextLine();
                        linha = linha.replace(" ", "");
                        dados = linha.trim().split(",");
                        //break;
                    //}
                    //Matriz V
                    //if (d == 1 && u == 1) {
                        //System.out.println("Entrou no V");
                        for (int i = 0; i < dados.length; i++) {
                            matV[trio][i] = Double.parseDouble(dados[i]);
                            v = 1;
                        }
                        trio++;
                        d = 0;
                        u = 0;
                        v = 0;
                        //break;
                    //}
                }
            }
        }
        ficheiro3.close();
        
        //Imprimir as matrizes DUV
/*       
        //imprime matD
        System.out.println("Matriz D:");
        for(int i=0;i<matD.length;i++){
            for(int j=0;j<matD[0].length;j++){
                System.out.print(matD[i][j]+" ");
            }
            System.out.println();
        }
        //imprime matU
        System.out.println("Matriz U:");
        for(int i=0;i<matU.length;i++){
            for(int j=0;j<matU[0].length;j++){
                System.out.print(matU[i][j]+" ");
            }
            System.out.println();
        }
        //imprime matV
        System.out.println("Matriz V:");
        for(int i=0;i<matV.length;i++){
            for(int j=0;j<matV[0].length;j++){
                System.out.print(matV[i][j]+" ");
            }
            System.out.println();
        }
*/
        
        //Cálculos das Matrizes
        double [][][] matrizes=new double[sval][matU.length][matV[0].length];
        
        for(int i=0;i<sval;i++){
            //Para cada sval calcular a matriz DUV
            double[][]Utemp=new double[matU.length][1];
            double[][]Vtemp=new double[1][matV[0].length];
            for(int line=0;line<matU.length;line++){
                Utemp[line][0]=matU[line][i];
            }
            Vtemp[0]=matV[i].clone();
            
            double [][] matrizTemp=matrixResult(matD[i][i],Utemp,Vtemp);
/*            
            //imprime matResult parcial
        System.out.println("Matriz result parcial:");
        for(int z=0;z<matrizTemp.length;z++){
            for(int j=0;j<matrizTemp[0].length;j++){
                System.out.print(matrizTemp[z][j]+" ");
            }
            System.out.println();
        }
*/
            //continua cálculo
            for(int j=0;j<Utemp.length;j++){
                for(int k=0;k<Vtemp[0].length;k++){
                matrizes[i][j][k]=matrizTemp[j][k];
                }
            }
/*
            //Imprime matrizes do array de matrizes Resultado
            System.out.println("\nMatriz RESULTADO array de matrizes:");
            for(int j=0;j<Utemp.length;j++){
                for(int k=0;k<Vtemp[0].length;k++){
                    System.out.print(matrizes[i][j][k] + " ");
                }
                System.out.println();
            }
*/
        }
        
        //SOMAR as matrizes parciais
        int [][] matrizFINAL=new int [matU.length][matV[0].length];
        double [][] matrizFINALdouble=new double [matU.length][matV[0].length];
        double[][] matrixSum1=new double [matU.length][matV[0].length];
        double[][] matrixSum2=new double [matU.length][matV[0].length];
        if(sval>1){
            for(int i=0;i<sval-1;i+=2){
                matrixSum1=matrizes[i].clone();
                matrixSum2=matrizes[i+1].clone();
                
                matrizFINALdouble=matrixSumResult(matrixSum1, matrixSum2);
            }
            for(int i=0;i<matrizFINAL.length;i++){
                for(int j=0;j<matrizFINAL[0].length;j++){
                    matrizFINAL[i][j]=(int)(matrizFINALdouble[i][j]+0.5);
                }
            }
        } else{
            matrizFINALdouble=matrizes[0].clone();
//            //IMPRIMIR Double
//            System.out.println("\nMatriz FINAL double:");
//        for(int z=0;z<matrizFINALdouble.length;z++){
//            for(int j=0;j<matrizFINALdouble[0].length;j++){
//                System.out.print(matrizFINALdouble[z][j]+" ");
//            }
//            System.out.println();
//        }
        
//CAST para int e arredondamento
            for(int i=0;i<matrizFINAL.length;i++){
                for(int j=0;j<matrizFINAL[0].length;j++){
                    matrizFINAL[i][j]=(int)(matrizFINALdouble[i][j]+0.5);
                }
            }
        }
  /*      
        //IMPRIMIR MATRIZ FINAL INT
        System.out.println("\nMatriz FINAL int:");
        for(int z=0;z<matrizFINAL.length;z++){
            for(int j=0;j<matrizFINAL[0].length;j++){
                System.out.print(matrizFINAL[z][j]+"\t");
            }
            System.out.println();
        }
   */
        //clearConsole();
        System.out.println("\n\n\tImagem descomprimida para memória!\n");
        //System.in.read();
     
        return matrizFINAL;
    }
 
}
    

