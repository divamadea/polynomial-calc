package Polynomial;
import java.util.Scanner;
public class Interface {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Program tentang Polinomial"
                + "\nMenu : "
                + "\n1. Penjumlahan Polinomial F(x) + G(x)"
                + "\n2. Pengurangan Polinomial F(x) - G(x)"
                + "\n3. Perkalian Polinomial F(x) * G(x)");
        System.out.print("Masukkan piihan anda: ");
        int menu = input.nextInt();
        
        Polynomial poly1 = new Polynomial();
        System.out.print("\nMasukkan derajat polinomial F(x) : ");
        int derajat1 = input.nextInt();
        for(int i = 0; i <= derajat1; i++){
            System.out.print("Koefisien x derajat ke-" + i + ": ");
            poly1.append(input.nextInt());
        }
        System.out.println("F(x) = " + poly1);
        
        Polynomial poly2 = new Polynomial();
        System.out.print("\nMasukkan derajat polinomial G(x) : ");
        int derajat2 = input.nextInt();
        for(int i = 0; i <= derajat2; i++){
            System.out.print("Koefisien x derajat ke-" + i + ": ");
            poly2.append(input.nextInt());
        }
        System.out.println("G(x) = " + poly2);
        
        switch (menu){
            case 1:
                System.out.println("\nDidapatkan hasil penjumlahan"
                        + " polinomial\nF(x) + G(x) = " 
                        + Polynomial.addition(poly1,poly2));
                break;
            case 2:
                System.out.println("\nDidapatkan hasil pengurangan"
                        + " polinomial\nF(x) - G(x) = " 
                        + Polynomial.subtraction(poly1,poly2));
                break;
            case 3:
                System.out.println("\nDidapatkan hasil perkalian"
                        + " polinomial\nF(x) * G(x) = " 
                        + Polynomial.multiplication(poly1,poly2));
                break;
                
        }
    }
    
}
