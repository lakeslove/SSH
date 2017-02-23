<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.logo{
	margin-left:8%;
	width: 61.6%;
	hight:100px;
}
.logo a {
    height: 39px;
    display: block;
}
.search {
	position:relative;
	top:30px;
    width: 26%;
    margin:15px;
}
.search input {
    line-height: 34px;
    left: 9px;
    top: 0;
    text-align: initial;
    white-space: nowrap;
    right: 9px;
    height: 35px;
    width: 94%;
    padding: 0 3% 0 3%;
    background-color: #fff;
    border: 1px solid #c8c8c8;
    border-radius: 3px;
    color: #ccc;
    font-weight: 400;
    font-size: 1.2em;
}
.navigation{
    background: #b0dce0;
}
.nav li{
display:inline;
margin:10px;
font-size:28px;
font-weight: bolder;
}
.nav li a{
text-decoration: none;
color:#f3a;
}
.selected-nav{
	color:white;
}

</style>
<div class="subject row pr">
    <div class="col logo">
      <h1><a href="index.htm">javaEE功能示例--SSH</a></h1>
    </div>    
    <div class="col search">
      <form action="" target="_blank">
        <input class="" id="s" placeholder="search"  name="search" autocomplete="off">
      </form>
    </div>
</div>
<div class="container navigation">
	<div class="row">
		<div class="col nav">
			<ul id="index-nav">
				<li><a href="index.htm" id="nav-index-id" data-id="index" title="SSH" class="current">首页</a></li>
				<li><a href="datas.htm" id="nav-datas-id" target="_blank" data-id="note" title="数据处理">数据处理</a></li>
				<li><a href="files.htm" id="nav-files-id" target="_blank" data-id="tool" title="文件处理">文件处理</a></li>
				<li><a href="methods.htm" id="nav-files-id" target="_blank" data-id="tool" title="共通方法">共通方法</a></li>
				<li><a href="frames.htm" id="nav-files-id" target="_blank" data-id="tool" title="框架功能">框架功能</a></li>
				<li><a href="others.htm" id="nav-files-id" target="_blank" data-id="tool" title="其他">其他</a></li>
			</ul>
		</div>
	</div>
</div>