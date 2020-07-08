package blockchain;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Miner {
    private BlockFactory blockFactory;
    private static final int POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private final ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE);

    void setFactory(BlockFactory blockFactory) {
        this.blockFactory = blockFactory;
    }

    void mine() throws InterruptedException, IOException {
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
