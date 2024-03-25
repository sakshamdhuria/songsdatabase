// -== CS400 Spring 2024 File Header Information ==-
// Name:Saksham Dhuria
// Email: sdhuria@wisc.edu
// Lecturer: Gary Dahl
// Notes to Grader: well nothing much to add this was not too shabby i think i am right if i did it correctly lol also easter egg thats my parents and sisters name in testers xdddd
import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IterableRedBlackTree<T extends Comparable<T>>
    extends RedBlackTree<T> implements IterableSortedCollection<T> {
    private Comparable<T> startPt = (t) -> -1;

    public void setIterationStartPoint(Comparable<T> startPoint) {
		if(startPoint==null){
			this.startPt = (t) -> -1;
		}
		else{
			startPt = startPoint;
		}
	}

    public Iterator<T> iterator() {
		return new RBTIterator<>(root, startPt); 
	}


@Override
	protected boolean insertHelper(Node<T> newNode) throws NullPointerException {
		if(newNode == null) throw new NullPointerException("new node cannot be null");
		if (this.root == null) {
            		// add first node to an empty tree
            		root = newNode;
            		size++;
            		return true;
       	 	} else {
            		// insert into subtree
            		Node<T> current = this.root;
            		while (true) {
                		int compare = newNode.data.compareTo(current.data);
                		/*if (compare == 0) {
                    			return false;
               		 	} else*/ if (compare <= 0) {
                    			// insert in left subtree
                    			if (current.down[0] == null) {
                        			// empty space to insert into
                        			current.down[0] = newNode;
                        			newNode.up = current;
                        			this.size++;
						return true;
                    			} else {
                        			// no empty space, keep moving down the tree
                        			current = current.down[0];
                    			}
                		} else {
                    			// insert in right subtree
                  	  		if (current.down[1] == null) {
                        			// empty space to insert into
                        			current.down[1] = newNode;
                        			newNode.up = current;
                        			this.size++;
                        			return true;
                    			} else {
                        			// no empty space, keep moving down the tree
                    	 		 	current = current.down[1];
                    		}
                	}
		}

	}
}
/*
	iterator class
*/
    private static class RBTIterator<R> implements Iterator<R> {
	private Comparable<R> startPt;
	private Stack<Node<R>> stck;
/*
	constructor where we just set start point if we have and also initialize stack 
*/
	public RBTIterator(Node<R> root, Comparable<R> startPoint) {
		startPt=startPoint;
		stck = new Stack<>();
		buildStackHelper(root);
	}

/*
	um there is teh base then recursion for if its larger than start then go next value on the left and otherwise go to the right 
*/

	private void buildStackHelper(Node<R> node) {
		if(node==null){
			return;
		}

		if(startPt.compareTo(node.data)>0){
			buildStackHelper(node.down[1]);
		}else{
			stck.push(node);
			buildStackHelper(node.down[0]);
		}
	}
/*
	having a stack makes life really easy just need to know if its empty or not
*/
	public boolean hasNext() {
		return !stck.isEmpty();
	}

/*
	just following directions about reusing buildstackhelper to build the stack if necessary and then just simply getting the node 
*/
	public R next() {
		if(!hasNext()){
			throw new NoSuchElementException("No more elements.");
		}
		Node<R> node = stck.pop();
		buildStackHelper(node.down[1]);
		return node.data;
	}
    }

	/*
		this test is a normal iterator check to check if i add a bunch of strings(my families names lol) to my tree it will iterate correctly 
	*/
	@Test
	public void testOne(){
		IterableRedBlackTree<String> tree = new IterableRedBlackTree<>();
		tree.insert("amit");
		tree.insert("shrey");
		tree.insert("shikha");
		tree.insert("arshi");

		Iterator<String> it = tree.iterator();
		if(it.hasNext()){
			Assertions.assertEquals("amit", it.next());
		}
		if(it.hasNext()){
			Assertions.assertEquals("arshi", it.next());
		}
		if(it.hasNext()){
			Assertions.assertEquals("shikha", it.next());
		}
		if(it.hasNext()){
			Assertions.assertEquals("shrey",it.next());
		}
	}

	/*
                this test is an iterator check to check if i add a bunch of integers with duplicates to my tree it will iterate correctly
        */
        @Test
        public void testTwo(){
                IterableRedBlackTree<Integer> tree = new IterableRedBlackTree<>();
                tree.insert(1);
                tree.insert(2);
                tree.insert(3);
		tree.insert(2);

                Iterator<Integer> it = tree.iterator();
                if(it.hasNext()){
                        Assertions.assertEquals(1,(int)  it.next());
                }
                if(it.hasNext()){
                        Assertions.assertEquals(2, (int) it.next());
                }
                if(it.hasNext()){
                        Assertions.assertEquals(2,(int) it.next());
                }
                if(it.hasNext()){
                        Assertions.assertEquals(3,(int) it.next());
                }
        }

 /*
                this test is an iterator check to check if i add a bunch of integers and check if it iterates correctly with a startpoint 
        */
        @Test
        public void testThree(){
                IterableRedBlackTree<Integer> tree = new IterableRedBlackTree<>();
                tree.insert(4);
                tree.insert(3);
                tree.insert(7);
                tree.insert(1);
		tree.setIterationStartPoint(2);
                Iterator<Integer> it =  tree.iterator();
                if(it.hasNext()){
                        Assertions.assertEquals(3,(int)  it.next());
                }
                if(it.hasNext()){
                        Assertions.assertEquals(4, (int) it.next());
                }
                if(it.hasNext()){
                        Assertions.assertEquals(7,(int) it.next());
                }
        }

}
