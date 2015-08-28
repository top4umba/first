package ru.tyanmt.task.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by mityan on 24.08.2015.
 */
public class Dictionary {

    private WordHolder wordHolder = new WordTree();

    public Dictionary(String fileName) throws URISyntaxException, IOException {
        Path file = Paths.get(ClassLoader.getSystemResource(fileName).toURI());
        Files.lines(file).forEach(word ->
            wordHolder.addWord(word)
        );
    }

    public boolean containsWord(String word){
        return wordHolder.containsWord(word);
    }


    public boolean containsSubstring(String word){
        return wordHolder.containsWord(word);
    }





}
