package com.api.wallet.operation;

import com.api.wallet.db.entity.Transaction;

import java.util.List;

public interface CrudOperations<T>{
    T findById(String id);
    List<T> findAll();
    T save(T toSave);
    List<T> saveAll(List<T> toSave);
    T update(T toUpdate);
}
