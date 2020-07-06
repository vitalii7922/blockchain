package blockchain;
import java.util.*;

public class BlockChainFactory extends BlockFactory {

    protected void generateBlock(int zerosNumber) {
        String hash;
        int magicNumber;
        String zeros = generateStringWithNZeros(zerosNumber);
        long startTime = new Date().getTime();
        do {
            magicNumber = randomMagicNumber.nextInt();
            hash = calculateHash(magicNumber);
        } while (!hash.substring(0, zerosNumber).equals(zeros));
        long resultTime = new Date().getTime() - startTime;
        Block block = Block.builder()
                .id(atomicInteger.incrementAndGet())
                .timeStamp(new Date().getTime())
                .magicNumber(magicNumber)
                .blockHash(hash)
                .timeGenerating(resultTime)
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


}
