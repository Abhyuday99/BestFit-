package binarySearch;

public class Entry implements Comparable<Entry>{
	
		int id ;
		int size ;
		Bin location ;
		
		Entry(int x ,int size, Bin bin){
			this.id = x ;
			this.size = size ;
			this.location = bin ;
		}
		
		public int compareTo( Entry that) {
			
			if(this.id > that.id) {
				return 1 ;
			}
			else if(this.id < that.id) {
				return -1 ;
			}
			else {
				return 0 ;
			}
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
