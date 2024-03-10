#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>
#include <limits>
using namespace std;

// Структура для представления графа
struct Graph {
	int neighbor; // соседние вершины
	int thisTop; // текущая вершина
	int weight; // Вес ребра
};

// Функция для добавления ребра в граф
void addEdge(vector<Graph>& graph, int thisTop, int neighbor, int weight) {
	Graph edge;
	edge.thisTop = thisTop;
	edge.neighbor = neighbor;
	edge.weight = weight;
	graph.push_back(edge);
}
// Функция для вывода графа
void printGraph(const vector<Graph>& graph) {
	for (int i = 0; i < graph.size(); ++i) {
		cout << "Список смежных вершин для вершины " << graph[i].thisTop << ": ";
		for (const auto& edge : graph) {
			if (edge.thisTop == graph[i].thisTop) {
				cout << edge.neighbor << "(" << edge.weight << ") ";
			}
		}
		cout << "\n";
		// Пропуск оставшихся рёбер для той же вершины
		while (i + 1 < graph.size() && graph[i].thisTop == graph[i + 1].thisTop) {
			i++;
		}
	}
}

// Функция для определения степени вершины графа
int degreeOfVertex(const vector<Graph>& graph, int vertex) {
	int degree = 0;
	for (const auto& edge : graph) {
		if (edge.thisTop == vertex || edge.neighbor == vertex) {
			degree++;
		}
	}
	return degree;
}

// Функция для нахождения кратчайшего пути методом Дейкстры
static void shortestPathDijkstra(vector<Graph>& graph, int startVertex, int endVertex) {
	const int INF = std::numeric_limits<int>::max(); // Бесконечность
	int numVertices = 0;
	for (const auto& edge : graph) {
		numVertices = max(numVertices, max(edge.thisTop, edge.neighbor));
	}
	numVertices++; // Число вершин в графе

	vector<int> distance(numVertices, INF); // Массив расстояний до каждой вершины
	vector<int> previous(numVertices, -1); // Массив предыдущих вершин

	distance[startVertex] = 0; // Расстояние от начальной вершины до самой себя равно 0

	vector<bool> visited(numVertices, false); // Посещенные вершины

	while (true) {
		int closestVertex = -1;
		for (int i = 0; i < numVertices; ++i) {
			if (!visited[i] && (closestVertex == -1 || distance[i] < distance[closestVertex])) {
				closestVertex = i;
			}
		}

		if (closestVertex == -1 || closestVertex == endVertex) {
			break; // Дошли до конечной вершины или все вершины посещены
		}

		visited[closestVertex] = true;

		for (const auto& edge : graph) {
			if (edge.thisTop == closestVertex) {
				int to = edge.neighbor;
				int weight = edge.weight;
				if (distance[closestVertex] + weight < distance[to]) {
					distance[to] = distance[closestVertex] + weight;
					previous[to] = closestVertex;
				}
			}
		}
	}

	if (distance[endVertex] == INF) {
		cout << "Нет пути от вершины " << startVertex << " к вершине " << endVertex << "\n";
		return;
	}

	// Восстановление пути
	vector<int> path;
	for (int at = endVertex; at != -1; at = previous[at]) {
		path.push_back(at);
	}

	reverse(path.begin(), path.end());

	cout << "Кратчайший путь от вершины " << startVertex << " к вершине " << endVertex << ": ";
	for (int vertex : path) {
		cout << vertex << " ";
	}
	cout << "\n";
}

int main() {
	setlocale(LC_ALL, "Russian");
	locale::global(std::locale("en_US.UTF-8"));
	// Пример использования
	vector<Graph> graph;
	// Добавление ребер
	addEdge(graph, 1, 2, 2);
	addEdge(graph, 1, 3, 4);
	addEdge(graph, 1, 4, 5);
	addEdge(graph, 2, 4, 4);
	addEdge(graph, 2, 5, 3);
	addEdge(graph, 3, 6, 7);
	addEdge(graph, 4, 6, 4);
	addEdge(graph, 4, 5, 6);
	addEdge(graph, 5, 6, 2);

	// Вывод содержимого графа
	printGraph(graph);

	int vertex;
	cout << "Введите вершину для определения степени: ";
	cin >> vertex;
	cout << "Степень данной вершины - " << degreeOfVertex(graph, vertex) << endl;
	
	int source, destination, maxWeight;
	cout << "Введите начальную вершину и конечную вершину: ";
	cin >> source >> destination;

	shortestPathDijkstra(graph, source, destination);
	return 0;
}