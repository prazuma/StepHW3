import java.util.*;
import java.io.*;

public class FileIO{
    int pageSize = 1483277;
    
    String[] readPages(){//pages.txtを読み込む
	String[] pageTable = new String[pageSize];
	int n = 0;
	try {
	    File file = new File("pages.txt");
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    String str = br.readLine();
	    String[] page = new String[2];
	    while(str != null){//n番目の配列にn番目のページ名を格納
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

    Link[] readLinks(){//links.txtを読み込む
	Link[] linkPageTable = new Link[pageSize];//固定長×可変長の擬似二次元配列
	for(int i = 0; i < pageSize; i++){//初期化
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
	    while(str != null){//id1番のリンクをid1番目の第2次配列に入れる（本当は、リストに入れてる）
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
    
    void writeCSVFile(Rate[] rate){//result.csvを出力
	try {
	    File file = new File("result.csv");
	    FileWriter fw = new FileWriter(file);
	    BufferedWriter bw = new BufferedWriter(fw);
	    for(int i = 0; i < pageSize; i++){
		bw.write(rate[i].name + ",");//ページ名
		bw.write(String.valueOf(rate[i].rate));//そのページの相互リンク率
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
