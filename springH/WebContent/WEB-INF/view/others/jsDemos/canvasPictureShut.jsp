<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script>
$(document).ready(function() {
	$.changeSelectedNav("nav-others-id");
	 var bitString;
     var img = document.getElementById("testImg");
     var Map,ctx,Map2,ctx2;
     // 设置绘图环境  
     function getPostion(){
         var img_x = $("#positon_x").val();
         var img_y = $("#positon_y").val();
         var img_w = $("#positon_w").val();
         var img_h = $("#positon_h").val();
         var img_angle = $("#positon_angle").val();
         var position = {
             "x":img_x,
             "y":img_y,
             "w":img_w,
             "h":img_h,
             "angle":img_angle
         }
         return position;
     }
     function setCutPosition(){
         var postion = getPostion();
         $("#testImg").css({
             "-webkit-transform":"rotate("+postion.angle+"deg)"
         });
         var cutDiv = $("#cutDiv");
         cutDiv.css({
             "top":postion.y+"px",
             "left":postion.x+"px",
             "width":postion.w+"px",
             "height":postion.h+"px",
             "border":"solid 1px red"
         })
     }
     function createString(){
         var postion = getPostion();
         var imgString = $.createImgString(img,postion.x,postion.y,postion.w,postion.h,postion.angle);
         $("#testImg2").attr("src",imgString);
     }
});
</script>
<style>
img {
	position: absolute;
	top: 00px;
}

#testImg {
	top: 0px;
	left: 0px;
}

#testImg2 {
	left: 350px;
}
#myCanvas {
	position: absolute;
	left: 850px;
}

#inputTable {
	position: absolute;
	top: 320px;
	left: 20px;
}
</style>
<div class="blocks_title">JS小例子</div>
<div class="blocks">
    <img src = "images/model.jpg" id="testImg">
    <img src = "" id="testImg2">
   <%--  <canvas id="myCanvas" style="border:1px solid #000000;"></canvas> --%>
    <div id="cutDiv" style="position:absolute"></div>
    <table id="inputTable">
        <tr><td>x:</td><td><input id="positon_x" value="-50"/></td></tr>
        <tr><td>y:</td><td><input id="positon_y" value="50"/></td></tr>
        <tr><td>w:</td><td><input id="positon_w" value="100"/></td></tr>
        <tr><td>h:</td><td><input id="positon_h" value="100"/></td></tr>
        <tr><td>angle:</td><td><input id="positon_angle" value="45"/></td></tr>
        <tr><td>set</td><td><button onclick="setCutPosition()">set</button></td></tr>
        <tr><td>cut</td><td><button onclick="createString()">cut</button></td></tr>
    </table>
</div>
