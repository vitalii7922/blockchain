package blockchain;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        BlockFactory blockFactory = new BlockChainFactory();
        Optional<LinkedList<Block>> optionalBlocks =
                Optional.ofNullable((LinkedList<Block>) SerializationUtils.deserialize());
        optionalBlocks.ifPresent(blockFactory::setChain);
        if (blockFactory.validateChain()) {
            System.out.print("Enter how many zeros the hash must start with: ");
            blockFactory.generateChain(5, scanner.nextInt());
            blockFactory.printChain();
        }
    }
}
