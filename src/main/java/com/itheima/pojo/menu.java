package com.itheima.pojo;/*
 *
 * @Param
 */

public class menu {
    private int id;
    private String name;
    private String icon;
    private String path;
    private int pid;
    private String component;

    public menu(int id, String name, String icon, String path, int pid, String component) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.path = path;
        this.pid = pid;
        this.component = component;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

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

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
