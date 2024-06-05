package com.rarmash.prac8;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {
    // Тег для логирования
    public final String TAG = "LOGGER_TAG";

    // Параметры рабочего
    public WorkerParameters workerParameters;

    // Конструктор класса
    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        // Вызываем конструктор суперкласса
        super(context, workerParams);

        // Сохраняем параметры рабочего
        workerParameters = workerParams;
    }

    // Метод, выполняющий работу рабочего
    @NonNull
    @Override
    public Result doWork() {
        // Выводим в лог сообщение о начале работы
        Log.v(TAG, "Work is in progress");

        try {
            // Имитируем работу рабочего с помощью остановки текущего потока на 3 секунды
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // Если произошло прерывание потока, выводим стек трейс ошибки в лог
            e.printStackTrace();
        }

        // Выводим в лог сообщение об окончании работы и ключ из входных данных
        Log.v(TAG, "Work finished with result " + workerParameters.getInputData().getString("key"));

        // Возвращаем результат успешного выполнения работы
        return Worker.Result.success();
    }
}