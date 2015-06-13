import java.util.*;
import java.io.*;

public class ReciprocalLink{
    public static void main(String[] args){

	ArrayList<Page> pageList = new ArrayList<Page>();
	try {
	    File file = new File("pages.txt");
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    String str = br.readLine();
	    String[] page = new String[2];
	    while(str != null){
		page = str.split("\t");
		pageList.add(new Page(Integer.parseInt(page[0]), page[1]));
		str = br.readLine();
	    }
	    br.close();
	} catch(FileNotFoundException e) {
	    System.out.println("FILE NOT FOUND: " + e);
	} catch(IOException e) {
	    System.out.println("IOExcpetion: " + e);
	}

	//	ArrayList<Integer> linkPageList = new ArrayList<Integer>();
	//	ArrayList<Link> linkPageList = new ArrayList<Link>();
	Link[] linkPageList = new Link[pageList.size()];
	for(int i = 0; i < linkPageList.length; i++){
	    linkPageList[i] = new Link();
	}
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
		linkPageList[id1].setLinks(id2);
		//linkPageList.add(Integer.parseInt(page[0]), Integer.parseInt(page[1]));
		str = br.readLine();
	    }
	    br.close();
	} catch(FileNotFoundException e) {
	    System.out.println("FILE NOT FOUND: " + e);
	} catch(IOException e) {
	    System.out.println("IOException: " + e);
	}
	//System.out.println(linkPageList.size());
	//System.out.println("finish");
	System.out.println((linkPageList[0].getLinks()).size());
	
    }
}
