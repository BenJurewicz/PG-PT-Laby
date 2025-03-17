package jkz;

import java.util.*;

public class App {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("No arguments provided");
			return;
		}
		String argument = args[0];
		Set<Human> humans;
		if (argument.equals("None")) {
			humans = new HashSet<>();
		} else if (argument.equals("Default")) {
			humans = new TreeSet<>();
		} else if (argument.equals("Alternative")) {
			humans = new TreeSet<>(new HumanAgeComparator());
		} else {
			System.out.println("Invalid argument");
			return;
		}

		int numberOfHumans = 6;
		fillSet(humans, numberOfHumans);
		printSet(humans);
	}

	private static void fillSet(Set<Human> set, int numberOfHumans) {
//		for (int i = 0; i < numberOfHumans; i++) {
//			int age;
//			if (i % 3 == 0) {
//				age = (int) (Math.random() * 21); // Age between 0-20
//			} else if (i % 3 == 1) {
//				age = 40 + (int) (Math.random() * 21); // Age between 40-60
//			} else {
//				age = 80 + (int) (Math.random() * 21); // Age between 80-100
//			}
//			Human human = new Human("Name" + i, (i % 2 == 0) ? 'M' : 'F', age );
//			set.add(human);
//		}
		List<Human> firstGeneration = new ArrayList<>();
		List<Human> secondGeneration = new ArrayList<>();

		// Create first generation
		for (int i = 0; i < numberOfHumans / 3; i++) {
			int age = 60 + (int) (Math.random() * 21); // Age between 60-80
			char gender;
			if(i%3==0){
				gender = 'm';
			} else if (i%3==1) {
				gender = 'f';
			} else {
				gender = 'o';
			}
			Human human = new Human("FirstGen" + i, gender, age);
			firstGeneration.add(human);
			set.add(human);
		}

		// Create second generation
		for (int i = 0; i < numberOfHumans / 3; i++) {
			int age = 30 + (int) (Math.random() * 21); // Age between 30-50
			char gender;
			if(i%3==0){
				gender = 'm';
			} else if (i%3==1) {
				gender = 'f';
			} else {
				gender = 'o';
			}
			Human human = new Human("SecondGen" + i, gender, age);
			secondGeneration.add(human);
			set.add(human);

			// Add to a random parent from the first generation
			Human parent = firstGeneration.get((int) (Math.random() * firstGeneration.size()));
			parent.addChild(human);
		}

		// Create third generation
		for (int i = 0; i < numberOfHumans / 3; i++) {
			int age = (int) (Math.random() * 21); // Age between 0-20
			char gender;
			if(i%3==0){
				gender = 'm';
			} else if (i%3==1) {
				gender = 'f';
			} else {
				gender = 'o';
			}
			Human human = new Human("ThirdGen" + i, gender, age);
			set.add(human);

			// Add to a random parent from the second generation
			Human parent = secondGeneration.get((int) (Math.random() * secondGeneration.size()));
			parent.addChild(human);
		}
	}

	private static void printSet(Set<Human> set) {
		for (Human human : set) {
			System.out.println(human);
		}
	}
}