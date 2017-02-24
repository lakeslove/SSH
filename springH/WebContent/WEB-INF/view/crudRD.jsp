<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script>
$(document).ready(function() {
	$.changeSelectedNav("nav-datas-id");
	$("#searchUsersButton").click(function(){
		searchAdminUserList(1);
	})
});
function searchAdminUserList(page) {
	var searchFormData = $('#searchUsersForm').serialize();
	var tempData = searchFormData + ('&pageIndex=' + pageIndex);
	$.AJAX({
		url :  "searchUsers.htm",
		type : "post",
		data : tempData,
		dataType: dataType,
		success : function(data) {
			showDatasInTable(data);
		}
	});
}
function showDatasInTable(data){
	$.deleteTds();
	var sizeInCurrentPage = data.sizeInCurrentPage;
	var sizeOfAll = data.sizeOfAll;
	var sumPage = data.sumPage;
	var resultList = data.listInCurrentPage;
	var page=data.currentPage;
	var perPageNum = data.perPageNum;
	var startNo = (page-1)*perPageNum+1;
	var insertTds = "";
	if(sizeOfAll>0){
		for(var i =0;i<resultList.length;i++){
			resultList[i] = $.JSONobjectConvert(resultList[i]);
			var teminsertTds =
				"<tr class='list-content-datas'>"+
				"<td>"+(i+startNo)+"</td>"+
				"<td>"+resultList[i].id+"</td>"+
				"<td>"+resultList[i].name+"</td>"+
				"<td>"+resultList[i].slogan+"</td>"+
				"<td>"+"<a href=''>编辑</a>"+"</td>"+
				"<td>"+"<a href=''>删除</a>"+"</td>"+
				"</tr>"
				insertTds = insertTds+teminsertTds;
		}
		$("#list-content-header").html(insertTds);
		if(sumPage>1){
			pageUtils.pageHelper({
				nowPage:page,
		        numPerPage:perPageNum,
		        sizeOfAll:sizeOfAll,
		        methodName:"turnPage",
		        success:function(data){
		        	$("#searchUsersResultPages").html(bodyTurnPageString);
		 		   	$("#searchUsersResultNumbers").html(bodyTurnPageString);
		        }
			})
		}else{
			$("#bodyTurnPageTop").html("");
	   		$("#bodyTurnPage").html("");
		}
	}
}
function turnPage(page){
	
}
</script>
<style>
#searchUsersResultPagesDiv{
}
</style>
<div class="small_content">
	<form:form id="searchUsersForm">
		<table>
			<tr>
				<td colspan="2">数据检索</td>
			</tr>
			<tr>
				<td colspan="2"></td>
			</tr>
			<tr>
				<td>用户名:</td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td>标语:</td>
				<td><form:textarea path="slogan" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="数据检索"
					id="searchUsersButton" /></td>
			</tr>
		</table>
	</form:form>
	<table>
		<thead>
		<tr>
			<td>No.</td><td>id</td><td>name</td><td>slogan</td><td>编辑</td><td>删除</td>
		</tr>
		</thead>
		<tbody id="searchUsersResult">
		</tbody>
	</table>
	<div class="searchUsersResultPagesDiv">
	<div id="searchUsersResultPages"></div>
	<div id="searchUsersResultNumbers"></div>
	</div>
</div>