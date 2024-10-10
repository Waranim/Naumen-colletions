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
     * Я выбрал LinkedHasHMap, т.к. коллекция хранит данные в ввиде ключ:значение.
     * Обращение по ключу имеет сложность О(1) при хорошей хэш-функции, поэтому увиличение счётсчика будет за О(1).
     * А также LinkedHashMap итерируется лучше HashMap, что необходимо в этой задаче при сортировке
     * Общая сложность: О(n + nlogn)
     */
    public static void main(String[] args) {
        Map<String, Integer> counter = new LinkedHashMap<>();
        new WordParser(WAR_AND_PEACE_FILE_PATH)
                .forEachWord(word -> {
                    if (counter.containsKey(word)) {
                        counter.put(word, counter.get(word) + 1);
                    } else {
                        counter.put(word, 1);
                    }
                });
        List<Map.Entry<String, Integer>> top = counter
                                                    .entrySet()
                                                    .stream()
                                                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                                                    .toList();

        System.out.println("TOP 10 наиболее используемых слов:");
        for (int i = 0; i < 10; i++) {
            var entry = top.get(i);
            System.out.println(entry.getKey() + ": " + entry.getValue() + " раз(а)");
        }
        System.out.println("\nLAST 10 наименее используемых:");
        for (int i = top.size() - 1; i > top.size() - 11; i--) {
            var entry = top.get(i);
            System.out.println(entry.getKey() + ": " + entry.getValue() + " раз(а)");
        }
    }
}
