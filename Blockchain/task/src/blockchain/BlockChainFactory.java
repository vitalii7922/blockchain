package blockchain;

import java.io.IOException;
import java.util.*;

public class BlockChainFactory extends BlockFactory implements Runnable {
    private int zerosNumber;
    private String message;
    private int magicNumber;
    private String hash;

    protected void generateBlock() {
        String zeros = generateStringWithNZeros(zerosNumber);
        long startTime = System.currentTimeMillis() / 1000;
        validateHash(zeros);
        long resultTime = System.currentTimeMillis() / 1000 - startTime;
        takeZerosNumber(resultTime);
        Block block = Block.builder()
                .miner(String.valueOf(Thread.activeCount()))
                .id(blockId.incrementAndGet())
                .timeStamp(new Date().getTime())
                .magicNumber(magicNumber)
                .blockHash(hash)
                .timeGenerating(resultTime)
                .previousBlockHash(chain.isEmpty() ? "0" : chain.getLast().getBlockHash())
                .changedZerosNumber(message)
                .build();
        chain.add(block);
        System.out.println(block);
    }


    private void validateHash(String zeros) {
        do {
            magicNumber = randomMagicNumber.nextInt();
            hash = calculateHash(magicNumber);
            if (zeros.isEmpty()) {
                break;
            }
        } while (!hash.substring(0, zerosNumber).equals(zeros));
    }

    private void takeZerosNumber(long resultTime) {
        if (resultTime > 60) {
            zerosNumber--;
            message = "N was decreased by 1";
        } else if (resultTime < 15) {
            zerosNumber++;
            message = String.format("N was increased to %d", zerosNumber);
        } else {
            message = "N stays the same";
        }
    }

    private String calculateHash(int magicNumber) {
        return StringUtil.applySha256(String.valueOf(magicNumber));
    }

    private String generateStringWithNZeros(int zerosNumber) {
        return "0".repeat(Math.max(0, zerosNumber));
    }

    @Override
    public void run() {
        try {
            generateChain(5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
