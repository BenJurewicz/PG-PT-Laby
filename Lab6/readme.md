# TODO

## Setup
- [x] Dodać JUnit
- [x] Dodać AssertJ
- [x] Dodać Mockito
- [x] Napisać przykładowe użycie AssertJ

## Implementation
- [x] [Implement Entity Class](#dto-employee-from-lab5) \
    (`Employee` from `Lab5` without the `departament` field)
- [x] Mock implementation of `Repository` (using Mockito)
- [ ] [Implement `Repository`](#repository-details)
- [ ] [Implement `Controller`](#controller-details) \
    (should work with actual `Repository` implementation and mock implementation)
- [ ] [Write tests for `Repository`](#repository)
- [ ] [Write tests for `Controller`](#controller)

### Repository Details
Usunięcie i wyszukanie realizowane jest na podstawie przyjętego klucza głównego.
#### Methods:
- `Optional<Employee> find(Long id)`
- `void remove(Long id) throws IllegalArgumentException`
- `void save(Employee employee) throws IllegalArgumentException`
#### Notes:
- próba **usunięcia nieistniejącego** obiektu powoduje `IllegalArgumentException`,
- próba **pobrania nieistniejącego** obiektu zwraca  pusty obiekt `Optional`,
- próba **pobrania istniejącego** obiektu zwraca obiekt `Optional` z zawartością,
- próba **zapisania obiektu**, którego klucz główny już znajduje się w repozytorium
powoduje `IllegalArgumentException`.

### Controller Details
> Kontroler powinien korzystać z repozytorium dostarczonego przez wstrzykiwanie zależności.
  
In other words, controller should be able to switch between mock and actual
implementation of `Repository`, by taking a reference to it in the constructor.
 
#### Methods:
- `String remove(Long id)`
- `String find(Long id)`
- `String remove(Long id)`

#### Notes:
- próba **usunięcia istniejącego** obiektu powoduje zwrócenie obiektu **String** o wartości
`"done"`,
- próba **usunięcia nieistniejącego** obiektu powoduje zwrócenie obiektu **String** o wartości
`"not found"`,
- próba **pobrania nieistniejącego** obiektu powoduje zwrócenie obiektu **String** o wartości
`"not found"`,
- próba **pobrania istniejącego** obiektu zwraca obiekt **String reprezentujący znaleziony
obiekt encyjny**,
- próba **zapisania nowego obiektu** skutkuje wywołaniem metody z serwisu z poprawnym
parametrem i zwróceniem obiektu **String** o wartości `"done"`,
- próba **zapisania nowego obiektu**, którego **klucz główny znajduje się już w repozytorium**
powoduje zwrócenie obiektu **String** o wartości `"bad request"`.

### DTO (`Employee` from `Lab5`)
["Inspiration"](https://git.pg.edu.pl/p1473366/pt_d7_g7a_j_jurewicz_kociszewszka_zawrzykraj/-/blob/3d84a6ca697585bfc7324fabe0e05c19e402e0f7/Lab5/src/main/java/jkz/Database/Entities/Employee.java) \
Copy everything except the `departament` field.
Figure out how to generate unique IDs.

## Tests

### Repository
(Sourced from [Repository Details](#repository-details))
- próba **usunięcia nieistniejącego** obiektu powoduje `IllegalArgumentException`,
- próba **pobrania nieistniejącego** obiektu zwraca pusty obiekt `Optional`,
- próba **pobrania istniejącego** obiektu zwraca obiekt `Optional` z zawartością,
- próba **zapisania obiektu**, którego klucz główny już znajduje się w repozytorium
  powoduje `IllegalArgumentException`.

### Controller
Use mock `Repository` implementation. \
(Sourced from [Controller Details](#controller-details))
- próba **usunięcia istniejącego** obiektu powoduje zwrócenie obiektu **String** o wartości
  `"done"`,
- próba **usunięcia nieistniejącego** obiektu powoduje zwrócenie obiektu **String** o wartości
  `"not found"`,
- próba **pobrania nieistniejącego** obiektu powoduje zwrócenie obiektu **String** o wartości
  `"not found"`,
- próba **pobrania istniejącego** obiektu zwraca obiekt **String reprezentujący znaleziony
  obiekt encyjny**,
- próba **zapisania nowego obiektu** skutkuje wywołaniem metody z serwisu z poprawnym
  parametrem i zwróceniem obiektu **String** o wartości `"done"`,
- próba **zapisania nowego obiektu**, którego **klucz główny znajduje się już w repozytorium**
  powoduje zwrócenie obiektu **String** o wartości `"bad request"`.