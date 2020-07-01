package blockchain;


public class BlockFactory implements BlockElementFactory {


    @Override
    public Block makeBlock(int id, long timeStamp, int magicNumber, String currentBlockHash, long timeGenerating) {
        return new Block(id, timeStamp, currentBlockHash, magicNumber, timeGenerating);
    }
}
