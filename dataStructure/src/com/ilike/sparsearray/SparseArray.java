package com.ilike.sparsearray;

/**
 * 稀疏数组的实现
 */
public class SparseArray {

    public static void main(String[] args) {
        //创建一个原始的二维数组11*11
        //0：表示没有棋子，1表示黑色棋子，2表示蓝色棋子。
        int chessArray1[][] = new int[11][11];
        chessArray1[1][2] = 1;
        chessArray1[2][3] = 2;
        chessArray1[4][5] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组");
        for (int row[] : chessArray1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //将二维数组转换成稀疏数组
        //1.先遍历二维数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray1[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum=" + sum);
        //2.创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        //遍历二维数组，将非0的值存放到sparseArray中
        int count = 0;//count用于记录是第几个非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArray1[i][j];
                }
            }
        }
        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组如下：");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        System.out.println();
        // 将稀疏数组=====》恢复成原始的二维数组
        /**
         * 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的chessArr2=new int [11][11]
         * 2.读取稀疏数组后几行的数据，并赋给原始的二维数组即可。
         */
        // 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int chessArray2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2.读取稀疏数组后几行的数据(从第二行开始)，并赋给原始的二维数组即可
        for (int i = 1; i <sparseArr.length; i++) {
            chessArray2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }
        //输出恢复后的二维数组
        System.out.println("恢复后的二维数组");
        for (int row[] : chessArray2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

    }



}
