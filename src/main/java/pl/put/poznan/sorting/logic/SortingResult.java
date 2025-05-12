package pl.put.poznan.sorting.logic;

import lombok.NoArgsConstructor;

/**
 * Klasa będąca strukturą do przechowywania wyników sortowania
 *
 * @param <T> Typ elementów
 */

public class SortingResult<T> {
    /**
     * Nazwa użytej strategii sortowania.
     */
    private String algorithm;

    /**
     * Posortowane dane.
     */
    private T[] sortedData;

    /**
     * Czas wykonania operacji sortowania.
     */
    private long executionTimeNs;

    /**
     * Konstruktor pełny z parametrami.
     *
     * @param algorithm       Nazwa użytej strategii sortowania.
     * @param sortedData      Posortowane dane.
     * @param executionTimeNs Czas wykonania operacji sortowania.
     */
    public SortingResult(String algorithm, T[] sortedData, long executionTimeNs) {
        this.algorithm = algorithm;
        this.sortedData = sortedData;
        this.executionTimeNs = executionTimeNs;
    }

    public SortingResult() {
    }

    // Gettery
    public String getAlgorithm() {
        return algorithm;
    }

    public T[] getSortedData() {
        return sortedData;
    }

    public long getExecutionTimeNs() {
        return executionTimeNs;
    }

    // Settery
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setSortedData(T[] sortedData) {
        this.sortedData = sortedData;
    }

    public void setExecutionTimeNs(long executionTimeNs) {
        this.executionTimeNs = executionTimeNs;
    }
}
