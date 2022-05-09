package com.gamelib.Logic.Structures;

import java.io.Serializable;

public class Queue<T> implements Serializable {
    private LinkedListSimple<T> listaEnlaz;

    public Queue(){
        listaEnlaz = new LinkedListSimple<T>();
    }

    //Methods
    public void enqueue(T newObj){
        listaEnlaz.addFinal(newObj);
    }
    public T dequeue(){
        return listaEnlaz.removeInicio();
    }
    public boolean isEmpty(){
        return listaEnlaz.isEmpty();
    }
    public void print(){
        listaEnlaz.printLista();
    }

}
