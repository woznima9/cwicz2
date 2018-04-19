package blackjack;

import java.util.Random;
import java.util.Scanner;

public class BlackJack {

    public static int[][] cards = new int[52][4];                       //dane ułozonych kart z fabryki
    public static int[] talia = new int[52];                            //indeksy potasowanej talii
    static int cardToBeTaken = 0;

    public static void main(String[] args) {
        int decision;
        String hiddenCroupierCard = "";

        do {
            int orderedDeck = 0;
            for (int cardName = 2; cardName < 15; cardName++) {
                for (int cardColor = 0; cardColor < 4; cardColor++) {
                    int cardDetail = 0;
                    cards[orderedDeck][cardDetail++] = orderedDeck;
                    cards[orderedDeck][cardDetail++] = cardName;
                    cards[orderedDeck][cardDetail++] = cardColor;
                    if (cardName < 10) {
                        int cardScore = cardName;
                        cards[orderedDeck][cardDetail] = cardScore;       // punkty za numery
                        orderedDeck++;
                        continue;
                    } else {
                        if (cardName < 14) {
                            int cardScore = 10;                           //punkty za figurę
                            cards[orderedDeck][cardDetail] = cardScore;
                            orderedDeck++;
                            continue;
                        } else {
                            int cardScore = 11;                           //punkty za asa
                            cards[orderedDeck][cardDetail] = cardScore;
                            orderedDeck++;
                            continue;
                        }
                    }
                }
            }                                                            // tablica danych nowej talii kart przygotowana


            // tasowanie kart w indeksie talia
            for (int i = 0; i < 52; i++) {
                talia[i] = cards[i][0];
            }
            Random random = new Random();
            int positionOn, positionOff;
            for (int i = 0; i < 10002; i++) {
                do {
                    positionOn = random.nextInt(52);
                    positionOff = random.nextInt(52);
                }
                while (positionOn == positionOff);
                int swapPosition;
                swapPosition = talia[positionOn];
                talia[positionOn] = talia[positionOff];
                talia[positionOff] = swapPosition;
            }                                                           // talia potasowana

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 52; i++) {
                for (int j = 0; j < 4; j++) {
                    sb.append(cards[i][j] + ".");
                }
                sb.append("\t");

            }
//          System.out.println(sb);                                     // wyświetli kontrolnie karty w nowej talii
            System.out.println("");

            StringBuffer sbTalia = new StringBuffer();
            for (int i = 0; i < 52; i++) {
                sbTalia.append(pickUpCard(i) + "  ");
            }
//            System.out.println(sbTalia);                              // wyświetli kontrolnie karty w potasowanej talii
            cardToBeTaken = 0;
            System.out.println("");

            System.out.println("\t\t\tN_O_W_E  R_O_Z_D_A_N_I_E\n");
            int playerScore = 0, crupierScore = 0;
            for (int i = 0; i < 2; i++) {
                System.out.println("Karta gracza " + pickUpCard(cardToBeTaken));
                playerScore = playerScore + valuePickUpCard(cardToBeTaken);
                System.out.println("punkty gracza " + playerScore);
                cardToBeTaken++;
                if (i==0) {
                    System.out.println("\t\t\t\t\t\t\t\tKarta krupiera " + pickUpCard(cardToBeTaken));
                    crupierScore = crupierScore + valuePickUpCard(cardToBeTaken);
                    System.out.println("\t\t\t\t\t\t\t\tpunkty krupiera " + crupierScore);
                } else {
                    System.out.println("\t\t\t\t\t\t\t\tKarta zakryta");
                    hiddenCroupierCard = pickUpCard(cardToBeTaken);
                    crupierScore = crupierScore + valuePickUpCard(cardToBeTaken);
                }
                cardToBeTaken++;
            }

            Scanner scanner = new Scanner(System.in);
            boolean playerTurn = true;
            boolean crupierTurn = true;
            do {
                System.out.println("Decyzja gracza:\n");
                System.out.println("1 - dobranie karty");
                System.out.println("2 - sprawdzam");
                decision = scanner.nextInt();
                switch (decision) {
                    case 1:
                        System.out.println("Karta gracza " + pickUpCard(cardToBeTaken));
                        playerScore = playerScore + valuePickUpCard(cardToBeTaken);
                        System.out.println("punkty gracza " + playerScore);
                        if (playerScore > 21) {
                            System.out.println("graczu FURA - przegrałeś");
                            return;
                        }
                        cardToBeTaken++;
                        break;
                    case 2:
                        playerTurn = false;
                        break;
                }
            } while (playerTurn);

            System.out.println("\t\t\t\t\t\t\t\todkrywam zakrytą kartę: " +hiddenCroupierCard);
            System.out.println("\t\t\t\t\t\t\t\tpunkty krupiera: "+crupierScore+"\n");
            do {
                System.out.println("\t\t\t\t\t\t\t\tDecyzja krupiera:\n");
                System.out.println("\t\t\t\t\t\t\t\t1 - dobranie karty");
                System.out.println("\t\t\t\t\t\t\t\t2 - sprawdzam");
                decision = scanner.nextInt();
                switch (decision) {
                    case 1:
                        System.out.println("\t\t\t\t\t\t\t\tKarta krupiera " + pickUpCard(cardToBeTaken));
                        crupierScore = crupierScore + valuePickUpCard(cardToBeTaken);
                        System.out.println("\t\t\t\t\t\t\t\tpunkty krupiera " + crupierScore);
                        if (crupierScore > 21) {
                            System.out.println("\t\t\t\t\t\t\t\tFURA - kasyno przegrało");
                            return;
                        }
                        cardToBeTaken++;
                        break;
                    case 2:
                        crupierTurn = false;
                        break;
                }
            } while (crupierTurn);
            if (playerScore > crupierScore) {
                System.out.println("Brawo -wygrałeś z kasynem");
            } else if (crupierScore > playerScore) {
                System.out.println("KASYNO WYGRYWA");
            } else {
                System.out.println("REMIS");
            }
            System.out.println("Jeśli nowe rozdanie - wybierz  9 ");
            decision = scanner.nextInt();
        } while (decision == 9);

        System.out.println("Gra zakończona");
    }
    // **************************************************************
    static String pickUpCard(int cardNumber) {
        String cardName = "";
        String colorName = "";
        switch (cards[talia[cardNumber]][1]) {
            case 2:
                cardName = "2--";
                break;
            case 3:
                cardName = "3--";
                break;
            case 4:
                cardName = "4--";
                break;
            case 5:
                cardName = "5--";
                break;
            case 6:
                cardName = "6--";
                break;
            case 7:
                cardName = "7--";
                break;
            case 8:
                cardName = "8--";
                break;
            case 9:
                cardName = "9--";
                break;
            case 10:
                cardName = "10-";
                break;
            case 11:
                cardName = "J--";
                break;
            case 12:
                cardName = "Q--";
                break;
            case 13:
                cardName = "K--";
                break;
            case 14:
                cardName = "AS-";
                break;
        }
        switch (cards[talia[cardNumber]][2]) {
            case 0:
                colorName = "Trefl";
                break;
            case 1:
                colorName = "Karo";
                break;
            case 2:
                colorName = "Kier";
                break;
            case 3:
                colorName = "Pik";
                break;
        }
        String card = (cardName + colorName);
        return card;
    }

    static int valuePickUpCard(int cardNumber) {
        return cards[talia[cardNumber]][3];
    }
}

