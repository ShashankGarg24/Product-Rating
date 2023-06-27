package com.rating.dtos;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RateDto {

    private Integer rating;
    private Long userId;
}
