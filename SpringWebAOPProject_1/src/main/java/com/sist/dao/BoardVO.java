package com.sist.dao;
import java.util.*;

import lombok.Data;

@Data
public class BoardVO {
	private int no,hit,group_id,group_tab,group_step,root,depth;
	private String name,subject,content,pwd,dbday;
	private Date regdate;
}
