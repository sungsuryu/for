package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;
import java.util.Date;

public class BoardVO implements Serializable {
	private static final long serialVersionUID = 5404555371266643157L;
	
	private int cnt;
	
	private int listNum; 
	
	private int boardidx;
	
	private String title;
	
	private String content;

	private String insurcd;
	
	private String user_id;
	
	private String user_nm;
	
	private String insrt_id;
	
	private Date insrtdate = null;
	
	private String updtid;
	
	private Date updtdate = null;
	
	private int viewcnt;
	
	private String isdel;
	
	private String alarmyn;
	
	private Date alarmdate = null;
	
	private String boardtype;
	
	private String timestamp;
	
	private int totalcnt;
	
	private int rowNo;
	
	private int pageNo;
	
	private int StartNo;
	
	private int EndNo;
	
	private int fileCnt;
	
	public int getTotalcnt() {
		return totalcnt;
	}

	public void setTotalcnt(int totalcnt) {
		this.totalcnt = totalcnt;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getStartNo() {
		return StartNo;
	}

	public void setStartNo(int startNo) {
		StartNo = startNo;
	}

	public int getEndNo() {
		return EndNo;
	}

	public void setEndNo(int endNo) {
		EndNo = endNo;
	}
	
	public Date getInsrtdate() {
		return insrtdate;
	}

	public void setInsrtdate(Date insrtdate) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
//		return sdf.format(insrtdate);
		this.insrtdate = insrtdate;
	}

	public Date getUpdtdate() {
		return updtdate;
	}

	public void setUpdtdate(Date updtdate) {
		this.updtdate = updtdate;
	}

	public Date getAlarmdate() {
		return alarmdate;
	}

	public void setAlarmdate(Date alarmdate) {
		this.alarmdate = alarmdate;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getBoardidx() {
		return boardidx;
	}

	public void setBoardidx(int boardidx) {
		this.boardidx = boardidx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getInsurcd() {
		return insurcd;
	}

	public void setInsurcd(String insurcd) {
		this.insurcd = insurcd;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_nm() {
		return user_nm;
	}

	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}

	public String getInsrt_id() {
		return insrt_id;
	}

	public void setInsrt_id(String insrt_id) {
		this.insrt_id = insrt_id;
	}


	public String getUpdtid() {
		return updtid;
	}

	public void setUpdtid(String updtid) {
		this.updtid = updtid;
	}


	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}

	public String getAlarmyn() {
		return alarmyn;
	}

	public void setAlarmyn(String alarmyn) {
		this.alarmyn = alarmyn;
	}

	public String getBoardtype() {
		return boardtype;
	}

	public void setBoardtype(String boardtype) {
		this.boardtype = boardtype;
	}

	public int getListNum() {
		return listNum;
	}

	public void setListNum(int listNum) {
		this.listNum = listNum;
	}

	public int getFileCnt() {
		return fileCnt;
	}

	public void setFileCnt(int fileCnt) {
		this.fileCnt = fileCnt;
	}
	
	
	
}
