package blockchain;


public class Block {
    private long id;
    private long timeStamp;
    private String previousBlockHash;
    private String blockHash;
    private int magicNumber;
    private long timeGenerating;

    public Block(long id, long timeStamp, String blockHash, int magicNumber, long timeGenerating) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.blockHash = blockHash;
        this.magicNumber = magicNumber;
        this.timeGenerating = timeGenerating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    public void setPreviousBlockHash(String previousBlockHash) {
        this.previousBlockHash = previousBlockHash;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    public long getTimeGenerating() {
        return timeGenerating;
    }

    public void setTimeGenerating(long timeGenerating) {
        this.timeGenerating = timeGenerating;
    }


    @Override
    public String toString() {
        return String.format("Block:\n" +
                "Id: %d\n" +
                "Timestamp: %d\n" +
                "Magic number: %d\n" +
                "Hash of the previous block:\n%s\n" +
                "Hash of the block:\n%s\n" +
                "Block was generating for %d seconds\n", id, timeStamp, magicNumber, previousBlockHash, blockHash,
                timeGenerating);


    }
}
