package com.bill;

public class Recipient {
    private int recipientID;
    private String firstName;
    private String lastName;
    private String department;

    public Recipient(int recipient_id, String firstName, String lastName, String department) {
        this.recipientID = recipient_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

    public Recipient(String firstName, String lastName, String department) {
        this.department = department;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getRecipientID() {
        return recipientID;
    }


    public String getDepartment() {
        return this.department;
    }
}
