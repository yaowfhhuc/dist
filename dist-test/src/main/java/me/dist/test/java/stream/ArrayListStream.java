package me.dist.test.java.stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yaowf
 * @createDate 2018-05-10 9:26
 */
public class ArrayListStream {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        String str = list.stream().filter(t->t.equalsIgnoreCase("aa"))
                .reduce("",String::concat);
        System.out.println("-------------------1--string filter----------------------");
        System.out.println(str);
        System.out.println("-------------------2--collect StringBuilder----------------------");

        System.out.println(list.stream().collect(StringBuilder::new,
                StringBuilder::append,StringBuilder::append).toString());
        System.out.println("-------------------3----Collectors.joining()--------------------");
        System.out.println(list.stream().collect(Collectors.joining()));




        System.out.println("-------------------4--int filter %3----------------------");
        List<Integer> intlist = new ArrayList<>();
        for(int i=0;i<10;i++){
            intlist.add(i*2);
        }
        System.out.println(
                intlist.stream().filter( t->t.intValue()%3==0)
                .mapToInt(i->i.intValue()).toArray().length
        );
        System.out.println("-------------------5--collect hashSet----------------------");
        intlist.stream().collect(HashSet::new,HashSet::add,HashSet::addAll).forEach(t->System.out.println(t));
        System.out.println("-------------------6----Collectors.toSet()--------------------");

        intlist.stream().collect(Collectors.toSet()).forEach(System.out::println);
        System.out.println("-------------------7--Collectors.toMap()----------------------");
        intlist.stream().collect(Collectors.toMap((index)->index,(v)->v)).forEach((k,va)->System.out.println(k+":"+va));

        Arrays.stream(ArrayListStream.class.getMethods())
                .forEach(System.out::println);

    }
}
