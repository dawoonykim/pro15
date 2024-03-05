package sec01.ex01;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload.do")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("초기화");
	}

	public void destroy() {
		System.out.println("종료");
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
		File serverRepo = new File("C:\\file_repo");
		System.out.println("서버에 저장될 경로: " + serverRepo);

		// 기본 FileItemFactory 구현입니다. 이 구현은 작은 항목의 경우 콘텐츠를 메모리에,
		// 큰 항목의 경우 디스크의 임시 파일에 보관하는 FileItem 인스턴스를 만듭니다.
		// 콘텐츠가 디스크에 저장되는 크기 임계값은 임시 파일이 생성될 디렉터리와 마찬가지로 구성 가능합니다.
		DiskFileItemFactory factory = new DiskFileItemFactory();
//		System.out.println(factory);

		// DiskFileItemFactory 클래스의 setSizeThreshold() 메서드는 메모리에 보관할 임시 파일의 크기 임계값을 설정하는
		// 데 사용됩니다. 이 메서드에서 사용되는 단위는 바이트(Byte)입니다.
		// setSizeThreshold() 메서드에 전달하는 매개변수는 바이트 단위로 지정되며,
		// 임시 파일의 크기가 이 임계값보다 크면 디스크에 파일이 저장됩니다. 임시 파일의 크기가 임계값보다 작으면 메모리에 보관됩니다.
		// 이렇게 함으로써 작은 파일은 메모리에 보관되어 더 빠른 처리가 가능하고, 큰 파일은 디스크에 저장되어 메모리 사용량을 줄일 수 있습니다.

		// 파일 용량 크기 설정
		factory.setSizeThreshold(1024 * 1024);

		ServletFileUpload upload = new ServletFileUpload(factory);
		System.out.println("upload 객체" + upload);

		try {
			List<FileItem> fileItemList = upload.parseRequest(request);
			System.out.println("fileItem item란: " + fileItemList);
			System.out.println("items 크기: " + fileItemList.size());

			for (int i = 0; i < fileItemList.size(); i++) {
//				System.out.println(fileItemList.get(i));

				// FileItem 은 인코딩 타입이 multipart/form-data 일 때 , POST로 요청시 받을 수 있는 항목 클래스
				FileItem fileItem = (FileItem) fileItemList.get(i);

				if (fileItem.getSize() > 0) {
//					System.out.println("각 파일 아이템의 사이즈: " + fileItem.getSize());
					if (fileItem.isFormField()) {
//						System.out.println("여기는 폼필드");
						System.out.println(fileItem.getFieldName() + "=" + fileItem.getString("utf-8"));
					} else {
//						System.out.println("여기는 폼필드가 아닌 내용");
//						System.out.println("파일명: " + fileItem.getName());
						String uploadFileName = fileItem.getName();
						System.out.println("업로드할 파일명: " + uploadFileName);
						int index = uploadFileName.lastIndexOf("\\");
//						System.out.println(index);
						String fileName = uploadFileName.substring(index + 1);
						System.out.println(fileName);
						System.out.println("최종 업로드한 파일명 : " + uploadFileName);
						File uploadFile = new File(serverRepo + "\\" + uploadFileName);
//						System.out.println("서버에 올라갈 경로 " + uploadFile);
						fileItem.write(uploadFile);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("파일 업로드 시 예외 발생");
		}
	}

}
