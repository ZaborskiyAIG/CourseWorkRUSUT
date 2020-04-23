package com.CourseWorkRusut.DTO;

public class AdminDTO extends UserDTO {

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AdminDTO() {

    }

    public AdminDTO(String phone) {
        this.phone = phone;
    }
}
