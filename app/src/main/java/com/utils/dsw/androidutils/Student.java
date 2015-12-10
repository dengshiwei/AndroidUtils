package com.utils.dsw.androidutils;


import com.dsw.androidutils.database.AnnotationColumn;
import com.dsw.androidutils.database.AnnotationTable;

@AnnotationTable(name="Student")
public class Student {
	@AnnotationColumn(name="name",isNull=false,isPrimaryKey=false,type="String")
	private String name;
	@AnnotationColumn(name="age",isNull=false,isPrimaryKey=false,type="int")
	private int age;
	private int id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
