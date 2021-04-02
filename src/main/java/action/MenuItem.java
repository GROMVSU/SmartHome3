package action;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private String url;
    private String name;
    private int level;

    public MenuItem(String url, String name, int level) {
        this.url = url;
        this.name = name;
        this.level = level;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {return level;}
}