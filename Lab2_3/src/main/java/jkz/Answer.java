package jkz;

import java.util.List;

public class Answer {
	public long number;
	public List<Long> numbers;

	public Answer(long number, List<Long> numbers) {
		this.number = number;
		this.numbers = numbers;
	}

	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("Number: ").append(number).append("  ");
		output.append("Divisor Count: ").append(numbers.size()).append("  ");
		//		output.append("Divisors: ");
		//		for (int i = 0; i < numbers.size(); i++) {
		//			output.append(numbers.get(i));
		//			if (i < numbers.size() - 1) {
		//				output.append(", ");
		//			}
		//		}
		return output.toString();
	}
}
