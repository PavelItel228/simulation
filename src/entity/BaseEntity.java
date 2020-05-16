package entity;

public class BaseEntity {
    private Long id;

    public static final int COMMON_COLOR_EMPTY  = 0xF0F0F0;
    public static final int COMMON_COLOR_UNDEFINED = 0xC22E35;

    public BaseEntity(Long id) {
        this.id = id;
    }

    public BaseEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
