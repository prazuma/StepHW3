import java.util.*;
import java.io.*;

public class ReciprocalLink{
    static int pageSize = 1483277;
    public static void main(String[] args){
	/*
	int pageSize = 1483277;
	int linkSize = 52973671;
	*/
	
	String[] pageTable = readPages();
	System.out.println("finish1");

	Link[] linkPageTable = readLinks();
	System.out.println("finish2");

	int[] counter = new int[pageSize];
	for(int i = 0; i < pageSize; i++){
	    ArrayList<Integer> list = linkPageTable[i].getList();
	    for(int j = 0; j < list.size(); j++){
		int id2 = list.get(j);
		ArrayList<Integer> list2 = linkPageTable[id2].getList();
		if(list2.contains(i)){
		    counter[i]++;
		}
	    }
	}
	System.out.println("finish3");

	int max = 0;
	int id = 0;
	for(int i = 0; i < pageSize; i++){
	    if(max < counter[i]){
		max = counter[i];
		id = i;
	    }
	}
	System.out.println(pageTable[id] + ": " + max);
	
    }

    static String[] readPages(){
	String[] pageTable = new String[pageSize];
	int n = 0;
	try {
	    File file = new File("test.txt");
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    String str = br.readLine();
	    String[] page = new String[2];
	    while(str != null){
		page = str.split("\t");
		pageTable[n] = page[1];
		n++;
		str = br.readLine();
	    }
	    br.close();
	} catch(FileNotFoundException e) {
	    System.out.println("FILE NOT FOUND: " + e);
	} catch(IOException e) {
	    System.out.println("IOExcpetion: " + e);
	}
	return pageTable;
    }

    static Link[] readLinks(){
	Link[] linkPageTable = new Link[pageSize];
	for(int i = 0; i < pageSize; i++){
	    linkPageTable[i] = new Link();
	}
	int m = 0;
	try {
	    File file = new File("test2.txt");
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    String str = br.readLine();
	    String[] page = new String[2];
	    int id1;
	    int id2;
	    while(str != null){
		page = str.split("\t");
		id1 = Integer.parseInt(page[0]);
		id2 = Integer.parseInt(page[1]);
		linkPageTable[id1].addLink(id2);
		m++;
		str = br.readLine();
	    }
	    br.close();
	} catch(FileNotFoundException e) {
	    System.out.println("FILE NOT FOUND: " + e);
	} catch(IOException e) {
	    System.out.println("IOException: " + e);
	}
	return linkPageTable;
    }
}
