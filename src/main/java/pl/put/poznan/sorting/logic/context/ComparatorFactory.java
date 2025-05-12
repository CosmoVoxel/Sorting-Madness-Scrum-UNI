package pl.put.poznan.sorting.logic.context;

import java.util.Comparator;
import java.util.Map;
/**
 * Factory do tworzenia Comparatora dla obiektu jakiejś klasy (W postaci mapy) według wybranego atrybutu.
 */
public class ComparatorFactory {
    /**
     * Metoda do tworzenia komparatora według podanej nazwy atrybutu
     * @param attributeName nazwa atrybutu według którego będą porównywane obiekty
     * @return metoda porównania obiektów
     * @throws IllegalArgumentException jeśli nie można porównać typu argumentów lub atrybut nie istnieje
     */
    public static Comparator<Map<String, Object>> createComparator(String attributeName) {
        return (o1, o2) -> {
            try {
                Object value1 = o1.get(attributeName);
                Object value2 = o2.get(attributeName);

                if (value1 == null || value2 == null) {
                    return 0; // Jeśli którekolwiek z pól jest null, uznaj porównanie jako neutralne
                }

                if (value1 instanceof Comparable && value2 instanceof Comparable) {
                    return ((Comparable) value1).compareTo(value2);
                }

                throw new IllegalArgumentException(
                        "Nieprawidłowe typy danych do porównania: " + value1 + ", " + value2
                );
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "Błąd podczas porównywania danych z atrybutu: " + attributeName, e
                );
            }
        };
    }
}
