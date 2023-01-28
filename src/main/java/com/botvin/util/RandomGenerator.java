package com.botvin.util;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Setter
@Getter
public class RandomGenerator {

    private static final Random random = new Random();
    private static String[] emails = {"botvinmaxim@gmail.com", "v.cheslav84@gmail.com", "rostislavdubina94@gmail.com"
            , "jmakaaa@gmail.com", "vizion1992@gmail.com", "foed111@gmail.com", "mihalovivan90@gmail.com"
            , "mlarisa7777@gmail.com", "vladnecrutenko1@gmail.com", "dp191192orv@gmail.com"};

    public static String generateRandomEmail() {
        return emails[random.nextInt(emails.length)];
    }

    public static int generateRandomAge() {
        return random.nextInt(5, 101);
    }

}