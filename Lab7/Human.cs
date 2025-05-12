using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab1
{
	public enum Gender {
		Male,
		Female,
		Other
	}
	public class Human {
		public string Name { get; set; }
		public Gender Gender { get; set; }
		public int Age { get; set; }
		
		public HashSet<Human> humans { get; set; }

		public Human() { 
			Name = String.Empty;
			humans = new HashSet<Human>(); 
		}

		public Human(string name, Gender gender, int age)
			: this()
		{
			Name = name;
			Gender = gender;
			Age = age;
		}

		public bool AddChild(Human child)
		{
			return humans.Add(child);
		}

		public override string ToString() {
			return $"Name: {this.Name}\nAge: {this.Age}\nGender: {this.Gender}";
		}
		public string ChildrenToString()
        {
            StringBuilder sb = new StringBuilder();
            foreach (var child in humans)
            {
                sb.Append(child.ToString());
            }
            return sb.ToString();
        }

    }

}
