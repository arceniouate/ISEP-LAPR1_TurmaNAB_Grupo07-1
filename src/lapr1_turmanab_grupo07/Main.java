/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package lapr1_turmanab_grupo07;

import java.io.File;
import java.io.IOException;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import static jdk.nashorn.internal.objects.NativeMath.round;
import static lapr1_turmanab_grupo07.ImageCaract.*;
import static lapr1_turmanab_grupo07.Menu.*;
import static lapr1_turmanab_grupo07.Utils.*;
import static lapr1_turmanab_grupo07.Filters.*;
import static lapr1_turmanab_grupo07.ImageCompression.imageCompression;
import static lapr1_turmanab_grupo07.InputFile.*;

/**
 *
 * 
 */
public class Main {
    
    private int M;             // number of rows
    private int N;             // number of columns
    static int[][] data;
   static int[][] changedMatrix;
    static int[][] finalResultMatrix;
    static int [][] matrixChanged;
    
    public static String fileName;
 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        
        //Verificação da existência de args
        if(args.length==0){
            System.out.println("\n\nInicialização incorrecta!\n\nCorrer programa com a sintaxe:\n"
                    + "java -jar LAPR1_TurmaNAB_Grupo07 nomeDoTxt.txt");
            //System.in.read();
            return;
        }
        
        int mods=0;
        int op2 = 0;
        int op = 0;
        int op3 =0;
        String name = args[0];
        //name = name.substring(0,name.length()-4);
        
        //Imagem ou Directório?
  //TO DO - Verificar se imagens estão descomprimidas para as apresentar
  
        File folder = new File(name);
        if (folder.exists() && folder.isDirectory()) {
            clearConsole();
            System.out.println("\t\nO argumento é um Directório!\n\n");
            System.out.println("Pressione enter para Visualizar as seguintes imagens contidas no Directório:\n\n");
            
            //Criar lista com todas as imagens da pasta
            File [] listaFicheiros=folder.listFiles();
            for(int i=0;i<listaFicheiros.length;i++){
                System.out.println(listaFicheiros[i]);
            }
            System.in.read();
            
            //Copiar os nomes dos ficheiros para String (nesta string JÁ está o caminho pasta\nome.txt)
            String fileNames[]=new String[listaFicheiros.length];
            int index = 0;
            for (File f : listaFicheiros) {
                fileNames[index] =name+"\\"+ f.getName();
                index++;
            }
            
            //Imprime só os nomes sem a pasta
//            for(int var=0;var<fileNames.length;var++){
//            System.out.println(fileNames[var]);
//            }
//            System.in.read();
            
            //Testar imagens do vector para compressão
            int tempNumber=0;
            String[] temps=new String[fileNames.length];//String para guardar os nomes dos temporários
            for(int i=0;i<listaFicheiros.length;i++){
                Scanner teste = new Scanner(listaFicheiros[i]);
                
                
                
                if(checkCompression(teste)){
                    
                    //teste.close();
                    String nameTemp=fileNames[i];
                    nameTemp=nameTemp.substring(0,nameTemp.length()-4)+"_uncompressed";
                    String [] uncompressTemp = new String [1];
                    uncompressTemp[0]=listaFicheiros[i].getName();
                    //Descomprimir e guardar como temporário
                    //Descomprime
                    //int matrix [][] = uncompress(args);
                    int matrix [][] = uncompress(uncompressTemp);
                    //Guarda descomprimido
                   // int[][]matrix={{0,255},{255,0}};
                    saveToFile(nameTemp, matrix);
                    
                    
                    //saveTemp(teste);
                    //Altera o nome na listaFicheiros[i]
                    fileNames[i]=nameTemp+".txt";

                    //guarda nome do temporário no string[] temps
                    
                    temps[tempNumber]=nameTemp+".txt";
                    tempNumber++;
                    
//                    String fileNames[]=new String[listaFicheiros.length];
//                    for(File f:listaFicheiros){
//                        fileNames[i]=f.getName();
//                    }
                    
                    
//                    List <File> temp=null;
//                    temp.addAll(Arrays.asList(listaFicheiros));
//                    saveTemp(teste);
//                    listaFicheiros[i]=listaFicheiros[i].substring(0,listaFicheiros[i].length()-4);
//                    temp[i]=temp[i].substring(0,temp[i].length()-4);

                    
                    
                }
            }
            
            //abrir o gnuplot com todas as imagens do vector
            for(int i=0;i<fileNames.length;i++){
                //if(listaFicheiros[i].isFile()){
                    Runtime rt = Runtime.getRuntime();
                    Process prcs = rt.exec("cmd /c script.bat "+fileNames[i]);

                    prcs.waitFor();//espera que o processo do cmd do gnuplot termine para criar um novo com a próxima imagem
                //}
                //prcs.destroy();
            }
            //Apagar ficheiros TEMP
//            for(int i=0;i<temps.length;i++){
////                Runtime rt = Runtime.getRuntime();
////                Process prcs = rt.exec("cmd /c delete.bat "+temps[i]);
////                prcs.waitFor();
//                Files.delete(Paths.get(temps[i]));
//                
//                //NÃO É PRECISO APAGAR OS FICHEIROS
//                //CRIAR UMA PASTA, MOVER OS COMPRIMIDOS ORIGINAIS PARA LÁ
//                //CRIAR OS FICHEIROS DESCOMPRIMIDOS COM O MESMO NOME NA PASTA ORIGINAL
//            }
            clearConsole();
            System.out.println("\n\n\tVisualização terminada!\n\nPressione enter para sair.");
            System.in.read();
            System.in.read();
            return;
        }
//FIM DA OPÇÃO DIRECTÓRIO---------------------------------------------------------------------------------------

        //Retira ".txt" ao nome do ficheiro em memória
        name = name.substring(0,name.length()-4);
        
        //Verificar compressão da imagem
        Scanner imgTest = new Scanner(new File(args[0]));
        if(checkCompression(imgTest)){
            System.out.println("Imagem comprimida!\n\nPressione enter para descomprimir para memória.");
            System.in.read();
            //Descomprimir imagem para memória
            //data=uncompress(imgTest);
            data=uncompress(args);
            name=name+"_uncompressed";
            mods=1;
            
            
            
        } else{
            data = readFile(args[0]);
        }
        
        imgTest.close();
        
        //Verificações Iniciais
        
        clearConsole();
        System.out.println();
                    
//                    if (!Utils.isLengthValid(data)) {
//                        System.out.println("Tamanho da Matriz superior a 200!");
//                        System.out.println();
//                        System.out.println("Execute o programa com um ficheiro correcto!");
//                        return;
//                    }
                    
                    if (!Utils.isMatrixSquare(data)) {
                        System.out.println("A Matriz não é Quadrada!");
                        System.out.println();
                        System.out.println("Execute o programa com um ficheiro correcto!");
                        return;
                    }
                    
                    if (!Utils.isIndexValid(data)) {
                        System.out.println("Pelo menos um dos valores da Matriz é inferior a 0 ou superior a 255!");
                        System.out.println();
                        System.out.println("Execute o programa com um ficheiro correcto!");
                        return;
                    }
                    else{
                        System.out.println("Matriz Válida!");
                        System.out.println();
                        System.out.println("(Pressione Enter para continuar)");
                        System.in.read();
                        clearConsole();
                        
                    }
        
        changedMatrix = data;
        matrixChanged = data;
        
        do {
            op = mainMenu();
            switch (op) {
                case 1:
                    //Imprimir matriz actual
                    showMatrix(changedMatrix);
                    System.out.println();
                    break;
                case 2:
                    //Guardar Imagem Actual para Ficheiro
                    if(mods!=0){
                        confirmationImageSaving(true,matrixChanged);
                        saveToFile(name, matrixChanged);
                        clearConsole();
                        System.out.println();
                        System.out.print("Ficheiro Guardado!");
                        System.out.println();
                        System.out.println("(Pressione Enter para continuar)");
                        System.in.read();
                    }
                    else{
                        clearConsole();
                        System.out.println();
                        System.out.print("Ficheiro ainda não modificado!");
                        System.out.println();
                        System.out.println("(Pressione Enter para continuar)");
                        System.in.read();
                    }
                    clearConsole();
                    
                    break;
                case 3:
                    //Caracterização total da imagem
                    out.format("%s%n", "3.Caracterização Total");
                    System.out.println();
                    allImageCaraterization(changedMatrix);
                    System.out.println();
                    break;
                case 4:
                    //Soma
                    out.format("%s%n", "4.Soma");
                    System.out.println();
                    System.out.println("A Soma da matriz é: " + sumMatrix(changedMatrix));
                    System.out.println();
                    break;
                case 5:
                    //Média
                    out.format("%s%n", "5.Média");
                    System.out.println();
                    System.out.println("A Média da matriz é: " + meanMatrix(changedMatrix));
                    System.out.println();
                    break;
                case 6:
                    //Valores e Vectores Próprios
                    out.format("%s%n", "6.Valores e vetores proprios");
                    System.out.println();
                    eigenDecomposition(changedMatrix);
                    System.out.println();
                    break;
                case 7:
                    //Estatística dos Trapézios
                    out.format("%s%n","7.Estatística dos Trapézios");
                    System.out.println();
                    double  [] column=TrapezeRule(changedMatrix);
                   System.out.println( "A estatísticas dos Trapézios é:");
                   for(int i=0;i<column.length;i++){
                       System.out.println("\t\t\t\t"+column[i]);
                   };
                    
                    break;
                case 8:
                    //Filtro da Média
                    out.format("%s%n", "8. Aplicar Filtro da Média");
                    meanFilter(changedMatrix);
                    mods = 1;
                    System.out.println();
                    showImg(changedMatrix);
                    name = name + "_Média";
                    System.out.println();
                    break;
                case 9:
                    //Filtro da Mediana
                    out.format("%s%n", "9. Aplicar Filtro da Mediana");
                    median(changedMatrix);
                    mods = 1;
                    System.out.println();
                    showImg(changedMatrix);
                    name = name + "_Mediana";
                    System.out.println();
                    break;
                case 10:
                    //Filtro de Màximo
                    out.format("%s%n", "10. Aplicar Filtro de Máximo");
                    maxFilter(changedMatrix);
                    mods = 1;
                    System.out.println();
                    showImg(changedMatrix);
                    name = name + "_Máximo";
                    System.out.println();
                    break;
                case 11:
                    //Filtro de Mìnimo
                    out.format("%s%n", "11. Aplicar Filtro do Mínimo");
                    minFilter(changedMatrix);
                    mods = 1;
                    System.out.println();
                    showImg(changedMatrix);
                    name = name + "_Mínimo";
                    System.out.println();
                    break;
                case 12:
                    //Filtro de Convolução
                    int[][]mask=new int[3][3];
                    
                    String valores[];
                    
                    //Pedir matriz máscara ao utilizador
                    clearConsole();
                    System.out.println("Insira a matriz máscara 3x3 separando os valores por vírgulas.\nExemplo:"
                            + "\n\t1 2 3\n\t4 5 6\n\t7 8 9");
                    System.out.println("\nSintaxe: 1,2,3,4,5,6,7,8,9\n");
                    Scanner scanner=new Scanner(System.in);
                    String maskString=scanner.nextLine();
                    valores=maskString.split(",");
                    int count=0;
                    for(int i=0;i<3;i++){
                        for(int j=0;j<3;j++){
                            mask[i][j]=Integer.parseInt(valores[count]);
                            count++;
                        }
                    }
                    //Aplicar o Filtro de Convolução
                    convolutionFilter(changedMatrix,mask);
                    System.out.println();
                    showImg(changedMatrix);
                    //System.in.read();
                    name = name + "_Conv";
                    mods=1;
                    System.out.println();
                    
                    break;
                case 13:
                    //Filtro de Variância
                    out.format("%s%n", "13. Aplicar Filtro de Variância");
                    changedMatrix=varianceFilter(changedMatrix);
                    mods=1;
                    System.out.println();
                    showImg(changedMatrix);
                    name = name + "_Variância";
                    System.out.println();
                    break;
                case 14:
                    //Rotação +90
                    out.format("%s%n", "14. Aplicar Rotação +90 graus");
                    rotation(changedMatrix);
                    mods = 1;
                    System.out.println();
                    showImg(changedMatrix);
                    name = name + "_Rotação+90";
                    System.out.println();
                    break;
                case 15:
                    //Rotação -90
                    out.format("%s%n", "15. Aplicar Rotação -90 graus");
                    rotationC(changedMatrix);
                    mods = 1;
                    System.out.println();
                    showImg(changedMatrix);
                    name = name + "_Rotação-90";
                    System.out.println();
                    break;
                case 16:
                    //Ordenar colunas
                    out.format("%s%n", "16. Ordenar Colunas");
                    bubbleSort(changedMatrix);
                    mods = 1;
                    System.out.println();
                    showImg(changedMatrix);
                    name = name + "_Ordenar";
                    System.out.println();
                    break;
                case 17:
                    //Compressão de Imagem
                        //Compressão de Imagem
                           //Compressão de Imagem
                        //Compressão de Imagem
                    boolean imageModified = false;
                    double result = 0;
                     
                                      
                                        
                    do {
                        System.out.println("Insira o número de valores próprios para comprimir a imagem  \n ");
                       
                        Scanner sc = new Scanner(System.in);
                                    
                        int i = sc.nextInt();
                           
                                                               
                        result = imageCompression(changedMatrix, i,name);
                        System.out.println();
                    } while (result == 0);
                      
//                    if (result > 0) {
//                        //desenvolver tratamento da imagem
//                       // System.out.println("(Nao há erro)");
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
//                        if (ascii == 'c'){
//                           break;
//                        }
//                        else if (ascii == 's' && imageModified == true) {
//                            System.out.println("Entrou no Salvar Imagem \n\n ");
////                            if (ascii == 'N') {
////                                System.out.println("Entrou em nao salvar imagem e continuar \n\n ");
////                            }
//                        
//                        
//                        }
//                        else if(ascii == 's' && imageModified == false){
//                            System.out.println("só passa aqui quando ainda nao tiver salvo a imagem\n\n ");
//                       
//                            
//                            
//                            imageModified = true;
//                            
//                        }
//                        
//                        
//                        else if(ascii == 'n' && imageModified == false){
//                              System.out.println("Entrou em não salvar imagem e continuar \n\n ");
//                         
//                              
//                              imageModified = false;
//                        
//                         break; 
//                        }
//                      
//                    
//                        }
//                    
//                     name = name + "_Compressão";
                    
                    
                    break;
                    
                    
                 
                
                case 18:
                    //Visualização de Imagens
                    
                    String filePathString=name+".txt";
                    File f = new File(filePathString);
                    if(f.exists() && !f.isDirectory()) { 
                        
                        Runtime.getRuntime().exec("cmd /c script.bat "+name+".txt");
                    }
                    else{
                        System.out.println("Ficheiro inexistente!\n\nA imagem que está a tentar visualizar "
                                + "ainda não foi guardada!\nProceda à gravação através da opção 2 e volte a tentar"
                                + " a visualização.\n\n");
                    }

                    break;
                
                case 99:
                    clearConsole();
                    long start,end,elapsed_s,elapsed_b,elapsed_i;
                    System.out.println("\n\n\t___PERFORMANCE TESTS (hidden menu - opção 99)___\n\n\n");
                    System.out.print("Selection Sort - time: ");
                    start=System.nanoTime();
                    selectionSort(changedMatrix);
                    end=System.nanoTime();
                    elapsed_s=end-start;
                    System.out.println(elapsed_s+"  nanoseconds");
                    
                    System.out.print("Bubble Sort - time: ");
                    start=System.nanoTime();
                    bubbleSort(changedMatrix);
                    end=System.nanoTime();
                    elapsed_b=end-start;
                    System.out.println(elapsed_b+"  nanoseconds");
                    
                    System.out.print("Insertion Sort - time: ");
                    start=System.nanoTime();
                    insertionSort(changedMatrix);
                    end=System.nanoTime();
                    elapsed_i=end-start;
                    System.out.println(elapsed_i+"  nanoseconds");
                    
                    System.out.println();
                    System.out.println("(Pressione Enter para continuar)");
                    System.in.read();
                    clearConsole();
                    
                    break;
                case 0:
                    int op4 = showMessage();
                    if (op4 == 1 && mods!=0){
                        confirmationImageSaving(true,matrixChanged);
                        saveToFile(name, matrixChanged);
                        }
                    else {
                        confirmationImageSaving(false,matrixChanged);
                        }
                    
                    
                    break;
                default:
                    out.format("%s%n", "Opção Inválida!\n\nTente novamente");
                    System.out.println();
                    System.out.println("(Pressione Enter para voltar ao Menu)");
                    System.in.read();
                    clearConsole();
                    break;
            }
            
        } while (op != 0);
    }
}
