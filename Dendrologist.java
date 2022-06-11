package dendrologist;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.Scanner;

/**
 * A testbed for an augmented implementation of an AVL tree
 * @author William Duncan, Olivia Cheung
 * @see AVLTreeAPI, AVLTreeException
 * <pre>
 * Date: 10-21-2021
 * CSC 3102 Programming Project # 2
 * Instructor: Dr. Duncan 
 * </pre>
 */
public class Dendrologist
{
    public static void main(String[] args) throws FileNotFoundException 
    {
        String usage = "Dendrologist <order-code> <command-file>\n";
        usage += "  <order-code>:\n";
        usage += "  0 ordered by increasing string length, primary key, and reverse lexicographical order, secondary key\n";
        usage += "  -1 for reverse lexicographical order\n";
        usage += "  1 for lexicographical order\n";
        usage += "  -2 ordered by decreasing string\n";
        usage += "  2 ordered by increasing string\n";
        usage += "  -3 ordered by decreasing string length, primary key, and reverse lexicographical order, secondary key\n";
        usage += "  3 ordered by increasing string length, primary key, and lexicographical order, secondary key\n";      
        if (args.length != 2)
        {
            System.out.println(usage);
            throw new IllegalArgumentException("There should be 2 command line arguments.");
        }
        String Tree = args[1];
        String getInteger = args[0];
        int key; 
       
        key = Integer.parseInt(getInteger);
     
        //checks to make sure the key is between -3 and 3 
        
        if (key != -3 && key != -2 && key!= -1 && key != 0 && key != 1 && key != 2 && key != 3) {
        	
        	System.out.println(usage);
        	System.exit(-1);
        }
        
            
        
        //comparator function and passes key through corresponding case
        
        final int finalKey = key; 
        
        Comparator<String> comparator; 
        
        
        comparator = (Tree_One, Tree_Two) ->{
        	
        switch(finalKey) {

        
        case (-3): 
        	//ordered by decreasing string length, primary key, and reverse lexicographical order, secondary key 
      	
        	
    		if (Tree_One.length() - Tree_Two.length() != 0) {
    		
    			return -1;
		       		
    		}
    			
    			return -1 * Tree_One.compareTo(Tree_Two); 
    		
    		
        	
       
        case(-2):
        	//Ordered by decreasing string length
        	
        		return -1 * Tree_One.length() - Tree_Two.length();
        		  
        	
        case (-1): 
        	//Reverse lexicographical order
        	
        	return -1 * Tree_One.compareTo(Tree_Two);
        	
        case (0): 
        	// Ordered by increasing string length, primary key, and reverse lexicographical order, secondary key 
        	
        	if (Tree_One.length() - Tree_Two.length() != 0) {
        		
	       		return Tree_One.length() - Tree_Two.length(); 
        	}
                
        		
        		return -1 * Tree_One.compareTo(Tree_Two); 
    		   		
    	
        case (1): 
        	//Lexicographical order 
        	
        	return Tree_One.compareTo(Tree_Two);
        
        case(2):
        	//Ordered by increasing string length
        	
        
        	if (Tree_One.length() - Tree_Two.length() != 0) {
    		
        		return Tree_One.length() - Tree_Two.length(); 
		       		
        	}
            	
        	else {
    		
        		return Tree_One.compareTo(Tree_Two);  
		   				
        	}
        	
        	
        case(3):
        	//Ordered by increasing string length, primary key, and lexicographical order, secondary key 
        	
        	
        	if (Tree_One.length() - Tree_Two.length() != 0) {
        		return Tree_One.length() - Tree_Two.length(); 
        	}
        
        	else {
        		
        		return Tree_One.compareTo(Tree_Two); 
        	}
        	
        	
        
		default: 
			System.out.println("Given Key is not applicable");
			return 0;   
 
    }   
};

//creates AVL tree, reads through string.avl, does the corresponding case, and prints 
	AVLTree tree = new AVLTree<>(comparator);
	FileReader reader = null; 
	
	try {
		
		reader = new FileReader(Tree);
	}
	
	catch (Exception e) {
		
		System.out.println(usage);
	}

	try {
		Scanner scan = new Scanner(reader);
	
		while(scan.hasNextLine()) {
		
			String[] array; 
		
			array = scan.nextLine().split(" ");
		
			switch(array[0]) {
		
			case("insert"):
				
				//inserts node 
				tree.insert(array[1]);
				System.out.println("Inserted: " + array[1]);
				break;
			
			case("delete"):
			
				//deletes the node			
				tree.remove(array[1]);
				break;
			
			case("traverse"):
	
			
				if ((Integer.parseInt(array[1]) != 0 && Integer.parseInt(array[1]) != -1)) {
				
					System.out.println(usage);
					break;
				}
		
				if (Integer.parseInt(array[1]) == 0) {
				
					System.out.println("In-Order Traversal");
					
					tree.traverse(Tree_One -> System.out.printf("%s\n", Tree_One));
				}
			
				else {
				
					System.out.println("Level-Order Traversal");
					tree.levelOrder(Tree_One -> System.out.printf("%s\n", Tree_One));
				
				}
			
				break;
			
				
		
			case("stats"):
		
				//prints out the statistics
				System.out.printf("Stats: size = %d, height = %d, diameter = %d, complete? = %B, full? = %B\n", tree.size(), tree.height(), tree.diameter(), tree.isComplete(), tree.isFull());
				break;
			
			default:
				System.out.println("Command not recognized");
			}	
	
		}
	
	
	} catch  (Exception e) {
		System.out.println(e.getStackTrace()[0]);
	}
  }
   
    
}