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
	}
}
