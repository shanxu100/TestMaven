package weather;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList<String> list=new LinkedList<>();
        list.addFirst("abc");

        for (String s:list){
            System.out.println(s);
        }

    }


}
