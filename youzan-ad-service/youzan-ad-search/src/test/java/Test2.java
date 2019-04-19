import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by baimugudu on 2019/4/19
 */
public class Test2 {

    public static boolean isFol(Integer i){
        System.out.println(i);
        System.out.println(i%2>0.5);
        return i%2>0.5;
    }

    public static void main(String[] args) {
        // final String format = String.format("%s%s", "a", "b");
        // System.out.println(format);
        System.out.println("----------------------");

        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(9);
        integers.add(3);
        System.out.println(integers);

        HashSet<Integer> integers1 = new HashSet<>();
        integers1.add(2);
        integers1.add(3);
        integers1.add(4);
        integers1.add(5);
        System.out.println(integers1);

        boolean subCollection = CollectionUtils.isSubCollection(integers1, integers);
        //System.out.println(subCollection);
        //System.out.println(integers);
        //System.out.println(integers1);


        CollectionUtils.filter(
                integers1,
                i->isFol(i)
        );





    }
}
