package org.pshenko.javacore.oop.primitivesandreferences;

public class PrimitivesAndReferencesLesson {
    public static void main(String[] args) {
        int value = 10;
        changeValue(value);
        System.out.println(value);

        StringBuilder sb = new StringBuilder("Hello");
        changeStringBuilder(sb);
        System.out.println(sb);
    }

    private static void changeValue(int num) {
        num = 20;
    }

    private static void changeStringBuilder(StringBuilder sb) {
        sb.append(" World");
    }
}
