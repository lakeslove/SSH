<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.row {
    width: 100%;
    max-width: 1140px;
    min-width: 755px;
    margin: 0 auto;
    overflow: hidden;
}
.nav li{
display:inline;

}
</style>
<div class="row">
    <div class="col logo">
      <h1><a href="/">菜鸟教程 -- 学的不仅是技术，更是梦想！</a></h1>
    </div>
        <div class="col search search-desktop last">
      <form action="//www.runoob.com/" target="_blank">
        <input class="placeholder" id="s" name="s" value="搜索……" autocomplete="off">
      </form>
    </div>
  </div>
<div class="container navigation">
	<div class="row">
		<div class="col nav">
			<ul id="index-nav">
				<li><a href="//www.runoob.com/" data-id="index" title="菜鸟教程" class="current">首页</a></li>
				<li><a href="//www.runoob.com/w3cnote/" target="_blank" data-id="note" title="菜鸟笔记">菜鸟笔记</a></li>
				<li><a href="//c.runoob.com/" target="_blank" data-id="tool" title="不止于工具">菜鸟工具</a></li>
			</ul>
		</div>
	</div>
</div>