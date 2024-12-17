package com.cosmoIntl.LiquibaseTest1.dto.responseDTO;


import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDTO {

    private Long id;
    private String title;
    private String authorName;
    private String genre;
    private String imageUrl;
}
