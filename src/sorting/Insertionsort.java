package sorting;

import java.util.Scanner;

public class Insertionsort {
    public static void insertionSort(int[] tablica) {

        int klucz, j;

        //dla kazdego elementu tablicy do posortowania, poczawszy od drugiego
        for (int i = 1; i < tablica.length; i++) {
            j = i;
            klucz = tablica[i];
            //poszukaj miejsca dla aktualnego elementu
            //szukaj tylko w posortowanej juz czescie tablicy
            //(czyli wsrod elementow o indeksach mniejszych od aktualnego)
            //przesuwaj element w kiedunku poczatku tablicy
            //tak dlugo, az przed nim jest element wiekszy i
            //nie znajduje sie na poczatku tablicy
            while (j > 0 && tablica[j - 1] > klucz) {
                tablica[j] = tablica[j - 1];
                j--;
            }
            tablica[j] = klucz;
        }

        //wypisz zawartosc posortowanej tablicy
        for (int i = 0; i < tablica.length; i++) {
            System.out.println("#" + (i + 1) + " :\t" + tablica[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {

        String liczba;
        String ile_liczb;
        int wielkosc, l;

        //wyswietl tytul
        System.out.println("\n\nSortowanie");
        System.out.println("Insertion Sort\n");

        //pobierz od uzytkownika liczbe elementow do sortowania
        Scanner odczyt = new Scanner(System.in);
        System.out.println("Jak duzo liczb chcesz posortowac?:");
        ile_liczb = odczyt.nextLine();
        wielkosc = Integer.parseInt(ile_liczb);
        System.out.println("\n");

        //utworz tablice i wczytaj do niej elementy
        int[] tablica = new int[wielkosc];
        for (int i = 0; i < wielkosc; i++) {
            l = i + 1;
            System.out.print("Wpisz liczbę\t #" + l + " \t ");
            liczba = odczyt.nextLine();
            tablica[i] = Integer.parseInt(liczba);
        }
        System.out.println();

        //posortuj elementy tablicy
        insertionSort(tablica);
    }


}
