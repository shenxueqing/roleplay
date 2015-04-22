package com.ebupt.roleplay.server.north.ability.base;


import java.util.HashMap;
import java.util.List;




import org.apache.commons.fileupload.FileItem;

import com.ebupt.roleplay.server.north.data.entity.ResultModel;
import com.ebupt.roleplay.server.north.exception.JhoServerException;






public interface BaseAbility {
	ResultModel doAbility(HashMap<String,String> varsMap,List<FileItem> fileList)throws JhoServerException;
	
}
