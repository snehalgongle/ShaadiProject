package com.snake.shaadiproject.data;

public class Info {
    private String seed;

    private int results;

    private int page;

    private String version;

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public String getSeed() {
        return this.seed;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public int getResults() {
        return this.results;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return this.page;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return this.version;
    }
}
