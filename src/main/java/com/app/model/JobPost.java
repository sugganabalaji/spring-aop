package com.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
public class JobPost {

	@Id
	private int postId;
	@Column(length = 50)
	private String postProfile;
	private String postDesc;
	private Integer reqExperience;
	private List<String> postTechStack;
	

}
