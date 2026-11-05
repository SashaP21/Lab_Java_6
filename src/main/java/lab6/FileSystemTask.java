package lab6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.stream.Stream;

public class FileSystemTask {

    public static void run() throws IOException {
        String surname = "Pustozerova";
        String name = "Sasha";

        Path dir = Paths.get(surname);
        Path file = dir.resolve(name + ".txt");

        if (Files.exists(dir)) {
            deleteRecursively(dir);
        }

        // 1-2. Директория с фамилией и файл с именем внутри неё
        Files.createDirectories(dir);
        Files.createFile(file);

        // 3. Вложенные директории dir1/dir2/dir3 и копия файла туда
        Path nested = dir.resolve("dir1/dir2/dir3");
        Files.createDirectories(nested);
        Files.copy(file, nested.resolve(name + ".txt"), StandardCopyOption.REPLACE_EXISTING);

        // 4-5. Файл file1 в dir1 и файл file2 в dir2
        Files.createFile(dir.resolve("dir1/file1.txt"));
        Files.createFile(dir.resolve("dir1/dir2/file2.txt"));

        // 6. Рекурсивный обход директории
        try (Stream<Path> paths = Files.walk(dir)) {
            paths.forEach(path -> {
                if (Files.isDirectory(path)) {
                    System.out.println("D: " + path);
                } else {
                    System.out.println("F: " + path);
                }
            });
        }

        // 7. Удаление dir1 со всем содержимым
        deleteRecursively(dir.resolve("dir1"));
    }

    private static void deleteRecursively(Path path) throws IOException {
        try (Stream<Path> paths = Files.walk(path)) {
            paths.sorted(Comparator.reverseOrder()).forEach(p -> {
                try {
                    Files.delete(p);
                    System.out.println("Удалено: " + p);
                } catch (IOException e) {
                    System.out.println("Не удалось удалить " + p + ": " + e.getMessage());
                }
            });
        }
    }
}