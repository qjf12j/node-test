package net.datasa.test.domain.dto;

import lombok.Data;

/**
 * 학생 정보 DTO
 */

@Data
public class StudentDTO {
	
	String sid;   // 학번
	String name; // 학생이름
	int kor; 	// 국어점수
	int eng;	// 영어점수
	int mat;	// 수학점수
}