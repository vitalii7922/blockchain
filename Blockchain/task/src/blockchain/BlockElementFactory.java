package blockchain;


import java.time.LocalTime;

public interface BlockElementFactory {
    public Block makeBlock(int id, long timeStamp, int magicNumber, String currentBlockHash,
                           long timeGenerating);
}
