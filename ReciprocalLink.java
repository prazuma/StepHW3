import java.util.*;
import java.io.*;

public class ReciprocalLink{
    static int pageSize = 1483277;
    
    public static void main(String[] args){
	FileIO fileIO = new FileIO();
	
	String[] pageTable = fileIO.readPages();
	System.out.println("finish read pages.txt");

	Link[] linkPageTable = fileIO.readLinks();
	System.out.println("finish read links.txt");

	int[] counter = countReciprocal(linkPageTable);
	System.out.println("finish count reciprocal");
        
	fileIO.writeCSVFile(linkPageTable, pageTable, counter);
	System.out.println("made result.csv");
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
