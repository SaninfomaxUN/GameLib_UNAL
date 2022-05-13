package com.gamelib.Logic.Structures;

public class Stack<T> {
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
