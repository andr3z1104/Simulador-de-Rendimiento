
package clases;


public class Lista implements iLista {
  
     private NodoLista head;
    private int length;

    public Lista() {
        this.head = null;
        this.length = 0;
    }

    public NodoLista getHead() {
        return head;
    }

    public void setHead(NodoLista head) {
        this.head = head;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
     @Override
    public boolean isEmpty() {
        return getHead() == null;
    }
    
     @Override
    public void insertBegin(Object element) {
        NodoLista nodo = new NodoLista(element);
        if (isEmpty()){
            setHead(nodo);
        } else {
            nodo.setNext(getHead());
            setHead(nodo);
        }
        length++;
    }
    
     @Override
    public void insertFinal(Object element) {
        NodoLista nodo = new NodoLista(element);
        if (isEmpty()){
            setHead(nodo);
        } else {
            NodoLista pointer = getHead();
            while (pointer.getNext() != null) {
                pointer = pointer.getNext();
            }
            pointer.setNext(nodo);
        }
        length++;
    }
    
    
    
     @Override
    public void insertAtIndex(Object element, int index) {
        NodoLista nodo = new NodoLista(element);
        if (isEmpty() || index == 0){
            insertBegin(element);
        } else {
            if (index < getLength()) {
                NodoLista pointer = getHead();
                int cont = 0;
                while (cont < index - 1) {
                    pointer = pointer.getNext();
                    cont++;
                }
                NodoLista temp = pointer.getNext();
                nodo.setNext(temp);
                pointer.setNext(nodo);
                length++;
            } else if (index == getLength()) {
                insertFinal(element);
            } else {
                System.out.println("Index not valid");
            }
        }
    }
    
     @Override
    public NodoLista deleteBegin() {
        if(isEmpty()) {
            System.out.println("La lista esta vacia");
            return null;
        } else {
            NodoLista pointer = getHead();
            setHead(pointer.getNext());
            pointer.setNext(null);
            length--;
            return pointer;
        }
    }
    
     @Override
    public NodoLista deleteFinal() {
        if(isEmpty()) {
            System.out.println("La lista esta vacia");
            return null;
        } else {
            NodoLista pointer = getHead();
            while (pointer.getNext().getNext() != null) {
                pointer = pointer.getNext();
            }
            NodoLista temp = pointer.getNext();
            pointer.setNext(null);
            length--;
            return temp;
        }
    }
    
    
     @Override
    public NodoLista deleteAtIndex(int index) {
        if(isEmpty()) {
            System.out.println("La lista esta vacia");
            return null;
        } else {
            if (index == 0){
            return deleteBegin();
            } else {
                if (index < getLength()) {
                    NodoLista pointer = getHead();
                    int cont = 0;
                    while (cont < index - 1) {
                        pointer = pointer.getNext();
                        cont++;
                    }
                    NodoLista temp = pointer.getNext();
                    pointer.setNext(temp.getNext());
                    temp.setNext(null);
                    length--;
                    return temp;
                } else if (index == getLength()) {
                    return deleteFinal();
                } else {
                    System.out.println("Index not valid");
                    return null;
                }
            }
        }
    }
    
    public void print() {
        NodoLista pointer = getHead();
        while (pointer != null) {
            System.out.print(" [ " + pointer.getElement() + " ] ");
            pointer = pointer.getNext();
        }
    }
}
