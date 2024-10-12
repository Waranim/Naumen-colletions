package ru.naumen.collection.task3;

import java.nio.file.Path;
import java.util.*;

/**
 * <p>Написать консольное приложение, которое принимает на вход произвольный текстовый файл в формате txt.
 * Нужно собрать все встречающийся слова и посчитать для каждого из них количество раз, сколько слово встретилось.
 * Морфологию не учитываем.</p>
 * <p>Вывести на экран наиболее используемые (TOP) 10 слов и наименее используемые (LAST) 10 слов</p>
 * <p>Проверить работу на романе Льва Толстого “Война и мир”</p>
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class WarAndPeace
{

    private static final Path WAR_AND_PEACE_FILE_PATH = Path.of("src/main/resources",
            "Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt");

    /**
     * Я выбрал HasHMap, т.к. коллекция хранит данные в виде ключ:значение.
     * Обращение по ключу имеет сложность О(1) при хорошей хэш-функции, поэтому увеличении счётчика будет за О(1).
     * PriorityQueue я выбрал из-за сложности O(log n) при удалении/добавлении элемента
     * А также эта коллекция позволяет избежать сортировки, которая бы имела сложность O(nlog n)
     * Общая сложность не будет превышать O(n), т.к. приоритетная очередь имеет фиксированный размер и все операции будут константными O(1).
     * Общая сложность: О(n)
     */
    public static void main(String[] args) {
        Map<String, Integer> counter = new HashMap<>();
        Queue<Map.Entry<String, Integer>> topWords = new PriorityQueue<>(10, Comparator.comparingInt(Map.Entry::getValue));
        Queue<Map.Entry<String, Integer>> rareWords = new PriorityQueue<>(10, (o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));
        new WordParser(WAR_AND_PEACE_FILE_PATH)
                .forEachWord(word -> { // O(n)
                    if (counter.containsKey(word)) {
                        counter.put(word, counter.get(word) + 1);
                    } else {
                        counter.put(word, 1);
                    }
                });

        for (Map.Entry<String, Integer> entry : counter.entrySet()) {
            if (topWords.size() < 10) {
                topWords.offer(entry);
            } else {
                Map.Entry<String, Integer> minEntry = topWords.peek();
                if (minEntry != null && entry.getValue() > minEntry.getValue()) {
                    topWords.poll();
                    topWords.offer(entry);
                }
            }

            if (rareWords.size() < 10) {
                rareWords.offer(entry);
            } else {
                Map.Entry<String, Integer> maxEntry = rareWords.peek();
                if (maxEntry != null && entry.getValue() < maxEntry.getValue()) {
                    rareWords.poll();
                    rareWords.offer(entry);
                }
            }
        }

        System.out.println("TOP 10 наиболее используемых слов:");
        topWords.forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " раз(а)")); // O(1)

        System.out.println("\nLAST 10 наименее используемых:");
        rareWords.forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " раз(а)")); // O(1)
    }
}
