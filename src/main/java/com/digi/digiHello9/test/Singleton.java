package com.digi.digiHello9.test;

public class Singleton {
	private final Singleton INSTANCE = new Singleton();
	private Singleton() {
		
	}
	public Singleton getINSTANCE() {
		return INSTANCE;
	};

}
