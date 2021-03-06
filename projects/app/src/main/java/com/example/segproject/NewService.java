package com.example.segproject;

public class NewService {

    String serviceID;
    String name;
    boolean isOffered;
    double rate;
    boolean firstName;
    boolean lastName;
    boolean dob;
    boolean address;
    boolean email;
    boolean g1;
    boolean g2;
    boolean g3;
    boolean compact;
    boolean intermediate;
    boolean suv;
    boolean pickupdate;
    boolean pickuptime;
    boolean returndate;
    boolean returntime;
    boolean movingstartlocation;
    boolean movingendlocation;
    boolean area;
    boolean kmdriven;
    boolean numberofmovers;
    boolean numberofboxes;

    public NewService(){
    }

    public NewService(boolean address, boolean area, boolean compact, boolean dob,
                      boolean email, boolean firstName, boolean g1, boolean g2, boolean g3,
                      boolean intermediate, boolean kmdriven, boolean lastName, boolean movingendlocation,
                      boolean movingstartlocation, String name, boolean numberofboxes,boolean numberofmovers,
                      boolean pickupdate, boolean pickuptime,double rate,boolean returndate, boolean returntime,
                      boolean suv, boolean isOffered,  String serviceID) {

        this.isOffered = isOffered;
        this.name = name;
        this.rate = rate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.email = email;
        this.g1 = g1;
        this.g2 = g2;
        this.g3 = g3;
        this.compact = compact;
        this.intermediate = intermediate;
        this.suv = suv;
        this.pickupdate = pickupdate;
        this.pickuptime = pickuptime;
        this.returndate = returndate;
        this.returntime = returntime;
        this.movingstartlocation = movingstartlocation;
        this.movingendlocation = movingendlocation;
        this.area = area;
        this.kmdriven = kmdriven;
        this.numberofmovers = numberofmovers;
        this.numberofboxes = numberofboxes;
        this.serviceID = serviceID;
    }

    public boolean isOffered(){return isOffered;}

    public String getServiceID(){return serviceID;}

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public boolean isFirstName() {
        return firstName;
    }

    public boolean isLastName() {
        return lastName;
    }

    public boolean isDob() {
        return dob;
    }

    public boolean isAddress() {
        return address;
    }

    public boolean isEmail() {
        return email;
    }

    public boolean isG1() {
        return g1;
    }

    public boolean isG2() {
        return g2;
    }

    public boolean isG3() {
        return g3;
    }

    public boolean isCompact() {
        return compact;
    }

    public boolean isIntermediate() {
        return intermediate;
    }

    public boolean isSUV() {
        return suv;
    }

    public boolean isPickupdate() {
        return pickupdate;
    }

    public boolean isPickuptime() {
        return pickuptime;
    }

    public boolean isReturndate() {
        return returndate;
    }

    public boolean isReturntime() {
        return returntime;
    }

    public boolean isMovingstartlocation() {
        return movingstartlocation;
    }

    public boolean isMovingendlocation() {
        return movingendlocation;
    }

    public boolean isArea() {
        return area;
    }

    public boolean isKmdriven() {
        return kmdriven;
    }

    public boolean isNumberofmovers() {
        return numberofmovers;
    }

    public boolean isNumberofboxes() {
        return numberofboxes;
    }

}
