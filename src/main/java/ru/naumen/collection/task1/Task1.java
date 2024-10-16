package ru.naumen.collection.task1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Дано:
 * <pre>
 * public class Ticket {
 *     private long id;
 *     private String client;
 *     …
 * }</pre>
 * <p>Разработать программу для бармена в холле огромного концертного зала.
 * Зрители в кассе покупают билет (класс Ticket), на котором указан идентификатор билета (id) и имя зрителя.
 * При этом, есть возможность докупить наборы разных товаров (комбо-обед): нет товаров, напитки, еда и напитки.
 * Доп. услуги оформляются через интернет и привязываются к билету, но хранятся отдельно от билета
 * (нельзя добавить товары в класс Ticket).</p>
 * <p>Бармен сканирует билет и получает объект Ticket. По этому объекту нужно уметь
 * находить необходимые товары по номеру билета. И делать это нужно очень быстро,
 * ведь нужно как можно быстрее всех накормить.</p>
 * <p>
 * См. {@link Ticket}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task1
{
    public enum Goods {
        /**
         * нет товаров
         */
        EMPTY,
        /**
         * напитки
         */
        DRINKS,
        /**
         * еда и напитки
         */
        FOOD_AND_DRINKS
    }

    /**
     * Я выбрал HashMap, т.к. получение по ключу имеет сложность О(1)
     * Equals и hashcode переопределены, поэтому получение по ключу будет О(1)
     *
     * Общая сложность О(1)
     */
    private Map<Ticket, Goods> orders = new HashMap<>();

    /**
     * Получить товары по билету
     * <p>Сложность алгоритма O(1)</p>
     *
     * <p><b>Мы не забыли определить equals и hashcode у класса {@link Ticket}</b></p>
     * <p>Достаточно их определить только для id, т.к. он уникален</p>
     */
    public Goods getGoods(Ticket ticket) {
        return orders.get(ticket);
    }
}
