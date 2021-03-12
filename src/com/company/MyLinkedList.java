package com.company;

public class MyLinkedList <E>{
    private Node<E> head, tail;
    private int size = 0;

    public MyLinkedList() {
    }

    public MyLinkedList(E[] objects) {
        for (E object : objects) addLast(object);
    }


    public E getFirst() {
        if (size == 0) {
            return null;
        }
        else {
            return head.element;
        }
    }

    public E getLast() {
        if (size == 0) {
            return null;
        }
        else {
            return tail.element;
        }
    }

    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;
        size++;

        if (tail == null)
            tail = head;
    }

    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);

        if (tail == null) {
            head = tail = newNode;
        }
        else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    public void add(int index, E e) {
        if (index == 0) addFirst(e);
        else if (index >= size) addLast(e);
        else {
            Node<E> current = head;
            for (int i = 1; i < index; i++)
                current = current.next;
            Node<E> temp = current.next;
            current.next = new Node<>(e);
            (current.next).next = temp;
            size++;
        }
    }

    public E removeFirst() {
        if (size == 0) return null;
        else {
            Node<E> temp = head;
            head = head.next;
            size--;
            if (head == null) tail = null;
            return temp.element;
        }
    }

    public E removeLast() {
        if (size == 0) return null;
        else if (size == 1) {
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        }
        else {
            Node<E> current = head;

            for (int i = 0; i < size - 2; i++)
                current = current.next;

            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    public E remove(int index) {
        if (index < 0 || index >= size) return null;
        else if (index == 0) return removeFirst();
        else if (index == size - 1) return removeLast();
        else {
            Node<E> previous = head;

            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }

            Node<E> current = previous.next;
            previous.next = current.next;
            size--;

            return current.element;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", ");
            }
            else {
                result.append("]");
            }
        }

        return result.toString();
    }

    public void clear() {
        size = 0;
        head = tail = null;
    }

    public boolean contains(Object e) {
        Node<E> curr = head;
        for(int i = 1;i<size-1;++i){
            if(e.equals(curr.element)){
                return true;
            }
            curr = curr.next;
        }
        return e.equals(curr.element);
    }

    public E get(int index) {
        Node<E> curr = head;
        for(int i=0;i<index;++i){
            curr = curr.next;
        }
        return curr.element;
    }

    public int indexOf(Object e) {
        Node<E> curr = head;
        for(int i=0;i<size-1;++i){
            if(e.equals(curr.element)) return i;
            curr = curr.next;
        }
        return (e.equals(curr.element) ? size - 1 : -1);
    }

    public int lastIndexOf(E e) {
        Node<E> curr = head;
        int id = -1;
        for(int i=0;i<size-1;++i){
            if(e.equals(curr.element)) id = i;
            curr = curr.next;
        }
        return (e.equals(curr.element) ? size - 1 : id);
    }

    public void set(int index, E e) {
        Node<E> curr = head;
        for(int i=0;i<size;++i){
            if(index == i){
                curr.element = e;
                return;
            }
            curr = curr.next;
        }
    }

    public java.util.Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements java.util.Iterator<E> {
        private Node<E> current = head;

        public boolean hasNext() {
            return (current != null);
        }

        public E next() {
            E e = current.element;
            current = current.next;
            return e;
        }

        public void remove() {
            if(head == current){
                head = head.next;
            }
            Node<E> curr = head;
            for(int i=0;i<size-1;++i){
                if(curr.next == current){
                    curr.next = curr.next.next;
                }
            }
        }
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    public int size() {
        return size;
    }
}
