package kr.kakaotech.community.dto;

import kr.kakaotech.community.dto.response.PostDetailResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private String message;
    private T data;

    public static <T> ResponseEntity<ApiResponse<T>> success(String message, T data) {
        return ResponseEntity.ok(new ApiResponse<>(message, data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> create(String message, T data) {
        return ResponseEntity.status(201).body(new ApiResponse<>(message, data));
    }

    @Override
    public String toString() {
        return "{\n" +
                "    \"message\": \""+ message +"\",\n" +
                "    \"data\": " + data +"\n" +
                "}";
    }
}
