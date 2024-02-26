package ru.ac.uniyar.domain;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Matrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] mtr = matrixInitialization("src/main/resources/input.txt");
        if (mtr == null) {
            return;
        }
        int n = mtr.length;
        int m = mtr[0].length;
        if (n < m - 1) {
            int[] basic = new int[n];
            System.out.println("Choose " + n + " basic columns. Enter " + n + " numbers from 0 to " + (m - 2) + ":");
            for (int i = 0; i < n; i++) {
                basic[i] = sc.nextInt();
            }
            solveSystemWithBasis(mtr, basic);
        } else {
            solveSystem(mtr);
        }
    }

    public static void solveSystem(double[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            double divisor = matrix[i][i];
            for (int j = i; j < n + 1; j++) {
                matrix[i][j] /= divisor;
            }
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double multiplier = matrix[k][i];
                    for (int l = i; l < n + 1; l++) {
                        matrix[k][l] -= multiplier * matrix[i][l];
                    }
                }
            }
            System.out.println();
            System.out.println("-".repeat(n * 5));
            for (double[] doubles : matrix) {
                for (int j = 0; j < n + 1; j++) {
                    System.out.print(" " + doubles[j] + " ");
                }
                System.out.println();
            }
            System.out.println("-".repeat(n * 5));
        }

        for (int i = 0; i < n; i++) {
            System.out.println("x" + (i + 1) + " = " + matrix[i][n]);
        }
    }

    public static void solveSystemWithBasis(double[][] matrix, int[] basic) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            double divisor = matrix[i][basic[i]];
            for (int j = 0; j < n + 1; j++) {
                matrix[i][j] /= divisor;
            }
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double multiplier = matrix[k][basic[i]];
                    for (int l = 0; l < n + 1; l++) {
                        matrix[k][l] -= multiplier * matrix[i][l];
                    }
                }
            }
            System.out.println();
            System.out.println("-".repeat(n * 5));
            for (double[] doubles : matrix) {
                for (int j = 0; j < n + 1; j++) {
                    System.out.print(" " + doubles[j] + " ");
                }
                System.out.println();
            }
            System.out.println("-".repeat(n * 5));
        }

        for (int i = 0; i < n; i++) {
            System.out.print("x" + (basic[i] + 1) + " = ");
            double sum = matrix[i][n];
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double val = -matrix[i][basic[j]];
                    System.out.print(val + "*x" + (j + 1) + " ");
                    sum += val * matrix[j][n];
                }
            }
            System.out.println(sum);
        }
    }

    public static double[][] matrixInitialization(String filePath) {
        try (Scanner sc = new Scanner(new File(filePath))) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            double[][] matrix = new double[n][m];
            System.out.println("MATRIX");
            for (int i = 0; i < m; i++) {
                System.out.print("  " + i + "  ");
            }
            System.out.println();
            System.out.println("-".repeat(m * 5));
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = sc.nextDouble();
                    System.out.print(" " + matrix[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("-".repeat(m * 5));
            return matrix;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}