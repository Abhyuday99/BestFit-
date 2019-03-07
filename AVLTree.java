package binarySearch;
import java.lang.Math ;


public class AVLTree<E extends Comparable<E>> {
	
	public Leaf<E> root ;
	public int size ;
	
	AVLTree() {
		this.root = new Leaf<E>() ;
		this.size = 0 ;
	}
	AVLTree(E entry) {
		
		this.root = new Leaf<E>(entry) ;
		this.size = 1 ;
	}
	
	public Leaf<E> balanceTree(Leaf<E> node) {
		int rightheight = node.right.height ;
		int leftheight = node.left.height ;
		Leaf<E> temp = node.parent,temp2 ;
		if(Math.abs(rightheight - leftheight) > 1) {
			//System.out.println(node.value);
			if(leftheight > rightheight) {
				if(node.left.left.height < node.left.right.height) {
					node.left = rotateLeft(node.left) ;
				}
				node = rotateRight(node) ;
			}
			else {
				if(node.right.right.height < node.right.left.height) {
					node.right = rotateRight(node.right) ;
					//System.out.println("TEST2");
				}
				//System.out.println("TEST3");
				node  = rotateLeft(node) ;
			}
			setNodeHeight(node.left) ;
			setNodeHeight(node.right) ;
			setNodeHeight(node) ;
			/*if(temp == null) {
				System.out.println("TEST5");
				setNodeHeight(root.left) ;
				setNodeHeight(root.right) ;
				setNodeHeight(root) ;
			}
			else {
				if(node.value.compareTo(temp.value) > 0) {
					temp2 = temp.right ;
				}
				else {
					temp2 = temp.left ;
				}
				setNodeHeight(temp2.left) ;
				setNodeHeight(temp2.right) ;
				setNodeHeight(temp2) ;
			}*/
			//System.out.println("\nBalaned Root Value: " + root.value);
			return node ;
			
		}
		else {
			return node ;
		}
	}
	
	public Leaf<E> rotateRight(Leaf<E> node) {
		Leaf<E> temp = node.left ;
		node.left = temp.right ;
		temp.right = node ;
		Leaf<E> temp2 =node.parent ;
		temp.parent = temp2 ;
		node.parent = temp ;
		if(temp2 == null) {
			this.root = temp ;
		}
		else {
			if(node.value.compareTo(temp2.value) > 0) {
				temp2.right = temp;
			}
			else {
				temp2.left = temp ;
			}
		}
		return temp ;
	}
	
	public Leaf<E> rotateLeft(Leaf<E> node) {
		Leaf<E> temp = node.right ;
		//System.out.println("Value of temp is " + temp.value);
		node.right = temp.left ;
		temp.left = node ;
		Leaf<E> temp2 =node.parent ;
		temp.parent = temp2 ;
		node.parent = temp ;
		if(temp2 == null) {
			//System.out.println("TEST4");
			root = temp ;
			//System.out.println("Value of root is " + root.value);
		}
		else {
			if(node.value.compareTo(temp2.value) > 0) {
				temp2.right = temp;
			}
			else {
				temp2.left = temp ;
			}
		}
		return temp ;
	}
	
	public Leaf<E> addX(E entry,Leaf<E> root) {
		
		if(root.value == null ) {
			return new Leaf<E>(entry) ;
		}
		
		if(entry.compareTo(root.value) < 0) {
			root.left = addX(entry,root.left) ;
			root.left.parent = root ;
		}
		else {
			root.right = addX(entry,root.right) ;
			root.right.parent = root ;
		}
		setNodeHeight(root) ;
		//System.out.println("Check Root Value : " + root.value);
		root = balanceTree(root) ;
		return root ;
		
	}
	
	public void setNodeHeight(Leaf<E> root) {
		
		root.height = Math.max(root.left.height,root.right.height) + 1 ;
		
	}
	public void add(E entry) {
		
		size++ ;
		//System.out.println("Root Value : " + root.value);
		root = addX(entry,root) ;
		//System.out.println("Changed Root Value : " + root.value);
		return ;
	}
	public Leaf<E> search(E entry){
		
		Leaf<E> temp = root ;
		while(true) {
			if(temp.value == null) {
				return null ;
			}
			if(entry.compareTo(temp.value) < 0) {
				temp = temp.left ;
			}
			else if(entry.compareTo(temp.value) > 0) {
				temp = temp.right ;
			}
			else {
				return temp ;
			}
			
		}
	}
	
	public Leaf<E> removeX(Leaf<E> temp) {
		Leaf<E> temp2,temp3 ;
		if(temp == null) {
			return null ;
		}
		temp2 = temp.parent ;
		if(temp2 == null) {
			if(temp.right.value == null && temp.left.value == null) {
				root = new Leaf<E>() ;
			}
			else if(temp.right.value == null) {
				root = temp.left ;
				temp3 = root ;
			}
			else if(temp.left.value == null) {
				root = temp.right ;
				temp3 = root ;
			}
			else {
				temp3 = inorderPredecessor(temp) ;
				E val = root.value ;
				root.value = temp3.value ;
				if(temp3.parent.left == temp3) {
					temp3.parent.left = new Leaf<E>() ;
				}
				else {
					temp3.parent.right = new Leaf<E>() ;
				}
				root = balanceTree(root) ;
				
			}
		}
		
		
		else {
			if(temp.right.value == null && temp.left.value == null) {
				 if(temp2.right == temp) {
					 temp2.right = new Leaf<E>() ;
				 }
				 else {
					 temp2.left = new Leaf<E>() ;
				 }
				 temp3 = temp ;
			}
			else if(temp.right.value == null) {
				if(temp2.right == temp) {
					 temp2.right = temp.left ;
				}
				else {
					 temp2.left = temp.left ;
				}
				temp3 = temp ;
			}
			else if(temp.left.value == null) {
				if(temp2.right == temp) {
					 temp2.right = temp.right ;
				}
				else {
					 temp2.left = temp.right ;
				}
				temp3 = temp ;
			}
			else {
				temp3 = inorderPredecessor(temp) ;
				E val = root.value ;
				temp.value = temp3.value ;
				
				if(temp3.parent.left == temp3) {
					temp3.parent.left = new Leaf<E>() ;
				}
				else {
					temp3.parent.right = new Leaf<E>() ;
				}
			}
			while(temp3.parent != null) {
				setNodeHeight(temp3.parent) ;
				temp3.parent = balanceTree(temp3.parent) ;
				temp3 = temp3.parent ;
			}
			
		}
		size-- ;
		return temp ;
		
	}
	public E remove(E entry) {
		
		Leaf<E> temp = search(entry) ;
		
		return removeX(temp).value ;
		
	}
	
	
	public static <E,V> void inorder(Leaf<E> root) {
		
		if(root.value == null) {
			return  ;
		}
		inorder(root.left) ;
		System.out.println(root.value);
		inorder(root.right) ;
		
		return ;
	}
	
	public Leaf<E> inorderPredecessor(Leaf<E> root) {
		if(root.left.value == null) {
			while(true) {
				if(root.parent == null) {
					return null ;
				}
				else if(root.parent.left == root) {
					root = root.parent ;
				}
				else {
					return root.parent ;
				}
			}
		}
		root = root.left ;
		while(root.right.value != null) {
			root = root.right ;
		}
		return root ;
	}
	
	public static <E> int height(Leaf<E> root) {
		
		if(root == null) {
			return 0 ;
		}
		int rightheight = AVLTree.height(root.right) ;
		int leftheight = AVLTree.height(root.left) ;
		return Math.max(rightheight ,leftheight) + 1 ;
	}
	
	public static <E> void printTree(Leaf<E> x) {
		if(x.value == null) {
			return ;
		}
		else {
			printTree(x.left) ;
			System.out.println(x.value);
			printTree(x.right) ;
			
			return ;
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AVLTree<Integer> test = new AVLTree<Integer>() ;
		System.out.println("\nHeight : " + test.root.height);
		test.add(6) ;
		System.out.println("\nHeight : " + test.root.height);
		test.add(3) ;
		System.out.println("");
		test.add(4) ;
		System.out.println("\nHeight : " + test.root.height + "   " + test.root.value);
		test.remove(3) ;
		System.out.println("Success");
		System.out.println("\nHeight : " + test.root.height + "   " + test.root.value);
		test.add(8);
		System.out.println("\nHeight : " + test.root.height + "   " + test.root.value);
		
		
		
	}

}
