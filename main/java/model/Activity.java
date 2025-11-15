package model;

import java.util.Date;

public class Activity {
    private int id;
    private int userId;
    private String type;
    private double quantity;
    private Date date;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}
