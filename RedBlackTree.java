// -== CS400 Spring 2024 File Header Information ==-
// Name:Saksham Dhuria
// Email: sdhuria@wisc.edu
// Lecturer: Gary Dahl
// Notes to Grader: fun time messed up rotate LOLLLLL
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
/*
implemented a fun red black tree with like making sure all properties work!!!!!!
*/
public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T>{
	/*
		takes in a new node that is meant to be insert goes through all cases in a recursive way
	*/
	protected void enforceRBTreePropertiesAfterInsert(RBTNode<T> newNode){
		//check if the node being checked is a root and if it is we make it black
		if(newNode.getUp()==null){
			newNode.isBlack=true;
			return;
		}
		//now we are at a point where its not a root so we get the parent
		RBTNode<T> parent = newNode.getUp();

		//now we try to see if it has a grandparent and if it doesnt we just kind of make that node red
		RBTNode<T> grandParent=null;
		if(parent.getUp()!=null){
			grandParent=parent.getUp();
		}else{
			newNode.isBlack=false;
			return;
		}

		RBTNode<T> uncle=null;

		//checking for if the node has uncle and populating it
		if(grandParent.getDownLeft()!=null && grandParent.getDownLeft().equals(parent)){
			if(grandParent.getDownRight()!=null){
				uncle=grandParent.getDownRight();
			}
		}
		else{
			if(grandParent.getDownLeft()!=null){
				uncle=grandParent.getDownLeft();
			}
		}
		//case 1 if parent is black then just return 

		if(parent.isBlack){
			return;
		}

		//case 2 if uncle is either null or is black then rotate parent and grandparent and then color parent black and grandparent is red 
		if(uncle==null || uncle.isBlack){
			if(newNode.isRightChild() != parent.isRightChild()){
				rotate(newNode, parent);
				parent=newNode;
			}
			this.rotate(parent, grandParent);
			parent.isBlack=true;
			grandParent.isBlack=false;
			
		}else{
			grandParent.isBlack=false;
			uncle.isBlack=true;
			parent.isBlack=true;
			enforceRBTreePropertiesAfterInsert(grandParent);
		}

	}
/*
	a method to insert a data and used helper methods to fulfill red black properties
*/
	@Override
	public boolean insert(T data) throws NullPointerException {
		if(data==null){
			throw new NullPointerException("Null input get better skill issue");
		}
		RBTNode<T> newNode = new RBTNode(data);
		if(this.insertHelper(newNode)){
			enforceRBTreePropertiesAfterInsert(newNode);
			return true;
		}
		return false;
	}
	protected static class RBTNode<T> extends Node<T> {
		public boolean isBlack = false;
		public RBTNode(T data) { super(data); }
		public RBTNode<T> getUp() { return (RBTNode<T>)this.up; }
		public RBTNode<T> getDownLeft() { return (RBTNode<T>)this.down[0]; }
		public RBTNode<T> getDownRight() { return (RBTNode<T>)this.down[1]; }
	}
	/*
	this one is just checking if after couple additions they are going to be good and then adding couple more and checking again and such 
	*/

	@Test
	public void firstTest() {
		RedBlackTree<Integer> trSim = new RedBlackTree<>();
		trSim.insert(1);
		Assertions.assertEquals("[ 1 ]", trSim.toLevelOrderString());
		trSim.insert(2);
		trSim.insert(3);
		Assertions.assertEquals("[ 2, 1, 3 ]",  trSim.toLevelOrderString());
		trSim.insert(0);
		trSim.insert(4);
		trSim.insert(5);
		Assertions.assertEquals("[ 2, 1, 4, 0, 3, 5 ]",  trSim.toLevelOrderString());
	}
	/*
	second tester which will add values in lever order traversal and eventually after all additions check whether nothing was changed
	*/
	@Test
	public void secondTest(){
		RedBlackTree<Integer> trSim = new RedBlackTree<>();
                trSim.insert(4);
                trSim.insert(2);
                trSim.insert(6);
                trSim.insert(1);
                trSim.insert(3);
                trSim.insert(5);
		trSim.insert(9);
		trSim.insert(7);
		trSim.insert(10);
                Assertions.assertEquals("[ 4, 2, 6, 1, 3, 5, 9, 7, 10 ]",  trSim.toLevelOrderString());
	}
	/*
	third tester which will be adding values from 10 to 1 in a descending order fashion and then eventually check in the end if it has been rotated accurately

	*/
	@Test
	public void thirdTest(){
		RedBlackTree<Integer> trSim = new RedBlackTree<>();
		trSim.insert(10);
		Assertions.assertEquals("[ 10 ]", trSim.toLevelOrderString());
		trSim.insert(9);
		Assertions.assertEquals("[ 10, 9 ]", trSim.toLevelOrderString());
		trSim.insert(8);
		Assertions.assertEquals("[ 9, 8, 10 ]", trSim.toLevelOrderString());
		trSim.insert(7);
		Assertions.assertEquals("[ 9, 8, 10, 7 ]", trSim.toLevelOrderString());
		trSim.insert(6);
		Assertions.assertEquals("[ 9, 7, 10, 6, 8 ]", trSim.toLevelOrderString());
		trSim.insert(5);
		Assertions.assertEquals("[ 9, 7, 10, 6, 8, 5 ]", trSim.toLevelOrderString());
		trSim.insert(4);
		Assertions.assertEquals("[ 9, 7, 10, 5, 8, 4, 6 ]", trSim.toLevelOrderString());
		trSim.insert(3);
		Assertions.assertEquals("[ 7, 5, 9, 4, 6, 8, 10, 3 ]", trSim.toLevelOrderString());
		trSim.insert(2);
		Assertions.assertEquals("[ 7, 5, 9, 3, 6, 8, 10, 2, 4 ]", trSim.toLevelOrderString());
		trSim.insert(1);
		Assertions.assertEquals("[ 7, 5, 9, 3, 6, 8, 10, 2, 4, 1 ]", trSim.toLevelOrderString());
	}
}
