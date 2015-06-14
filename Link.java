import java.util.*;

public class Link{
    ArrayList<Integer> list;
    
    Link(){
	list = new ArrayList<Integer>();
    }

    void addLink(int id){
	list.add(id);
    }

    ArrayList<Integer> getList(){
	return list;
    }

    int getSize(){
	return list.size();
    }

}
