package org.example.api_jersey;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import jakarta.xml.bind.annotation.XmlRootElement;

@ApplicationPath("/api")
@XmlRootElement
public class Alien extends Application {
    private int id;
    private String name;
    private String color;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    //toString method
    @Override
    public String toString() {
        return "Alien [id=" + id + ", name=" + name + ", color=" + color + "]";
    }
}
