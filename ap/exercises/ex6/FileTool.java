package ap.exercises.ex6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileTool {

    public static List<String> getTextFileLines(String filePath){
        try {
            return Files.lines(Paths.get(filePath))
                    .collect(Collectors.toList());
        } catch (IOException e) {
//            throw new RuntimeException(e);
            return null;
        }
    }


}
