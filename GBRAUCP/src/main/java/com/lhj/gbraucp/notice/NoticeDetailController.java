package com.lhj.gbraucp.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhj.gbraucp.member.MemberDAO;

@WebServlet("/NoticeDetailController")
public class NoticeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NoticeDetailController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO.isLogined(request);
		if (NoticeDAO.getNdao().getDetail(request)) {
			request.setAttribute("contentPage", "notice/detail.jsp");			
		} else {
			NoticeDAO.getNdao().get(1, request);
			request.setAttribute("contentPage", "notice/notice.jsp");
		}
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (MemberDAO.isLogined(request)) {
			NoticeDAO.getNdao().update(request);
		}
		NoticeDAO.getNdao().getDetail(request);
		request.setAttribute("contentPage", "notice/detail.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

}
