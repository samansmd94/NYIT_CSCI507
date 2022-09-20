package week2;

import java.util.Random;

class ArrayPrinter{

    static int rows=5, cols=3;

    /*
    @param myArray
     */
    public static void GenerateArray(int[][] myArray){
        Random random= new Random();

        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                myArray[row][col] = random.nextInt(0,100);
            }
        }
    }

    public static void PrintArrayByRow(int[][] myArray){

        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                System.out.print(myArray[row][col]+ " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){

        int[][] MyArray = new int[rows][cols];

        GenerateArray(MyArray);

        PrintArrayByRow(MyArray);
    }
}