package ru.tyanmt.task;

import org.junit.Test;
import ru.tyanmt.task.util.Dictionary;
import ru.tyanmt.task.util.WordHolder;
import ru.tyanmt.task.util.WordTree;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by mityan on 26.08.2015.
 */
public class EncodingTest {

    private static final String WORD = "testWord";
    private static final String HALF_WORD = WORD.substring(0,4);
    private static final String WORD_WITH_SUFFIX = WORD+"suffix";

    private static final String TEST_DICTIONARY = "testDict.txt";

    @Test
    public void shouldReturnSavedWord(){
        WordHolder tree = new WordTree();
        tree.addWord(WORD);
        assertThat(tree.containsWord(WORD),equalTo(true));
    }

    @Test
    public void shouldReturnFalseIfEmpty(){
        WordHolder tree = new WordTree();
        assertThat(tree.containsWord(WORD),equalTo(false));
    }

    @Test
    public void shouldReturnFalseWhenWordWithSuffixProvided(){
        WordHolder tree = new WordTree();
        tree.addWord(WORD_WITH_SUFFIX);
        assertThat(tree.containsWord(WORD),equalTo(false));
    }

    @Test
    public void shouldReturnFalseWhenHalfWordProvided(){
        WordHolder tree = new WordTree();
        tree.addWord(HALF_WORD);
        assertThat(tree.containsWord(WORD),equalTo(false));
    }

    @Test
    public void shouldReturnTrueIfContainsWord() throws IOException, URISyntaxException {
        Dictionary dict = new Dictionary(TEST_DICTIONARY);
        assertThat(dict.containsWord(WORD),equalTo(true));
    }

    @Test
    public void shouldReturnFalseIfDoesntContainWord() throws IOException, URISyntaxException {
        Dictionary dict = new Dictionary(TEST_DICTIONARY);
        assertThat(dict.containsWord(WORD_WITH_SUFFIX), equalTo(false));
    }


}
