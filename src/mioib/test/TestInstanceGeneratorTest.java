package mioib.test;

import mioib.qap.model.QAPInstance;
import mioib.qap.utils.TestInstanceGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestInstanceGeneratorTest {

    @Test
    void loadedFileShouldBeSameAsHardCoded(){
        //given
        final QAPInstance expected = TestInstanceGenerator.Bur26a();
        //when
        final QAPInstance actual = TestInstanceGenerator.fromFile("http://anjos.mgi.polymtl.ca/qaplib/data.d/bur26a.dat");
        //then
        assertEquals(expected,actual);

    }

}