package com.kolosov.learnjava_jc_spring.views;

public class View {
    public interface BasicView {};

    public interface UserSummary extends BasicView {};
    public interface UserDetails extends BasicView {};

    public interface CreateEntity extends BasicView {};
    public interface UpdateEntity extends BasicView {};

    public interface CreateOrder extends BasicView {};
}
