package blockchain;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Miner {
    private BlockFactory blockFactory;
    private static final int POOL_SIZE = Runtime.getRuntime().availableProcessors();

    void setFactory(BlockFactory blockFactory) {
        this.blockFactory = blockFactory;
    }

    void mine() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE);
        executor.submit(() -> blockFactory.run());
        executor.awaitTermination(10, TimeUnit.SECONDS);
        executor.shutdown();
        /*Thread thread1 = new Thread(blockFactory, "thread1");
        Thread thread2 = new Thread(blockFactory, "thread2");
        thread1.start();
        thread2.start();*/
    }
}
