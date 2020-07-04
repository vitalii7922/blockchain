package blockchain;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter how many zeros the hash must start with: ");
        BlockFactory blockFactory = new BlockChainFactory();
        blockFactory.generateChain(5, scanner.nextInt());
        blockFactory.printChain();
        //added comment
    }
}
