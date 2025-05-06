package com.gradebook.system.DTO;



public class SubjectDTO {

	private long id;
	private String name;
	
	private TeacherDTO teacher;

	public SubjectDTO(long id, String name, TeacherDTO teacher) {
		super();
		this.id = id;
		this.name = name;
		this.teacher = teacher;
	}

	public SubjectDTO() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TeacherDTO getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherDTO teacher) {
		this.teacher = teacher;
	}
	
}