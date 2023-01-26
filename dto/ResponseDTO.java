package dto;

public class ResponseDTO<T> {
    private final boolean success;
    private final T data;

    public ResponseDTO(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public ResponseDTO(T data) {
        this.success = true;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "success=" + success +
                ", data=" + data +
                '}';
    }
}
