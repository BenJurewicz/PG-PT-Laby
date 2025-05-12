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
        private static Random random = new();
        public static HashSet<Human> Generate(uint depth)
		{
            // TODO: Implement it similarly to Lab1
            // https://git.pg.edu.pl/p1473366/pt_d7_g7a_j_jurewicz_kociszewszka_zawrzykraj/-/blob/10af7336c5a23a8c16099ca04ecf53c185d7e861/Lab1/src/main/java/jkz/Lab1.java
            var humans = new HashSet<Human>();
            var generations = new List<List<Human>>();

            const int MaxAge = 120;
            const int MinAge = 0;

            double chunk = (MaxAge - MinAge) / (double)depth;

            for (int gen = 0; gen < depth; gen++)
            {
                var thisGen = new List<Human>();

                double high = MaxAge - chunk * gen;
                double low = high - chunk;

                int genCount = (gen == 0) ? random.Next((int)depth, (int)depth * 4 + 1) : (int)depth;

                for (int i = 0; i < genCount; i++)
                {
                    int age = (int)(low + random.NextDouble() * (high - low));

                    // cycle gender m/f/o
                    Gender gender = (Gender)(i % 3);

                    Human human = new Human($"Gen{gen}_{i}", gender, age);
                    thisGen.Add(human);
                    if (gen == 0) humans.Add(human);


                }

                // parents
                if (gen > 0)
                {
                    var parents = generations[gen - 1];
                    foreach (var child in thisGen)
                    {
                        Human parent = parents[random.Next(parents.Count)];
                        parent.AddChild(child);
                    }
                }

                generations.Add(thisGen);
            }

            return humans;
		}
	}
}
