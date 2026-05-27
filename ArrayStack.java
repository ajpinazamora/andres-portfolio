//*********************************************************
// ArrayStack.java
// Author: Andres Joab Pina Zamora
//
// This class implements a bounded stack using a fixed-size
// array. It extends AbstractStack and provides actual
// implementations of the primary methods defined in BDDStack.
//
// Key Features:
// - Uses a plain array to store elements.
// - Implements push(), pop(), clear(), and depth().
// - Provides an iterator to traverse from bottom to top.
// - Supports reversing the stack (flip()) and shallow copying (copy()).
//
// The array size is fixed based on the initial capacity given
// in the constructor.
//*********************************************************

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<E> extends AbstractStack<E> {

    private final E[] data; //Array holding the stack elements

    public ArrayStack(int capacity){
        super(capacity);
        data = (E[]) new Object[capacity];
    }
   
    @Override
    public void push(E element){
        if (element == null) throw new NullPointerException();
        if (isFull()) throw new IllegalStateException("Stack is full");
        data[depth++] = element;
    }

    @Override
    public E pop(){
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        E element = data[--depth];
        data[depth] = null;
        return element;
    }
    
    @Override
    public int depth(){
        return depth;
    }

    @Override
    public void clear(){
        for (int i = 0; i < depth; i++){
            data[i] = null;
        }
        depth = 0;
    }

    @Override
    public BDDStack<E> newInstance(){
        return new ArrayStack<>(capacity());
    }

    @Override
    public Iterator<E> iterator(){
        return new ArrayStackIterator();
    }

    private class ArrayStackIterator implements Iterator<E>{
        private int index = 0;

        @Override
        public boolean hasNext(){
            return index < depth;
        }

        @Override
        public E next(){
            if (!hasNext()) throw new NoSuchElementException();
            return data[index++];
        }
    }

    @Override
    public void flip(){
        for(int i =0; i < depth / 2; i++){
            E temp = data[i];
            data[i] = data[depth - 1 - i];
            data[depth - 1 - i] = temp;
        }
    }

    @Override
    public BDDStack<E> copy(){
        ArrayStack<E> copy = new ArrayStack<>(capacity());
        for(int i = 0; i < depth; i++){
            copy.push(data[i]);
        }
        return copy; 
    }

}