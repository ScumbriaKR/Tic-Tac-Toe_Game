import java.util.Scanner;
import java.util.Arrays;

public class Main {

    public static char[] fillTheField(String input) {
        int n = input.length();
        char[] array = new char[n];
        for (int i = 0; i < n; i++) {
            array[i] = input.charAt(i);
            if (array[i] != 'X' && array[i] != 'O' && array[i] != '_') {
                System.out.println("Wrong symbol!");
                break;
            }
        }
        return array;
    }

    public static void showTheField(char[] array) {
        System.out.println("---------");
        System.out.println("| " + array[0] + " " + array[1] + " " + array[2] + " |");
        System.out.println("| " + array[3] + " " + array[4] + " " + array[5] + " |");
        System.out.println("| " + array[6] + " " + array[7] + " " + array[8] + " |");
        System.out.println("---------");
    }

    public static String findTheState(char[] array) {
        // impossible case 1
        int sumOfO = 0;
        int sumOfX = 0;
        for (char c : array) {
            if (c == 'O') {
                sumOfO++;
            } else if (c == 'X') {
                sumOfX++;
            }
        }
        if (Math.abs(sumOfO - sumOfX) > 1) {
            return "Impossible";
        }
        boolean xWins = false;
        boolean oWins = false;
        boolean noCells = true;
        char[][] field2D = new char[3][3];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field2D[i][j] = array[index];
                if (array[index] == '_' || array[index] == ' ') {
                    noCells = false;
                }
                index++;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                if (field2D[i][j] == 'X' && field2D[i][j + 1] == 'X' && field2D[i][j + 2] == 'X') {
                    xWins = true;
                } else if (field2D[i][j] == 'O' && field2D[i][j + 1] == 'O' && field2D[i][j + 2] == 'O') {
                    oWins = true;
                }
            }
        }
        if (array[0] == 'X' && array[4] == 'X' && array[8] == 'X') {
            xWins = true;
        }
        if (array[0] == 'O' && array[4] == 'O' && array[8] == 'O') {
            oWins = true;
        }
        if (array[2] == 'X' && array[4] == 'X' && array[6] == 'X') {
            xWins = true;
        }
        if (array[2] == 'O' && array[4] == 'O' && array[6] == 'O') {
            oWins = true;
        }
        for (int i = 0; i < 3; i++) {
            if (array[i] == 'X' && array[i + 3] == 'X' && array[i + 6] == 'X') {
                xWins = true;
            }
            if (array[i] == 'O' && array[i + 3] == 'O' && array[i + 6] == 'O') {
                oWins = true;
            }
        }
        // impossible case 2
        if (xWins && oWins) {
            return "Impossible";
        }
        if (xWins && !oWins) {
            return "X wins";
        }
        if (oWins && !xWins) {
            return "O wins";
        }
        if (!xWins && !oWins && noCells) {
            return "Draw";
        }
        return "Game not finished";
    }

    public static void nextMove(char[] array, int move) {
        Scanner sc = new Scanner(System.in);
        char[][] field2D = new char[3][3];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field2D[i][j] = array[index];
                index++;
            }
        }
        while (true) {
            System.out.println("Enter the coordinates:");
            if (sc.hasNextInt()) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                if (y > 3 || y < 1 || x > 3 || x < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
                if (field2D[3 - y][x - 1] == 'O' || field2D[3 - y][x - 1] == 'X') {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }
                if (field2D[3 - y][x - 1] != 'O' || field2D[3 - y][x - 1] != 'X') {
                    if (move % 2 != 0) {
                        if (3 - y == 0) {
                            array[x - 1] = 'X';
                        } else if (3 - y == 1) {
                            array[x - 1 + 3 - y + 2] = 'X';
                        } else if (3 - y == 2) {
                            array[x - 1 + 3 - y + 4] = 'X';
                        }
                        break;
                    } else {
                        if (3 - y == 0) {
                            array[x - 1] = 'O';
                        } else if (3 - y == 1) {
                            array[x - 1 + 3 - y + 2] = 'O';
                        } else if (3 - y == 2) {
                            array[x - 1 + 3 - y + 4] = 'O';
                        }
                        break;
                    }
                } else {
                    System.out.println("You should enter numbers!");
                    break;
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter cells: ");
        //String input = sc.nextLine();
        char[] field = new char[9];
        Arrays.fill(field, '_');
        showTheField(field);
        int move = 1;
        while (true) {
            nextMove(field, move++);
            showTheField(field);
            String result = findTheState(field);
            if (result.equals("X wins") || result.equals("O wins") || result.equals("Draw")) {
                System.out.println(result);
                break;
            }
        }
    }
}