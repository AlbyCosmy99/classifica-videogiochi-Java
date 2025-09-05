import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class EmptyQueueException extends RuntimeException{}

public class Queue{
    private Videogioco[] elements;
    int size;

    public Queue(){
        elements = new Videogioco[10];
        size = 0;
    }

    public Videogioco enqueue(Videogioco elem){
        if(size==elements.length){
            resize();
        }
        elements[size++] = elem;

        return elem;
    }

    public Videogioco dequeue(){
        if(size==0){
            throw new EmptyQueueException();
        }
        Videogioco element = elements[0];
        for(int i=1;i<size;i++){
            elements[i-1] = elements[i];
        }
        size--;
        return element;
    }

    public void removeFromQueueElementsInArray(Videogioco[] array){
        for(int i=0;i<size;i++){
            for(int j=0;j<array.length;j++){
                Videogioco elem1 = elements[i];
                Videogioco elem2 = array[j];
                if(elem1!=null && elem2!=null){
                    if(elem1.equals(elem2)){
                        for(int l=i+1;l<elements.length;l++){
                            elements[l-1] = elements[l];
                        }
                        size--;
                    }
                }
            }
        }
    }

    private void resize(){
        Videogioco[] newArray = new Videogioco[elements.length*2];
        for(int i=0;i<elements.length;i++){
            newArray[i] = elements[i];
        }
        this.elements = newArray;
    }

    public int getSize(){
        return size;
    }

    public String toString(){
        String s = "";
        for(int i=0;i<size;i++){
            s = s + elements[i] + " ";
        }
        return s.trim();
    }

    public void setSize(int size){
        this.size = size;
    }

    public void setElements(Videogioco[] elements){
        Videogioco[] array = new Videogioco[elements.length];
        for(int i=0;i<elements.length;i++){
            array[i] = elements[i];
        }
        this.elements = array;
    }

    public Queue copy(){
        Queue queue = new Queue();
        queue.setSize(size);
        queue.setElements(elements);
        return queue;
    }

    public void mixVideogiochi(){
		List<Videogioco> list = Arrays.asList(elements);
		Collections.shuffle(list);
		list.toArray(elements);
    }
}