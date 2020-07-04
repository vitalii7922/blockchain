package blockchain;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class BlockChainFactory extends BlockFactory {

    @Override
    protected void generateBlock(int zerosNumber) {
        String hash;
        int magicNumber;
        String zeros = generateStringWithNZeros(zerosNumber);
        Instant startTime = Instant.now();
        do {
            magicNumber = randomMagicNumber.nextInt();
            hash = calculateHash(magicNumber);
        } while (!hash.substring(0, zerosNumber).equals(zeros));
        Block block = Block.builder()
                .id(atomicInteger.incrementAndGet())
                .timeStamp(new Date().getTime())
                .magicNumber(magicNumber)
                .blockHash(hash)
                .timeGenerating(Duration.between(startTime, Instant.now()).getSeconds())
                .previousBlockHash(chain.isEmpty() ? "0" : chain.getLast().getBlockHash())
                .build();
        chain.add(block);
    }

    private String calculateHash(int magicNumber) {
        return StringUtil.applySha256(String.valueOf(magicNumber));
    }

    private String generateStringWithNZeros(int zerosNumber) {
        return "0".repeat(Math.max(0, zerosNumber));
    }

    @Override
    protected void generateChain(int size, int zerosNumber) {
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

    @Override
    protected void printChain() {
        for (Block block : chain) {
            System.out.println(block);
        }
    }
}
