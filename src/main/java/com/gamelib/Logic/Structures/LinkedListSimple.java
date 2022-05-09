package com.gamelib.Logic.Structures;

import java.io.Serializable;

public class LinkedListSimple<T> implements Serializable {
    private Nodo primerNodo;
    private int numNodos;
    private Nodo ultimoNodo;
    public LinkedListSimple(){
        primerNodo = null;
        numNodos = 0;
    }

    public void addInicio(T elemento){
        if (primerNodo.getSig()==null){
            ultimoNodo = primerNodo;
        }
        Nodo nuevo = new Nodo (elemento, primerNodo);
        primerNodo = nuevo;
        numNodos++;
    }

    public void addFinal(T elemento){
        Nodo nuevo = new Nodo(elemento, null);
        if (primerNodo == null){
            primerNodo = nuevo;
        }else{
            ultimoNodo.setSig(nuevo);
        }

        /*else {
            Nodo actual = primerNodo;
            while (actual.getSig()!= null){
                actual = actual.getSig();

            }
            actual.setSig(nuevo);
            numNodos++;
        }*/
        ultimoNodo = nuevo;
        numNodos++;
        //System.out.println("Final");
    }
    public T remove(T elementoBorrar){
        T coincidencia = null;
        if (primerNodo == null)
            System.out.println("Lista enlazada vacía");

        else if (primerNodo.getElemento().equals(elementoBorrar)){
            primerNodo = primerNodo.getSig();
            numNodos--;
        }
        else {
            Nodo actual = primerNodo;
            while (actual.getSig()!=null && !actual.getSig().getElemento().equals(elementoBorrar)){
                actual = actual.getSig();
            }


            if (actual.getSig()== null ){
                System.out.println ("Elemento "+elementoBorrar+" no esta en la lista");
            }
            else{
                coincidencia = (T) actual.getSig().getElemento();
                actual.setSig(actual.getSig().getSig());
                numNodos--;
            }
        }
        return coincidencia;
    }
    public T removeInicio(){
        T primerObj = (T) primerNodo.getElemento();
        primerNodo = primerNodo.getSig();
        numNodos--;
        if(primerNodo==null){
            ultimoNodo=null;
        }
        return primerObj;
    }
    public T getInicio(){
        return (T) primerNodo.getElemento();
    }
    public boolean isEmpty(){
        if (primerNodo==null){
            return true;
        }else {
            return false;
        }
    }

    public int getLength(){
        return numNodos;
    }

    public void printLista(){
        Nodo actual = primerNodo;
        while (actual.getSig()!= null){
            System.out.println(actual.getElemento());

            actual = actual.getSig();

        }
        System.out.println(actual.getElemento());
    }
}

class Nodo<T> implements Serializable{
    private T elemento;
    private Nodo siguiente;

    public Nodo (T elem, Nodo sig){
        elemento = elem;
        siguiente = sig;
    }
    public T getElemento(){
        return elemento;
    }
    public Nodo getSig(){
        return siguiente;
    }
    public void setElemento(T elem){
        elemento = elem;
    }
    public void setSig(Nodo sig){
        siguiente = sig;
    }

}
