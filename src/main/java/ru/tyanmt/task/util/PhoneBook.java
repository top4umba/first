package ru.tyanmt.task.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by mityan on 24.08.2015.
 */
public class PhoneBook {

    List<String> items;

    public PhoneBook(String fileName) throws URISyntaxException, IOException {
        Path file = Paths.get(ClassLoader.getSystemResource(fileName).toURI());
        items = Files.readAllLines(file);
    }

    public String get(int number) {
        return items.get(number);
    }

    public List<String> getValues() {
        return items.stream().collect(toList());
    }

    public Stream<String> stream() {
        return items.stream();
    }
}
