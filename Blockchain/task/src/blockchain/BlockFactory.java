package blockchain;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

abstract class BlockFactory {
    final LinkedList<Block> chain = new LinkedList<>();
    final AtomicInteger atomicInteger = new AtomicInteger();
    final Random randomMagicNumber = new Random();

    protected abstract void generateBlock(int zerosNumber);

    void generateChain(int size, int zerosNumber) {
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
}
