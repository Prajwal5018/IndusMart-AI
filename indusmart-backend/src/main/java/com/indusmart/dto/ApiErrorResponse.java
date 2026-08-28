package com.indusmart.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiErrorResponse {

    /**
     * Error Timestamp
     */
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    /**
     * HTTP Status Code
     */
    private Integer status;

    /**
     * HTTP Error
     * Example:
     * Bad Request
     * Not Found
     * Conflict
     * Internal Server Error
     */
    private String error;

    /**
     * Detailed Error Message
     */
    private String message;

    /**
     * Request Path
     */
    private String path;

}