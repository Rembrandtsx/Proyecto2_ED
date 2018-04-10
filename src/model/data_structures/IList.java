package model.data_structures;

import java.util.Iterator;

public interface IList<T extends Comparable<T>> extends Iterable<T>{

    void add(T elem);

    int size();

    T get(T elem);

}

