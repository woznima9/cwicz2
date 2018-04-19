package play;

import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
    public static void main(String[] args) {
        int lookInt;
        int tryNumber = 1;
        System.out.println("Odgadnij wylosowaną liczbę z przedziału 0-100. \n\n - podaj liczbę: ");
        Random randomInt = new Random();
        lookInt = randomInt.nextInt(100);
//      System.out.println(lookInt);
        Scanner scanner = new Scanner(System.in);
        int inputInt = 0;
        inputInt = Integer.parseInt(scanner.nextLine());
        while (!(lookInt == inputInt)) {
            if (inputInt > lookInt) {
                System.out.println(inputInt + "za duża - spróbuj jeszcze raz");
            } else {
                System.out.println(inputInt + "za mała - spróbuj jeszcze raz");
            }
            inputInt = Integer.parseInt(scanner.nextLine());
            tryNumber++;
        }
        System.out.println("Brawo " + inputInt + " to szukana liczba odgadnięta za " + tryNumber + " razem");

    }
}
