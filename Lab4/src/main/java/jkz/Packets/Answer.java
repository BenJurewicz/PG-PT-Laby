package jkz.Packets;

import java.io.Serializable;
import java.util.List;

public record Answer(int SenderID, long number, List<Long> numbers) implements Serializable {

	@Override
	public String toString() {
		return "Number: " + number + "  " + "Divisor Count: " + numbers.size() + "  ";
	}
}
