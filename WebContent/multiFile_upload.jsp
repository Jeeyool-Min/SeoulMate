<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.*"%>
<%@ page import = "java.util.*" %>
<%@ page import = "java.io.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title> fileupload5_process.jsp </title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		String path="c:\\upload";
		DiskFileUpload upload = new DiskFileUpload();
		/*최대 용량 제한*/
		/*한번에 셋팅할수도 있음*/
		List items = upload.parseRequest(request, 4096, 1000000L, path);
		System.out.println("request가 empty?" + items.isEmpty());
		Iterator params = items.iterator();
		
		System.out.println(params.hasNext());
		
		ArrayList<String> imgUrl= new ArrayList<String>();
		//fileItem 객체에 대한 예외처리 필요
		while(params.hasNext()){
			FileItem item = (FileItem) params.next();
			if(item.isFormField()){ /*파일이 아니라면*/
				String name = item.getFieldName(); /*요청파라미터 이름. 여기선 쓰지 않음*/
				String value = item.getString("utf-8");
				out.println(name + "=" + value + "<br>");
			} else{
				String fileName = item.getName();
				
				System.out.println(fileName);
				fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
				long fileSize = item.getSize();
				
				File file = new File(path + "/" + fileName); //"파일경로/파일이름.png"
				item.write(file); //파일 업로드
				//#파일크기 제한해야함
				imgUrl.add(fileName);
				System.out.println(fileName);
			}
		}
	%>
	

</body>
</html>