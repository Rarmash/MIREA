#include <iostream>

using namespace std;

// Проверка на правильную комбинацию
bool Checking(int* balls, int countBalls)
{
    for (int i = 0; i < countBalls; ++i)
    {
        // Если хотя бы одно число сходится с номером в массиве, то комбинация подходит
        if (balls[i] == (i + 1))
        {
            return true;
        }
    }
    return false;
}

// Перестановка
void Swap(int* balls, int firstIndex, int secondIndex)
{
    int buffer = balls[firstIndex];
    balls[firstIndex] = balls[secondIndex];
    balls[secondIndex] = buffer;
}

bool Searching(int* balls, int countBalls)
{
    // минус 2, т.к 2 последних будут менятся между собой
    int firstIndex = countBalls - 2;
    // Нужно для определения смены определенного(старшего) разряда по прошлой комбинации,
    // Т.е после 123 и 132 больше комбинацей с 1 нет, значит меняем её с двойкой и т.д
    // 231 и 213 -> в 321 и 312
    // Если firstIndex == -1, то это была последняя комбинация, тк 123 -> 321
    while (firstIndex != -1 && balls[firstIndex] >= balls[firstIndex + 1])
    {
        firstIndex--;
    }

    // Если мы достигли последней комбинации, пройдя все комбинации, то больше комбинаций нет
    if (firstIndex == -1)
    {
        return false;
    }

    int secondIndex = countBalls - 1;
    // Нужно для замены на следующее число, т.е 123 -> 231 -> 321, при firstIndex = 0
    while (balls[firstIndex] >= balls[secondIndex])
    {
        secondIndex--;
    }

    // Генерируем новую комбинацию
    Swap(balls, firstIndex, secondIndex);
    // Переменные для смен остальных разрядов комбинации, так 1234 -> 1243 -> 1324 -> 1342 и т.д 
    int dopFirst = firstIndex + 1;
    int dopSecond = countBalls - 1;

    // Если еще не менялись отсальные разряды, то меняем их
    while (dopFirst < dopSecond)
    {
        // Меняем на следющую комбинацию, в конце приходим к исходной
        // Т.е 321 -> 312 -> 321 (сначала все, а потом к исходной)
        Swap(balls, dopFirst++, dopSecond--);
    }
    return true;
}

int main()
{
    setlocale(LC_ALL, "Russian");
    // Изначальная комбинация уже входит в счетчик
    int count = 1;
    int countBalls;
    cout << "Введите число шариков: "; cin >> countBalls;
    int* balls = new int[countBalls];

    // Заполняем массив с шариками
    for (int i = 1; i < (countBalls + 1); i++)
    {
        balls[i - 1] = i;
    }

    // Пока находит
    while (Searching(balls, countBalls))
    {
        // Проверка на правильную комбинацию
        if (Checking(balls, countBalls))
        {
            count++;
        }
    }

    cout << "Количество подходящих ситуаций: " << count << endl;
    return 0;
}