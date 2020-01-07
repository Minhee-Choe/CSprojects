package FileManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class FileManager {
	Vector<String> vector = new Vector<String>();
	
	public void loadMap(String file){
		String a;
		try{
			BufferedReader rd=new BufferedReader(new FileReader(file));//c:\\oop\\map.dat
			while((a=rd.readLine())!=null) vector.addElement(a);
			rd.close();
		}
		catch (IOException e) {//***************
			e.printStackTrace();
		}
	}
	
	public char[][] buildMap(){
		char[][] array = new char[vector.lastIndexOf(vector.lastElement())+1][];
		for(int i=0;i<array.length;i++) array[i]=vector.get(i).toCharArray();
		return array;
	}
}
