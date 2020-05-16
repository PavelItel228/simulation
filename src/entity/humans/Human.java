package entity.humans;

import entity.BaseEntity;
import entity.enums.HumanStatus;

public class Human extends BaseEntity {
    private  Integer age;
    private  HumanStatus status;
    public static int lifeDuration = 30;
    public final static long HUMAN_TYPE_MASK = 0x0000_0000_0000_0018L;
    public final static int HUMAN_TYPE_SHIFT = 3;
    public final static int HUMAN_TYPE_EMPTY = 0x0;
    public final static long HUMAN_AGE_MASK = 0x0000_0000_000F_FFE0L;
    public final static int HUMAN_AGE_SHIFT = 5;
    public final static long HUMAN_ENERGY_MASK = 0x0000_0000_03F0_0000L;
    public final static int HUMAN_ENERGY_SHIFT = 20;
    public final static long HUMAN_SATIETY_MASK = 0x0000_0000_FC00_0000L;
    public final static int HUMAN_SATIETY_SHIFT = 26;
    public final static long ACTIVE_FLAG_HUMAN_MASK   = 0x4000_0000_0000_0000L;
    public final static int ACTIVE_FLAG_HUMAN_SHIFT   = 62;

    public Human(Integer age, HumanStatus status) {
        this.age = age;
        this.status = status;
    }

    public Human() {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public HumanStatus getStatus() {
        return status;
    }

    public void setStatus(HumanStatus status) {
        this.status = status;
    }

    public static void setLifeDuration(int lifeDuration) {
        Human.lifeDuration = lifeDuration;
    }

    public static int getLifeDuration() {
        return lifeDuration;
    }
}
