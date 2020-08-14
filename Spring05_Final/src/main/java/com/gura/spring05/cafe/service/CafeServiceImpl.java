package com.gura.spring05.cafe.service;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.cafe.dao.CafeDao;
import com.gura.spring05.cafe.dto.CafeDto;
import com.gura.spring05.exception.NotDeleteException;

@Service
public class CafeServiceImpl implements CafeService{
	@Autowired
	private CafeDao cafeDao;

	//한 페이지에 나타낼 row 의 갯수
	final int PAGE_ROW_COUNT=5;
	//하단 디스플레이 페이지 갯수
	final int PAGE_DISPLAY_COUNT=5;
	
	@Override
	public void getList(HttpServletRequest request) {
		//보여줄 페이지의 번호
		int pageNum=1;
		//보여줄 페이지의 번호가 파라미터로 전달되는지 읽어와 본다.	
		String strPageNum=request.getParameter("pageNum");
		if(strPageNum != null){//페이지 번호가 파라미터로 넘어온다면
			//페이지 번호를 설정한다.
			pageNum=Integer.parseInt(strPageNum);
		}
		//보여줄 페이지 데이터의 시작 ResultSet row 번호
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT; //공차 수열 an=a1+(n-1)d
		//보여줄 페이지 데이터의 끝 ResultSet row 번호
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		
		/*
		검색 키워드에 관련된 처리
		*/
		String keyword=request.getParameter("keyword");
		//어떤 것으로 검색했는지 파라미터 값
		String condition=request.getParameter("condition");
		if(keyword==null){//전달된 키워드가 없다면
			condition="";
			keyword="";//빈 문자열을 넣어준다.
		}
		//인코딩된 키워드를 미리 만들어 둔다.
		String encodedK=URLEncoder.encode(keyword);
		//검색 키워드와 startRowNum, endRowNum 을 담을 FileDto 객체 생성
		CafeDto dto=new CafeDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		if(!keyword.equals("")){//만일 키워드가 넘어온다면
			if(condition.equals("title_content")){
				//검색 키워드를 FileDto 객체의 필드에 담는다.
				dto.setTitle(keyword);
				dto.setContent(keyword);
			}else if(condition.equals("title")){
				dto.setTitle(keyword);
			}else if(condition.equals("writer")){
				dto.setWriter(keyword);
			}
		}
		//파일 목록 얻어오기
		List<CafeDto> list=cafeDao.getList(dto);
		//전체 row 의 갯수를 담을 변수// 전체 row 의 갯수를 알아야지 페이지를 나눌수 있다.
		int totalRow=cafeDao.getCount(dto);
		
		//전체 페이지의 갯수 구하기 //나눈것의 실수를 정수값으로 올림해서 사용한다는뜻 // 실수를 나오게하려면 정수 나누기 실수를 해야되서 나누는건 double로 우선 캐스팅
		int totalPageCount=
				(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//시작 페이지 번호 //1,2,3이든 page_display_count 가 5 이면 0 // 정수 정수끼리 나누면 정수이기때문
		int startPageNum=
			1+((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//끝 페이지 번호가 잘못된 값이라면 
		if(totalPageCount < endPageNum){
			endPageNum=totalPageCount; //보정해준다. 
		}
		
		//EL 에서 사용할 값을 미리 request 에 담아두기
		request.setAttribute("list", list);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("condition", condition);
		request.setAttribute("keyword", keyword);
		request.setAttribute("encodedK", encodedK);
	}

	@Override
	public void getDetail(HttpServletRequest request) {
		//파라미터로 전달되는 글번호
		int num=Integer.parseInt(request.getParameter("num"));
		/*
		검색 키워드에 관련된 처리
		*/
		String keyword=request.getParameter("keyword");
		//어떤 것으로 검색했는지 파라미터 값
		String condition=request.getParameter("condition");
		if(keyword==null){//전달된 키워드가 없다면
			condition="";
			keyword="";//빈 문자열을 넣어준다.
		}
		//인코딩된 키워드를 미리 만들어 둔다.
		String encodedK=URLEncoder.encode(keyword);
		//글번호와 건색 키워드를 담을 CafeDto 객체 생성
		CafeDto dto=new CafeDto();
		dto.setNum(num);//글번호 담기
		
		if(!keyword.equals("")){//만일 키워드가 넘어온다면
			if(condition.equals("title_content")){
				//검색 키워드를 FileDto 객체의 필드에 담는다.
				dto.setTitle(keyword);
				dto.setContent(keyword);
			}else if(condition.equals("title")){
				dto.setTitle(keyword);
			}else if(condition.equals("writer")){
				dto.setWriter(keyword);
			}
		}
		//자세히 보여줄 글 정보
		CafeDto resultDto=cafeDao.getData(dto);
		
		//view 페이지에서 필요한 내용 HttpServletRequest 에 담기
		request.setAttribute("dto", resultDto);
		request.setAttribute("condition", condition);
		request.setAttribute("keyword", keyword);
		request.setAttribute("encodedK", encodedK);
		
		//글 조회수 올리기
		cafeDao.addViewCount(num);
	}

	@Override
	public void saveContent(CafeDto dto) {
		cafeDao.insert(dto);
	}

	@Override
	public void getData(int num, ModelAndView mView) {
		CafeDto dto=cafeDao.getData(num);
		mView.addObject("dto", dto);
	}

	@Override
	public void updateContent(CafeDto dto) {
		cafeDao.update(dto);
	}

	@Override
	public void deleteContent(int num, HttpServletRequest request) {
		//1. 삭제할 글의 정보를 읽어온다.
		CafeDto dto=cafeDao.getData(num);
		//2. 본인이 작성한 글이 아닌경우 에러 처리를 한다.(예외를 발생시킨다)
		String id=(String)request.getSession().getAttribute("id");
		//만일 로그인된 아이디와 글 작성자가 다르면
		if(!id.equals(dto.getWriter())) {
			throw new NotDeleteException("남의 글을 지우지 마세요!!");
		}
		cafeDao.delete(num);
	}
	
}
