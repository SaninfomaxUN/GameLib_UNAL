package com.gamelib.Logic.Structures;

import java.util.Arrays;

public class DynamicArray<T> {
    private int size;
    private int usedSpace;
    private T array[];

    public DynamicArray(){
        this.array = (T[]) new Object[4];
        this.usedSpace = 0;
        this.size = 2;
    }

    public void growSize(){
        if (usedSpace == size) {


            T temp[] = (T[]) new Object[size*2];

            for (int i = 0; i < size; i++) {
                temp[i] = array[i];
            }
            size = size*2;
            array = temp;
            System.out.println("the array has grown in size");
        }
    }

    public void add(T var){
        growSize();

        array[usedSpace] = var;
        usedSpace++;
    }

    public void add(T var, int index){
        growSize();
        T temp[] = (T[])new Object[size];

        for (int i = 0; i < usedSpace; i++){
            if ( i < index){
                temp[i] = array[i];
            }
            else if(i == index){
                temp[i] = var;
                temp[i+1] = array[i];
            }
            else {
                temp[i+1] = array[i];
            }
        }
        array = temp;
        usedSpace++;
    }

    public T delete(){
        T temp[] = (T[]) new Object[size];
        T temp2 = null;

        for (int i = 0; i < usedSpace; i++){
            if (i+1 == usedSpace) {
                temp2 = array[i];
            }else{
                temp[i] = array[i];
            }
        }
        array = temp;
        usedSpace--;
        return temp2;
    }

    public T delete(int index){
        T temp[] = (T[]) new Object[size];
        T temp2 = null;

        for (int i = 0; i < usedSpace; i++){
            if ( i < index){
                temp[i] = array[i];
            }
            else if(i == index){
                temp2 = array[i];
            }
            else {
                temp[i-1] = array[i];
            }
        }
        array = temp;
        usedSpace--;
        return temp2;
    }

    public void print(){
        T temp[] = (T[]) new Object[usedSpace];
        for (int i = 0; i < usedSpace; i++)
            temp[i]  = array[i];
        System.out.println(Arrays.toString(temp));
    }
}
