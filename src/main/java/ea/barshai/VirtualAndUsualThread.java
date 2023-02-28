package ea.barshai;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class VirtualAndUsualThread {
  @Benchmark
  public void testCreateVirtualThread(Blackhole blackhole) {
    for (int i=0; i<100; ++i) {
      int finalI = i;
      Thread.startVirtualThread(() -> blackhole.consume(finalI));
    }
  }

  @Benchmark
  public void testCreateThread(Blackhole blackhole) {
    for (int i = 0; i < 1000; ++i) {
      int finalI = i;
      var thread = new Thread(() -> blackhole.consume(finalI));
      thread.start();
    }
  }
}
