package ea.barshai;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/*@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 1)
@Measurement(iterations = 2)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)*/
public class BenchmarkThreading {

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(BenchmarkThreading.class.getSimpleName())
        .forks(1)
        .build();
    new Runner(opt).run();
  }

  //тут тесты
}