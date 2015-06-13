import java.util.*;
import java.io.*;

public class ReciprocalLink{
    public static void main(String[] args){
	int pageSize = 1483277;
	int linkSize = 52973671;

	Page[] pageTable = new Page[pageSize];
	int n=0;
	try {
	    File file = new File("pages.txt");
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    String str = br.readLine();
	    String[] page = new String[2];
	    while(str != null){
		page = str.split("\t");
		pageTable[n] = new Page(Integer.parseInt(page[0]), page[1]);
		n++;
		str = br.readLine();
	    }
	    br.close();
	} catch(FileNotFoundException e) {
	    System.out.println("FILE NOT FOUND: " + e);
	} catch(IOException e) {
	    System.out.println("IOExcpetion: " + e);
	}

	Link[] linkPageTable = new Link[linkSize];
	int m = 0;
	try {
	    File file = new File("links.txt");
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    String str = br.readLine();
	    String[] page = new String[2];
	    int id1;
	    int id2;
	    while(str != null){
		page = str.split("\t");
		id1 = Integer.parseInt(page[0]);
		id2 = Integer.parseInt(page[1]);
		linkPageTable[m] = new Link(id1, id2);
		m++;
		str = br.readLine();
	    }
	    br.close();
	} catch(FileNotFoundException e) {
	    System.out.println("FILE NOT FOUND: " + e);
	} catch(IOException e) {
	    System.out.println("IOException: " + e);
	}

    }
}
