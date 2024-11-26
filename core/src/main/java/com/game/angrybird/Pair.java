package com.game.angrybird;

import java.io.Serial;
import java.io.Serializable;

public  class Pair<T, U> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private T first;
    private U second;

    // Constructor
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    // Getters
    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    // Setters
    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(U second) {
        this.second = second;
    }
}

