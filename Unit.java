package binarySearch;

public class Unit implements Comparable<Unit> {
	
	int size ;
	int id ;
	
	Unit(int x , int s){
		this.size = s ;
		this.id = x ;
	}
	Unit(int s){
		this(0,s) ;
	}
	
	public int compareTo(Unit that) {
		if(this.size > that.size) {
			return 1 ;
		}
		else if(this.size < that.size) {
			return -1 ;
		}
		else {
			if(this.id > that.id) {
				return 1; 
			}
			else if(this.id < that.id) {
				return -1 ;
			}
			else {
				return 0 ;
			}
		}
	}
	
	public String toString() {
		return id + " " + size ;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
