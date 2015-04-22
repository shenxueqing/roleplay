package com.ebupt.roleplay.server.north.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.ebupt.roleplay.server.north.exception.JhoNorthResultStatus;
import com.ebupt.roleplay.server.north.exception.JhoServerServiceException;

public class FileUtil {
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	

	 public static boolean SaveFile( InputStream in,String fileName) {   
		  FileOutputStream fos = null;    
		   BufferedInputStream bis = null;       
		    int BUFFER_SIZE = 1024; 
		    byte[] buf = new byte[BUFFER_SIZE];    
		    int size = 0;    

		    bis = new BufferedInputStream(in);     
		    try {
				fos = new FileOutputStream(fileName);
				//保存文件    
			    while ( (size = bis.read(buf)) != -1)     
			      fos.write(buf, 0, size);      
			    fos.close();    
			    bis.close();  
			   
			} catch (Exception e) {
				logger.error("e:{}",e);
				return false;
			}     		  
		    return true;
		  } 
	 
	 

		/**
		 * @param request
		 * @return List<FileItem> 
		 * 调用restlet的api,根据boundary分割entity为FileItem的列表。
		 * @throws JhoServerServiceException 
		 */
	 /*
		public static List<FileItem> getRequestFileList(HttpServletRequest request) throws JhoServerServiceException {
			
			List<FileItem> list = null;
			DiskFileItemFactory  factory = new DiskFileItemFactory ();
			factory.setSizeThreshold(10240);
			factory.setRepository(new File(JhoNorthConfig.picDirectory+"tmp"));
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				list = upload.parseRequest(request);
			} catch (Exception e) {
                e.printStackTrace();
				JhoServerServiceException e1= new JhoServerServiceException(JhoNorthResultStatus.reqParser_Error,"Muitipart请求解析异常");
				e1.initCause(e);
				throw e1;
			}
			logger.debug("After FileUpload the FileItem-list size is  {}" , list.size());

			return list;
		}
		
		public static Map<String,Object> parseFileItemToVarsMap(List<FileItem> fileList) throws JhoServerServiceException{
			Map<String,Object> reqMap = new HashMap<String,Object>();
			List<InputStream> fileStreamList = new ArrayList<InputStream>();
			for(FileItem formdata: fileList){	
				String name = formdata.getFieldName();
				logger.debug("formName:{}",name);
				if(!name.equals("file")){
					try {
						reqMap.put(name, formdata.getString("utf-8"));
					} catch (UnsupportedEncodingException e) {
						throw new JhoServerServiceException(JhoNorthResultStatus.reqParser_Error, "参数解析错误，请确认编码方式为utf-8");
					}
				}else{
					try {
						fileStreamList.add(formdata.getInputStream());
					} catch (IOException e) {
						throw new JhoServerServiceException(JhoNorthResultStatus.reqParser_Error, "签到图像文件解析错误");
					}
				}
			}
			reqMap.put("file",fileStreamList );
			return reqMap;
		}
		*/
		
		/**
		 * @param path
		 * @return 子目录地址
		 */
		public static void makeChildPath(String path)throws JhoServerServiceException{	
			try{	
			
				File Dir = new File(path);
				Dir.mkdirs();
			}catch(Exception e){
				JhoServerServiceException e1 = new JhoServerServiceException(JhoNorthResultStatus.file_save_error,"创建图片文件失败");
				e1.initCause(e);
				throw e1;
			}
		
				
		}
		
		
		 /** 
	     * 复制文件或者目录,复制前后文件完全一样。 
	     *  
	     * @param resFilePath 
	     *            源文件路径 
	     * @param distFolder 
	     *            目标文件夹 
	     * @IOException 当操作发生异常时抛出 
	     */  
	    public static void copyFile(String resFilePath, String distFolder)  
	            throws IOException {  
	        File resFile = new File(resFilePath);  
	        File distFile = new File(distFolder);  
	        if (resFile.isDirectory()) {  
	            FileUtils.copyDirectoryToDirectory(resFile, distFile);  
	        } else if (resFile.isFile()) {  
	            FileUtils.copyFileToDirectory(resFile, distFile, true);  
	        }  
	    }  
	  
	    /** 
	     * 删除一个文件或者目录 
	     *  
	     * @param targetPath 
	     *            文件或者目录路径 
	     * @IOException 当操作发生异常时抛出 
	     */  
	    public static void deleteFile(String targetPath) throws IOException {  
	        File targetFile = new File(targetPath);  
	        if (targetFile.isDirectory()) {  
	            FileUtils.deleteDirectory(targetFile);  
	        } else if (targetFile.isFile()) {  
	            targetFile.delete();  
	        }  
	    }  
	  
	    /** 
	     * 移动文件或者目录,移动前后文件完全一样,如果目标文件夹不存在则创建。 
	     *  
	     * @param resFilePath 
	     *            源文件路径 
	     * @param distFolder 
	     *            目标文件夹 
	     * @IOException 当操作发生异常时抛出 
	     */  
	    public static void moveFile(String resFilePath, String distFolder)  
	            throws IOException {  
	        File resFile = new File(resFilePath);  
	        File distFile = new File(distFolder);  
	        if (resFile.isDirectory()) {  
	            FileUtils.moveDirectoryToDirectory(resFile, distFile, true);  
	        } else if (resFile.isFile()) {  
	            FileUtils.moveFileToDirectory(resFile, distFile, true);  
	        }  
	    }  
	  
	  
	  
	    /** 
	     * 读取文件或者目录的大小 
	     *  
	     * @param distFilePath 
	     *            目标文件或者文件夹 
	     * @return 文件或者目录的大小，如果获取失败，则返回-1 
	     */  
	    public static long getFileSize(String distFilePath) {  
	        File distFile = new File(distFilePath);  
	        if (distFile.isFile()) {  
	            return distFile.length();  
	        } else if (distFile.isDirectory()) {  
	            return FileUtils.sizeOfDirectory(distFile);  
	        }  
	        return -1L;  
	    }  
	  
	    /** 
	     * 判断一个文件是否存在 
	     *  
	     * @param filePath 
	     *            文件路径 
	     * @return 存在返回true，否则返回false 
	     */  
	    public static boolean isExist(String filePath) {  
	        return new File(filePath).exists();  
	    }  
	  
	  
	    /** 
	     * 将字符串写入指定文件(当指定的父路径中文件夹不存在时，会最大限度去创建，以保证保存成功！) 
	     *  
	     * @param res 
	     *            原字符串 
	     * @param filePath 
	     *            文件路径 
	     * @return 成功标记 
	     */  
	    public static boolean string2File(String res, String filePath) {  
	        boolean flag = true;  
	        BufferedReader bufferedReader = null;  
	        BufferedWriter bufferedWriter = null;  
	        try {  
	            File distFile = new File(filePath);  
	            if (!distFile.getParentFile().exists())  
	                distFile.getParentFile().mkdirs();  
	            bufferedReader = new BufferedReader(new StringReader(res));  
	            bufferedWriter = new BufferedWriter(new FileWriter(distFile));  
	            char buf[] = new char[1024]; // 字符缓冲区  
	            int len;  
	            while ((len = bufferedReader.read(buf)) != -1) {  
	                bufferedWriter.write(buf, 0, len);  
	            }  
	            bufferedWriter.flush();  
	            bufferedReader.close();  
	            bufferedWriter.close();  
	        } catch (IOException e) {  
	            flag = false;  
	            e.printStackTrace();  
	        }  
	        return flag;  
	    }  
}
