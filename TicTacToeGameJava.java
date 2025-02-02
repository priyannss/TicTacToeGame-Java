import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class TicTacToeGameJava {
    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {
        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };

        printGameBoard(gameBoard);

        while(true) {
            int playerPos = getPlayerPos();
            placePiece(gameBoard, playerPos, "player");

            String result = checkWinner();
            if (!result.isEmpty()) {
                printGameBoard(gameBoard);
                System.out.println(result);
                break;
            }

            int cpuPos = getCpuPos();
            System.out.println("Cpu placement : " + cpuPos);
            placePiece(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);

            result = checkWinner();
            if (!result.isEmpty()) {
                System.out.println(result);
                break;
            }
        }
    }

    private static int getPlayerPos() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter your placement(1-9) : ");
        int playerPos = scan.nextInt(); scan.nextLine();

        while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos) || playerPos > 9 || playerPos < 1) {
            System.out.print("Position taken! Enter a correct position : ");
            playerPos = scan.nextInt(); scan.nextLine();
        }

        return playerPos;
    }

    private static int getCpuPos() {
        Random rand = new Random();

        int cpuPos = rand.nextInt(9) + 1;
        while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
            cpuPos = rand.nextInt(9) + 1;
        }

        return cpuPos;
    }

    private static void placePiece(char[][] gameBoard, int pos, String user) {
        char symbol = ' ';
        if(user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if(user.equals("cpu")){
            symbol = 'O';
            cpuPositions.add(pos);
        }

        switch (pos) {
            case 1: gameBoard[0][0] = symbol; break;
            case 2: gameBoard[0][2] = symbol; break;
            case 3: gameBoard[0][4] = symbol; break;
            case 4: gameBoard[2][0] = symbol; break;
            case 5: gameBoard[2][2] = symbol; break;
            case 6: gameBoard[2][4] = symbol; break;
            case 7: gameBoard[4][0] = symbol; break;
            case 8: gameBoard[4][2] = symbol; break;
            case 9: gameBoard[4][4] = symbol; break;
            default: break;
        }
    }

    private static String checkWinner() {
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> midRow = Arrays.asList(4, 5, 6);
        List<Integer> bottomRow = Arrays.asList(7, 8, 9);
        List<Integer> leftCol = Arrays.asList(1, 4, 7);
        List<Integer> midCol = Arrays.asList(2, 5, 8);
        List<Integer> rightCol = Arrays.asList(3, 6, 9);
        List<Integer> cross1 = Arrays.asList(1, 5, 9);
        List<Integer> cross2 = Arrays.asList(3, 5, 7);

        List<List<Integer>> winningConditions = new ArrayList<>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(bottomRow);
        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);
        winningConditions.add(cross1);
        winningConditions.add(cross2);

        for (List<Integer> list : winningConditions) {
            if (playerPositions.containsAll(list)) {
                return "Congratulations you won!";
            } else if(cpuPositions.containsAll(list)) {
                return "Cpu wins! Sorry :(";
            }
        }

        if (playerPositions.size() + cpuPositions.size() == 9) {
            return "CAT!";
        }

        return "";
    }

    private static void printGameBoard(char[][] gameBoard) {
        for(char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
