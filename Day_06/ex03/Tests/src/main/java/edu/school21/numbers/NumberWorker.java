package edu.school21.numbers;

public class NumberWorker {
	public boolean isPrime(int number) {
		long i = 2;

		if (number < 2) {
			throw new IllegalNumberException();
		}
		while (i * i <= number) {
			if (number % i == 0) {
				return false;
			}
		i++;
	}
	return true;
}

	public int digitsSum(int number) {
		int sum = 0;

		while (number != 0) {
			sum += number % 10;
			number /= 10;
		}
		return sum;
	}

	class IllegalNumberException extends RuntimeException {
		public IllegalNumberException() {
			super("Error: number < 2");
		}
	}
}