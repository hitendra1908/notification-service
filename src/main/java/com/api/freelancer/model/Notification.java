package com.api.freelancer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    private String receiver;

    private String documentName;

    private LocalDateTime timestamp;
}
