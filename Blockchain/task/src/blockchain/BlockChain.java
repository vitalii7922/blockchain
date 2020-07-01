package blockchain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockChain {

    private final LinkedList<Block> chain = new LinkedList<>();
    private final AtomicInteger atomicInteger = new AtomicInteger();
    private static final Random RANDOM_MAGIC_NUMBER = new Random();

    public BlockChain(int size, int zerosNumber) {
        generateChain(size, zerosNumber);
    }

    private void generateBlock(int zerosNumber) {
        BlockFactory blockFactory = new BlockFactory();

        StringBuilder zeros = new StringBuilder();
        zeros.append("0".repeat(Math.max(0, zerosNumber)));
        long startTime = System.currentTimeMillis();
        String hash;
        int magicNumber;

        do {
            magicNumber = RANDOM_MAGIC_NUMBER.nextInt();
            hash = StringUtil.applySha256(String.valueOf(magicNumber));
        } while (!hash.substring(0, zerosNumber).equals(zeros.toString()));

        long resultTime = System.currentTimeMillis() - startTime;

        Block block = blockFactory.makeBlock(atomicInteger.incrementAndGet(), new Date().getTime(), magicNumber, hash,
                resultTime);

        block.setPreviousBlockHash(chain.isEmpty() ? "0" : chain.getLast().getBlockHash());

        chain.add(block);
    }

    private void generateChain(int size, int zerosNumber) {
        for (int i = 0; i < size; i++) {
            generateBlock(zerosNumber);
        }
    }

    public boolean validateChain() {
        for (int i = 1; i < chain.size(); i++) {
            if (!chain.get(i).getPreviousBlockHash().equals(chain.get(i - 1).getBlockHash())) {
                return false;
            }
        }
        return true;
    }

    public List<Block> getChain() {
        return chain;
    }
}
