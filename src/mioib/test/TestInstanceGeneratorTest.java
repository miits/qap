package mioib.test;

import mioib.qap.model.QAPInstance;
import mioib.qap.utils.TestInstanceGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TestInstanceGeneratorTest {

    @Test
    void loadedFileShouldBeSameAsHardCoded(){
        //given
        final QAPInstance expected = TestInstanceGenerator.Bur26a();
        //when
        final QAPInstance actual = TestInstanceGenerator.fromFile("http://anjos.mgi.polymtl.ca/qaplib/data.d/bur26a.dat", Arrays.asList(26, 15, 11, 7, 4, 12, 13, 2, 6, 18, 1, 5, 9, 21, 8, 14, 3, 20, 19, 25, 17, 10, 16, 24, 23, 22), "bur26a");
        //then
        assertEquals(expected,actual);

    }

}