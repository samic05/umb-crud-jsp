package com.umb.santiago.galvis.crud.dao.interfaces;

import java.util.List;

public interface Crud<T> {

    List<T> list();
    T findById(int id);
    boolean add(T toAdd);
    boolean edit(T toEdit);
    boolean delete(int id);
}
