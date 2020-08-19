package com.czxy;

import java.util.HashSet;

/**
 * @author 庭前云落
 * @Date 2020/4/3 15:35
 * @description
 */
public class Test {

    public static void main(String[] args) {
        String[] X = {"a", "c", "e"};
        String[] Y = {"b", "c"};

        HashSet<String> Z = new HashSet<>();
        for (String x : X) {
            Z.add(x);
        }
        for (String s : Y) {
            Z.add(s);
        }
        System.out.println(Z);

    }
}


