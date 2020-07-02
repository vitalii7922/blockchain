package blockchain;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

abstract class BlockFactory {
    final LinkedList<Block> chain = new LinkedList<>();
    final AtomicInteger atomicInteger = new AtomicInteger();
    final Random randomMagicNumber = new Random();

    protected abstract void generateBlock(int zerosNumber);
}
