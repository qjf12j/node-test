package net.datasa.test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.test.domain.dto.StudentDTO;
import net.datasa.test.domain.entity.StudentEntity;
import net.datasa.test.repository.StudentRepository;

/**
 * 학생 정보를 관리하는 서비스 클래스
 *
 */
@Service
@Slf4j
@RequiredArgsConstructor

public class StudentService {

	private final StudentRepository Repository;

	/**
	 * 정보 저장
	 * 
	 */
	public void save(StudentDTO dto) {

		StudentEntity entity = new StudentEntity();

		entity.setSid(dto.getSid());
		entity.setName(dto.getName());
		entity.setKor(dto.getKor());
		entity.setEng(dto.getEng());
		entity.setMat(dto.getMat());
		log.debug("entity정보:{}", entity);
		Repository.save(entity);
	}

	/**
	 * 정보 조회
	 * 
	 */
	public List<StudentDTO> serchall() {
		List<StudentEntity> entitylist = Repository.findAll();
		List<StudentDTO> dolist = new ArrayList<>();
		for (StudentEntity entity : entitylist) {
			StudentDTO dto = new StudentDTO();

			dto.setSid(entity.getSid());
			dto.setName(entity.getName());
			dto.setKor(entity.getKor());
			dto.setEng(entity.getEng());
			dto.setMat(entity.getMat());
			dolist.add(dto);

		}
		return dolist;
	}

	/**
	 * 정보 삭제
	 * 
	 */
	public boolean delete(String sid) {
		boolean result = Repository.existsById(sid);
		if (result) {
			Repository.deleteById(sid);
		}

		return result;
	}

	/**
	 * 정보 수정
	 * 
	 */
	public void update(StudentDTO dto) {
		// 잘못된 학번으로 접속시 예외처리
		StudentEntity entity = Repository.findById(dto.getSid())
				.orElseThrow(() -> new EntityNotFoundException("해당 학번이 존재하지 않습니다. ID: " + dto.getSid()));
		// dto의 수정할 정보를 entity에 세팅
		
		entity.setEng(dto.getEng());
		entity.setKor(dto.getKor());
		entity.setMat(dto.getMat());

		// entity 저장
		Repository.save(entity);

	}

	public StudentDTO selet(String sid) {
		StudentEntity entity = Repository.findById(sid).orElse(null); // null이면 null인 채로 리턴

		if (entity == null)
			return null;

		StudentDTO dto = new StudentDTO();
		dto.setSid(entity.getSid());
		dto.setEng(entity.getEng());
		dto.setKor(entity.getKor());
		dto.setMat(entity.getMat());
		return dto;
	}
}
