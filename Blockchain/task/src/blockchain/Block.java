package blockchain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
class Block implements Serializable {
    private int id;
    private String miner;
    private long timeStamp;
    private String previousBlockHash;
    private String blockHash;
    private int magicNumber;
    private long timeGenerating;
    private int zerosNumber;
    private String changedZerosNumber;


    @Override
    public String toString() {
        return String.format("Block:\n" +
                        "Created by miner # %s\n" +
                        "Id: %d\n" +
                        "Timestamp: %d\n" +
                        "Magic number: %d\n" +
                        "Hash of the previous block:\n%s\n" +
                        "Hash of the block:\n%s\n" +
                        "Block was generating for %d seconds\n" +
                        "%s\n", miner, id, timeStamp, magicNumber, previousBlockHash,
                blockHash, timeGenerating, changedZerosNumber);
    }
}
