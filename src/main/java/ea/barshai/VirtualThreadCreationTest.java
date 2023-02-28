package ea.barshai;

// 4 - how to create Virtual Thread
public class VirtualThreadCreationTest {

  public static void main(String[] args) throws InterruptedException {
    Runnable runnable = () -> System.out.println("Inside Runnable");

    // Thread.ofVirtual()
    Thread virtualThread = Thread.ofVirtual().start(runnable);

    // or Thread.Builder
    Thread.Builder builder1 = Thread.ofVirtual().name("JVM-Thread");

    Thread t1 = builder1.start(runnable);
    Thread t2 = builder1.start(runnable);

    //
    Thread.Builder builder2 = Thread.ofPlatform().name("Platform-Thread");

    Thread t3 = builder2.start(runnable);
    Thread t4 = builder2.start(runnable);
  }
}