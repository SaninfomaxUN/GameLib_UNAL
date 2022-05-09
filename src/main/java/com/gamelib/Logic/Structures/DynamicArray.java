package com.gamelib.Logic.Structures;

import java.util.Arrays;

public class DynamicArray {
    private int size;
    private int usedSpace;
    private int array[];

    public DynamicArray(){
        this.array = new int[2];
        this.usedSpace = 0;
        this.size = 2;
    }

    public void growSize(){
        if (usedSpace == size) {


            int temp[] = new int[size*2];

            for (int i = 0; i < size; i++) {
                temp[i] = array[i];
            }
            size = size*2;
            array = temp;
            System.out.println("the array has grown in size");
        }
    }

    public void add(int var){
        growSize();

        array[usedSpace] = var;
        usedSpace++;
    }

    public void add(int var, int index){
        growSize();
        int temp[] = new int[size];

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

    public int delete(){
        int temp[] = new int[size];
        int temp2 = 0;

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

    public int delete(int index){
        int temp[] = new int[size];
        int temp2 = 0;

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
        int temp[] = new int[usedSpace];
        for (int i = 0; i < usedSpace; i++)
            temp[i]  = array[i];
        System.out.println(Arrays.toString(temp));
    }
}
