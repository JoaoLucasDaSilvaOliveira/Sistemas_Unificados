package Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    public static boolean fileNotFound (Path PATH) {
        if (!Files.exists(PATH)) {
            System.out.println("Arquivo não encontrado. Retornando lista vazia.");
            return true;
        }
        return false;
    }

    public static boolean fileIsEmpty (Path PATH) throws IOException {
        // <-- Verifica se o arquivo está vazio
        return Files.size(PATH) == 0;
    }


}
