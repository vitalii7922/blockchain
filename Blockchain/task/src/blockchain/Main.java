package blockchain;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter how many zeros the hash must start with: ");
        BlockChain blockChain = new BlockChain(5, scanner.nextInt());
        System.out.println();
        List<Block> chain = blockChain.getChain();
        for (Block block : chain) {
            System.out.println(block);
        }
    }

}
