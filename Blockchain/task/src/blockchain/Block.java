package blockchain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
class Block {
    private int id;
    private long timeStamp;
    private String previousBlockHash;
    private String blockHash;
    private int magicNumber;
    private long timeGenerating;


    @Override
    public String toString() {
        return String.format("Block:\n" +
                        "Id: %d\n" +
                        "Timestamp: %d\n" +
                        "Magic number: %d\n" +
                        "Hash of the previous block:\n%s\n" +
                        "Hash of the block:\n%s\n" +
                        "Block was generating for %d seconds\n", id, timeStamp, magicNumber, previousBlockHash,
                blockHash, timeGenerating);
    }
}
