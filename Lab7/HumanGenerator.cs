using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace Lab1
{
	internal class HumanGenerator
	{
		static string[] names = { "John", "Jane", "Alice", "Bob", "Charlie", "Diana" };
		static string[] surnames = { "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis" };
        static Random rand = new Random();

        public static HashSet<Human> Generate(uint depth)
		{
			// TODO: Implement it similarly to Lab1
			// https://git.pg.edu.pl/p1473366/pt_d7_g7a_j_jurewicz_kociszewszka_zawrzykraj/-/blob/10af7336c5a23a8c16099ca04ecf53c185d7e861/Lab1/src/main/java/jkz/Lab1.java
			HashSet<Human> humans = [];
			for (int i = 0; i < depth; i++)
			{
				Human h1 = new("Name", Gender.Other, i);
				humans.Add(h1);
				for (int j = 0; j < depth/2; j++)
				{
					Human h2 = new("Name2", Gender.Other, j);
					h1.AddChild(h2);
				}
			}
			return humans;
		}

		public static Human generateRandomHuman(Human parent)
		{
            string randomName = names[rand.Next(0, names.Length-1)];
			string randomSurname = surnames[rand.Next(0, surnames.Length - 1)];
			string fullName = randomName + " " + randomSurname;

			int randomAge = parent.Age + rand.Next(20,30);

            Array values = Enum.GetValues(typeof(Gender));

            int random_index = rand.Next(values.Length);

            Gender randomGender = (Gender)values.GetValue(random_index);

            Human child = new(fullName, randomGender, randomAge);

            parent.AddChild(child);

            return child;

        }


    }
}
