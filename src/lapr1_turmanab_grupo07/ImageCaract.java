/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package lapr1_turmanab_grupo07;

import static lapr1_turmanab_grupo07.Utils.showMatrix;
import org.la4j.Matrix;
import org.la4j.decomposition.EigenDecompositor;
import org.la4j.matrix.dense.Basic2DMatrix;

/**
 *
 * 
 */
public class ImageCaract {

    /**
     * sumLine - retorna a soma da linha "line" da matriz "matrix"
     *
     * @param matrix
     * @param line
     * @return
     */
    public static int sumLine(int matrix[][], int line) {
        int sum = 0;
        for (int j = 0; j < matrix[0].length; j++) {
            sum += matrix[line][j];
        }
        //System.out.println(sum);
        return sum;
    }

    /**
     * sumCol - retorna a soma da coluna "col" da matriz "matrix"
     *
     * @param matrix
     * @param col
     * @return
     */
    public static int sumCol(int matrix[][], int col) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][col];
        }
        //System.out.println(sum);
        return sum;
    }

    /**
     * sumMatrix - Retorna a soma de todos os elementos da matriz em "sum"
     *
     * @param matrix
     * @return
     */
    public static int sumMatrix(int matrix[][]) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                sum += matrix[i][j];
            }
        }
        //System.out.println(sum);
        return sum;
    }

    /**
     * meanLine - Retorna a média (mean) da linha "line"
     *
     * @param matrix
     * @param line
     * @return
     */
    public static double meanLine(int matrix[][], int line) {
        double mean = sumLine(matrix, line) / matrix[0].length;
        return mean;
    }

    /**
     * meanCom - Retorna a média (mean) da coluna "col"
     *
     * @param matrix
     * @param col
     * @return
     */
    public static double meanCol(int matrix[][], int col) {
        double mean = sumCol(matrix, col) / matrix.length;
        return mean;
    }

    /**
     * meanMatrix - Retorna a média (mean) da matrix "matrix"
     *
     * @param matrix
     * @return
     */
    public static double meanMatrix(int matrix[][]) {
        double mean = sumMatrix(matrix) / (matrix.length * matrix[0].length);
        return mean;
    }

    /**
     * eigenDecomposition - Calcula e imprime os Valores Próprios (Eigen Values)
     * e os Vectores Próprios (Eigen Vectors)
     *
     * @param matrix - matriz argumento
     * @return
     */
    public static void eigenDecomposition(int matrix[][]) {

        double matrix2[][] = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix2[i][j] = matrix[i][j];
            }
        }
        Matrix a = new Basic2DMatrix(matrix2);
        EigenDecompositor eigenD = new EigenDecompositor(a);
        Matrix[] matrixD = eigenD.decompose();

        //Conversão do objecto matriz em 2 matrizes
        double matA[][] = matrixD[0].toDenseMatrix().toArray(); //Matriz dos Vectores Próprios
        double matB[][] = matrixD[1].toDenseMatrix().toArray(); //Matriz dos Valores Próprios

        //Eigen VALUES
        System.out.println("Valores Próprios:");
        double eigenVal[] = new double[matB.length];
        for (int i = 0; i < matB.length; i++) {
            eigenVal[i] = matB[i][i];
        }
        for (int i = 0; i < eigenVal.length; i++) {
            System.out.println(eigenVal[i]);
        }
        
        System.out.println();

        //Eigen VECTORS
        System.out.println("Vectores Próprios:");
        for (int l = 0; l < matA.length; l++) {
            System.out.print("(");
            for (int c = 0; c < matA.length; c++) {
                System.out.print(matA[c][l]);
                if (c < matA.length - 1) {
                    System.out.print(" , ");
                }
            }
            System.out.println(")");
        }
    }

    public static void allImageCaraterization(int[][] matrix) {

        System.out.println("\n\t***Caraterização total da Imagem***");
        showMatrix(matrix);


        System.out.println("\n\nSoma das linhas");
        for (int i = 0; i < matrix[0].length; i++) {
            sumLine(matrix, i);
            int sumLineResult = sumLine(matrix, i);
            System.out.println("A soma da linha " + (i + 1) + "  é  " + sumLineResult);
        }
        System.out.println("\n\nMédia das linhas");
        for (int i = 0; i < matrix[0].length; i++) {
            meanLine(matrix, i);
            int meanLineResult = (int) meanLine(matrix, i);
            System.out.println("A média da linha  " + (i + 1) + "  é  " + meanLineResult);
        }

        System.out.println("\nSoma das colunas");
        for (int j = 0; j < matrix[0].length; j++) {
            sumCol(matrix, j);
            int result = sumCol(matrix, j);
            System.out.println("A soma da coluna " + (j + 1) + "  é  " + result);
        }
        System.out.println("\n\nMédia colunas");
        for (int j = 0; j < matrix[0].length; j++) {
            meanCol(matrix, j);
            int meanColResult = (int) meanCol(matrix, j);
            System.out.println("A média da coluna  " + (j + 1) + "  é  " + meanColResult);
        }
    }
    
    
    /***
     * Este método aplica a regra do trapézio a uma matriz de inteiros
     * @param matrix A matriz de inteiros sobre a qual se pretende aplicar a regra dos trapézios
     * @return retorna uma coluna com o resultado da aplicação da regra dos trapézios
     */
    public static double [] TrapezeRule(int  [][] matrix){
        double [] returnColumn = new double [matrix.length];
        //precorrer a matriz 
        for(int i=0; i<matrix.length;i++){
            //fazer a extraçaoa da linha  
            int[] linha = matrix[i];
            returnColumn[i]=Trapeze(linha);
        }
        
        return returnColumn;
    }
    
    
    
    private static double   Trapeze(int   [] line){
        double  returnValue=0;
        for(int i=0;i<line.length-1;i++){
            returnValue=returnValue+(line[i] + line[i+1])/2.0;
        }  
        //devolve o valor a matriz que lhe chamou
        return returnValue;
    }
    
    
    
    
    
}
