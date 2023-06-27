package com.rating.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Userr {

    @Id
    private Long userId;
    private String username;
    private String phoneNumber;
}
