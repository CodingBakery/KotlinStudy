package com.kidel.expend;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 자바에서 호출할 때: 첫 번째 인자로 수신 객체가 들어간다.
        String decoStr = StringUtilsKt.getDecorated("KIDEL", "#####", "#####");
        String hello = StringUtilsKt.getHello("");

        System.out.println("decoStr ==> " + decoStr);
        System.out.println("hello ==> " + hello);

        // 컬렉션 확장함수
        List<Integer> numList = List.of(1, 55, 2, 22);
        double avg = CollectionUtilsKt.getPartialAverage(numList, 1, 2);
        System.out.println("PARTIAL AVG => " + avg);
    }
}
