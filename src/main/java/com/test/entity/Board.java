/**타입*/
package com.test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Board {
    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    private String content;

    private LocalDateTime regDt;

    private LocalDateTime ModDt;
}
