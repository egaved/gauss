package ru.ac.uniyar;


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import org.apache.commons.lang3.math.Fraction;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Fraction[][] mtr = matrixInitialization("src/main/resources/input.txt");
        if (mtr == null) {
            return;
        }
        int n = mtr.length;
        int m = mtr[0].length;
        if (n < m - 1) {
            int[] basic = new int[n];
            System.out.println("Choose " + n + " basic columns. Enter two number from 0 to " + (m - 2) + ":");
            for (int i = 0; i < n; i++) {
                basic[i] = sc.nextInt();
            }
        }
        solveSystem(mtr);
    }

    public static void solveSystem(Fraction[][] matrix) {
        System.out.println("MATRIX TRANSFORMATION");
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            Fraction divisor = matrix[i][i];
            for (int j = i; j < n + 1; j++) {
                matrix[i][j] = matrix[i][j].divideBy(divisor);
            }
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    Fraction multiplier = matrix[k][i];
                    for (int l = i; l < n + 1; l++) {
                        matrix[k][l] = matrix[k][l].subtract(matrix[i][l].multiplyBy(multiplier));
                    }
                }
            }
            printMatrix(matrix);
        }

        System.out.println("ANSWER");
        for (int i = 0; i < n; i++) {
            System.out.println("x" + (i + 1) + " = " + matrix[i][n]);
        }
    }

    public static void printMatrix(Fraction[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        System.out.println("-".repeat(m * 5));
        for (Fraction[] fractions : matrix) {
            for (int j = 0; j < m; j++) {
                if (fractions[j].getDenominator() == 1) {
                    System.out.print(" " + fractions[j].getNumerator() + " ");
                } else {
                    System.out.print(" " + fractions[j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("-".repeat(m * 5));
    }

    public static Fraction[][] matrixInitialization(String filePath) {
        try (Scanner sc = new Scanner(new File(filePath))) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            Fraction[][] matrix = new Fraction[n][m];
            System.out.println("MATRIX");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = Fraction.getFraction(sc.next());
                }
            }
            printMatrix(matrix);
            return matrix;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}