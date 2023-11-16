package pl.polsl.lab1.votesystem.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.lab1.votesystem.Model.VoteSystemModel;
import pl.polsl.lab1.votesystem.ModelList.VoteSystemModelList;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit tests for the {@link VoteSystemModelList} class.
 */
public class ModelListTest {

    private VoteSystemModelList voteSystemModelList;

    /**
     * Set up a fresh instance of {@link VoteSystemModelList} before each test.
     */
    @BeforeEach
    void setUp() {
        voteSystemModelList = new VoteSystemModelList();
    }

    /**
     * Parameterized test for the {@link VoteSystemModelList#vote(int)} method.
     *
     * @param name              The name of the candidate.
     * @param initialVoteCount  The initial vote count for the candidate.
     * @param expectedVoteCount The expected vote count after voting.
     * @throws IOException if an error occurs during the test.
     */
    @ParameterizedTest
    @CsvSource({ "John, 0, 1", "Alice, 2, 3", "Bob, 5, 6" })
    void testVote(String name, int initialVoteCount, int expectedVoteCount) throws IOException {
        VoteSystemModel candidate = new VoteSystemModel(name, initialVoteCount);
        voteSystemModelList.addVoteSystemModelList(candidate);
        voteSystemModelList.vote(0);
        assertEquals(expectedVoteCount, voteSystemModelList.getCandidateList().get(0).getVoteCount());
    }
}
