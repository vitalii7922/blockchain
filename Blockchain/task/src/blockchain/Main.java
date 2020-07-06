package blockchain;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BlockChainFactory blockFactory = new BlockChainFactory();
       /* Optional<LinkedList<Block>> optionalBlocks =
                Optional.ofNullable((LinkedList<Block>) SerializationUtils.deserialize());*/
//        optionalBlocks.ifPresent(blockFactory::setChain);
        Miner miner = new Miner();
        miner.setFactory(blockFactory);
        miner.mine();

        /*if (blockFactory.validateChain()) {
            blockFactory.generateChain(5);
            blockFactory.printChain();
        }*/
    }
}
