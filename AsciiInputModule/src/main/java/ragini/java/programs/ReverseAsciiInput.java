package ragini.java.programs;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

public class ReverseAsciiInput {
    public static void main(String[] args) {
        ReverseAsciiInput a1 = new ReverseAsciiInput();
        try {
            Properties properties = loadProperties("application.properties");
            FilePaths paths = new FilePaths();
            a1.setPaths(paths, properties);
            a1.reverseInputFileContent(paths);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This will load input/out file paths from properties file
     * @param resourceFileName String
     * @return Properties
     * @throws IOException io
     */
    public static Properties loadProperties(String resourceFileName) throws IOException {
        Properties configuration = new Properties();
        InputStream inputStream = ReverseAsciiInput.class.getClassLoader().getResourceAsStream(resourceFileName);
        configuration.load(inputStream);
        if (!Objects.isNull(inputStream)) {
            inputStream.close();
        }
        return configuration;
    }

    /**
     * It will read the input file and write output to another file
     * @param paths FilePaths
     */
    public void reverseInputFileContent(FilePaths paths) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(paths.getInputFileName()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(paths.getOutputFileName(), false));

            String line;
            while ((line = reader.readLine()) != null) {
                StringBuilder stringBuffer = new StringBuilder();
                stringBuffer.append(line);
                stringBuffer.reverse();
                stringBuffer.append("\n");
                writer.write(String.valueOf(stringBuffer));
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Setting path of input / out dirs and files
     * @param paths FilePaths
     * @param properties Properties
     */
    public void setPaths(FilePaths paths, Properties properties) {
        File mk = new File(String.valueOf(properties.get("output_file_path")));
        mk.mkdir();
        paths.setInputFileName(properties.get("input_file_path") + String.valueOf(properties.get("input_file_name")));
        paths.setOutputFileName(properties.get("output_file_path") + String.valueOf(properties.get("output_file_name")));
    }
}
