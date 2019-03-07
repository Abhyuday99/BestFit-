package binarySearch;

public class Bin implements Comparable<Bin>{
	
	int capacity ;
	int id ;
	AVLTree<Unit> units ;
	
	Bin(){
		this.capacity = 0 ;
		this.id = 0 ;
		this.units = null ;
	}
	Bin(int s){
		this(0,s) ;
	}
	Bin(int x, int s){
		this.capacity = s ;
		this.id = x ;
		this.units = new AVLTree<Unit>() ;
	}
	
	public int compareTo(Bin that) {
		if(this.capacity > that.capacity) {
			return 1 ;
		}
		else if(this.capacity < that.capacity) {
			return -1 ;
		}
		else {
			if(this.id > that.id) {
				return 1  ;
			}
			else if(this.id < that.id) {
				return -1 ;
			}
			else {
				return 0 ;
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
