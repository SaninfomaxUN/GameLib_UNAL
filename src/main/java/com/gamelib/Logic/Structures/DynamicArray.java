package com.gamelib.Logic.Structures;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;

public class DynamicArray<T> implements Serializable {
    private int size;
    private int usedSpace;
    private T array[];

    private Class<T> classType;

    public DynamicArray(Class<T> classType_){
        this.usedSpace = 0;
        this.size = 2;
        this.classType=classType_;
        this.array = (T[])  Array.newInstance(classType, 4);
    }

    public void growSize(){
        if (usedSpace == size) {
            T temp[] = (T[]) Array.newInstance(classType, size*2);

            for (int i = 0; i < size; i++) {
                temp[i] = array[i];
            }
            size = size*2;
            array = temp;
            //System.out.println("the array has grown in size");
        }
    }

    public void add(T var){
        growSize();

        array[usedSpace] = var;
        usedSpace++;
    }

    public void add(T var, int index){
        growSize();
        T temp[] = (T[])Array.newInstance(classType, size);

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
        T temp[] = (T[]) Array.newInstance(classType, size);
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
        T temp[] = (T[]) Array.newInstance(classType, size);
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

    public T[] getArray() {
        return array;
    }

    public boolean isEmpty(){
        if (usedSpace==0){
            return true;
        }else{
            return false;
        }

    }

    public void print(){
        T temp[] = (T[]) Array.newInstance(classType, usedSpace);
        for (int i = 0; i < usedSpace; i++)
            temp[i]  = array[i];
        System.out.println(Arrays.toString(temp));
    }

    public int getLength(){
        return usedSpace;
    }
}
