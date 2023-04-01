#include <iostream>
using namespace std;

struct tnode // Структура - узел дерева
{
    double field;           // поле данных
    struct tnode* left;  // левый потомок
    struct tnode* right; // правый потомок
};

void treeprint(tnode* tree) // Вывод узлов дерева (обход в инфиксной форме)
{
    if (tree != NULL) {      //Пока не встретится пустой узел
        treeprint(tree->left);  //Рекурсивная функция вывода левого поддерева
        cout << tree->field << " "; //Отображаем корень дерева
        treeprint(tree->right); //Рекурсивная функция вывода правого поддерева
    }
}

struct tnode* addnode(double x, tnode* tree) { // Добавление узлов в дерево
    if (tree == NULL)     // Если дерева нет, то формируем корень
    {
        tree = new tnode; //память под узел
        tree->field = x;   //поле данных
        tree->left = NULL;
        tree->right = NULL; //ветви инициализируем пустотой
    }
    else     // иначе
        if (x < tree->field)   //Если элемент x меньше корневого, уходим влево
            tree->left = addnode(x, tree->left); //Рекурсивно добавляем элемент
        else  //иначе уходим вправо
            tree->right = addnode(x, tree->right); //Рекурсивно добавляем элемент
    return(tree);
}

void freemem(tnode* tree) //Освобождение памяти дерева
{
    if (tree != NULL)    // если дерево не пустое
    {
        freemem(tree->left);   // рекурсивно удаляем левую ветку
        freemem(tree->right);  // рекурсивно удаляем правую ветку
        delete tree;           // удаляем корень
    }
}

int main() // Тестирование работы
{
    setlocale(LC_ALL, "Russian");
    struct tnode* root = 0;    // Объявляем структуру дерева
    system("cls");
    double a;            // текущее значение узла
    
    for (int i = 0; i < 8; i++) // В цикле вводим 8 узлов дерева
    {
        cout << "Введите узел " << i + 1 << ": ";
        cin >> a;
        root = addnode(a, root); // размещаем введенный узел на дереве
    }
    treeprint(root);    // выводим элементы дерева, получаем отсортированный массив
    freemem(root);      // удаляем выделенную память
    cin.get();  cin.get();
    return 0;
}
