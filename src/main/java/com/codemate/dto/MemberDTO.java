package com.codemate.dto;

public class MemberDTO {
	private int id;
	private String name;
	private int age;
	private String email;
	private String password;
	private String sns_id;
	private String sns_type;
	private String role;
	private String postcode;
	private String address;
	private String detail_address;
	
	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		return id;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	
	public void setAge(int age)
	{
		this.age = age;
	}
	public int getAge()
	{
		return age;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getEmail()
	{
		return email;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPassword()
	{
		return password;
	}
	
	public void setSns_id(String sns_id)
	{
		this.sns_id = sns_id;
	}
	public String getSns_id()
	{
		return sns_id;
	}
	
	public void setSns_type(String sns_type)
	{
		this.sns_type = sns_type;
	}
	public String getSns_type()
	{
		return sns_type;
	}
	
	public void setRole(String role)
	{
		this.role = role;
	}
	public String getRole()
	{
		return role;
	}
	
	public void setPostcode(String postcode)
	{
		this.postcode = postcode;
	}
	public String getPostcode()
	{
		return postcode;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getAddress()
	{
		return address;
	}
	
	public void setDetail_address(String detail_address)
	{
		this.detail_address = detail_address;
	}
	public String getDetail_address()
	{
		return detail_address;
	}
}
