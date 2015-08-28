package ru.tyanmt.task;

import ru.tyanmt.task.util.Dictionary;
import ru.tyanmt.task.util.FileMapper;
import ru.tyanmt.task.util.PhoneBook;

import java.util.List;
import java.util.Map;

/**
 * Created by mityan on 24.08.2015.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        FileMapper mapper = new FileMapper("mapper.txt");
        PhoneBook phoneBook = new PhoneBook("phonebook.txt");
        Encoder encoder = new Encoder(mapper, new Dictionary("dictionary.txt"));
        for (Map.Entry<String,List<String>> number: encoder.encodePhonebook(phoneBook).entrySet()){
            System.out.println(number.getKey()+": "+number.getValue());
        }
    }
}
