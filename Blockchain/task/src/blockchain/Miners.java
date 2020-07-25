package blockchain;

import java.io.IOException;
import java.util.concurrent.*;

class Miners {
    private BlockFactory blockFactory;
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    void setFactory(BlockFactory blockFactory) {
        this.blockFactory = blockFactory;
    }

    void mine() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                try {
                    blockFactory.generateChain();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.awaitTermination(1, TimeUnit.SECONDS);
        executor.shutdown();
    }
}
