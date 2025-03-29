package jkz.lab1;

import java.util.*;

public class Lab1 {
	static int populationCount = 20;

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("No arguments provided");
			return;
		}
		String argument = args[0];
		Human.Sort sort = Human.Sort.NONE;
		Set<Human> humans;
		if (argument.equals("None")) {
			sort = Human.Sort.NONE;
			humans = new HashSet<>();
		} else if (argument.equals("Default")) {
			sort = Human.Sort.DEFAULT;
			humans = new TreeSet<>();
		} else if (argument.equals("Alternative")) {
			sort = Human.Sort.ALTERNATIVE;
			humans = new TreeSet<>(new HumanAgeComparator());
		} else {
			System.out.println("Invalid argument");
			return;
		}

		fillSet(humans, populationCount);

		for (Human human : humans) {
			System.out.println(human.getHierarchyAsString());
		}
		Human.printStatistic(humans, sort, new HumanAgeComparator());
	}

	private static void fillSet(Set<Human> set, int numberOfHumans) {
		List<Human> firstGeneration = new ArrayList<>();
		List<Human> secondGeneration = new ArrayList<>();

		// Create first generation
		for (int i = 0; i < numberOfHumans / 3; i++) {
			int age = 60 + (int) (Math.random() * 21); // Age between 60-80
			char gender;
			if (i % 3 == 0) {
				gender = 'm';
			} else if (i % 3 == 1) {
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
			if (i % 3 == 0) {
				gender = 'm';
			} else if (i % 3 == 1) {
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
			if (i % 3 == 0) {
				gender = 'm';
			} else if (i % 3 == 1) {
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
}