package pl.put.poznan.sorting.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sorting.logic.algorithms.*;
import pl.put.poznan.sorting.logic.context.ComparatorFactory;
import pl.put.poznan.sorting.logic.context.SortContext;
import pl.put.poznan.sorting.rest.SortingMadnessController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Główna klasa do obsługi programu do sortowania danych
 *
 * @param <T>
 */
public class SortingMadness<T> {
    /**
     * Lista wyników dla poszczególnych algorytmów
     */

    private static final Logger logger = LoggerFactory.getLogger(SortingMadnessController.class);
    private final List<SortingResult<T>> result = new ArrayList<>();

    /**
     * Tablica wybranych algorytmów
     */
    private final String[] algoList;

    /**
     * Tablica elementów do posortowania
     */
    private final T[] data;

    public SortingMadness(T[] data, String[] algoList) {
        this(data, algoList, null, false,false);
    }

    /**
     * Konstruktor
     *
     * @param data          tablica elementów do posortowania
     * @param algoList      tablica wybranych do sortowania algorytmów
     * @param attributeName nazwa atrybutu według którego bedą sortowane obiekty, jesli data nie jest tablicą obiektów podajemy null
     * @throws IllegalArgumentException jeśli tablica wybranych algorytmów jest pusta
     */
    public SortingMadness(T[] data, String[] algoList, String attributeName, Boolean reverse,Boolean autoAlgorithm) {
        if (algoList == null || algoList.length == 0) {
            logger.error("Tablica wybranych algorytmów nie może być pusta");
            throw new IllegalArgumentException("Lista algorytmów nie może być pusta");
        }
        this.algoList = algoList;
        this.data = data;

        logger.info("Rozpoczęcie sortowania dla {} algorytmów", algoList.length);

        if (attributeName != null) {
            // Sortowanie obiektów przy pomocy stworzonego komparatora
            sortThrough((Comparator<T>) ComparatorFactory.createComparator(attributeName),reverse,autoAlgorithm);
        } else {
            sortThrough(null,reverse,autoAlgorithm);
        }
    }

    /**
     * Metoda do uruchomienia sortowania dla wybranych algorytmów
     *
     * @param comparator Utworzony komparator dla obiektów. Jeśli podano null to używany jest domyślny komparator (naturalOrder())
     */
    private void sortThrough(Comparator<T> comparator,boolean reverse,Boolean autoAlgorithm) {
        if (comparator == null) {
            logger.debug("Tworzenie komparatora in ({} order)", reverse ? "reverse" : "natural");
            if (!reverse) {
                comparator = (Comparator<T>) Comparator.naturalOrder();
            }
            else {
                comparator = (Comparator<T>) Comparator.reverseOrder();
            }
        }
        else {
            if (reverse) {
                comparator = (Comparator<T>) comparator.reversed();
            }
            logger.debug("Uzycie komparatora in ({} order)", reverse ? "reverse" : "natural");
        }

        //Posortuj z użyciem algorytmów z tablicy
        for (String algorithm : algoList) {
            logger.info("Rozpoczęcie sortowania przy użyciu algorytmu: {}", algorithm);
            SortContext<T> context = null;
            if (!autoAlgorithm) {
                context = createContext(algorithm);
            }
            else {
                context = autoContext();
            }
            context.sort(this.data, comparator);
            result.add(context.getResult());
            logger.info("Zakończono sortowanie przy użyciu algorytmu: {}", algorithm);
        }
    }

    /**
     * Metoda pomocniczna do wybrania odpowiedniej strategii na podstawie przekazanej nazwy
     *
     * @param algorithm nazwa algorytmu którego chcemy uzyć
     * @return kontekst używający wybranego algorytmu
     */
    private SortContext<T> createContext(String algorithm) {
        switch (algorithm) {
            case "BubbleSort":
                return new SortContext<>(new BubbleSort());
            case "InsertionSort":
                return new SortContext<>(new InsertSort());
            case "SelectionSort":
                return new SortContext<>(new SelectionSort());
            case "MergeSort":
                return new SortContext<>(new MergeSort());
            case "QuickSort":
                return new SortContext<>(new QuickSort());
            case "HeapSort":
                return new SortContext<>(new HeapSort());
            default:
                logger.error("Nieobsługiwany algorytm: {}", algorithm);
                throw new IllegalArgumentException("Unsupported algorithm: " + algorithm);
        }
    }

    //!TODO: More complex auto algorithm selection
    private SortContext<T> autoContext(){
        if (data.length < 1000) {
            return new SortContext<>(new InsertSort<>());
        }
        if (data.length < 100000) {
            return new SortContext<>(new QuickSort());
        }
        return new SortContext<>(new HeapSort());
    }

    public List<SortingResult<T>> getResult() {
        logger.debug("Zwracanie wyników sortowania ({} wyników)", result.size());
        return this.result;
    }
}
