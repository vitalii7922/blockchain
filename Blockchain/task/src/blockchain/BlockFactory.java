package blockchain;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

abstract class BlockFactory {
    LinkedList<Block> chain = new LinkedList<>();
    AtomicInteger blockId = new AtomicInteger();
    final Random randomMagicNumber = new Random();
    int zerosNumber;
    String message;
    int magicNumber;
    String hash;

    protected abstract void generateBlock();

    protected abstract void generateChain() throws IOException;

    protected abstract void printChain();

    protected abstract boolean validateChain();
}
