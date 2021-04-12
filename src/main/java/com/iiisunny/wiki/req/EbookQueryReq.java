package com.iiisunny.wiki.req;

public class EbookQueryReq extends PageReq{

    private Long id;

    private String name;

    private Long categoryId2;

    public Long getCategoryId2() {
        return categoryId2;
    }

    public void setCategoryId2(Long categoryId2) {
        this.categoryId2 = categoryId2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "EbookQuerylReq{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId2=" + categoryId2 +
                "} " + super.toString();
    }
}