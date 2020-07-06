package blockchain;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Miner {
    private BlockFactory blockFactory;
    private static final int POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static final ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE);

    void setFactory(BlockFactory blockFactory) {
        this.blockFactory = blockFactory;
    }

    void mine() {
        executor.submit(() -> blockFactory.run());
        executor.shutdown();
/*        Thread thread = new Thread(blockFactory);
        thread.start();
        blockFactory.printChain();*/
    }
}
