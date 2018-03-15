package cloud.socify.server.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileOperations {

    public List<String> readFileToStringList(String filename, String charset) throws IOException {
        return Files.lines(Paths.get(filename), Charset.forName(charset)).collect(Collectors.toList());
    }

    public List<String> readResourceFileToStringList(String filename, String charset) throws IOException {
        return readFileToStringList(getResource(filename), charset);
    }

    public String readFileToString(String filename, String charset) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)), Charset.forName(charset));
    }

    public String readResourceFileToString(String filename, String charset) throws IOException {
        return readFileToString(getResource(filename), charset);
    }

    public String readBigFileToString(String filename, String charset) throws IOException {
        StringBuilder sb = new StringBuilder();
        LineIterator it = FileUtils.lineIterator(new File(filename), charset);
        try {
            while (it.hasNext()) {
                sb.append(it.nextLine());
            }
        } finally {
            LineIterator.closeQuietly(it);
        }

        return sb.toString();
    }

    public String readBigResourceFileToString(String filename, String charset) throws IOException {
        return readBigFileToString(getResource(filename), charset);
    }

    public long countLineWith(String filename, String charset, String find) throws IOException {
        long count = 0;
        LineIterator it = FileUtils.lineIterator(new File(filename), charset);
        try {
            while (it.hasNext()) {
                if (it.nextLine().contains(find)) {
                    count++;
                }
            }
        } finally {
            LineIterator.closeQuietly(it);
        }

        return count;
    }


    private String getResource(String filename) {
        return Objects.requireNonNull(getClass().getClassLoader().getResource(filename)).getFile();
    }

}
