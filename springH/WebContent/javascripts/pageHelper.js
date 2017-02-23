//使用示例
//pageHelper.setPage(7,8,124,"test");
//    alert(pageHelper.pagesString);
//    document.getElementById("pages").innerHTML=pageHelper.pagesString;
//    alert(pageHelper.numString);
//    document.getElementById("nums").innerHTML=pageHelper.numString;
//    
//    function test(page){
//        alert(page)
//    }
var pageHelper = {
	Num: 5,//最多展示的页数
    pagesString:"",
    numString:"",
	setPage:function(nowPage,numPerPage,sizeOfAll,methodName){
		var Num = pageHelper.Num;
        var pagesString = pageHelper.pagesString;
        var numString = pageHelper.numString;
        var sumpage = sizeOfAll%numPerPage==0?parseInt(sizeOfAll/numPerPage):parseInt(sizeOfAll/numPerPage)+1;
		var startPage = parseInt((nowPage-1)/Num)*Num+1;
		var endPage = (startPage +Num-1)>sumpage?sumpage:startPage +Num-1;
		if(nowPage>Num){
			pagesString = pagesString+'<a style="color:blue;" onclick="'+methodName+'('+1+')">首页</a>　';
			var preStartPage=startPage-Num;
			pagesString = pagesString+'<a style="color:blue;" onclick="'+methodName+'('+preStartPage+')">上一页</a>　';
		}
		for(var i=startPage;i<=endPage;i++){
			if(i==nowPage){
				pagesString =pagesString +i+'　';
			}else{
				pagesString =pagesString +'<a style="color:blue;" onclick="'+methodName+'('+i+')">'+i+'</a>　';
			}
		}
		if((startPage+Num-1)<sumpage){
			var nextStartPage=startPage+Num;
			pagesString =pagesString +'<a style="color:blue;" onclick="'+methodName+'('+nextStartPage+')">下一页</a>　';
			pagesString =pagesString +'<a style="color:blue;" onclick="'+methodName+'('+sumpage+')">尾页</a>';
		}
		var startSize = parseInt((nowPage-1))*numPerPage+1;
		var endSize= (startSize +numPerPage-1)<sizeOfAll?(startSize +numPerPage-1):sizeOfAll;
		numString = numString + startSize + " - " + endSize;
		numString = numString + " / " +sizeOfAll;
		
		pageHelper.pagesString = pagesString;
		pageHelper.numString = numString;
	}
}