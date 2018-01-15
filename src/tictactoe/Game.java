package tictactoe;

import java.util.Scanner;

public class Game {

    //Pola gry
    private NumbersOfField field; //Enum do ustawiania pola do wypełnienia
    private Scanner scanner = new Scanner(System.in); //ustawienie Scannera
    private boolean mainResult; //
    private int typeOfGame; // typ gry
    private char[][] gameArray = new char[3][3];
    private static int[][] gameArrayNumbers = new int[3][3];


    public void start() {

        mainResult = true;
        //wyświetla menu gry
        System.out.println("To jest gra w kółko krzyżyk. " +
                "\n Menu gry: " +
                "\n 1 - aby zagrać przeciwko komputerowi " +
                "\n 2 - aby zagrać w dwie osoby " +
                "\n Inne - wyjście z programu");

        //W bloku try i catch, aby złapać wyjątek gdy użytkownik wprowadzi inną daną niż int
        try {
            typeOfGame = scanner.nextInt();
        } catch (Exception e) {

        }

        //wybór typu gry w zależności od wprowadzonej zmiennej/ jeżeli nie będzie 1 lub 2 to aplikacja sie skończy :)
        if (typeOfGame == 1 || typeOfGame == 2) {
            play(typeOfGame);
        } else {
            System.out.println("Zamykam grę!");
        }
    }

    private void play(int type) {

        //Wypełnienie tablicy do grania znakami '?'
        for (int i = 0; i < gameArray.length; i++) {
            for (int j = 0; j < gameArray.length; j++) {
                gameArray[i][j] = 63;
            }
        }
        // Wypełnienie i wyświetlenie tablicy wzorcowej  z podpowiedziami numerów pól
        fillArray();

        //Jeżeli mainresult czyli wynik gry jest jeszcze true -> gra się nie skończyła gramy dalej
        while (mainResult) {
            //uruchomienie wyboru pola do wypełnienia znakiem z przekazaniem znaku i typu gry
            chooseField('X', type);
            // Sprawdzenie stanu gry przed ruchem przeciwnika
            if (mainResult) {
                //uruchomienie wyboru pola do wypełnienia znakiem z przekazaniem znaku i typu gry
                chooseField('O', type);

            }
        }

        // ponowne przejście do menu
        start();


    }

    private static void fillArray() {
        //Wydruk i wypełnienie tablicy "1-9"
        int k = 1;
        for (int i = 0; i < gameArrayNumbers.length; i++) {
            for (int j = 0; j < gameArrayNumbers.length; j++) {
                gameArrayNumbers[i][j] = k;
                System.out.print(" " + gameArrayNumbers[i][j] + " |");
                k++;
            }
            System.out.println();
        }
    }

    private void print() {
        for (int i = 0; i < gameArray.length; i++) {
            for (int j = 0; j < gameArray.length; j++) {
                System.out.print(" " + gameArray[i][j] + " |");
            }
            System.out.println();
        }
    }

    private boolean checkField() {
        boolean state = false;
        for (char[] ch1 : gameArray) {
            for (char ch : ch1) {
                if (ch == '?') {
                    state = true;
                }
            }
        }
        return state;
    }

    private void chooseField(char ch, int type) {
        if (checkField()) {

            int number = 0;
            do {
                if (ch == 'O' && type == 1) {
                    number = (int) ((Math.random() * 9) + 1);
                } else {
                    System.out.println("Podaj numer pola do wypełnienia: " + ch);
                    number = scanner.nextInt();
                }
                switch (number) {
                    case 1:
                        field = NumbersOfField.ONE;
                        break;
                    case 2:
                        field = NumbersOfField.TWO;
                        break;
                    case 3:
                        field = NumbersOfField.THREE;
                        break;
                    case 4:
                        field = NumbersOfField.FOUR;
                        break;
                    case 5:
                        field = NumbersOfField.FIVE;
                        break;
                    case 6:
                        field = NumbersOfField.SIX;
                        break;
                    case 7:
                        field = NumbersOfField.SEVEN;
                        break;
                    case 8:
                        field = NumbersOfField.EIGHT;
                        break;
                    case 9:
                        field = NumbersOfField.NINE;
                        break;
                    default:
                        if (type != 1 || ch == 'X') {
                            System.out.println("Wybierz ponownie!");
                        }
                        break;
                }
            }
            //Sprawdza czy wybrane pole nie jest zajęte
            while (gameArray[field.getX()][field.getY()] != 63);
            gameArray[field.getX()][field.getY()] = ch;


        } else {
            System.out.println("Koniec gry");
            mainResult = false;
        }
        System.out.println("Stan gry:");
        print();
        System.out.println(checksum(ch));
    }

    private String checksum(char ch) {
        int[] sum = new int[8];
        sum[0] = gameArray[0][0] + gameArray[0][1] + gameArray[0][2];
        sum[1] = gameArray[0][0] + gameArray[1][0] + gameArray[2][0];
        sum[2] = gameArray[0][0] + gameArray[1][1] + gameArray[2][2];
        sum[3] = gameArray[0][1] + gameArray[1][1] + gameArray[2][1];
        sum[4] = gameArray[0][2] + gameArray[1][2] + gameArray[2][2];
        sum[5] = gameArray[0][2] + gameArray[1][1] + gameArray[2][0];
        sum[6] = gameArray[1][0] + gameArray[1][1] + gameArray[1][2];
        sum[7] = gameArray[2][0] + gameArray[2][1] + gameArray[2][2];
        int sumchar = 3 * ch;
        String result = "";
        for (int summ : sum) {
            if (summ == sumchar) {
                result = "Wygrały " + ch;
                mainResult = false;
            }
        }
        return result;
    }




}
