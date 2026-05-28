//*********************************************************
// AbstractStack.java
// Author: Andres Joab Pina Zamora
//
// This class provides a base implementation for a bounded
// stack. It implements secondary methods from the BDDStack
// interface, which are methods that can be built using
// primary methods like push() and pop().
//
// This class defines:
// - capacity() - Returns the maximum size of the stack.
// - isEmpty() - Checks if the stack is empty.
// - isFull() - Checks if the stack is full.
// - flip() - Reverses the order of the elements (to be overridden).
// - copy() - Creates a shallow copy of the stack (to be overridden).
//
// ArrayStack and ListStack will extend this class and provide
// actual implementations of the primary methods (push, pop, etc.).
//*********************************************************

public abstract class AbstractStack<E> implements BDDStack<E> {

    protected final int capacity; //Maximum number of elements
    protected int depth; //current number of elements

    public AbstractStack(int capacity){
        if(capacity <= 0){
            throw new IllegalArgumentException("Capacity must be > 0");
        }
        this.capacity = capacity;
    }

    //Secondary Methods
    
    @Override
    public int capacity(){
        return capacity;
    }

    @Override
    public boolean isEmpty(){
        return depth() ==0;
    }

    @Override
    public boolean isFull(){
        return depth == capacity();
    }
     
    @Override
    public void flip(){
        throw new UnsupportedOperationException("flip() not implemented yet");
    }

    @Override
    public BDDStack<E> copy(){
        throw new UnsupportedOperationException("copy() not implemented yet");
    }

    @Override
    public abstract void push(E element);

    @Override
    public abstract E pop();

    @Override
    public abstract int depth();

    @Override
    public abstract void clear();

    @Override
    public abstract BDDStack<E> newInstance();
}