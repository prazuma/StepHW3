import java.util.*;
import java.io.*;

public class ReciprocalLink{
    static int pageSize = 1483277;
    
    public static void main(String[] args){
	FileIO fileIO = new FileIO();

	/*
	  read pages.txt
	  pageTable[i] means pageName
	 */
	String[] pageTable = fileIO.readPages();
	System.out.println("finish read pages.txt");
	
	/*
	  read links.txt
	  linkPageTable[i] = a set of links for pageTable[i]
	 */
	Link[] linkPageTable = fileIO.readLinks();
	System.out.println("finish read links.txt");

	/*
	  count reciprocal links
	  counter[i] means pageTable[i]'s reciprocal links
	 */
	int[] counter = countReciprocal(linkPageTable);
	System.out.println("finish count reciprocal");

	/*
	  find rate
	 */
	double[] rate = new double[pageSize];
	double size;
	for(int i = 0; i < pageSize; i++){
	    size = (double)linkPageTable[i].getSize();
	    if(counter[i] == 0){
		rate[i] = 0;
	    } else {
		rate[i] = counter[i] / size * 100;
	    }
	}

	/*
	  merge rate & pageTable
	  rateList[i] has PageName and its rate
	 */
	Rate[] rateList = new Rate[pageSize];
	for(int i = 0; i < pageSize; i++){
	    rateList[i] = new Rate(pageTable[i], rate[i]);
	}

	/*
	  write result.csv
	 */
	fileIO.writeCSVFile(rateList);
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
