package binarySearch;

public class Leaf<E> {
	
	public E value ;
	public int height ;
	public Leaf<E> left,right,parent ;
	
	Leaf(E entry){
		this.value = entry ;
		this.height = 1 ;
		this.left = new Leaf<E>() ;
		this.right = new Leaf<E>() ;
	}
	Leaf() {
		this.height = 0 ;
		this.value = null ;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer x = 20 ;
		System.out.println(x.compareTo(25));
	}

}
