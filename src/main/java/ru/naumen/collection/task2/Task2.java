package ru.naumen.collection.task2;

import java.util.*;

/**
 * Дано:
 * <pre>
 * public class User {
 *     private String username;
 *     private String email;
 *     private byte[] passwordHash;
 *     …
 * }
 * </pre>
 * Нужно реализовать метод
 * <pre>
 * public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB);
 * </pre>
 * <p>который возвращает дубликаты пользователей, которые есть в обеих коллекциях.</p>
 * <p>Одинаковыми считаем пользователей, у которых совпадают все 3 поля: username,
 * email, passwordHash. Дубликаты внутри коллекций collA, collB можно не учитывать.</p>
 * <p>Метод должен быть оптимален по производительности.</p>
 * <p>Пользоваться можно только стандартными классами Java SE.
 * Коллекции collA, collB изменять запрещено.</p>
 *
 * См. {@link User}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task2
{

    /**
     * Возвращает дубликаты пользователей, которые есть в обеих коллекциях
     * Я выбрал ArrayList для динамического добавления объектов, эта операция в среднем выполняется за О(1)
     * HashSet я выбрал из-за свойств Set. У множества отсутствуют дубликаты, а также contains выполняется при хорошей хеш-функции за О(1)
     * Общая сложность: О(n)
     */
    public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB) {
        List<User> result = new ArrayList<>(); // Именно эта коллекция, т.к. она позволяет легко динамически добавлять объекты
        Set<User> setA = new HashSet<>(collA); // Эту коллекция я выбрал из-за свойств
        for (User userB : collB) {
            if (setA.contains(userB)) {
                result.add(userB);
            }
        }
        return result;
    }
}
