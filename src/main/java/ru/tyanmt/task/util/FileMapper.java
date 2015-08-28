package ru.tyanmt.task.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

/**
 * Created by mityan on 24.08.2015.
 */
public class FileMapper implements Function<Integer, List<Character>> {

    public static Map<Integer, List<Character>> numberMap = new HashMap<>();

    public FileMapper(String fileName) throws IOException, URISyntaxException {
        range(0, 10).forEach(i -> numberMap.put(i, new ArrayList<>(i)));
        Path mapperFile = Paths.get(ClassLoader.getSystemResource(fileName).toURI());
        Files.lines(mapperFile).map(line -> Arrays.asList(line.split("\\|")))
                .forEach(FileMapper::addValuesToNumberMap);
    }

    private static void addValuesToNumberMap(List<String> values) {
        range(0, 10).forEach(i ->
                        numberMap.get(i).addAll(
                                Arrays.asList(values.get(i).split(" ")).stream()
                                        .filter(value -> !value.isEmpty())
                                        .map(letter -> letter.charAt(0))
                                        .collect(toList())
                        )
        );
    }

    @Override
    public List<Character> apply(Integer number) {
        return numberMap.get(number);
    }
}
