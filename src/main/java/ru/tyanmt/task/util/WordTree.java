package ru.tyanmt.task.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by mityan on 24.08.2015.
 */
public class WordTree implements WordHolder {

    private Node head = new Node('\0');

    @Override
    public void addWord(String word) {
        head.addChars(getCharList(word));
    }

    @Override
    public boolean containsWord(String word) {
        return head.containsChars(getCharList(word));
    }

    private List<Character> getCharList(String word) {
        return word.chars().mapToObj(i -> (char) i).collect(toList());
    }


    private class Node {
        private final Character value;
        private boolean isEndingChar;
        private List<Node> children = new ArrayList<>();

        private Node(Character value) {
            this.value = value;
        }

        private void addChars(List<Character> chars) {
            char nextChar = chars.remove(0);
            Optional<Node> nextChild = getNextChild(nextChar);
            if (nextChild.isPresent()) {
                nextChild.get().addChars(chars);
            } else {
                Node child = new Node(nextChar);
                this.children.add(child);
                if (chars.isEmpty()) {
                    this.isEndingChar = true;
                } else {
                    child.addChars(chars);
                }
            }
        }

        private boolean containsChars(List<Character> chars) {
            char nextChar = chars.remove(0);
            Optional<Node> nextChild = getNextChild(nextChar);
            if (nextChild.isPresent()) {
                if (chars.isEmpty()) {
                    return this.isEndingChar;
                } else {
                    return nextChild.get().containsChars(chars);
                }
            } else {
                return false;
            }
        }

        private Optional<Node> getNextChild(char nextChar) {
            return this.children.stream().filter(child -> child.value == nextChar).findFirst();
        }
    }
}
