package binarySearch;
import java.io.* ;
import java.util.StringTokenizer ;

public class BestFit {
	
	AVLTree<Bin> bins ;
	AVLTree<Entry> obj_directory ;
	AVLTree<Entry> bin_directory ;
	BestFit(){
		this.bins = new AVLTree<Bin>() ;
		this.obj_directory = new AVLTree<Entry>() ;
		this.bin_directory = new AVLTree<Entry>() ;
	}
	BestFit(int x , int c){
		this.bins = new AVLTree<Bin>(new Bin(x,c)) ;
		this.obj_directory = new AVLTree<Entry>() ;
		this.bin_directory = new AVLTree<Entry>() ;
	}
	public void add_bin(int bin_id, int capacity){
		
		Bin temp = new Bin(bin_id,capacity) ;
		bin_directory.add(new Entry(bin_id,capacity,temp));
		bins.add(temp);
		
    }
	
	public int add_object(int obj_id, int size){
        // Fill this
		Unit temp = new Unit(obj_id,size) ;
		Leaf<Bin> storage = getMax() ;
		if(storage.value.capacity < size) {
			System.out.println("Too big !");
			return -1 ;
		}
		else {
			storage.value.capacity -= size ;
			storage.value.units.add(temp);
			bins.removeX(storage) ;
			bins.add(storage.value);
			obj_directory.add(new Entry(obj_id,size,storage.value));
			return storage.value.id ;
			
		}
		
        // return the bin id to which this object is added
   }
	
	public int delete_object(int obj_id){
        // Remove the object with id "id" from the bin it is placed in.
       // Fill this
        // Return the bin_id from which this object was removed
		Leaf<Entry> temp ;
		Entry location ;
		try {
			temp = obj_directory.search(new Entry(obj_id,0,null)) ;
			location = temp.value ;
		}
		catch(NullPointerException e) {
			System.out.println("Object with given id was not found.");
			return -1 ;
		} 
		Leaf<Bin> storage = bins.search(location.location) ;
		storage.value.capacity += location.size ;
		storage.value.units.remove(new Unit(location.id,location.size));
		bins.removeX(storage) ;
		bins.add(storage.value);
		obj_directory.removeX(temp) ;
		return storage.value.id ;
		
		
	}
	
	public void printBin(int bin_id){
			   // Return the list of current objects in the bin with id "bin_id" : list of pairs  for each object in the bin.
		Leaf<Entry> temp ;
		Entry location ;
		try {
			temp = bin_directory.search(new Entry(bin_id,0,null)) ;
			location = temp.value ;
		}
		catch(NullPointerException e) {
			System.out.println("Bin with given id was not found.");
			return ;
		}
		
		Bin b = location.location ;
		AVLTree.printTree(b.units.root) ;
	}

	public Leaf<Bin> getMax() {
		
		Leaf<Bin> temp = bins.root ;
		if(temp == null) {
			return null ;
		}
		while(temp.right.value != null) {
			temp = temp.right ;
		}
		return temp ;
		
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br ;
		try {
			br = new BufferedReader(new FileReader(args[0])) ;
		}catch(FileNotFoundException e) {
			System.out.println(args[0] + "could not be found.Exiting....");
			return ;
		}
		StringTokenizer st ;
		String str = br.readLine();
		int command,id,size;
		BestFit bf = new BestFit() ;
		while(str != null) {
			st = new StringTokenizer(str) ;
			command = Integer.parseInt(st.nextToken());
			if(command == 1) {
				try {
					id =  Integer.parseInt(st.nextToken());
					size = Integer.parseInt(st.nextToken());
				}
				catch(Exception e) {
					System.out.println("Invalid number of arguments.Exiting...");
					br.close();
					return ;
				}
				bf.add_bin(id,size);
				
			}
			else if(command == 2) {
				try {
					id =  Integer.parseInt(st.nextToken());
					size = Integer.parseInt(st.nextToken());
				}
				catch(Exception e) {
					System.out.println("Invalid number of arguments.Exiting...");
					br.close();
					return ;
				}
				bf.add_object(id,size);
				
			}
			else if(command == 3) {
				try {
					id =  Integer.parseInt(st.nextToken());
				}
				catch(Exception e) {
					System.out.println("Invalid number of arguments.Exiting...");
					br.close();
					return ;
				}
				bf.delete_object(id);
				
			}
			else if(command == 4) {
				try {
					id =  Integer.parseInt(st.nextToken());
				}
				catch(Exception e) {
					System.out.println("Invalid number of arguments.Exiting...");
					br.close();
					return ;
				}
				bf.printBin(id);
			}
			else {
				System.out.println("Command type is invalid. Please use an option between between 1 and 4.Exiting....");
				br.close();
				return ;
			}
			
			str = br.readLine();
		}
	}

}
