package com.xaviwang.demo.bean.benchmark;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import com.xaviwang.demo.bean.SixtyTwoBitsIdTransformer;
import com.xaviwang.demo.bean.Transformer;

@State(Scope.Thread)
public class SixtyTwoBitsIdTransformerReadBenchmarkTest {

	public static void main(String[] args) throws Exception {
		Options opt = new OptionsBuilder().include(SixtyTwoBitsIdTransformerReadBenchmarkTest.class.getSimpleName())
				.forks(1)
				.build();
		new Runner(opt).run();
	}
	
	private Transformer sixtyTwoBitsIdIdTransformer;
	private String existedKey;
	private String notExistedKey;
	
	@Setup
	public void init() {
		this.sixtyTwoBitsIdIdTransformer = new SixtyTwoBitsIdTransformer();
		this.existedKey = sixtyTwoBitsIdIdTransformer.transformFromOriginalUrlToTinyUrl("www.test.com");
		this.notExistedKey = "NOT_EXIST";
	}
	
	@TearDown
	public void down() {
		
	}
	
	@Benchmark
	//@BenchmarkMode(Mode.AverageTime)
	@BenchmarkMode(Mode.AverageTime)
	@Warmup(iterations = 3)
	@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.NANOSECONDS)
	@Threads(1)
	@Fork(1)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	public void testHit() throws InterruptedException {
		this.sixtyTwoBitsIdIdTransformer.transformFromTinyUrlToOriginalUrl(this.existedKey);
	}
	
	@Benchmark
	//@BenchmarkMode(Mode.AverageTime)
	@BenchmarkMode(Mode.AverageTime)
	@Warmup(iterations = 3)
	@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.NANOSECONDS)
	@Threads(1)
	@Fork(1)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	public void testNotHit() throws InterruptedException {
		this.sixtyTwoBitsIdIdTransformer.transformFromTinyUrlToOriginalUrl(this.notExistedKey);
	}

}
