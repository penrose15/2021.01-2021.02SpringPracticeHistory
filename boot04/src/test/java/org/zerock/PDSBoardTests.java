package org.zerock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.PDSBoard;
import org.zerock.domain.PDSFile;
import org.zerock.persistence.PDSBoardRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class PDSBoardTests {
	
	@Autowired
	PDSBoardRepository repo;
	
	/*@Test
	public void testInsertPDS() {
		PDSBoard pds = new PDSBoard();
		pds.setPname("Document");
		
		PDSFile file1 = new PDSFile();
		file1.setPdsfile("file1.doc");
		
		PDSFile file2 = new PDSFile();
		file2.setPdsfile("file2.doc");
		
		pds.setFiles(Arrays.asList(file1,file2));
		
		log.info("try to save pds");
		
		repo.save(pds);
	}*/
	@Transactional  //delete 나 update 사용시 반드시 이 어노테이션 사용해야 함
	@Test
	public void testUpdateFileName1() {
		Long fno = 1L;
		String newName = "updatedFile1.doc";
		
		int count = repo.updatePDSFile(fno, newName);
		//@Log 설정된 이후 사용 가능
		log.info("update count :" + count);
	}
	
	//fno가 2인 파일이름 수정
	
	@Transactional
	@Test
	public void testUpdateFileName2() {
		String newName = "updatedFile2.doc";
		//반드시 번호가 존재하는지 확인할것
		Optional<PDSBoard> result = repo.findById(2L);
		//Optional<T> 클래스는 Integer나 Double 클래스처럼 'T'타입의 객체를 포장해 주는 래퍼 클래스(Wrapper class)입니다.
		//따라서 Optional 인스턴스는 모든 타입의 참조 변수를 저장할 수 있습니다.
		// +)null처리도 쉽게 할 수 있음
		//findById는 Entity의 Id로 검색하여 Optional<T>를 리턴 타입으로 반환
		
		result.ifPresent(pds -> {  //ifpresent() 는 result값이 null이 아니면 작동한다
			log.info("데이터가 존재하므로 update 시도");
			
			PDSFile target = new PDSFile();
			
			target.setFno(2L);
			target.setPdsfile(newName);
			
			int idx = pds.getFiles().indexOf(target);
			
			if(idx > -1) {
				
				List<PDSFile> list = pds.getFiles();
				list.remove(idx);
				list.add(target);
				pds.setFiles(list);
				
			}
			
			repo.save(pds);
		});
		
	}
	
	@Transactional
	@Test
	public void deletePDSFile() {
		//첨부파일 번호
		Long fno = 3L;
		int count = repo.deletePDSFile(fno);
		
		log.info("DELETE PDSFILE" + count);
	}
	/*
	@Test
	public void insertDummies() {  //100개의 자료와 200개의 파일 데이터 추가한다
		
		List<PDSBoard> list = new ArrayList<>();
		
		IntStream.range(1,  100).forEach(i -> {
			
			PDSBoard pds = new PDSBoard();  //pds 1개
			pds.setPname("자료"+i);
			
			PDSFile file1 = new PDSFile();
			file1.setPdsfile("file1.doc");
			
			PDSFile file2 = new PDSFile();  //file 2개
			file2.setPdsfile("file2.doc");
			
			pds.setFiles(Arrays.asList(file1, file2));
			
			log.info("try to save pds");
			
			list.add(pds);
			
			
		});
		
		repo.saveAll(list);
	}
	*/
	
	@Test
	public void viewSummary() {
		repo.getSummary().forEach(arr ->log.info(Arrays.toString(arr)));
	}

}
