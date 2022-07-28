package edu.school21.reflection.classes;

import java.util.StringJoiner;

public class Car {

	private int horsepower;
	private String serialNumber;;
	private String gosNumber;

	public Car() {
		this.horsepower = 0;
		this.serialNumber = "Default";
		this.gosNumber = "Default";
	}

	public Car(int horsepower, String serialNumber, String gosNumber) {
		this.horsepower = horsepower;
		this.serialNumber = serialNumber;
		this.gosNumber = gosNumber;
	}

	public int boostion(int value) {
		this.horsepower += value;
		return horsepower;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Car.class . getSimpleName() + "[", "]")
		. add("serialNumber='" + serialNumber + "'")
		. add("gosNumber='" + gosNumber + "'")
		. add("horsepower=" + horsepower)
		. toString();
	}
}