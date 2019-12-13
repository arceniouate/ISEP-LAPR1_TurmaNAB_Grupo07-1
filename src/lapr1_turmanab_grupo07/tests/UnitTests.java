/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr1_turmanab_grupo07.tests;

import java.util.Arrays;
import lapr1_turmanab_grupo07.Utils;
import static lapr1_turmanab_grupo07.Filters.*;
import static lapr1_turmanab_grupo07.Utils.*;

/**
 *
 * 
 */
public class UnitTests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int[][]testMatrix={{89,92,242,64},
                           {66,4,207,152},
                           {207,36,181,213},
                           {251,164,180,240}
        };
        
        int[][]expected_meanFilter={{65,124,128,131},
                                    {95,124,132,165},
                                    {139,144,153,197},
                                    {170,179,177,187}
        };
        int[][]expected_meanFilterWRONG={{65,124,128,131},
                                    {95,124,12,165},
                                    {139,144,153,197},
                                    {170,179,177,187}
        };
        
        int[][]expected_medianFilter={{89,92,92,152},
                                      {89,92,152,181},
                                      {164,180,180,207},
                                      {207,180,180,213}
        };
        int[][]expected_medianFilterWRONG={{89,92,92,152},
                                      {89,92,152,181},
                                      {164,80,180,207},
                                      {207,180,180,213}
        };
        
        int[][]expected_maxFilter={{92,242,242, 242},
                                   {207, 242, 242, 242},
                                   {251, 251, 240, 240},
                                   {251, 251, 240, 240}
        };
        
        int[][]expected_maxFilterWRONG={{92, 152, 181, 181},
                                        {180 ,180, 207, 207},
                                        {207, 27, 213, 23},
                                        {207, 207, 213, 213}
        };
        
        int[][]expected_minFilter={{0, 4, 4, 0},
                                   {4, 4, 4, 64},
                                   {4, 4, 4, 152},
                                   {0, 36, 36, 0}
        };
        
        int[][]expected_minFilterWRONG={{0, 4, 4, 0},
                                        {4, 34, 4, 64},
                                        {4, 4, 4, 152},
                                        {0, 36, 36, 0}
        };
        
        int[][]expected_rotation={{251, 207, 66, 89},
                                  {164, 36, 4, 92},
                                  {180, 181, 207, 242},
                                  {240, 213, 152, 64}
        };
        
        int[][]expected_rotationWRONG={{98, 108, 110, 12},
                                       {110, 115, 5, 13},
                                       {119, 230, 128, 43},
                                       {128, 135, 101, 32}
        };
        
        int[][]expected_order={{251, 164, 242, 240},
                               {207, 92, 207, 213},
                               {89, 36, 181, 152},
                               {66, 4, 180, 64}
        };
        
        int[][]expected_orderWRONG={{251, 164, 242, 240},
                                    {207, 92, 54, 213},
                                    {89, 36, 181, 152},
                                    {66, 4, 180, 64}
        };
        
        
        
        
//meanFilter Test
        System.out.println("meanFilter Test");
        //true test
        System.out.print("True Test: ");
        boolean result=test_meanFilter(testMatrix,expected_meanFilter);
        if(result){
            System.out.println("OK");
        }
        else{
            System.out.println("NOT OK");
        }
        //false test
        System.out.print("False Test: ");
        result=test_meanFilter(testMatrix,expected_meanFilterWRONG);
        if(!result){
            System.out.println("OK");
        }
        else{
            System.out.println("NOT OK");
        }
        System.out.println();
        
        
        
 
        
//medianFilter Test
        System.out.println("medianFilter Test");
        //true test
        System.out.print("True Test: ");
        result=test_medianFilter(testMatrix,expected_medianFilter);
        if(result){
            System.out.println("OK");
        }
        else{
            System.out.println("NOT OK");
        }
        //false test
        System.out.print("False Test: ");
        result=test_medianFilter(testMatrix,expected_medianFilterWRONG);
        if(!result){
            System.out.println("OK");
        }
        else{
            System.out.println("NOT OK");
        }
        System.out.println();
        
//maxFilter Test
        System.out.println("maxFilter Test");
        //true test
        System.out.print("True Test: ");
        result=test_maxFilter(testMatrix,expected_maxFilter);
        if(result){
            System.out.println("OK");
        }
        else{
            System.out.println("NOT OK");
        }
        //false test
        System.out.print("False Test: ");
        result=test_maxFilter(testMatrix,expected_maxFilterWRONG);
        if(!result){
            System.out.println("OK");
        }
        else{
            System.out.println("NOT OK");
        }
        System.out.println();
        
//minFilter Test
        System.out.println("minFilter Test");
        //true test
        System.out.print("True Test: ");
        result=test_minFilter(testMatrix,expected_minFilter);
        if(result){
            System.out.println("OK");
        }
        else{
            System.out.println("NOT OK");
        }
        //false test
        System.out.print("False Test: ");
        result=test_maxFilter(testMatrix,expected_minFilterWRONG);
        if(!result){
            System.out.println("OK");
        }
        else{
            System.out.println("NOT OK");
        }
        System.out.println();
        
//rotation Test
        System.out.println("rotation Test");
        //true test
        System.out.print("True Test: ");
        result=test_rotation(testMatrix,expected_rotation);
        if(result){
            System.out.println("OK");
        }
        else{
            System.out.println("NOT OK");
        }
        //false test
        System.out.print("False Test: ");
        result=test_rotation(testMatrix,expected_rotationWRONG);
        if(!result){
            System.out.println("OK");
        }
        else{
            System.out.println("NOT OK");
        }
        System.out.println();
        
//order selectionSort Test
        System.out.println("Selection Sort order Test");
        //true test
        System.out.print("True Test: ");
        result=test_orderS(testMatrix,expected_order);
        if(result){
            System.out.println("OK");
        }
        else{
            System.out.println("NOT OK");
        }
        //false test
        System.out.print("False Test: ");
        result=test_orderS(testMatrix,expected_orderWRONG);
        if(!result){
            System.out.println("OK");
        }
        else{
            System.out.println("NOT OK");
        }
        System.out.println();
        
//order bubbleSort Test
        System.out.println("Bubble Sort order Test");
        //true test
        System.out.print("True Test: ");
        result=test_orderB(testMatrix,expected_order);
        if(result){
            System.out.println("OK");
        }
        else{
            System.out.println("NOT OK");
        }
        //false test
        System.out.print("False Test: ");
        result=test_orderB(testMatrix,expected_orderWRONG);
        if(!result){
            System.out.println("OK");
        }
        else{
            System.out.println("NOT OK");
        }
        System.out.println();
        
        
//order insertionSort Test
        System.out.println("Insertion Sort order Test");
        //true test
        System.out.print("True Test: ");
        result=test_orderI(testMatrix,expected_order);
        if(result){
            System.out.println("OK");
        }
        else{
            System.out.println("NOT OK");
        }
        //false test
        System.out.print("False Test: ");
        result=test_orderI(testMatrix,expected_orderWRONG);
        if(!result){
            System.out.println("OK");
        }
        else{
            System.out.println("NOT OK");
        }
        System.out.println();
        

    }
    
    public static boolean test_meanFilter(int[][]testMatrix,int[][]expected_meanFilter){
        int[][]matriz=new int [testMatrix.length][testMatrix[0].length];
        copyMatrix(testMatrix,matriz);
        return Arrays.deepEquals(meanFilter(matriz),expected_meanFilter);
    };
    
    public static boolean test_medianFilter(int[][]testMatrix,int[][]expected_medianFilter){
        int[][]matriz=new int [testMatrix.length][testMatrix[0].length];
        copyMatrix(testMatrix,matriz);
        return Arrays.deepEquals(median(matriz),expected_medianFilter);
    };
    
    public static boolean test_maxFilter(int[][]testMatrix,int[][]expected_maxFilter){
        int[][]matriz=new int [testMatrix.length][testMatrix[0].length];
        copyMatrix(testMatrix,matriz);
        return Arrays.deepEquals(maxFilter(matriz),expected_maxFilter);
    };
    
    public static boolean test_minFilter(int[][]testMatrix,int[][]expected_minFilter){
        int[][]matriz=new int [testMatrix.length][testMatrix[0].length];
        copyMatrix(testMatrix,matriz);
        return Arrays.deepEquals(minFilter(matriz),expected_minFilter);
    };
    
    public static boolean test_rotation(int[][]testMatrix,int[][]expected_rotation){
        int[][]matriz=new int [testMatrix.length][testMatrix[0].length];
        copyMatrix(testMatrix,matriz);
        return Arrays.deepEquals(rotation(matriz),expected_rotation);
    };
    
    
    
     public static boolean test_orderS(int[][]testMatrix,int[][]expected_order){
        int[][]matriz=new int [testMatrix.length][testMatrix[0].length];
        copyMatrix(testMatrix,matriz);
        return Arrays.deepEquals(selectionSort(matriz),expected_order);
    };
    
     public static boolean test_orderB(int[][]testMatrix,int[][]expected_order){
        int[][]matriz=new int [testMatrix.length][testMatrix[0].length];
        copyMatrix(testMatrix,matriz);
        return Arrays.deepEquals(bubbleSort(matriz),expected_order);
    };
     
     public static boolean test_orderI(int[][]testMatrix,int[][]expected_order){
        int[][]matriz=new int [testMatrix.length][testMatrix[0].length];
        copyMatrix(testMatrix,matriz);
        return Arrays.deepEquals(insertionSort(matriz),expected_order);
    };
    
    
    
    public static int[][] copyMatrix(int[][]original,int[][]copy){
        for(int i=0;i<original.length;i++){
            for(int j=0;j<original[0].length;j++){
               copy[i][j]=original[i][j]; 
            }
        }
        return copy;
    }
}
