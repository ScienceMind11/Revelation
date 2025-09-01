package com.sember.revelation.component.entity;

import org.ladysnake.cca.api.v3.component.Component;

import java.util.List;

public interface ListComponent<T> extends Component {

    T get(int index);
    void add(T t);
    void remove(T t);

    int size();

    List<T> getList();
    void setList(List<T> list);

}
