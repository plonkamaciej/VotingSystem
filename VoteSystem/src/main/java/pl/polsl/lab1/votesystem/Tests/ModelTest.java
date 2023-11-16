package pl.polsl.lab1.votesystem.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.lab1.votesystem.Model.VoteSystemModel;
import pl.polsl.lab1.votesystem.ModelList.VoteSystemModelList;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the {@link pl.polsl.lab1.votesystem.Model.VoteSystemModel} class.
 */
class ModelTest {

    private VoteSystemModel voteSystemModel;

    VoteSystemModel testUnit = new VoteSystemModel("John", 10);

    /**
     * Test for the {@link pl.polsl.lab1.votesystem.Model.VoteSystemModel#vote()} method.
     */
    @Test
    void testVote() {
        testUnit.vote();
        assertEquals(11, testUnit.getVoteCount());
    }

    /**
     * Test for handling null user input in the constructor.
     */
    @Test
    void testUserInputNull() {
        assertThrows(NullPointerException.class, () -> new VoteSystemModel(null, 10), "Name cannot be null");
    }

    /**
     * Test for handling null or empty user list input.
     */
    @Test
    void testUserListInputNull() {
        try {
            VoteSystemModelList testModel = (VoteSystemModelList) Arrays.asList();
            fail("null or empty");
        } catch (Exception ignored) {

        }
    }

    /**
     * Set up the initial state before each test.
     */
    @BeforeEach
    void setUp() {
        voteSystemModel = new VoteSystemModel("John", 0);
    }

    /**
     * Parameterized test for the {@link pl.polsl.lab1.votesystem.Model.VoteSystemModel#getName()} method.
     *
     * @param name              The name to set for the test unit.
     * @param initialVoteCount  The initial vote count to set for the test unit.
     */
    @ParameterizedTest
    @CsvSource({ "Alice, 0", "Bob, 5", "Eve, 10" })
    void testGetName(String name, int initialVoteCount) {
        voteSystemModel = new VoteSystemModel(name, initialVoteCount);
        assertEquals(name, voteSystemModel.getName());
    }

    /**
     * Parameterized test for the {@link pl.polsl.lab1.votesystem.Model.VoteSystemModel#getVoteCount()} method.
     *
     * @param initialVoteCount  The initial vote count to set for the test unit.
     * @param expectedVoteCount The expected vote count after initialization.
     */
    @ParameterizedTest
    @CsvSource({ "0, 0", "5, 5", "10, 10" })
    void testGetVoteCount(int initialVoteCount, int expectedVoteCount) {
        voteSystemModel = new VoteSystemModel("John", initialVoteCount);
        assertEquals(expectedVoteCount, voteSystemModel.getVoteCount());
    }

    /**
     * Parameterized test for the {@link pl.polsl.lab1.votesystem.Model.VoteSystemModel#vote()} method.
     *
     * @param initialVoteCount  The initial vote count to set for the test unit.
     * @param expectedVoteCount The expected vote count after voting.
     */
    @ParameterizedTest
    @CsvSource({ "0, 1", "5, 6", "10, 11" })
    void testVote(int initialVoteCount, int expectedVoteCount) {
        voteSystemModel = new VoteSystemModel("John", initialVoteCount);
        voteSystemModel.vote();
        assertEquals(expectedVoteCount, voteSystemModel.getVoteCount());
    }
}
