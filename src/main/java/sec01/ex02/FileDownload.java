package sec01.ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download.do")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("다운로드 서블릿 초기화");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String file_repo = "C:\\file_repo";
//		PrintWriter pw = response.getWriter();
//		pw.write("이미지 나옴 ");
//		pw.print(request.getParameter("fileName"));

		String fileName = (String) request.getParameter("fileName");
		String downFile = file_repo + "\\" + fileName;
		System.out.println("서버에 있는 다운 파일 " + downFile);

		// 파일 내려 받기 위한 OutputStream 객체,

		OutputStream out = response.getOutputStream();
		File f = new File(downFile);

		String encodedFileName=URLEncoder.encode(fileName,"utf-8");
		// HTTP 응답 헤더에 "Cache-Control" 필드를 설정하여 캐시 제어를 지정하는 것을 의미합니다.
		// "no-cache" 값은 캐시된 응답을 사용하지 않고 항상 서버로부터 새로운 응답을 요청해야 함을 나타냅니다.
		// 이렇게 설정하면 브라우저는 캐시된 버전을 사용하지 않고 항상 서버로부터 새로운 데이터를 가져오게 됩니다.
		response.setHeader("Cache-Control", "no-cache");

		// HTTP 응답 헤더에 "Content-disposition" 필드를 추가하여 첨부 파일 다운로드를 지정하는 것을 의미합니다.
		// "attachment" 값은 브라우저에게 응답을 첨부 파일로 처리하도록 지시하는 역할을 합니다.
		// "fileName=" 부분은 첨부 파일의 이름을 지정하는 부분으로,
		// 여기서는 변수 fileName에 저장된 값을 사용하여 다운로드되는 파일의 이름을 동적으로 설정하고 있습니다.
		// 이렇게 설정된 헤더를 받은 브라우저는 응답을 파일로 다운로드하게 됩니다.

//		response.addHeader("Content-disposition", "attachment; fileName" + fileName);
		response.addHeader("Content-disposition", "attachment; fileName" + encodedFileName);
		
		FileInputStream in = new FileInputStream(f);

		byte[] buffer = new byte[1024 * 8];

		while (true) {
			int count = in.read(buffer);
			if (count == -1) {
				break;
			}
			out.write(buffer, 0, count);
		}
		in.close();
//		pw.close();

	}

}
