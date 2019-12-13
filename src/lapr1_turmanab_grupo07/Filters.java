package lapr1_turmanab_grupo07;

import static lapr1_turmanab_grupo07.ImageCaract.meanMatrix;
import static lapr1_turmanab_grupo07.ImageCaract.sumMatrix;
import static lapr1_turmanab_grupo07.Utils.*;

/**
 * Filters - Junção dos Filtros a usar
 *
 */
public class Filters {

    /**
     * meanFilter - Aplica um filtro de média com uma máscara 3x3 à matriz
     * argumento
     *
     * @param matrix
     * @return - matrix modificada
     */
    public static int[][] meanFilter(int matrix[][]) {
        int matrixMod[][] = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrixMod[i][j] = (sumMatrix(matrixWk(matrix, i, j, 3)) / 9);
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = matrixMod[i].clone();
        }
        return matrix;
    }

    /**
     *
     * Esta função permite calcular a mediana de uma matriz utilizando array de
     * inteiros
     *
     * @param matriz esta é matriz para a qual se quer calcular a mediana
     * @return retorna o valor inteiro que representa a mediana
     */
    public static int[][] median(int[][] matrix) {
        int matrixMod[][] = new int[matrix.length][matrix[0].length];
        for (int l = 0; l < matrix.length; l++) {
            for (int c = 0; c < matrix[0].length; c++) {
                int valor = 0;
                int[][] matrixW3 = matrixWk(matrix, l, c, 3);
                // Criar um array para colocar tods elementos da matriz 
                int[] array = new int[matrixW3.length * matrixW3[0].length];
                //   percorrer todos os elementos da matriz e colocar num array
                int pos = 0;
                for (int i = 0; i < matrixW3.length; i++) {
                    for (int j = 0; j < matrixW3[0].length; j++) {
                        //A indice do array com a soma de i e j
                        array[pos++] = matrixW3[i][j];
                    }
                }
                //ordenar o array por ordem crescente       
                array = Utils.SortArray(array);

                //encontrar o valor do meio do array
                int middle = array.length / 2;
                if (array.length % 2 == 1) {
                    valor = array[middle];
                } else {
                    valor = (array[middle] + array[middle - 1]) / 2;
                }
                matrixMod[l][c] = valor;//(sumMatrix(matrixWk(matrix,l,c,3))/9);
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = matrixMod[i].clone();
        }
        return matrix;
    }

    /**
     * maxFilter - Filtro de Máximo Aplica um filtro de Máximo à matriz
     * argumento
     *
     * @param matrix
     * @return
     */
    public static int[][] maxFilter(int matrix[][]) {
        int matrixMod[][] = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrixMod[i][j] = findMax(matrixWk(matrix, i, j, 3));
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = matrixMod[i].clone();
        }
        return matrix;
    }

    /**
     * minFilter - Filtro de Mínimo Aplica um filtro de Mínimo à matriz
     * argumento
     *
     * @param matrix
     * @return
     */
    public static int[][] minFilter(int matrix[][]) {
        int matrixMod[][] = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrixMod[i][j] = findMin(matrixWk(matrix, i, j, 3));
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = matrixMod[i].clone();
        }
        return matrix;
    }

    /**
     * rotation - Efectua a rotação da matriz 90º no sentido dos ponteiros do
     * relógio e retorna a mesma.
     *
     * @param matrix
     * @return
     */
    public static int[][] rotation(int matrix[][]) {
        int[][] rotated = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                rotated[j][matrix.length - 1 - i] = matrix[i][j];
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = rotated[i].clone();
        }
        return matrix;
    }

    /**
     * rotationC - rotation Counterclockwise Efectua a rotação da matriz 90º no
     * sentido contário aos ponteiros do relógio e retorna a mesma.
     *
     * @param matrix
     * @return
     */
    public static int[][] rotationC(int matrix[][]) {
        int[][] rotated = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                //rotated[j][matrix.length-1-i]=matrix[i][j];
                rotated[matrix[0].length - 1 - j][i] = matrix[i][j];
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = rotated[i].clone();
        }
        return matrix;
    }

    /**
     * convolutionFilter - Filtro de Convolução
     *
     * @param matrix - matriz original
     * @param mask - matriz máscara
     * @return matriz modificada
     */
    public static int[][] convolutionFilter(int matrix[][], int mask[][]) {
        //Normalização da máscara inserida pelo utilizador
        float[][] maskN = maskNormalization(mask);

        float matrixMod[][] = new float[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int[][] wk = matrixWk(matrix, i, j, 3);
                for (int k = 0; k < wk.length; k++) {
                    for (int l = 0; l < wk[0].length; l++) {
                        if((wk[k][l] * maskN[k][l])<0){
                            matrixMod[i][j]=0;
                        }
                        if((wk[k][l] * maskN[k][l])>255){
                            matrixMod[i][j]=255;
                        }
                        else{
                            matrixMod[i][j] += wk[k][l] * maskN[k][l];
                        }
                    }
                }
            }
        }
        int[][] newmatrix = floatMatrixToInt(matrixMod);
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = newmatrix[i].clone();
        }

        return matrix;
    }

    /**
     * Normalização da matriz máscara
     *
     * @param mask
     * @return
     */
    public static float[][] maskNormalization(int mask[][]) {
        int soma = sumMatrix(mask);
        float matrixFloat[][] = new float[mask.length][mask[0].length];
        matrixFloat = intMatrixToFloat(mask);

        for (int i = 0; i < mask.length; i++) {
            for (int j = 0; j < mask[0].length; j++) {
                matrixFloat[i][j] = matrixFloat[i][j] / soma;
            }
        }
        return matrixFloat;
    }
    
    /**
     * Filtro de variância
     * 
     * @param matrix
     * @return 
     */
    public static int[][] varianceFilter(int matrix[][]) {
        int cont = 0, median, point, pointSqr;//, sum = 0;
        float variance;
        int array[] = new int[(int) Math.pow(matrix.length, 2)];
        float mat[][] = new float[matrix.length][matrix[0].length];
        int matFINAL[][]=new int [matrix.length][matrix[0].length];
   
        
        //Percorrer a matriz completa e seleccionar Wk
        double matrixMod[][] = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int[][]matrixWkTemp=matrixWk(matrix, i, j, 3);
                float[][] maskN = maskNormalization(matrixWkTemp);//Normalização
                double mean=sumMatrix(matrixWkTemp)/9.0;//meanMatrix(matrixWkTemp);
                double sum=0;
                for(int k=0;k<matrixWkTemp.length;k++){
                    for(int l=0;l<matrixWkTemp[0].length;l++){
                        sum+=(matrixWkTemp[k][l]-mean)*(matrixWkTemp[k][l]-mean);
                    }
                }
                matrixMod[i][j] = (sum/9.0>255) ? 255 : (sum/9.0);
            }
        }
        
        //cast e arredondamento
        for(int i=0;i<matFINAL.length;i++){
                for(int j=0;j<matFINAL[0].length;j++){
                    matFINAL[i][j]=(int)(matrixMod[i][j]+0.5);
                }
            }
        
        return matFINAL;
    }
    
    
   
}
