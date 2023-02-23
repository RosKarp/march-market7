package ru.geekbrains.march.market.api;

public class DeliveryDto {
    private String address;
    private String phone;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
