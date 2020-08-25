package com.gura.spring05.shop.service;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.exception.NoDeliveryException;
import com.gura.spring05.shop.dao.OrderDao;
import com.gura.spring05.shop.dao.ShopDao;
import com.gura.spring05.shop.dto.OrderDto;
import com.gura.spring05.shop.dto.ShopDto;

@Service
public class ShopServiceImpl implements ShopService{
	
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public void getList(ModelAndView mView) {
		//상품 목록
		List<ShopDto> list=shopDao.getList();
		//ModelAndView 객체에 list 라는 키값으로 담는다.
		mView.addObject("list", list);
	}
	/*
	 * 	- Spring 트렌젝션 설정 방법
	 * 	1. pom.xml 에 spring-tx dependency 추가
	 * 	2. servlet-context.xml 에 transaction 설정추가
	 * 	3. 트렌젝션을 관리할 서비스의  메소드에 @Transactional 어노테이션 붙이기 = > sqlException 도 성공 해야지만 결과를 다 반영해주는 것을 저절로 해준다.
	 * 
	 * 	트랜젝션 관리 : update , update, update , insert 를 다 함께 관리해야한다. (DataAccessException 이 발생 됬을 때 다 리셋 되도록한다.)
	 * 				프로그래머에 의도하에서 Exception 을 의도적으로 발생시킬수도 있다. 
	 * 				그때 DataAccessException 을 발생시키면 된다. DataAccessException을 상속받은 클래스를 만들어서 Exception을 발생시키면 된다.
	 * 				이렇게 하는 이유는 트랜젝션 관리에 영향을 주기 위해서
	 * 
	 * - 프로그래머의 의도 하에서 트랜젝션에 영향을 주는 Exception 을 발생 시키는 방법
	 * 	
	 * 	DataAccessException 클래스를 상속받은 클래스를 정의하고
	 * 	예) class MyException extends DataAccessException{	}
	 * 		throw new MyException("예외 메세지");
	 * 
	 * 	예외를 발생시킬 조건이라면 위와 같이 예외를 발생시켜서 
	 * 	트랜젝션이 관리 되도록 한다.
	 * 	
	 * 	이 예외 Exception 은 Dao 에서 적용시키면 된다.
	 */
	@Transactional
	@Override
	public void buy(HttpServletRequest request, ModelAndView mView) {
		//구입자의 아이디
		String id=(String)request.getSession().getAttribute("id");
		//1. 파라미터로 전달되는 구입할 상품 번호
		int num=Integer.parseInt(request.getParameter("num"));
		//2. 상품의 가격을 얻어온다.(select)
		int price=shopDao.getPrice(num);
		/*
		 * 	여러개의 동작을 같이 해야할때가 있다. 모두 성공했을때 정보를 반영하는 것을 트랜잭션 관리라고 한다.
		 * 	트랜잭션 관리를 받기 위해서는 @Transactional 어노테이션이 필요하다.
		 * 	- 이 전에 spring 에 트랜잭션을 설정해야 한다.
		 */
		//3. 상품의 가격만큼 계좌 잔액을 줄인다.(update) 
		ShopDto dto=new ShopDto();
		dto.setId(id);
		dto.setPrice(price);
		shopDao.minusMoney(dto);
		//4. 가격의 10% 를 포인트로 적립한다.(update)
		shopDao.plusPoint(dto);
		//5. 재고의 갯수를 1 줄인다.(update)
		shopDao.minusCount(num);
		//6. 주문 테이블(배송) 에 정보를 추가 한다.(insert)
		OrderDto dto2=new OrderDto();
		dto2.setId(id); //누가
		dto2.setCode(num);//어떤 상품을
		dto2.setAddr("강남구 삼원빌딩 5층");//어디로 배송할지
		
		//테스트로 예외를 발생 시켜 보기
		Random ran=new Random();
		int ranNum=ran.nextInt(2); //0 or 1 or 2 랜덤한 정수 발생 시키기
		if(ranNum == 0) {//만일 0이 나오면
			throw new NoDeliveryException("배송 기사가 아파서 배송을 못해요!");
		}
		
		orderDao.addOrder(dto2);
	}
}
