import java.util.*;
import java.io.*;

public class ReciprocalLink{
    static int pageSize = 1483277;
    
    public static void main(String[] args){
	FileIO fileIO = new FileIO();
	String[] pageTable = fileIO.readPages();
	System.out.println("finish read pages.txt");

	Link[] linkPageTable = readLinks();
	System.out.println("finish read links.txt");

	int[] counter = countReciprocal(linkPageTable);
	System.out.println("finish count reciprocal");
        
	writeCSVFile(linkPageTable, pageTable, counter);
	System.out.println("made result.csv");
    }

    static String[] readPages(){
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

    static void writeCSVFile(Link[] linkPageTable, String[] pageTable, int[] counter){
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

    static Link[] readLinks(){
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

    static int[] countReciprocal(Link[] linkPageTable){
	int[] counter = new int[pageSize];
	for(int i = 0; i < pageSize; i++){
	    ArrayList<Integer> list = linkPageTable[i].getList();
	    for(int j = 0; j < list.size(); j++){
		int link = list.get(j);
		ArrayList<Integer> list2 = linkPageTable[link].getList();
		if(list2.contains(i)){
		    counter[i]++;
		}
	    }
	}
	return counter;
    }
}
