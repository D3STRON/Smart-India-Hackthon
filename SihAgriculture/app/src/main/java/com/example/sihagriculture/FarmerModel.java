package com.example.sihagriculture;

/**
 * Created by Anurag on 28-03-2018.
 */

public class FarmerModel {
     public String name, number, id, location;

     public FarmerModel(String name, String number, String id, String location) {
          this.name = name;
          this.number = number;
          this.id = id;
          this.location = location;
     }

     public FarmerModel() {
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public String getNumber() {
          return number;
     }

     public void setNumber(String number) {
          this.number = number;
     }

     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }

     public String getLocation() {
          return location;
     }

     public void setLocation(String location) {
          this.location = location;
     }
}
