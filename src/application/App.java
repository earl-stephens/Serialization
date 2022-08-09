package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Person implements Serializable {
	/* have to have the interface if going to use
	 * serialization
	 * Use the transient keyword to stop a field from
	 * being serialized.
	 */
	private String name;
	private transient int id;
	
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
		
		try(var os = new ObjectInputStream(new FileInputStream(pathString))) {
			Person p = (Person)os.readObject();
			/*have to know what the object is.  Have to have the object
			 * defined in the program.  Have to cast to that object.  When
			 * deserializing, the object constructors are not run, so don't 
			 * put the data in for that
			 */
			
			System.out.println(p);
		}
		catch(FileNotFoundException e) {
			System.out.println("Cannot open file: " + pathString);
		}
		catch(IOException e) {
			System.out.println("Cannot read file: " + pathString);
		} catch (ClassNotFoundException e) {
			/*thrown if we try to read an object that is not 
			 * available in our program
			 */
			System.out.println("Cannot read object from file: " + pathString);
		}
	}
}
