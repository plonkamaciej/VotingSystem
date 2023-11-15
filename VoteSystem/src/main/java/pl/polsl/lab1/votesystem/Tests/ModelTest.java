package pl.polsl.lab1.votesystem.Tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static pl.polsl.lab1.votesystem.fileMenager.FileManager.Reader;

import org.junit.jupiter.api.Test;
import pl.polsl.lab1.votesystem.Model.VoteSystemModel;
import pl.polsl.lab1.votesystem.ModelList.VoteSystemModelList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ModelTest {

    VoteSystemModel testUnit = new VoteSystemModel("John", 10);
    VoteSystemModel testUnit2 = new VoteSystemModel("Adam", 5);

    //VoteSystemModelList testModel = (VoteSystemModelList) Arrays.asList(testUnit, testUnit2);

    @Test
    void testVote() {
        testUnit.vote();
        assertEquals(11, testUnit.getVoteCount());
    }

    @Test
    void testUserInputNull() {

        try{
            VoteSystemModel testUnitNull = new VoteSystemModel(null, 10);
            fail("null or empty");
        }catch (Exception e){
            System.out.println("cannot be null");
        }

    }
    @Test
    void testUserListInputNull() {

        try{
            VoteSystemModelList testModel = (VoteSystemModelList) Arrays.asList();
            fail("null or empty");
        }catch (Exception e){
            System.out.println("cannot be null");
        }
    }

}