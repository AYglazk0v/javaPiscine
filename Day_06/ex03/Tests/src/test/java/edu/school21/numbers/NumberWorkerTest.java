package edu.school21.numbers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {
	NumberWorker worker;

	@BeforeEach
	void beforeEachMethod() {
		worker = new NumberWorker();
	}

	@ParameterizedTest
	@ValueSource(ints = {2, 3, 5, 7, 11, Integer.MAX_VALUE})
	public void isPrimeForPrimes(int number) {
		assertTrue(worker.isPrime(number));
	}

	@ParameterizedTest
	@ValueSource(ints = {4, 6, 8, 9, 10, 12, 15})
	public void isPrimeForNotPrimes(int number) {
		assertFalse(worker.isPrime(number));
	}

	@ParameterizedTest
	@ValueSource(ints = {-10, 0, 1, -123, -1})
	public void isPrimeForIllegalNumber(int number) {
		assertThrows(NumberWorker.IllegalNumberException.class, () -> worker.isPrime(number));
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
	public void checkDigitsSum(int input, int expected) {
		assertEquals(expected, worker.digitsSum(input));
	}
}