package model;

public class SensorDto extends BaseEntity {
    private boolean state;
    private String name;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SensorDto(int id, String name, boolean state) {
        super();
        this.setIdentity(id);
        this.state = state;
        this.name = name;
    }
}
