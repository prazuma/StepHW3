import java.util.*;
import java.io.*;

public class FileIO{
    int pageSize = 1483277;
    
    String[] readPages(){
	String[] pageTable = new String[pageSize];
	int n = 0;
	try {
	    File file = new File("pages.txt");
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

    Link[] readLinks(){
	Link[] linkPageTable = new Link[pageSize];
	for(int i = 0; i < pageSize; i++){
	    linkPageTable[i] = new Link();
	}
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
    
    void writeCSVFile(Link[] linkPageTable, String[] pageTable, int[] counter){
	try {
	    File file = new File("result.csv");
	    FileWriter fw = new FileWriter(file);
	    BufferedWriter bw = new BufferedWriter(fw);
	    double rate;
	    double size;
	    for(int i = 0; i < pageSize; i++){
		bw.write(pageTable[i] + ",");
		size = (double)linkPageTable[i].getSize();
		if(counter[i] == 0){
		    rate = 0;
		} else {
		    rate = counter[i] / size * 100;
		}
		bw.write(String.valueOf(rate));
		bw.newLine();
	    }
	    bw.flush();
	    bw.close();
	    fw.close();
	} catch(IOException e) {
	    e.printStackTrace();
	}
    }
}
