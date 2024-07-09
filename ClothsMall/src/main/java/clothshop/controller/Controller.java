package clothshop.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.command.CommandAction;

@WebServlet(
		urlPatterns = { 
				"/Controller", 
				"*.do"
		}, 
		initParams = { 
				@WebInitParam(name = "propertyConfig", value = "commandMapping.properties")
		})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> commandMap = new HashMap<String, Object>();      

	public Controller() {
        super();
    }

    //명령어와 처리클래스가 매핑되어 있는 properties 파일을 읽어서 
    //HashMap객체인 commandMap에 저장
	@SuppressWarnings("deprecation")
	public void init(ServletConfig config) throws ServletException {
		// initParams에서 propertyConfig의 값을 읽어옴
		String props = config.getInitParameter("propertyConfig");
		// properties파일이 저장된 폴더
		String realFolder = "/property";
		//웹어플리케이션 루트 경로
		ServletContext context = config.getServletContext();
		//realFolder를 웹어플리케이션 시스템상의 절대경로로 변경
		String realPath = context.getRealPath(realFolder) +"\\"+props;
							    
		//명령어와 처리클래스의 매핑정보를 저장할 Properties객체 생성
		Properties pr = new Properties();
		FileInputStream f = null;
		try{
			//command.properties파일의 내용을 읽어옴
			f = new FileInputStream(realPath); 
			//command.properties의 내용을 Properties객체 pr에 저장
			pr.load(f);
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (f != null) try { f.close(); } catch(IOException ex) {}
		}
		//Set객체의 iterator()메소드를 사용해 Iterator객체를 얻어냄
		Iterator<?> keyIter = pr.keySet().iterator();
		//Iterator객체에 저장된 명령어와 처리클래스를 commandMap에 저장
		while( keyIter.hasNext() ) {
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);
			System.out.println(command + "=>"+className);
			try{
				Class<?> commandClass = Class.forName(className);
				Object commandInstance = commandClass.getDeclaredConstructor().newInstance();
				commandMap.put(command, commandInstance);
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}catch (InstantiationException e) {
				e.printStackTrace();
			}catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	//웹브라우저의 요청을 분석하고, 해당 로직의 처리를 할 모델 실행 및
	//처리 결과를 뷰에 보냄	
	private void requestPro(
			HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			String view = null;
			CommandAction com = null;
			try {
				// http://localhost:9999/ClothsMall/XXX.do
                // request.getRequestURI() = /ClothsMall/XXX.do
				String command = request.getRequestURI();
				// request.getContextPath() = /ClothsMall
		        if(command.indexOf(request.getContextPath()) == 0) 
					// request.getContextPath() = /ClothsMall  >> /XXX.do
		        	command = command.substring(request.getContextPath().length());
		        System.out.println("command ="+command);
		        com = (CommandAction)commandMap.get(command); 
		        System.out.println("com =" + com);
		        view = com.requestPro(request, response);
			}catch(Throwable e) {
				e.printStackTrace();
			}
			request.setAttribute("cont",view);
		    RequestDispatcher dispatcher = 
		       request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
}

