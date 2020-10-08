package com.gura.spring04;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
<<<<<<< HEAD
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileTestController {
	
	// <input type="file" name="myFile"/> 하나만 전송되는 경우
	@RequestMapping("/upload")//임시폴더에 있는 정보를 upload 폴더에 다른 이름들로 저장하는 것을 여기서 해줘야 된다.(MultipartFile 객체가)
	public String upload(@RequestParam MultipartFile myFile,
			HttpServletRequest request, @RequestParam String title) {//업로드한것을 일단 임시저장을 해놓고 업로드된 파일정보가 MultipartFile 객체에 들어온다.
		//원본 파일명
		String orgFileName=myFile.getOriginalFilename();
		//파일의 크기
		long fileSize=myFile.getSize();
		
		// webapp/upload 폴더 까지의 실제 경로
		String realPath=request.getServletContext().getRealPath("/upload");
		//저장할 파일의 상세 경로 // upload\ 뒤에 붙을 파일명을 위해 필요 upload 뒤에 \(윈도우에서) 를 만들기 위해 
		String filePath=realPath+File.separator;
		//디렉토리를 만들 파일 객체 생성
		File upload=new File(filePath);
		if(!upload.exists()) {//만일 디렉토리가 존재하지 않으면
			upload.mkdir();//만들어 준다
		}
		//저장할 파일 명을 구성한다.
		String saveFileName= System.currentTimeMillis()+orgFileName;
		
		try {
			//upload 폴더에 파일을 저장한다.
			myFile.transferTo(new File(filePath+saveFileName));
			System.out.println(filePath+saveFileName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("orgFileName", orgFileName);
		request.setAttribute("saveFileName", saveFileName);
		request.setAttribute("fileSize", fileSize);
		request.setAttribute("title", title);
		return "upload";
		
	}
	
	// <input type="file" name="myFile"/> 하나만 전송되는 경우
	@RequestMapping("/upload2")//임시폴더에 있는 정보를 upload 폴더에 다른 이름들로 저장하는 것을 여기서 해줘야 된다.(MultipartFile 객체가)
	public String upload2(HttpServletRequest request, @ModelAttribute FileDto dto) {//업로드한것을 일단 임시저장을 해놓고 업로드된 파일정보가 MultipartFile 객체에 들어온다.
		//FileDto 객체에 있는 MultiPartFiel 객체의 참조값 얻어오기
		MultipartFile myFile=dto.getMyFile();
		//원본 파일명
		String orgFileName=myFile.getOriginalFilename();
		//파일의 크기
		long fileSize=myFile.getSize();
		
		// webapp/upload 폴더 까지의 실제 경로
		String realPath=request.getServletContext().getRealPath("/upload");
		//저장할 파일의 상세 경로 // upload\ 뒤에 붙을 파일명을 위해 필요 upload 뒤에 \(윈도우에서) 를 만들기 위해 
		String filePath=realPath+File.separator;
		//디렉토리를 만들 파일 객체 생성
		File upload=new File(filePath);
		if(!upload.exists()) {//만일 디렉토리가 존재하지 않으면
			upload.mkdir();//만들어 준다
		}
		//저장할 파일 명을 구성한다.
		String saveFileName= System.currentTimeMillis()+orgFileName;
		
		try {
			//upload 폴더에 파일을 저장한다.
			myFile.transferTo(new File(filePath+saveFileName));
			System.out.println(filePath+saveFileName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//업로드된 파일 정보를 FileDto 에 담는다.
		dto.setOrgFileName(orgFileName);
		dto.setSaveFileName(saveFileName);
		dto.setFileSize(fileSize);
		
		//request 영역에 FileDto 담기
		request.setAttribute("dto", dto);
		
		// /WEB-INF/views/upload2.jsp 페이지로 forward 이동해서 응답
		return "upload2";
		
	}
}
=======
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileTestController {
	
	// <input type="file" name="myFile"/>  하나만 전송되는 경우 
	@RequestMapping("/upload")
	public String upload(@RequestParam MultipartFile myFile,
			HttpServletRequest request,@RequestParam String title) {
		//원본 파일명
		String orgFileName=myFile.getOriginalFilename();
		//파일의 크기
		long fileSize=myFile.getSize();
		
		// webapp/upload 폴더 까지의 실제 경로(서버의 파일시스템 상에서의 경로)
		String realPath=request.getServletContext().getRealPath("/upload");
		//저장할 파일의 상세 경로
		String filePath=realPath+File.separator;
		//디렉토리를 만들 파일 객체 생성
		File upload=new File(filePath);
		if(!upload.exists()) {//만일 디렉토리가 존재하지 않으면 
			upload.mkdir(); //만들어 준다.
		}
		//저장할 파일 명을 구성한다.
		String saveFileName=
				System.currentTimeMillis()+orgFileName;
		try {
			//upload 폴더에 파일을 저장한다.
			myFile.transferTo(new File(filePath+saveFileName));
			System.out.println(filePath+saveFileName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("orgFileName", orgFileName);
		request.setAttribute("saveFileName", saveFileName);
		request.setAttribute("fileSize", fileSize);
		request.setAttribute("title", title);
		return "upload";
	}
	// <input type="file" name="myFile"/>  하나만 전송되는 경우 
	@RequestMapping("/upload2")
	public String upload2(HttpServletRequest request, FileDto dto) {
		//FileDto 객체에 있는 MultiPartFile 객체의 참조값 얻어오기 
		MultipartFile myFile=dto.getMyFile();
		//원본 파일명
		String orgFileName=myFile.getOriginalFilename();
		//파일의 크기
		long fileSize=myFile.getSize();
		
		// webapp/upload 폴더 까지의 실제 경로(서버의 파일시스템 상에서의 경로)
		String realPath=request.getServletContext().getRealPath("/upload");
		//저장할 파일의 상세 경로
		String filePath=realPath+File.separator;
		//디렉토리를 만들 파일 객체 생성
		File upload=new File(filePath);
		if(!upload.exists()) {//만일 디렉토리가 존재하지 않으면 
			upload.mkdir(); //만들어 준다.
		}
		//저장할 파일 명을 구성한다.
		String saveFileName=
				System.currentTimeMillis()+orgFileName;
		try {
			//upload 폴더에 파일을 저장한다.
			myFile.transferTo(new File(filePath+saveFileName));
			System.out.println(filePath+saveFileName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//업로드된 파일 정보를 FileDto 에 담는다 
		dto.setOrgFileName(orgFileName);
		dto.setSaveFileName(saveFileName);
		dto.setFileSize(fileSize);
		
		//request 영역에 FileDto 담기 
		request.setAttribute("dto", dto);
		
		// /WEB-INF/views/upload2.jsp 페이지로 forward 이동해서 응답
		return "upload2";
	}
}





>>>>>>> refs/remotes/upstream/master
