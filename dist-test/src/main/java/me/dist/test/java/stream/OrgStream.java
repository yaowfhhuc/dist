package me.dist.test.java.stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author yaowf
 * @createDate 2018-05-10 17:01
 */
public class OrgStream {

    public static void main(String[] args) {

        IntStream intStream = IntStream.range(0,10);
        intStream.filter(i->i%3==0).forEach(System.out::println);

        Stream.of("aa","bb").forEach(System.out::println);

        Arrays.stream(new Object[]{"","",""}).forEach(System.out::println);
    }
}
