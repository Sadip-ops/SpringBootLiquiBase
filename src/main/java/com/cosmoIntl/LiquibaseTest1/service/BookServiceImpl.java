package com.cosmoIntl.LiquibaseTest1.service;


import com.cosmoIntl.LiquibaseTest1.dto.requestDTO.BookRequestDTO;
import com.cosmoIntl.LiquibaseTest1.dto.responseDTO.ApiResponse;
import com.cosmoIntl.LiquibaseTest1.dto.responseDTO.BookResponseDTO;
import com.cosmoIntl.LiquibaseTest1.entity.Author;
import com.cosmoIntl.LiquibaseTest1.entity.Book;
import com.cosmoIntl.LiquibaseTest1.repository.AuthorRepository;
import com.cosmoIntl.LiquibaseTest1.repository.BookRepository;
import com.cosmoIntl.LiquibaseTest1.util.FileUtility;
import com.cosmoIntl.LiquibaseTest1.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private static final String IMAGE_DIRECTORY = "images/";
    // Define the external path (outside project folder, e.g., "C:/files/")
    private static final String EXTERNAL_DIRECTORY = "D:/CosmoIntl Intern Period ko Kaam Haru/2024-12-09/images"; // Example external directory path

    @Override
    public ApiResponse<?> saveBook(BookRequestDTO requestDTO, MultipartFile file, boolean isExternal) {
            try {
                // Save file using FileUtility
//                String imagePath = FileUtility.saveFile(file, IMAGE_DIRECTORY);
                String imagePath;
                if (isExternal) {
                    // Save to external path
                    imagePath = FileUtility.saveFile(file, EXTERNAL_DIRECTORY, true);
                } else {
                    // Save to internal path
                    imagePath = FileUtility.saveFile(file, IMAGE_DIRECTORY, false);
                }
                // Fetch the author if provided
                Author author = null;
                if (requestDTO.getAuthorId() != null) {
                    author = authorRepository.findById(requestDTO.getAuthorId())
                            .orElseThrow(() -> new IllegalArgumentException("Invalid author ID."));
                }

                // Create and save the Book entity
                Book book = Book.builder()
                        .title(requestDTO.getTitle())
                        .genre(requestDTO.getGenre())
                        .author(author)
                        .imageUrl(imagePath)
                        .build();

                bookRepository.save(book);



                // Return a response DTO
                return ResponseUtil.getSuccessResponse("Book saved successfully.");
            } catch (IOException e) {
                throw new RuntimeException("Error while saving file: " + e.getMessage());
            }
    }

}
