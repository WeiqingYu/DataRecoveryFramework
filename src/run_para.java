import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class run_para {
    public static ReadData readdat = new ReadData("192.168.2.1:5433/test_dl","postgres","root","power_4yr");

    public static void main(String[] args) {
        readdat.read();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i<10; i++){
            executor.execute(new DataMatrix(readdat.data,i*10000,(i+1)*10000));
        }

        System.out.println("Starting shutdown...");
        executor.shutdown();

        try {
            executor.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            System.out.println("Interrupted...");
        }

        System.out.println("All done!");

    }
}
