package com.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreadDto {
    private Long id;
    private String name;
    private Long authorId;
    private Long gameId;
    private Long problemTopicId;
    private LocalDateTime mdDateCreated;
}
