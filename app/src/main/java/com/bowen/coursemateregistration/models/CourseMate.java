package com.bowen.coursemateregistration.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "course_mate")
public class CourseMate implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "matric_number")
    private String matricNumber;

    @ColumnInfo(name = "field_of_interest")
    private String fieldOfInterest;

    public CourseMate(int uid, String name, String matricNumber, String fieldOfInterest) {
        this.uid = uid;
        this.name = name;
        this.matricNumber = matricNumber;
        this.fieldOfInterest = fieldOfInterest;
    }

    @Ignore
    public CourseMate( String name, String matricNumber, String fieldOfInterest) {
        this.name = name;
        this.matricNumber = matricNumber;
        this.fieldOfInterest = fieldOfInterest;
    }

    protected CourseMate(Parcel in) {
        uid = in.readInt();
        name = in.readString();
        matricNumber = in.readString();
        fieldOfInterest = in.readString();
    }

    public static final Creator<CourseMate> CREATOR = new Creator<CourseMate>() {
        @Override
        public CourseMate createFromParcel(Parcel in) {
            return new CourseMate(in);
        }

        @Override
        public CourseMate[] newArray(int size) {
            return new CourseMate[size];
        }
    };

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatricNumber() {
        return matricNumber;
    }

    public void setMatricNumber(String matricNumber) {
        this.matricNumber = matricNumber;
    }

    public String getFieldOfInterest() {
        return fieldOfInterest;
    }

    public void setFieldOfInterest(String fieldOfInterest) {
        this.fieldOfInterest = fieldOfInterest;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(uid);
        parcel.writeString(name);
        parcel.writeString(matricNumber);
        parcel.writeString(fieldOfInterest);
    }
}
