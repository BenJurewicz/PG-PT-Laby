using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Reflection;
using System.Collections.ObjectModel;
using System.ComponentModel;

namespace Lab1
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window, INotifyPropertyChanged
    {
        public ObservableCollection<Human> humans { get; set; } = new ObservableCollection<Human>();

        public MainWindow()
        {
            InitializeComponent();
            DataContext = this;
            this.GenerateDataMenuItem_Click(this, null);
        }

        private void GenerateDataMenuItem_Click(object sender, RoutedEventArgs? e)
        {
            humans.Clear();
            HashSet<Human> humanSet = HumanGenerator.Generate(6);
            foreach (var human in humanSet)
            {
                humans.Add(human);
            }
            //MessageBox.Show($"Generated {humans.Count} humans.");
        }

        private void VersionMenuItem_Click(object sender, RoutedEventArgs e)
        {
            Version appVersion = Assembly.GetExecutingAssembly().GetName().Version!;
            MessageBox.Show($"Version menu item clicked\n The version is: {appVersion}");
        }

        private void ExitMenuItem_Click(object sender, RoutedEventArgs e)
        {
            Application.Current.Shutdown();
        }

        public event PropertyChangedEventHandler? PropertyChanged;
        protected void OnPropertyChanged(string propertyName)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}