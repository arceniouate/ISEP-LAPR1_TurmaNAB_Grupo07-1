/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package lapr1_turmanab_grupo07;

import static java.lang.System.in;
import java.util.Formatter;
import java.util.Scanner;
import static lapr1_turmanab_grupo07.Utils.saveImg;

/**
 *
 * 
 */
public class Menu {

    private static Scanner in = new Scanner(System.in);
    private static Formatter out = new Formatter(System.out);

    public static int mainMenu() {
        int op;
        String menu[] = {"1. \tApresentar Imagem em memória  ", 
                         "2. \tGuardar Imagem Atual para Ficheiro ", 
                         "3. \tCaraterização total da imagem ",
                         "4. \tSoma ",
                         "5. \tMédia ",
                         "6. \tValores e Vectores Próprios ",
                         "7. \tEstatística dos Trapézios ", 
                         "8. \tAplicar Filtro da Média ", 
                         "9. \tAplicar Filtro da Mediana ", 
                         "10.\tAplicar Filtro de Máximo ", 
                         "11.\tAplicar Filtro do Mínimo ", 
                         "12.\tAplicar Filtro de Convolução ", 
                         "13.\tAplicar Filtro de Variância ", 
                         "14.\tAplicar Rotação +90 graus",
                         "15.\tAplicar Rotação -90 graus",
                         "16.\tOrdenar Colunas (ordem decrescente)\n",
                         "17.\tCompressão de Imagem",
                         "18.\tVisualização de Imagens\n",
                        };
        System.out.println("\t*************************************************\n\t________________***  MENU  ***________________\n\t*************************************************\n");
        for (int i = 0; i < menu.length; i++) {
            System.out.println("\t" + menu[i]);
        }
        System.out.println("\n\t***  Escolha uma opção(0 para terminar). ***\n\n");
        op = in.nextInt();
        in.nextLine();
        return op;
    }

    public static int showSavedImageMenu() {
        int op1;
        String menu[] = { 
                         "1.\t Caraterização total da imagem ", 
                         "2.\t Soma ", 
                         "3.\t Média", 
                         "4.\t Valores e Vetores Próprios\n", 
                         "5.\t Ir Para o Menu de Filtragem e Transformação", 
                         "6.\t Regressar ao Menu anterior\n"};
        System.out.println("\t*************************************************\n\t______________***   Menu Tratamento de Imagem***____________\n\t*************************************************\n");
        for (int i = 0; i < menu.length; i++) {
            System.out.println("\t" + menu[i]);
        }
        //System.out.println("\n\t***Escolha uma opção(0 para terminar)***\n\n");
        op1 = in.nextInt();
        in.nextLine();
        return op1;
    }

    public static int filtTransfMenu() {//2 -Abrir Nova Imagem
        int op3;
        String menu[] = {"  ", "1.\t Aplicar Filtro da Média", 
                               "2.\t Aplicar Filtro da Mediana", 
                               "3.\t Aplicar Filtro de Máximo", 
                               "4.\t Aplicar Filtro do Mínimo", 
                               "5.\t Aplicar Rotação",
                               "6.\t Ordenar Colunas (ordem decrescente)\n",
                               "7.\t Regressar ao menu anterior"};
        System.out.println("\t*************************************************\n\t____________***   Menu Tratamento de Imagem***_________________\n\t*************************************************\n");
        for (int i = 0; i < menu.length; i++) {
            System.out.println("\t" + menu[i]);
        }
        //System.out.println("\n\t***  Escolha uma opção(0 para terminar).  ***");
        op3 = in.nextInt();
        in.nextLine();
        return op3;
    }  
}

