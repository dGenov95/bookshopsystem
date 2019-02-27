package app.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class FileContentsReader {

    private FileContentsReader(){

    }

    public static String[] getFileLines(String path) throws IOException {
        List<String> fileLines = new ArrayList<>();
        BufferedReader reader = Files.newBufferedReader(Paths.get(path));

        String line = reader.readLine();
        while(line != null){
            fileLines.add(line);
            line = reader.readLine();
        }

        return fileLines.stream().filter(fileLine -> !fileLine.equals("")).toArray(String[]::new);
    }

}
