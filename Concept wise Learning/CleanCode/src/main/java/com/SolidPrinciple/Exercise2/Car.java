package com.SolidPrinciple.Exercise2;

public class Car extends Vehicle implements RadioSwitch  {

    private boolean radioOn;
    public boolean isRadioOn() {

        return radioOn;
    }

    @Override
    public void turnRadioOn() {

        radioOn = true;
    }

    @Override
    public void turnRadioOff() {

        radioOn = false;
    }
}
