package com.enation.app.base.core.model;

import org.darcy.framework.database.PrimaryKeyField;



/**
 * Template generated by MyEclipse - Hibernate Tools
 */

public class Template  implements java.io.Serializable {


    // Fields    

     private Integer template_id;
     private String name;
     private String theme;
     private String author;
     private String site;
     private Long create_time;
     private Integer is_active;
     private String version;


    // Constructors

    /** default constructor */
    public Template() {
    }


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public Long getCreate_time() {
		return create_time;
	}


	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}


	public Integer getIs_active() {
		return is_active;
	}


	public void setIs_active(Integer is_active) {
		this.is_active = is_active;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSite() {
		return site;
	}


	public void setSite(String site) {
		this.site = site;
	}


	@PrimaryKeyField
	public Integer getTemplate_id() {
		return template_id;
	}


	public void setTemplate_id(Integer template_id) {
		this.template_id = template_id;
	}


	public String getTheme() {
		return theme;
	}


	public void setTheme(String theme) {
		this.theme = theme;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}

    
  

}