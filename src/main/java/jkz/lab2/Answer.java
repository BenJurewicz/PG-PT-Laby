package jkz.lab2;

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
        output.append("Number: ").append(number).append("\t");
        output.append("Divisors: ");
        for (int i = 0; i < numbers.size(); i++) {
            output.append(numbers.get(i));
            if (i < numbers.size() - 1) {
                output.append(", ");
            }
        }
        output.append("\n");
        return output.toString();
    }
}
