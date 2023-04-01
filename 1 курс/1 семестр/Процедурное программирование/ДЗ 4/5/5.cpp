#include <Windows.h>
#include <stdlib.h>
#include <math.h>
#include <iostream>

int main(void)
{
    HWND hWnd = GetConsoleWindow();
    HDC hDC = GetDC(hWnd);
    HPEN Pen = CreatePen(PS_SOLID, 2, RGB(255, 255, 255));//цвет осей
    SelectObject(hDC, Pen);
    MoveToEx(hDC, 0, 200, NULL);
    LineTo(hDC, 1000, 200);
    MoveToEx(hDC, 500, 0, NULL);
    LineTo(hDC, 500, 400);
    Pen = CreatePen(PS_SOLID, 2, RGB(255, 255, 255));//цвет графика
    SelectObject(hDC, Pen);
    for (float x = -74.0f; x <= 75.0f; x += 0.01f) //начало и конец графика
    {
        MoveToEx(hDC, 10 * x + 250, -10 * sin(x) + 200, NULL); //период/амплитуда/координата по y
        LineTo(hDC, 10 * x + 250, -10 * sin(x) + 200);
    }
    ReleaseDC(hWnd, hDC);
    while (true) {
        Sleep(0);
    }
}