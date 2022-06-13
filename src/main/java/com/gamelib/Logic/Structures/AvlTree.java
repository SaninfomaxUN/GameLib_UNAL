package com.gamelib.Logic.Structures;


import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class AvlTree<T extends Comparable<? super T>> implements Serializable {

    public static int numNodos = 0;

    public static class  AvlNode<T> implements Serializable{
        public T element;
        AvlNode<T> left;
        AvlNode<T> right;
        int height;



        public AvlNode(T x, AvlNode<T> left, AvlNode<T> right) {
            this.element = x;
            this.left = left;
            this.right = right;
        }

    }
    private AvlNode<T> root;

    private DynamicArray<T> arrayList;

    private Class<T> classType;

    public AvlTree(Class<T> classType_){
        root = null;
        classType = classType_;
        arrayList = new DynamicArray<>(classType);
    }

    public void makeEmpty(){
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }


    public AvlNode<T> updateHeight(AvlNode<T> t){
        if (t == null) {
            return t;
        }
        int maxHeight = Math.max(height(t.left), height(t.right));
        t.height = maxHeight + 1;

        return t;
    }
    public void updateNumNodos(boolean tipo){
        if (tipo){
            numNodos++;
        }else {
            numNodos--;
        }
    }

    private int height(AvlNode<T> t){
        return t != null ? t.height : -1;
        }

    private AvlNode<T> leftRotation(AvlNode<T> t){
        AvlNode<T> temp = t.left;
        t.left = temp.right;
        temp.right = t;

        updateHeight(t);
        updateHeight(temp);

        return temp;
    }

    private AvlNode<T> rightRotation(AvlNode<T> t){
        AvlNode<T> temp = t.right;
        t.right = temp.left;
        temp.left = t;

        updateHeight(t);
        updateHeight(temp);

        return temp;
    }

    public AvlNode<T> applyBalance(AvlNode<T> t){
        int balance = balance(t);
        if (balance > 1){
            if(balance(t.right) >= 0) {
                t = rightRotation(t);
            }else{
                t.right = leftRotation(t.right);
                t = rightRotation(t);
        }
    }


        if (balance < -1){
            if (balance(t.left) <= 0) {
                t = leftRotation(t);
            }else{
                t.left = rightRotation(t.left);
                t = leftRotation(t);
        }
    }
    return t;
    }

    private int balance(AvlNode<T> t){
        if (t == null){
            return -1;
        }
        int difference = height(t.right) - height(t.left);
        return difference;

    }

    private AvlNode<T> findMin(AvlNode<T> t){
        if (t != null){
            while(t.left != null){
                t = t.left;
            }
        }
        return t;
    }

    public void insert(T x){
        root = insert (x, root);
        updateNumNodos(true);
    }

    private AvlNode<T> insert (T x, AvlNode<T> t) {
        if (t == null)
            return new AvlNode<>(x, null, null);

        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left);


        }
        else if (compareResult > 0){
            t.right = insert(x, t.right);
        }


        updateHeight(t);
        return applyBalance(t);
    }
    public void remove(T x){
        root = remove(x, root);
        updateNumNodos(false);
    }

    private AvlNode<T> remove(T x, AvlNode<T> t){
        if(t == null) {

            return t;
        }

        int compareResult = x.compareTo(t.element);
        if(compareResult<0){
            t.left =remove(x, t.left);
        }else if( compareResult > 0){
            t.right = remove(x, t.right);
        }else if (t.left != null && t.right != null){
            t.element = findMin( t.right).element;
            t.right = remove(t.element, t.right);
        }
        else{
            t = (t.left != null) ? t.left : t.right;
        }


        updateHeight(t);
        return applyBalance(t);

    }

    public boolean contains ( T x) {
        return contains(x, root);
    }

    private boolean contains(T x, AvlNode<T> t)
    {
        if (t == null){
            return false;
        }
        int compareResult = x.compareTo(t.element);
        if( compareResult < 0) {
            return contains(x, t.left);
        }
        else if (compareResult > 0) {
            return contains(x, t.right);
        }
        else{

            return true;}
    }

    public T get(T x){
        try{
            T obj = (T) get(x, root).element;
            return obj;
        }catch (NullPointerException er){
            return null;
        }


    }
    
    private AvlNode<T> get (T x, AvlNode<T> t){
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            return get(x, t.left);
        }
        else if( compareResult > 0) {
            return get(x, t.right);
        }
        else{
            return t;
        }
    }



    public void inOrden(){
        inOrden(root);


    }

    private void inOrden(AvlNode<T> t){
        if( t == null)
            return;

        inOrden(t.left);
        System.out.println(t.element+" ");
        arrayList.add(t.element);

        inOrden(t.right);

        return;

    }

    public DynamicArray<T> getList(){
        inOrden();
        DynamicArray<T> copyArrayList = SerializationUtils.clone(arrayList);
        arrayList = new DynamicArray<>(classType);
        return copyArrayList;

    }
}

