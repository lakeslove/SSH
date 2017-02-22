package com.lx.utils;

import java.io.Serializable;
import java.util.List;

public class PageHelp<T> implements Serializable {

	private static final long serialVersionUID = 6004807783441437877L;

	public static int perPageNum = 10;

	private long sizeOfAll;

	private long sumPage;

	private int sizeInCurrentPage;

	private long currentPage;

	private List<T> listInCurrentPage;
	
	public PageHelp(){
		PageHelp<?> pageHelp = new PageHelp<>();
		pageHelp.sizeOfAll = 0l;
		pageHelp.sumPage = 1l;
		pageHelp.sizeInCurrentPage = 0;
	}
	
	public PageHelp(Long sizeOfAll, List<T> listInCurrentPage) {
		this.sizeOfAll = sizeOfAll;
		this.sumPage = sumPage(sizeOfAll);
		this.listInCurrentPage = listInCurrentPage;
		if(listInCurrentPage!=null){
			this.sizeInCurrentPage = listInCurrentPage.size();
		}else{
			this.sizeInCurrentPage = 0;
		}
	}

	public static long sumPage(Long sizeOfAll) {
		return (long) Math.ceil(sizeOfAll / perPageNum);
	}

	public static Integer getOffset(Integer page) {
		return (page - 1) * perPageNum;
	}

	public static int getPerPageNum() {
		return perPageNum;
	}

	public static void setPerPageNum(int perPageNum) {
		PageHelp.perPageNum = perPageNum;
	}

	public long getSizeOfAll() {
		return sizeOfAll;
	}

	public void setSizeOfAll(long sizeOfAll) {
		this.sizeOfAll = sizeOfAll;
	}

	public long getSumPage() {
		return sumPage;
	}

	public void setSumPage(long sumPage) {
		this.sumPage = sumPage;
	}

	public int getSizeInCurrentPage() {
		return sizeInCurrentPage;
	}

	public void setSizeInCurrentPage(int sizeInCurrentPage) {
		this.sizeInCurrentPage = sizeInCurrentPage;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

	public List<?> getListInCurrentPage() {
		return listInCurrentPage;
	}

	public void setListInCurrentPage(List<T> listInCurrentPage) {
		this.listInCurrentPage = listInCurrentPage;
	}
}
