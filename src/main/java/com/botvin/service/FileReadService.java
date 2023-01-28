package com.botvin.service;

import com.botvin.exception.IncorrectLineException;
import com.botvin.model.DeviceType;
import com.botvin.model.Telephone;
import com.botvin.model.Television;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Double.parseDouble;

@Getter
@Setter
public class FileReadService {

    public FileReadService() {
    }

    public LinkedList<String> readCsv(final String path) throws IncorrectLineException, IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        BufferedInputStream input = (BufferedInputStream) loader.getResourceAsStream(path);
        byte[] array = new byte[input.available()];
        LinkedList<String> list = new LinkedList<>();
        try {
            input.read(array);
            String data = new String(array);
            if (data.contains(",,")) { // FIXME: правильно обробити виняток!
                throw new IncorrectLineException(data);
            }
            String[] lines = data.split("\n");
            for (String line : lines) {
                list.add(line);
            }
            input.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

    public List<Telephone> telephoneList(final LinkedList<String> list) {
        final LinkedList<Telephone> telephones = new LinkedList<>();
        for (int line = 0; line < list.size(); line++) {
            final String[] words = list.get(line).split(","); //FIXME: "," || ", && \s"
            for (int i = 0; i < words.length; i++) {
                if (words[i].contains("Telephone")) {
                    String series = words[1];
                    String model = words[2];
                    String screenType = words[4];
                    double price = Double.parseDouble(words[6]);
                    Telephone telephone = new Telephone(DeviceType.TELEPHONE, series, model, screenType, price);
                    telephones.add(telephone);
                }
            }
        }
        return telephones;
    }

    public List<Television> televisionList(final LinkedList<String> list) {
        final LinkedList<Television> televisions = new LinkedList<>();
        for (int line = 0; line < list.size(); line++) {
            final String[] words = list.get(line).split(","); //FIXME: "," || ", && \s"
            for (int i = 0; i < words.length; i++) {
                if (words[i].contains("Television")) {
                    String series = words[1];
                    float diagonal = Float.parseFloat(words[3]);
                    String screenType = words[4];
                    String country = words[5];
                    double price = Double.parseDouble(words[6]);
                    Television television = new Television(DeviceType.TELEVISION, series, diagonal, screenType, country, price);
                    televisions.add(television);
                }
            }
        }
        return televisions;
    }






}
