package com.yang.pojo;

public class ServiceTag {
    private Long id;
    private Long serviceId;
    private Long tagId;

    public ServiceTag() {
    }

    public ServiceTag(Long id, Long serviceId, Long tagId) {
        this.id = id;
        this.serviceId = serviceId;
        this.tagId = tagId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "ServiceTag{" +
                "id=" + id +
                ", serviceId=" + serviceId +
                ", tagId=" + tagId +
                '}';
    }

    public static void main(String[] args) {
        Person p = new Person("yang", "12", "a", "aaa");
        Person b = p;
        b.setAlias("bbb");
        System.out.println(p);
        System.out.println(b);
    }
}