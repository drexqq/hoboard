package news;
    import java.io.File;
  import java.io.IOException;
  import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  
  import com.oreilly.servlet.MultipartRequest;
  import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Util.UtilEx;
import oracle.sql.TIMESTAMP;
import review.Review_Dto;
  
  @WebServlet("/news_file")
  public class News_File_Controller extends HttpServlet {
  
	  
  	@Override
  	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  		
  		System.out.println("news_file");
  		
  		String realFolder="";
  		
  		String saveFolder="upload";
  		String encType="utf-8";
  		int sizeLimit = 1024*1024*15;
  		
  		ServletContext context=this.getServletContext();
  		
  		realFolder = context.getRealPath(saveFolder);
  		
  		System.out.println("실제 서블릿 경로 : " + realFolder);
  		System.out.println("실제 서블릿 업로드 경로 : " + realFolder);
  		
  		MultipartRequest mr = null;

  		 try{
  			
  			mr = new MultipartRequest(req, realFolder, sizeLimit, encType, new DefaultFileRenamePolicy());   
  			 
  			Enumeration en = mr.getParameterNames();
  			while(en.hasMoreElements()) {
  				String title=(String)en.nextElement();
  				String value=mr.getParameter(title);
  				System.out.println(title+" / "+value);
  			}
  			
  			en = mr.getFileNames();
  			while(en.hasMoreElements()) {
  				String title=(String)en.nextElement();
  				String originFile = mr.getOriginalFileName(title);
  				
  				String systemFile = mr.getFilesystemName(title);
  				
  				String fileType=mr.getContentType(title);
  				
  				File file = mr.getFile(title);
  				
  				System.out.println("para = " +title);
  				System.out.println("originFile = " +originFile);
  				System.out.println("systemFile = " +systemFile);
  				System.out.println("fileType = " +fileType);
  				
  				if(file != null) {
  					System.out.println("크기 : " +file.length()+"byte");
  				}
  				
  			}
  		 
  	
  		 
  			
/*  			int seq = Integer.parseInt(multi.getParameter("seq"));
  	            Enumeration fileNames=multi.getFileNames();   //파일 이름 반환
  	            boolean file_update = true;
  	            boolean save= true;
  	            
  	            title = mr.getParameter("title");  // 폼으로 받아온 fileName
  	            String[] inters = mr.getParameterValues("inter");
  	            for(String inter : inters){
  	      		buffer.append(inter);
  	            content = mr.getParameter("content");  // 저장된 파일 종류
  	          
  	            
  	            File fileObj = null;   //저장된 파일 객체
  	            String originFileName = "";   //원본 파일 이름
  	            String fileExtend = "";   //jpg,png,gif 등 확장자
  	            String fileSize = "";   //저장된 파일 사이즈
  	            String newFileName = "Hoboard_"+System.currentTimeMillis();   //저장된 파일을 바꿀 이름
  	            System.out.println("newFileName:"+newFileName);
  
  	            while(fileNames.hasMoreElements()){
  	                fileInput = (String)fileNames.nextElement();
  	                fileName = multi.getFilesystemName(fileInput);
  	                if(fileName != null){
  	                    type = multi.getContentType(fileInput);
  	                    fileObj = multi.getFile(fileInput);
  	                    originFileName = multi.getOriginalFileName(fileInput);
  	                    fileExtend = fileName.substring(fileName.lastIndexOf(".")+1);  //"file1.jpg"라면 jpg 반환
  	                    fileSize = String.valueOf(fileObj.length());  //file도 결국 문자열이므로 length()로 반환
  	                    System.out.println("type:"+type+"||originFileName:"+originFileName+
  	                            "||fileExtend:"+fileExtend+"||fileSize:"+fileSize);
  	                    
  	                    String[] splitType = type.split("/");
  	                    if(!splitType[0].equals("image")){
  	                        save=false;
  	                        fileObj.delete();   //저장된 파일 객체로 삭제
  	                        break;
  	                    }else{  //만약 이미지 파일이면 저장 파일의 이름 바꾼다.
  	                        newFileName += "."+fileExtend;
  	                        fileObj.renameTo(new File(savePath+"\\"+newFileName));
  	                    }
  	                }
  	            }

  	  		 String context = req.getContextPath();
  	            if(save){   //파일 저장 성공시
  	                dto.setNews_seq(Integer.parseInt(multi.getParameter("seq")));
  	                dto.setId(multi.getParameter("id"));
  	                dto.setTitle(multi.getParameter("title"));
  	                dto.setContent(multi.getParameter("content"));
  	                dto.setViewcount(Integer.parseInt(multi.getParameter("viewcount")));
  	                dto.setDate(multi.getParameter("wdate"));
  	                dto.setFile(multi.getParameter("file"));
  	                
  	                file_update = dao.news_write(dto);
  	            }
  	            if (save && file_update) {   //DB file_update까지 성공시
  	                System.out.println("저장 성공");
  	                resp.sendRedirect(context + "/file?seq="+seq);
  	            } else {
  	                System.out.println("저장 실패");
  	                resp.sendRedirect(context + "/file");
  	            }*/
  	            }catch(Exception e){e.printStackTrace();
  	            System.out.println("졸려");
  	            }
  		 
  		String uploadPath = req.getRealPath("/upload");
  		
  		System.out.println("realPath : " + uploadPath);
  		
  		sizeLimit = 15*1024*1024;
  		String title="";
  		String content="";
  		
  		String file="";
  		String originalfile="";
  		
  		mr = null;
  		
        News_Dao dao = News_Dao.getInstance();
  		News_Dto dto = new News_Dto();
  		
  		try {
  			mr = new MultipartRequest(req, uploadPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
  		
  			title = mr.getParameter("title");
  			content = mr.getParameter("content");
  			System.out.println(title+"/"+content);
  			
  			Enumeration files = mr.getFileNames();
  			
  			String file1 = (String)files.nextElement();
  			file = mr.getFilesystemName(file1);
  			originalfile = mr.getOriginalFileName(file1);
  			
  		
  		}catch (Exception e) {
  			System.out.println("파일 업로드 실패" + e.getMessage());
  		
  		}
  		
  		req.setAttribute("title", title);
  		req.setAttribute("content", content);
  		req.setAttribute("file", file);
  		req.setAttribute("originalfile", originalfile);
  		
  		UtilEx.forward("news_file.jsp", req, resp);
  		
  		
		// DB에 저장
		boolean isS = dao.news_file(dto);
		if (isS) {
			System.out.println("파일 업로드 성공");
			resp.sendRedirect("news");

		} else {
			System.out.println("파일 업로드 실패");
			resp.sendRedirect("news");

	}
















 

  	}
  	    
  }
