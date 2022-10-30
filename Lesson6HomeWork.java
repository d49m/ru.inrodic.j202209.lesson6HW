package ru.inrodic.j202209.lesson6HW;

import ru.inrodic.j202209.lesson6HW.AutomatSpaceNormalizer;

public class Lesson6HomeWork {
    public static void main(String[] args) {

        SpaceNormalizer sp = new AutomatSpaceNormalizer();
        {
            String res = sp.normalizeSpace("    abc,,,cdb   ,     abc, errrr    ");
            System.out.println("res = '" + res + "'");
        }

    }
}
