package com.example.loocale;

import java.util.List;

public interface IAdapter<T> {
    List<T> getDisplayItems();
    void refresh();
}
