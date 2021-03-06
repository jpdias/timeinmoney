package com.toostronk.timeinmoney;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;


public class Data implements Serializable,Parcelable
{
	
    private static final long serialVersionUID = 1L;

    public String name;
    public String type;
    public double cost;
    public String data;
    public String notes;

    public boolean classEnabled;


    public Data() {
        this.name=name;
        this.type=type;
        this.cost=cost;
        this.data=data;
        this.notes=notes;
        this.classEnabled = true;
    }
    public Data(String  name, String type, String data, String notes, Double cost) {
        this.name=name;
        this.type=type;
        this.cost=cost;
        this.data=data;
        this.notes=notes;
        this.classEnabled = true;
    }
    
    public String toString(){
    	
		return name + "   " + cost + "�" + "\n" + data;
    	
    }

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeDouble(cost);
		dest.writeString(name);
		dest.writeString(type);
		dest.writeString(data);
		dest.writeString(notes);
	}
	
	public Data(Parcel source) {
        // TODO Auto-generated method stub
        cost = source.readDouble();
        name = source.readString();
        type = source.readString();
        data = source.readString();
        notes = source.readString();
 
    }

	 public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		 
	        @Override
	        public Data createFromParcel(Parcel source) {
	            // TODO Auto-generated method stub
	            return new Data(source);
	        }
	 
	        @Override
	        public Data[] newArray(int size) {
	            // TODO Auto-generated method stub
	            return new Data[size];
	        }
	    };
}