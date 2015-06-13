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
	    System.out.println("FILE NOT FOUND" + e);
	} catch(IOException e) {
	    System.out.println("IOExcpetion" + e);
	}

    }
}
