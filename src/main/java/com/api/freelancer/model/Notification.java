package com.api.freelancer.model;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Data
public class Notification {

    private String receiver;

    private String documentName;

    private LocalDateTime timestamp;
}
