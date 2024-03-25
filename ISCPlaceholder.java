// -== CS400 Spring 2024 File Header Information ==-
// Name:Saksham Dhuria
// Email: sdhuria@wisc.edu
// Lecturer: Gary Dahl
// Notes to Grader: easy class 
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class ISCPlaceholder<T extends Comparable<T>>
    implements IterableSortedCollection<T> {

    private T value;
    ArrayList<T> lst = new ArrayList();
    public boolean insert(T data)
	throws NullPointerException, IllegalArgumentException {
	value = data;
//	System.out.println("insering song name: " + ((SongInterface) data).getTitle());
	return lst.add(data);
    }

    public boolean contains(Comparable<T> data) {
	if(lst.contains(data)){
		return true;
	}
	return false;
    }
    public boolean isEmpty() {
	return lst.isEmpty();
    }

    public int size() {
	return lst.size();
    }

    public void clear() {
	lst.clear();
    }

    public void setIterationStartPoint(Comparable<T> startPoint) {
    }

    public Iterator<T> iterator() {
	return lst.listIterator();
    }
}
