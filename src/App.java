import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Random;

public class App {

    static Scanner i = new Scanner(System.in);
    static Random r = new Random();

    static int diff;

    public static void main(String[] args) throws Exception {
        System.out.println("----------    Bulls and Cows Game    ----------");
        System.out.println("----------    Press ENTER to start   ----------");
        i.nextLine();
        selectDiff();
    }

    static void selectDiff() {
        System.out.println("Choose the diff");
        System.out.println("1 - Easy");
        System.out.println("2 - Normal");
        System.out.println("3 - Hard");
        System.out.print("Choose: ");
        while (true) {
            int choose = inputI();
            if (choose == 1) {
                diff = 4;
                break;
            } else if (choose == 2) {
                diff = 5;
                break;
            } else if (choose == 3) {
                diff = 6;
                break;
            } else {
                System.out.println("Number Mismatch Exception: Please Try Again.");
                continue;
            }
        }
        createNumberRow(diff);
    }

    static void createNumberRow(int x) {
        System.out.println("Count of numbers: " + x);
        delay(1);
        ArrayList<Integer> deck = new ArrayList<>();
        for (int i = 1; i <= x; i++) {
            deck.add(r.nextInt(1, 10));
        }
        Collections.shuffle(deck);
        game(deck);
    }

    static void game(ArrayList<Integer> numbers){
        int tour = 1;
        int trueNumbers = 0;
        int truePlacement = 0;
        while(true){
            System.out.println("Tour " + tour);
            System.out.print("Attempt: ");
            ArrayList<Integer> playerInput = playerInput();

            for(int i = 0; i < numbers.size(); i++){
                if(numbers.get(i) != playerInput.get(i)){
                    continue;
                }
                truePlacement++;
            }
            if(truePlacement == diff){
                System.out.println("Congratulations! You are Win!");
                return;
            }
            ArrayList<Integer> copyNumbers = new ArrayList<>(numbers);

            for(int i = 0; i < playerInput.size(); i++){
                for(int j = 0; j < copyNumbers.size(); j++){
                    if(playerInput.get(i) == copyNumbers.get(j)){
                        trueNumbers++;
                        copyNumbers.remove(j);
                        break;
                    }
                }
            }

            System.out.println("Bull: " + truePlacement);
            System.out.println("Cow: " + (trueNumbers - truePlacement));

            tour++;
            truePlacement = 0;
            trueNumbers = 0;
            delay(1);
        }
    }

    static int inputI() {
        while (true) {
            int x = i.nextInt();
            i.nextLine();
            return x;
        }
    }

    static ArrayList<Integer> playerInput() {
        while (true) {
            String x = "";
            String combine = "";
            try {
                x = i.nextLine();
                combine = x.replace(" ", "");
                int result = Integer.parseInt(combine);
                ArrayList<Integer> playerNumbers = new ArrayList<>();
                while(result > 0){
                    int temp = result % 10;
                    playerNumbers.add(temp);
                    result /= 10;
                }
                if(diff != playerNumbers.size()){
                    throw new Exception("The number entered must be " + diff);
                }
                Collections.reverse(playerNumbers);
                return playerNumbers;
            } catch (Exception e) {
                System.out.println("Input Mismatch Error: Please try again.");
                continue;
            }
        }
    }

    static void delay(double x) {
        // x = second
        try {
            Thread.sleep((long) (x * 1000));
            return;
        } catch (Exception e) {
            System.out.println("Interrupted Exception: Game is crashed.");
            System.exit(0);
        }
    }
}
