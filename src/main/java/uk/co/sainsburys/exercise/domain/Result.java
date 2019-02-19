package uk.co.sainsburys.exercise.domain;

import java.util.List;

public class Result {

    private List<Product> results;
    private Total total;

    public Result(List<Product> results, Total total) {
        this.results = results;
        this.total = total;
    }

    public List<Product> getResults() {
        return results;
    }

    public Total getTotal() {
        return total;
    }
}
