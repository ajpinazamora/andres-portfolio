//*********************************************************
// ListStack.java
// Author: Andres Joab Pina Zamora
//
// This class implements a bounded stack using a Java ArrayList.
// It extends AbstractStack and provides actual implementations
// of the primary methods defined in BDDStack.
//
// Key Features:
// - Uses an ArrayList to store elements (dynamic size within capacity).
// - Implements push(), pop(), clear(), and depth().
// - Directly uses the ArrayList's built-in iterator for traversal.
// - Supports reversing the stack (flip()) and shallow copying (copy()).
//
// The stack's maximum size is controlled by the initial capacity
// passed to the constructor.
//*********************************************************

import java.util.ArrayList;
import java.util.Iterator;

public class ListStack<E> extends AbstractStack<E> {
    
    private final ArrayList<E> data;

    public ListStack(int capacity){
        super(capacity);
        data = new ArrayList<>(capacity);
    }

    @Override
    public void push(E element){
        if(element == null) throw new NullPointerException();
        if(isFull()) throw new IllegalStateException("Stack is full");
        data.add(element);
        depth++;
    }

    @Override
    public E pop(){
        if(isEmpty()) throw new IllegalStateException("Stack is empty");
        depth--;
        return data.remove(data.size() - 1);
    }

    @Override
    public int depth(){
        return depth;
    }

    @Override
    public void clear(){
        data.clear();
        depth = 0;
    }

    @Override
    public BDDStack<E> newInstance(){
        return new ListStack<>(capacity());
    }

    @Override
    public Iterator<E> iterator(){
        return data.iterator();
    }

    @Override
    public void flip(){
        for(int i = 0; i < depth / 2; i++){
            E temp = data.get(i);
            data.set(i, data.get(depth - 1 - i));
            data.set(depth - 1 - i, temp);
        }
    }

    @Override
    public BDDStack<E> copy(){
        ListStack<E> copy = new ListStack<>(capacity());
        copy.data.addAll(this.data);
        copy.depth = this.depth;
        return copy;
    }
}