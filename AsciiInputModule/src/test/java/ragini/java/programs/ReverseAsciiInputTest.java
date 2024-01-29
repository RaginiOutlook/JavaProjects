package ragini.java.programs;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.*;

@RunWith(MockitoJUnitRunner.class)
public class ReverseAsciiInputTest {
    private ReverseAsciiInput asi;
    private FilePaths paths;

    @Test
    public void asciiTest() throws IOException {
        String val = "ABCD";
        String rVal = "DCBA";

        File tmpFile1 = File.createTempFile("testInput", ".txt");
        FileWriter fw1 = new FileWriter(tmpFile1);
        BufferedWriter bw1 = new BufferedWriter(fw1);
        bw1.write(val);
        bw1.close();

        FileReader fr1 = new FileReader(tmpFile1);
        BufferedReader br1 = new BufferedReader(fr1);
        Assert.assertEquals(val, String.valueOf(br1.readLine()));

        File tmpFile2 = File.createTempFile("testOutput", ".txt");

        paths = new FilePaths();
        paths.setInputFileName(tmpFile1.getAbsolutePath());
        paths.setOutputFileName(tmpFile2.getAbsolutePath());
        asi = new ReverseAsciiInput();

        asi.reverseInputFileContent(paths);

        FileReader fr2 = new FileReader(tmpFile2.getAbsolutePath());
        BufferedReader br2 = new BufferedReader(fr2);
        Assert.assertEquals(rVal, String.valueOf(br2.readLine()));

        tmpFile1.deleteOnExit();
        tmpFile2.deleteOnExit();
    }
}
