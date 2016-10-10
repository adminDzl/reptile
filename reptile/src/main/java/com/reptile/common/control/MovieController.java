package com.reptile.common.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.reptile.common.control.common.BaseController;
import com.reptile.common.logic.entity.TMovieData;
import com.reptile.common.logic.service.movie.MovieServiceI;
import com.reptile.common.vo.Json;
import com.reptile.common.vo.toExcel.MovieDataToExcel;
import com.xing.common.interfacePort.OperateExcelServer;
import com.xing.config.StaticProperties;

/**
 * *
 * 类名称：		EmpAttendanceReport.java 
 * 类描述：   		员工考勤信息导出
 * 创建人：		
 * 创建时间：		2016-8-18下午1:58:38 
 * 修改人：		liuxing
 * 修改时间：		2016-8-18下午1:58:38 
 * 修改备注：   		
 * @version
 */
@Controller
@RequestMapping("/movieControl")
public class MovieController extends BaseController{
	
   @Resource
   private MovieServiceI MovieService;
   
	
   /****
    * 导出员工指定月份的考勤记录信息
    * @param request
    * @return
    */
	@RequestMapping("/generatelExcel")
	@ResponseBody
	public Json generatelExcel(HttpServletRequest request) {
		Json responseJson = new Json() ;
			List<TMovieData> list = MovieService.findAllMovie() ;
			//上方获取到数据源----下面将数据源封装到excel,然后下载到
			StringBuffer filePath = new StringBuffer() ;
			filePath.append( new File(request.getSession().getServletContext().getRealPath("/") ) ) ; //当前WEB环境的上层目录
			filePath.append( "/common/excel/reportTemp.xls" );
			File excelFIle = new File( filePath.toString() ) ;
			try {
				//设定默认生成文件的位置
				if( StringUtils.isEmpty( StaticProperties.FILE_SAVE_PATH ) ){
					StringBuffer defaultPath = new StringBuffer() ;
					defaultPath.append( new File( request.getSession().getServletContext().getRealPath("/") ) .getParent() ) ;
					defaultPath.append( "/excel/temp/" ) ;
					File fi = new File( defaultPath.toString() ) ;
					if( !fi.exists() ){
						fi.mkdirs();
					}
					StaticProperties.FILE_SAVE_PATH = defaultPath.toString() ;
				}
				//读取完文件后，将取到的数据再适配出所有的有效数据，然后封装到新的文件内(新文件路径会在调用后返回)
				String repalcePath = OperateExcelServer.getInstance().exportExcelServer( list , new MovieDataToExcel(), excelFIle , 2 ) ;
				responseJson.setSuccess( true ) ;
				responseJson.setMsg( "数据封装excel文件处理成功.文件地址：" + repalcePath ) ;
			} catch (Exception e) {
				responseJson.setSuccess( false ) ;
				responseJson.setMsg("数据封装excel文件处理异常，内容为：" + e.getMessage() ) ;
			}
		return responseJson ;
	}
	
	/****
	 * Excel文件的下载方法
	 * @param filename
	 * @param filePath
	 */
	@RequestMapping("/excelDownlaod")
	@ResponseBody
	public void excelDownlaod( HttpServletResponse response, String eid, String filePath) {
		//EmployeeAttendanceDay edp=employeeAttendanceService.getEntityById(EmployeeAttendanceDay.class,Long.parseLong(eid));
		StringBuffer fileName = new StringBuffer() ;
		//fileName.append( "员工_" ) ;
		//fileName.append( edp.getEmpName() ).append( "_" ).append( edp.getTjTime() ).append("月_考勤记录");
		
		StringBuffer defaultPath = new StringBuffer() ;
		defaultPath.append( StaticProperties.FILE_SAVE_PATH ) ;
		defaultPath.append( filePath ) ;
		defaultPath.append( ".xls" ) ;
		InputStream is = null;
		OutputStream os = null;
		File file = null;
		try {
			if ( defaultPath != null && new File(defaultPath.toString()).exists()) {
				file = new File( defaultPath.toString() );
				byte[] b = new byte[16000];
				int i = 0;
				is = new FileInputStream(file.getAbsolutePath());
				os = response.getOutputStream();
				response.setContentType("application/x-msdownload");
				response.setHeader(
						"Content-Disposition",
						"attachment; filename="
								+ new String( fileName.toString().getBytes("gb2312"),
										"ISO-8859-1") + ".xls");
				while ((i = is.read(b)) != -1) {
					os.write(b, 0, i);
					i = 0;
				}
				os.flush();
			}
		} catch (Exception e) {
			System.out.println( "文件不存在:" + e.getMessage() );
		} finally {
			try {
				os.close();
				is.close();
				if (file.exists()) {
					//file.delete();
				}
			} catch (Exception e) {
			}
		}
	}
	
}
