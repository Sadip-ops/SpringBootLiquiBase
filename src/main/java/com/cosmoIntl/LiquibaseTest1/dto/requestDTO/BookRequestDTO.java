package com.cosmoIntl.LiquibaseTest1.dto.requestDTO;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {

    private String title;
    private Long authorId;
    private String genre;
}
