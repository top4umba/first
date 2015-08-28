package ru.tyanmt.task;

import ru.tyanmt.task.util.Dictionary;
import ru.tyanmt.task.util.PhoneBook;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * Created by mityan on 24.08.2015.
 */
public class Encoder {
    private final Function<Integer, List<Character>> getMappedCaharcters;
    private final Dictionary dictionary;
    private final Function<String, List<Integer>> extractDigits = number ->
            number.chars().boxed().filter(Character::isDigit)
                    .map(Character::getNumericValue)
                    .collect(toList());


    public Encoder(Function<Integer, List<Character>> getMappedCaharcters, Dictionary dictionary) {
        this.getMappedCaharcters = getMappedCaharcters;
        this.dictionary = dictionary;
    }

    public Map<String, List<String>> encodePhonebook(PhoneBook phonepook) {
        Map<String, List<String>> numberEncoding = new HashMap<>();
        phonepook.stream().map(extractDigits).forEach(number ->
                        numberEncoding.put(String.join("", number.stream().map(digit -> digit.toString()).collect(toList())), encodeNumber(number))
        );
        return numberEncoding;
    }

    private void processNextDigit(List<String> words, List<List<Character>> possibleCharacters, String prefix, int position) {
        Optional<List<Character>> chars = possibleCharacters.stream().findFirst();
        if (chars.isPresent()) {
            List<List<Character>> passCharacters = new ArrayList<>(possibleCharacters.subList(1, possibleCharacters.size()));
            chars.get().forEach(ch -> {
                String currentPrefix = prefix + ch;
                if (Character.isDigit(ch) && position == prefix.length() && (prefix.isEmpty() || !Character.isDigit(prefix.charAt(prefix.length()-1)))) {
                    processNextDigit(words, passCharacters, currentPrefix, currentPrefix.length());
                }
                if (dictionary.containsWord(currentPrefix.substring(position))) {
                    processNextDigit(words, passCharacters, currentPrefix, currentPrefix.length());
                }
                processNextDigit(words, passCharacters, currentPrefix, position);
            });
        } else {
            if (position == prefix.length()) {
                words.add(prefix);
            }
        }

    }

    private List<String> encodeNumber(List<Integer> digits) {
        List<List<Character>> characters = digits.stream().map(getMappedCaharcters).collect(toList());
        List<String> words = new ArrayList<>();
        processNextDigit(words, characters, "", 0);
        return words;
    }


}
