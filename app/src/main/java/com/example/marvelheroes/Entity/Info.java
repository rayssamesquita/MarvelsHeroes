package com.example.marvelheroes.Entity;

import java.util.List;

public class Info {

    private List<Hero> results;
    private int offset;
    private int total;
    private int count;

    public void setResults(List<Hero> results){
        this.results = results;
    }

    public List<Hero> getResults(){
        return this.results;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
