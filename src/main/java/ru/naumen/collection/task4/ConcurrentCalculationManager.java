package ru.naumen.collection.task4;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * Класс управления расчётами
 */
public class ConcurrentCalculationManager<T> {
    /**
     * Коллекция выбрана в связи с возможностью работать в многопоточном режиме
     * А также из-за сложности О(1) при добавлении/удалении и упорядоченном хранении элементов как того требует условие задачи
     */
    Queue<Future<T>> queue = new ConcurrentLinkedQueue<>();

    /**
     * Добавить задачу на параллельное вычисление
     *
     * Общая сложность составит О(1)
     * Т.к. добавление задачи на выполнение и добавление в очередь имеет сложность О(1)
     */
    public void addTask(Supplier<T> task) {
        Future<T> future = CompletableFuture.supplyAsync(task);
        queue.add(future);
    }

    /**
     * Получить результат вычисления.
     * Возвращает результаты в том порядке, в котором добавлялись задачи.
     *
     * Общая сложность алгоритма зависит от задачи, которая будет выполняться.
     * Если учитывать только метод poll, то сложность алгоритма составит О(1)
     */
    public T getResult() {
        try {
            return queue.poll().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}