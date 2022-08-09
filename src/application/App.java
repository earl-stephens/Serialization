package application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Person implements Serializable {
	/* have to have the interface if going to use
	 * serialization
	 */
	private String name;
	private int id;
	
	public Person(String name, int id) {
		this.name = name;
		this.id = id;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", id=" + id + "]";
	}
	
	
	
}

public class App {
	public static void main(String[] args) {
		/* serialization is used for saving
		 * entire objects to a file. Deserializtion is 
		 * loading an object from a file.
		 */
		
		Person p1 = new Person("Joe", 1);
		
		String pathString = "test.bin";
		
		try(var os = new ObjectOutputStream(new FileOutputStream(pathString))) {
			os.writeObject(p1);
		}
		catch(FileNotFoundException e) {
			System.out.println("Cannot write file: " + pathString);
		}
		catch(IOException e) {
			System.out.println("Cannot write file: " + pathString);
		}
		
		System.out.println("Completed. " + pathString + " created.");
	}
}
