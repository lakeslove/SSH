package com.lx.utils;

import java.io.Serializable;
import java.util.List;

public class PageData<T> implements Serializable {

	private static final long serialVersionUID = 6004807783441437877L;

	public static int perPageNum = 10;

	private long sizeOfAll;

	private int sumPage;

	private int sizeInCurrentPage;

	private int currentPage;

	private List<T> listInCurrentPage;
	
	public PageData(){
		PageData<?> pageHelp = new PageData<>();
		pageHelp.sizeOfAll = 0l;
		pageHelp.sumPage = 1;
		pageHelp.sizeInCurrentPage = 0;
	}
	
	public PageData(Long sizeOfAll, List<T> listInCurrentPage) {
		this.sizeOfAll = sizeOfAll;
		this.sumPage = sumPage(sizeOfAll);
		this.listInCurrentPage = listInCurrentPage;
		if(listInCurrentPage!=null){
			this.sizeInCurrentPage = listInCurrentPage.size();
		}else{
			this.sizeInCurrentPage = 0;
		}
	}

	public static int sumPage(Long sizeOfAll) {
		return (int) Math.ceil(sizeOfAll / perPageNum);
	}

	public static int getOffset(int page) {
		return (page - 1) * perPageNum;
	}

	public static int getPerPageNum() {
		return perPageNum;
	}

	public static void setPerPageNum(int perPageNum) {
		PageData.perPageNum = perPageNum;
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

	public void setSumPage(int sumPage) {
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

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<?> getListInCurrentPage() {
		return listInCurrentPage;
	}

	public void setListInCurrentPage(List<T> listInCurrentPage) {
		this.listInCurrentPage = listInCurrentPage;
	}
}
