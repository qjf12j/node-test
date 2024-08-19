package net.datasa.test.domain.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


/**
 * 학생 정보 엔티티
 *
 * 
 */
@Data
@Table(name="student")
@Entity
public class StudentEntity {
	
	
	
	@Id
	@Column(name="sid", length = 10)
	private  String sid; //학번
	
	@Column(name="name" , length=30 , nullable = false)
	private String name; //이름
	
	@Column(name="kor" )
	private int kor; // 국어점수
	
	@Column(name="eng" )
	private int eng; // 영어점수
	
	@Column(name="mat" )
	private int mat; // 수학점수

}
