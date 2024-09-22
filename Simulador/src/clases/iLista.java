
package clases;


public interface iLista {

    public void insertBegin(Object element);
    public void insertFinal(Object element);
    public void insertAtIndex(Object element, int index);
    public Object deleteBegin();
    public Object deleteFinal();
    public Object deleteAtIndex(int index);
    public boolean isEmpty();
    
}
