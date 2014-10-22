package com.cs490;

public class OnlineForm {
	// Default Constructor
	private int id;
	//private String studentName;
	private String courseDept;
	private int courseNumber;
	private String semester;
	private int year;
	private String title;
	
	
	public OnlineForm(int id, String courseDept, int courseNumber, String semester, int year, String title)
	{
		this.id = id;
		//this.studentName = studentName;
		this.courseDept = courseDept;
		this.courseNumber = courseNumber;
		this.semester = semester;
		this.year = year;
		this.title = title;
	}
	
	public OnlineForm(String courseDept, int courseNumber, String semester, int year, String title)
	{
		id = 0;
		//this.studentName = studentName;
		this.courseDept = courseDept;
		this.courseNumber = courseNumber;
		this.semester = semester;
		this.year = year;
		this.title = title;
	}
	
	public String toString()
	{
		String result = "id: " + id + " Course dept: " + courseDept + "(" + courseNumber + ") " + "Semester: " + semester + "(" + year + ") " + "Title: " + title;
		return result;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	/**
	public String getStudentName()
	{
		return studentName;
	}
	
	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}
	*/
	
	public String getCourseDept()
	{
		return courseDept;
	}
	
	public void setCourseDept(String courseDept)
	{
		this.courseDept = courseDept;
	}
	
	public int getCourseNumber()
	{
		return courseNumber;
	}
	
	public void setCourseNumber(int courseNumber)
	{
		this.courseNumber = courseNumber;
	}
	
	public String getSemester()
	{
		return semester;
	}
	
	public void setSemester(String semester)
	{
		this.semester = semester;
	}
	
	public int getYear() 
	{
		return year;
	}
	
	public void setYear(int year)
	{
		this.year = year;
	}
	
	public String getTitle() 
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
}
	
	
	