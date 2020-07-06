package blockchain;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

abstract class BlockFactory {
    LinkedList<Block> chain = new LinkedList<>();
    AtomicInteger atomicInteger;
    final Random randomMagicNumber = new Random();

    protected abstract void generateBlock(int zerosNumber);

    void generateChain(int size, int zerosNumber) {
        if (!chain.isEmpty()) {
            atomicInteger = new AtomicInteger(chain.size());
        }
        for (int i = 0; i < size; i++) {
            generateBlock(zerosNumber);
        }
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

    public void setChain(LinkedList<Block> chain) {
        this.chain = chain;
    }
}
