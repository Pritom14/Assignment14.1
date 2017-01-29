package com.example.shaloin.fourteenthassignmenta;

/**
 * Created by shaloin on 28/12/16.
 */

public class Model {
    private Main main;


    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    class Main{
        double temp;
        double pressure;
        int humidity;

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getPressure() {
            return pressure;
        }

        public void setPressure(double pressure) {
            this.pressure = pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }
    }
}
