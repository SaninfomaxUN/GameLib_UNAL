package com.gamelib.Logic.Structures;

import java.io.Serializable;

public class Stack<T> implements Serializable {
    //--------------------Pila------------

    private LinkedListSimple<T> listaEnlaz;
    public Stack(){
        listaEnlaz = new LinkedListSimple<>();
    }

    //Methods
    public void push(T newObj){
        listaEnlaz.addInicio(newObj);
    }
    public T pop(){
        return listaEnlaz.removeInicio();
    }
    public T top(){
        return listaEnlaz.getInicio();
    }

    public boolean isEmpty(){
        return listaEnlaz.isEmpty();
    }

}
