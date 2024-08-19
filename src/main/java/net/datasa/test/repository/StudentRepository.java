package net.datasa.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.datasa.test.domain.entity.StudentEntity;
/**
 * 학생 정보 관련 Repository
 */
public interface StudentRepository  extends JpaRepository<StudentEntity, String> {

} 