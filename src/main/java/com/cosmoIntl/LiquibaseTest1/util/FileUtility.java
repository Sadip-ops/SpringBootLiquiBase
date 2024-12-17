package com.cosmoIntl.LiquibaseTest1.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileUtility {

    private static final String BASE_STATIC_DIR = "src/main/resources/static/";
    /**
     * Saves the given MultipartFile to the specified directory.
     *
     * @param file        The file to be stored.
     * @param targetDir   The directory path where the file should be stored.
     * @return The path of the stored file.
     * @throws IOException If an error occurs during file saving.
     */
    public static String saveFile(MultipartFile file, String targetDir,boolean isExternal) throws IOException {
        // Validate file
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty and cannot be saved.");
        }

        // Ensure the target directory exists
//        Path directoryPath = Path.of(BASE_STATIC_DIR,targetDir);

        // Determine the base directory
        Path directoryPath;
        if (isExternal) {
            // External directory (outside the project folder)
            directoryPath = Path.of(targetDir); // External folder, passed as an argument
        } else {
            // Internal directory (inside the project folder)
            directoryPath = Path.of(BASE_STATIC_DIR, targetDir); // Static folder inside the project
        }

        if (!Files.exists(directoryPath)) {
            Files.createDirectory(directoryPath);
        }

        // Create a unique file name to avoid overwriting existing files
        String originalFileName = file.getOriginalFilename();
        String uniqueFileName = System.currentTimeMillis() + "_" + (originalFileName != null ? originalFileName : "uploaded_file");

        // Define the file path
        Path targetPath = directoryPath.resolve(uniqueFileName);

        // Copy the file content to the target location
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        return targetDir + uniqueFileName; // Return the absolute path of the saved file
    }
}
