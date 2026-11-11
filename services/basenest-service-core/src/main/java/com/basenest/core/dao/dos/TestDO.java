package com.basenest.core.dao.dos;

/**
 * @ClassName TestDO
 * @Description
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
public class TestDO {
    private Long id;
    private String t1;

    public TestDO() {
    }

    public TestDO(String t1) {
        this.t1 = t1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    @Override
    public String toString() {
        return "Test{id=" + id + ", t1='" + t1 + "'}";
    }
}