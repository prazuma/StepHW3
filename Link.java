import java.util.*;

public class Link{
    ArrayList<Integer> links;

    Link(){
	links = new ArrayList<Integer>();
    }

    ArrayList<Integer> getLinks(){
	return links;
    }

    void setLinks(int id){
	links.add(id);
    }
}
