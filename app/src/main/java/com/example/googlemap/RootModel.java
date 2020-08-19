package com.example.googlemap;

import java.util.List;

class RootModel {
    private List<Result> results;

    public RootModel(List<Result> results) {
        this.results = results;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
