package blockchain;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

abstract class BlockFactory implements Runnable {
    LinkedList<Block> chain = new LinkedList<>();
    AtomicInteger blockId = new AtomicInteger();
    final Random randomMagicNumber = new Random();

    protected abstract void generateBlock();

    synchronized void generateChain(int size) throws IOException {
        if (!chain.isEmpty()) {
            blockId = new AtomicInteger(chain.size());
        }
            for (int i = 0; i < size; i++) {
                generateBlock();
            }
        SerializationUtils.serialize(chain);
    }

    boolean validateChain() {
        for (int i = 1; i < chain.size(); i++) {
            if (!chain.get(i).getPreviousBlockHash().equals(chain.get(i - 1).getBlockHash())) {
                return false;
            }
        }
        return true;
    }

    void printChain() {
        for (Block block : chain) {
            System.out.println(block);
        }
    }

    void setChain(LinkedList<Block> chain) {
        this.chain = chain;
    }
}
