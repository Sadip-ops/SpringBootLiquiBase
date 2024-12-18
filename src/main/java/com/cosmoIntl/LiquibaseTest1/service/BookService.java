package com.cosmoIntl.LiquibaseTest1.service;

import com.cosmoIntl.LiquibaseTest1.dto.requestDTO.BookRequestDTO;
import com.cosmoIntl.LiquibaseTest1.dto.responseDTO.ApiResponse;
import com.cosmoIntl.LiquibaseTest1.dto.responseDTO.BookResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface BookService {

    ApiResponse<?> saveBook(BookRequestDTO requestDTO, MultipartFile file, boolean isExternal);
}
