package blockchain;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockChainFactory extends BlockFactory {
    protected synchronized void generateBlock() {
        String zeros = generateStringWithNZeros(zerosNumber);
        long startTime = System.currentTimeMillis() / 1000;
        findHashWithLeadZeros(zeros);
        long resultTime = System.currentTimeMillis() / 1000 - startTime;
        takeZerosNumber(resultTime);
        Block block = Block.builder()
                .miner(String.valueOf(Thread.currentThread().getId()))
                .id(blockId.incrementAndGet())
                .timeStamp(new Date().getTime())
                .magicNumber(magicNumber)
                .blockHash(hash)
                .zerosNumber(zerosNumber)
                .timeGenerating(resultTime)
                .previousBlockHash(chain.isEmpty() ? "0" : chain.getLast().getBlockHash())
                .changedZerosNumber(message)
                .data(chain.isEmpty() ? "no messages" : String.join("\n", messages))
                .build();
        chain.add(block);
    }


    private void findHashWithLeadZeros(String zeros) {
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
    protected void generateChain() throws IOException {
        if (!chain.isEmpty()) {
            blockId = new AtomicInteger(chain.size());
            zerosNumber = chain.getLast().getZerosNumber();
            takeZerosNumber(chain.getLast().getTimeGenerating());
        }
        generateBlock();
        SerializationUtils.serialize(chain);
    }

    @Override
    protected boolean validateChain() {
        for (int i = 1; i < chain.size(); i++) {
            if (!chain.get(i).getPreviousBlockHash().equals(chain.get(i - 1).getBlockHash())) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void printChain() {
        for (Block block : chain) {
            System.out.println(block);
        }
    }

    void setChain(LinkedList<Block> chain) {
        this.chain = chain;
    }
}
