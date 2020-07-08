package blockchain;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        BlockChainFactory blockFactory = new BlockChainFactory();
        Optional<LinkedList<Block>> optionalBlocks =
                Optional.ofNullable((LinkedList<Block>) SerializationUtils.deserialize());
        optionalBlocks.ifPresent(blockFactory::setChain);
        Miner miner = new Miner();
        miner.setFactory(blockFactory);
        if (blockFactory.validateChain()) {
            miner.mine();
        }
        blockFactory.printChain();
    }
}
