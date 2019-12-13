/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package lapr1_turmanab_grupo07;

import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.abs;
import java.util.Arrays;
import java.util.Scanner;
import static lapr1_turmanab_grupo07.ImageCompression.absolutErrorSubtraction;
import static lapr1_turmanab_grupo07.InputFile.saveCompressed;
import org.la4j.Matrix;
import org.la4j.decomposition.SingularValueDecompositor;
import org.la4j.matrix.dense.Basic2DMatrix;

/**
 *
 * @author mjdg1
 */
public class ImageCompression {

    public static double imageCompression(int matrix2[][], int numberSingValue,String name) throws IOException {

        double matrix3[][] = new double[matrix2.length][matrix2[0].length];
        int sizeMatrix2 = matrix2.length * matrix2.length;
       // System.out.println("\n Tamanho da imagem original:  " + sizeMatrix2);
        boolean originalimageboolean = false;
        double value = 0;

        double[][] EAM = new double[matrix2.length][matrix2[0].length];

        for (int i = 0; i < matrix2.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                matrix3[i][j] = (double) matrix2[i][j];
            }

        }

        //showMatrixCompress(matrix3);

        Matrix a = new Basic2DMatrix(matrix3);
        // SVD decomposition
        SingularValueDecompositor eigenD = new SingularValueDecompositor(a);
        Matrix[] mattD = eigenD.decompose();

        // converte objeto Matrix (três matrizes)  para array Java
        double matU[][] = mattD[0].toDenseMatrix().toArray(); //vetores provenientes do Objecto    Matrix a

        double matD[][] = mattD[1].toDenseMatrix().toArray();//vetores provenientes do Objecto    Matrix a

        double matV[][] = mattD[2].toDenseMatrix().toArray();//vetores provenientes do Objecto    Matrix a

        //System.out.println("\n Valores Próprios Aqui:");
        int pos=0;
        int ipos=0;
            for(ipos=0;ipos<matD.length;ipos++){
                if(roundOff(matD[ipos][ipos])>0) pos=ipos;
            }
        if (numberSingValue>pos+1) {
            //System.out.println("\n\tDimensão da matrix excedida");
            int dimensao = matD.length;
            
            System.out.println("\tO valor escolhido deve ser inferior ou igual a " + (pos+1));

        } else {

            value = roundOff(matD[numberSingValue - 1][numberSingValue - 1]); // arredondar os valores da diagonal da matriz D

           // System.out.print("\n \n valor próprio= " + value + " \n");

            double matrixU[][] = new double[matU.length][numberSingValue];

            double vectorD[] = new double[numberSingValue];

            double matrixV[][] = new double[matU.length][numberSingValue];

            if(value == 0){
                 System.out.println("\n Valor próprio é nulo");
               
            }
                        
            /**
             * **
             * Iterações em função do número de valores próprios indicados pelo
             * utilizador
             */
            //value of numberSingValue validated and iteration for all singular values
        
            if (value > 0) {
      
                for (int i = 0; i < numberSingValue; i++) {
                    vectorD[i] = matD[i][i];
                }

                //showArray(vectorD);
                int totalSizeOfCompressedImage = 0;
                
                double[][] totalMatrixSum = new double[matU.length][matV[0].length];

                //inicialização da matriz que vai guardar os conjuntos de vetores para a imagem comprimida
                //Cálculos das Matrizes
               
                double[][][] matrizToBeSavedAsImageA = new double[numberSingValue][matU.length][matV[0].length];

                 
                for (int numValuesIterator = 0; numValuesIterator < numberSingValue; numValuesIterator++) {

                    int vectorDconstant1 = 0;
                    double matrixU2Copied[][] = new double[matU.length][1];
                    double matrixV2Copied[][] = new double[1][matU.length];
                    double vectorDConstant = vectorD[numValuesIterator];

                    double arrayU2Copied[] = new double[matU.length];

                    //***********************
                    double[][] Utemp = new double[matU.length][1];
                    double[][] Vtemp = new double[1][matV[0].length];
                    
                    //PREENCHE VECTOR U E V
                    for (int line = 0 ; line < matU.length; line++) {
                        Utemp[line][0] = matU[line][numValuesIterator];
                    }
                    Vtemp[0] = matV[numValuesIterator].clone();
                    
                    
                    double[][] matrizTemp = matrixResult(matD[numValuesIterator][numValuesIterator], Utemp, Vtemp);

                    double arrayV2Copied[] = new double[matU.length];
                    double arrayD2Copied[] = new double[vectorD.length];
                    double matrixR[][] = new double[matU.length][matU.length];
                    
                    {
                    //vetores U e Vetores V
                    arrayU2Copied = copyArrayUasEqual(numValuesIterator + 1, matU);
                    arrayV2Copied = copyVectorV_asTranspose(numValuesIterator + 1, matV);

                    arrayD2Copied = copyArrayUasEqual(numValuesIterator + 1, matD);

                    matrixU2Copied = copyMatrixUasEqual(numValuesIterator + 1, matU);

                    matrixV2Copied = copyMatrixVasTranspose(numValuesIterator + 1, matV);
                     vectorDconstant1++;
                           
                    }
//------------------------------------------------------------------------------------------------------------------------------------
//                    // PREENCHER VECTOR DE MATRIZES
//                    for(int d=0;d<matrizToBeSavedAsImageA.length;d++){
//                        for(int u=0;u<matrizToBeSavedAsImageA[d].length;u++){
//                            for(int v=0;v<matrizToBeSavedAsImageA[d][u].length;v++){
//                                matrizToBeSavedAsImageA[d]=vectorDconstant;
//                                matrizToBeSavedAsImageA[d][u]=matrixU2Copied[u].clone();
//                                matrizToBeSavedAsImageA[d][u][v]=matrixV2Copied[v].clone();
//                            }
//                        }
//                    }
//                    



        //Guardar e APPEND para ficheiro
        
    //    Files.write(Paths.get("myfile.txt"), "the text".getBytes(), StandardOpenOption.APPEND);
        
        
        FileWriter fw = new FileWriter(name+"compressed"+numberSingValue+".txt",true); //true - modo append
        
        //String D
        //for(int d=0;d<vectorD.length;d++){
            fw.write(vectorD[numValuesIterator] + ("\n"));
        //}
              
        //String U
        for(int u=0;u<matrixU2Copied.length;u++){
            fw.write(arrayU2Copied[u] + ((u==arrayU2Copied.length-1)?"\n":","));
        }
        
        //String V
        for(int v=0;v<matrixV2Copied.length;v++){
            fw.write(arrayV2Copied[v] + ((v==arrayV2Copied.length-1)?"\n\n":","));
        }
        
        
    
    fw.close();
                    
                    matrixR = matrixResult(vectorDConstant, matrixU2Copied, matrixV2Copied);
                    totalMatrixSum = matrixSumResult(totalMatrixSum, matrixR);
                    //Tamanho da imagem comprimida
                     totalSizeOfCompressedImage = totalSizeOfCompressedImage + (2 * vectorDconstant1 + 2 * sizeOf(arrayU2Copied) + 2 * sizeOf(arrayV2Copied));
                      // System.out.println("\n Tamanho da matriz comprimida\n\n" + totalSizeOfCompressedImage);
   
                       EAM = (absolutErrorSubtraction(matrix3, totalMatrixSum));
//                   System.out.println("\n Tamanho da matriz comprimida\n\n" + totalSizeOfCompressedImage);
//                    EAM = (absolutErrorSubtraction(matrix3, matrixR));
//                   
//                     System.out.println("\n Matriz EAM\n\n");
//                   // showMatrix(EAM); // matriz do erro absoluto
//
//                  
//                    float ErroRelativo = Erro(EAM);
//                    System.out.println("\n Erro relativo:\t" + ErroRelativo);
//
//                    //
//                     System.out.println("\n Reconstrução da imagem\n"); 
//                     showMatrixCompress(totalMatrixSum);
                    
                    /**
                     * ***************
                     * Calcular a matriz reusltado dos array decompostos
                     */
                    for (int z = 0; z < matrizTemp.length; z++) {
                        for (int j = 0; j < matrizTemp[0].length; j++) {
                           // System.out.print(matrizTemp[z][j] + " ");
                        }
                      // System.out.println();
                    }
                    //continua cálculo
                    for (int j = 0; j < Utemp.length; j++) {
                        for (int k = 0; k < Vtemp[0].length; k++) {
                            matrizToBeSavedAsImageA[numValuesIterator][j][k] = matrizTemp[j][k];//matrixResult(matD[i][i],matU[j][i],matV[i][k]);
                        }
                    }
                    //Imprime matrizes do array de matrizes Resultado
//                    System.out.println("\nMatriz RESULTADO matrizToBeSavedAsImageA array de matrizes:");
//                    for (int j = 0; j < Utemp.length; j++) {
//                        for (int k = 0; k < Vtemp[0].length; k++) {
//                           //System.out.print(matrizToBeSavedAsImageA[numValuesIterator][j][k] + " ");
//                        }
//                        System.out.println();
//              
//                    }

                  //System.out.println("\n Imprimiu dentro\n\n");
              
                  
                  
                } //fim do iteração number of sing values
             
                     System.out.println("\n Reconstrução da imagem (Apresentada em valores e com o gnuplot):"); 
                     showMatrixCompress(totalMatrixSum);
                     String nameprint=name+"compressed"+numberSingValue+"R.txt";

                FileWriter reconstruct = new FileWriter(nameprint);

                for (int i = 0; i < totalMatrixSum.length; i++) {
                    for (int j = 0; j < totalMatrixSum[0].length; j++) {
                        reconstruct.write((totalMatrixSum[i][j]) + ((j == totalMatrixSum[0].length - 1) ? "\n" : ","));
                    }
                }
                reconstruct.close();
       
                     
                     Runtime.getRuntime().exec("cmd /c scriptSingle.bat "+nameprint);
                
                System.out.println("\n Tamanho da matriz original\n" + sizeMatrix2);    
                System.out.println("\n Tamanho da matriz comprimida\n" + totalSizeOfCompressedImage);
                 
                
                 float ErroMedio = Erro(EAM);                    
                 System.out.println("\n Erro Absoluto Médio:\t" + ErroMedio);
                 
                 
                 System.out.println("\n\tImagem comprimida salva com o nome:\n"+name+"compressed"+numberSingValue+".txt");
                 System.out.println("\n\tImagem reconstruída salva com o nome:\n"+nameprint);
                 

                
               // double matrixUU[][] = new double[matU.length][1];
              //  double matrixVV[][] = new double[matV.length][1];
                originalimageboolean = true;
                
             
            }
 
            
            boolean imageModified=false;
            
            
            //SALVAR IMAGEM COMPRIMIDA
            
//            if (value > 0) {//Compressão bem sucedida
//                        
//                        
//                        
//                        System.out.println("\n\n Deseja salvar a imagem?\n S - Sim  N - Não C - cancelar");
//                        
//                        Scanner scanner3 = new Scanner(System.in);
//                        char c1 = scanner3.next().charAt(0);
//                        int ascii = (int) c1;
//                        
//                        
//                            
//                        if (ascii == 'c'||ascii=='C'){
//                           return 0;
//                        }
//                        else if ((ascii == 's'||ascii=='S') && imageModified == true) {
//                            System.out.println("Entrou no Salvar Imagem \n\n ");
////                            if (ascii == 'N') {
////                                System.out.println("Entrou em nao salvar imagem e continuar \n\n ");
////                            }
//                        
//                        
//                        }
//                        else if((ascii == 's'||ascii=='S') && imageModified == false){
//                            //salvar imagem
//                            //saveCompressed(name,matrizToBeSavedAsImageA);
//                            System.out.println("só passa aqui quando ainda nao tiver salvo a imagem\n\n ");
//                       
//                            
//                            
//                            imageModified = true;
//                            
//                        }
//                        
//                        
//                        else if((ascii == 'n' ||ascii=='N') && imageModified == false){
//                              System.out.println("Entrou em não salvar imagem e continuar \n\n ");
//                         
//                              
//                              imageModified = false;
//                        
//                        // break; 
//                        }
//                      
//                    
//                        }
                    
                     //name = name + "_Compressão";
                    
       
              return value;
            
        }
      

           // System.out.println("Insira um número de valores singulares \n\n ");
//            Scanner scanner2 = new Scanner(System.in);
//            char c = scanner2.next().charAt(0);
//            int ascii = (int) c;
//
//            if (ascii == 's' && originalimageboolean == false) {
//                System.out.println("Ainda nao alterou imagem \n\n ");
//
//            } else {
//                originalimageboolean = true;
//            }

        




        return value;
        
        
    }

    //Eigen VALUES
    public static double[] eigenValuess(double matD[][]) {
        System.out.println("Valores Próprios:");
        double eigenVal[] = new double[matD.length];
        for (int i = 0; i < matD.length; i++) {
            eigenVal[i] = matD[i][i];
        }

        return eigenVal;
    }

    public static float[] showEigenValues(double[] arrayEigenValues) {
        for (int i = 0; i < arrayEigenValues.length; i++) {
            System.out.println(arrayEigenValues[i]);
        }
        return null;
    }

    public static void showMatrixCompress(double[][] m) {
        int i, j;
       
        System.out.println("\n Matrix: ");
        for (i = 0; i < m.length; i++) {
            for (j = 0; j < m[i].length; j++) {

                System.out.print(m[i][j] + " ");
            }

            System.out.println();
        }

    }

    public static double[][] copyMatrixUasEqual(int numberSingValue, double matU[][]) {

        double matrixU_Copied[][] = new double[matU.length][matU[0].length];
        double arrayU[] = new double[matU.length];

        if (numberSingValue > 0) {
            // System.out.println("AAAAAAAAAAA " + numberSingValue);
            //System.out.println("BBBBBBBBBBB "+matU.length);
            for (int i = 0; i < matU.length; i++) {
                //System.out.println(matU[i][numberSingValue - 1]);
                //  System.out.println("ArrayyyyyyB\n "+arrayU[i]);
                arrayU[i] = matU[i][numberSingValue - 1];

                for (int j = 0; j < numberSingValue; j++) {

                    matrixU_Copied[i][1] = matU[i][numberSingValue - 1];
                }
            }

        }
        return matrixU_Copied;
    }

    public static double[] copyArrayUasEqual(int numberSingValue, double matU[][]) {
        
        double matrixU_Copied[][] = new double[matU.length][matU[0].length];
        double arrayU[] = new double[matU.length];
        if (numberSingValue > 0) {
            for (int i = 0; i < matU.length; i++) {
                arrayU[i] = matU[i][numberSingValue - 1];
                for (int j = 0; j < numberSingValue; j++) {
                    
                    matrixU_Copied[i][1] = matU[i][numberSingValue - 1];
                }
            }
            
        }
        return arrayU;
    }

    public static double[][] copyMatrixVasTranspose(int numberSingValue, double matV[][]) {
        double matrixV_Copied[][] = new double[matV.length][matV[0].length];
        double arrayV[] = new double[matV.length];
        if (numberSingValue > 0) {
            for (int i = 0; i < matV.length; i++) {
                for (int j = 0; j < matV.length; j++) {
                    matrixV_Copied[1][j] = matV[j][numberSingValue - 1];
                    arrayV[j] = matV[j][numberSingValue - 1];
                    
                }
            }
            
        }
        return matrixV_Copied;
    }

    public static double[] copyVectorV_asTranspose(int numberSingValue, double matV[][]) {
        double matrixV_Copied[][] = new double[matV.length][matV[0].length];
        double arrayV[] = new double[matV.length];
        if (numberSingValue > 0) {
            for (int i = 0; i < matV.length; i++) {
                for (int j = 0; j < matV.length; j++) {
                    matrixV_Copied[1][j] = matV[j][numberSingValue - 1];
                    arrayV[j] = matV[j][numberSingValue - 1];
                }
            }
        }
        return arrayV;
    }

    public static void showMatrix(double[][] matrix) {
        for (int j = 0; j < matrix.length; j++) {
            for (int k = 0; k < matrix[0].length; k++) {
                System.out.print(matrix[j][k] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void showArray(double[] array) {
        for (int j = 0; j < array.length; j++) {

            System.out.print(array[j] + " ");
        }
        System.out.print("\n");
    }

    public static int sizeOf(double array[]) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            count++;
        }
        return count;
    }

    private static double roundOff(double d) {
        
        double roundOff = Math.round(d * 1000.0000) / 1000.0000;
        return roundOff;
    }
    
    public static double[][] matrixResult(double matD, double matU[][], double matV[][]) {
        
        double Total = 0;
        int N1 = matU.length;
        int M1 = matU[0].length;
        double constant = matD;
        int M2 = matV[0].length;
        
        double resultMultiplyMatrix[][] = new double[N1][M2];
        
        for (int i = 0; i <= N1 - 1; i++) {
            
            for (int k = 0; k <= M2 - 1; k++) {
                Total = 0;
                for (int j = 0; j <= M1 - 1; j++) {
                    Total = Total + ((matU[i][j] * matV[j][k]) * constant);
                }
                
                resultMultiplyMatrix[i][k] = Total;
            }
        }
        
        return resultMultiplyMatrix;
        
    }

    public static double[][] matrixSumResult(double matTeste1[][], double matTeste2[][]) {
        int M = matTeste1.length;
        int N = matTeste1[0].length;

        double matrixSum[][] = new double[N][M];
        for (int i = 0; i <= N - 1; i++) {
            for (int j = 0; j <= M - 1; j++) {
                matrixSum[i][j] = matTeste1[i][j] + matTeste2[i][j];
            }
        }

        return matrixSum;
    }

    public static double[][] absolutErrorSubtraction(double[][] matrixImage, double[][] matrixCompressed) {
        int M = matrixImage.length;
        int N = matrixCompressed[0].length;
        double[][] subtract = new double[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                subtract[i][j] = (matrixImage[i][j] - matrixCompressed[i][j]);
            }

        }
        return subtract;
    }

    public static float Erro(double[][] matrixSubtracted) {

        float sum = 0;

        for (int row = 0; row < matrixSubtracted.length; row++) {
            for (int col = 0; col < matrixSubtracted[row].length; col++) {
                sum = (float) (sum + abs(matrixSubtracted[row][col]));
            }
        }

        return ((sum) / ((matrixSubtracted.length * matrixSubtracted[0].length)));

    }

//    public static double[][][] matrixReadToBeSaved ( int vectorDconstant,double[] vectorD , double[] arrayU, double[] arrayV){
//        //continua cálculo
//          int iIteration = vectorD.length ;
//
//          int  iteraçaok = arrayV.length;
//        double matrizTemp[][] =new double [arrayU.length][arrayU.length];
//         System.out.println(vectorDconstant );
//         System.out.println(arrayV.length);
//
//           showArray(vectorD);
//
//
//                    System.out.println("\n ArrayU2 copiado:" + arrayU);
//                    showArray(arrayU);
//
//
//
//                    System.out.print("\nArrayV2Copied\n" + arrayV);
//                   showArray(arrayV);
//
//
//
//         double [][][]  matrixReadToBeSaved  = new double[vectorD.length][arrayU.length][arrayU.length];
//
//      for (int i= 0; i <  vectorD.length ; i++)  {
//        for(int j=0;j <arrayU.length; j++){
//                for(int k=0; k < arrayV.length; k++){
//              matrixReadToBeSaved[i][j][k]=  matrizTemp[arrayU[j]][arrayV[k]];//matrixResult(matD[i][i],matU[j][i],matV[i][k]);
//                }
//            }
//    //   }
//            //Imprime matrizes do array de matrizes Resultado
//            System.out.println("\nMatriz RESULTADO array de matrizes:");
//           //for (int i = 0; i< vectorDconstant.length ; i++ ){
//                 for(int j=0;j<arrayU.length;j++){
//                for(int k=0;k<arrayV.length;k++){
//                    System.out.print(matrixReadToBeSaved[1][arrayU[j]][arrayV[k]] + " ");
//                }
//                System.out.println();
//            }
//          // }
//
//            return matrixReadToBeSaved;
//    }
}
