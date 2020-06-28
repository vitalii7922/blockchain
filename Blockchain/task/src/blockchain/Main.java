package blockchain;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        BlockChain blockChain = new BlockChain(5);
        LinkedList<Block> chain = blockChain.getChain();
        for (Block block : chain) {
            System.out.println(block);
        }
    }

}
