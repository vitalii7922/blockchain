package blockchain;

import java.util.Date;
import java.util.LinkedList;

public class BlockChain {

    private final LinkedList<Block> chain = new LinkedList<>();

    public BlockChain(int size) {
        generateChain(size);
    }

    private void generateBlock() {
        Date date = new Date();
        Block block = new Block();
        block.setId(chain.size() + 1);
        String sha256 = StringUtil.applySha256(date.toString());
        block.setBlockHash(sha256);
        block.setTimeStamp(date.getTime());
        block.setPreviousBlockHash(chain.isEmpty() ? "0" : chain.getLast().getBlockHash());
        chain.add(block);
        //change this block
    }

    private void generateChain(int size) {
        for (int i = 0; i < size; i++) {
            generateBlock();
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

    public LinkedList<Block> getChain() {
        return chain;
    }
}
