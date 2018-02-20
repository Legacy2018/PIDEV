/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservices;

import java.util.List;

/**
 *
 * @author medma
 */
public interface IService<T, R> {

    void add(T t);
    
    void edit(T t);

    void delete(R r);

    T findById(R r);
// find by id annonceur 
    List<T> getAll();
}