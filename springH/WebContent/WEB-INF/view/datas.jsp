<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
         img{
            position: absolute;
            top: 00px; 
         }
        #testImg{
            top: 0px;
            left: 0px;
         }
         #testImg2{
            left: 350px;
         }
         #testImg3{
            left: 600px;
         }
         #myCanvas{
             position: absolute;
             left:850px;
         }
         #inputTable{
            position: absolute;
            top: 320px;
            left: 20px; 
         }
    </style>
<script>
$(document).ready(function() {
	$.changeSelectedNav("nav-others-id");
	var bitString;
    var img = document.getElementById("testImg");
    var Map,ctx,Map2,ctx2;
    // 设置绘图环境  
    var j$=jQuery.noConflict();
    j$(document).ready(function(){
    })
    function getPostion(){
        var img_x = j$("#positon_x").val();
        var img_y = j$("#positon_y").val();
        var img_w = j$("#positon_w").val();
        var img_h = j$("#positon_h").val();
        var img_angle = j$("#positon_angle").val();
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
        j$("#testImg").css({
            "-webkit-transform":"rotate("+postion.angle+"deg)"
        });
        var cutDiv = j$("#cutDiv");
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
        var imgString = j$.createImgString(img,postion.x,postion.y,postion.w,postion.h,postion.angle);
        j$("#testImg2").attr("src",imgString);
    }
    j$.extend({
        createImgString:function(img,x,y,width,height,angle){
        x = parseInt(x);
        y = parseInt(y);
        width = parseInt(width);
        height = parseInt(height)
        var img_canvas = document.getElementById("myCanvas");
//        var img_canvas = document.createElement('canvas');
        var imgW = (img.width)*2;
        var imgH = img.height*2;
        img_canvas.width = imgW;
        img_canvas.height = imgH;
        var img_ctx = img_canvas.getContext('2d');
        img_ctx.translate(imgW/2,imgH/2);
        img_ctx.rotate(angle*Math.PI/180);
        img_ctx.translate(-imgW/4,-imgH/4);  
        img_ctx.drawImage(img,0,0);
        console.log(x);
        console.log(imgW/4);
        console.log(parseInt(x)+parseInt(imgW/4)+","+imgH/4+","+width+","+height);
        var subImgData = img_ctx.getImageData(x+imgW/4,y+imgH/4,width,height);
        
        var sub_canvas = document.createElement('canvas');
        sub_canvas.width = width;
        sub_canvas.height = height;
        var sub_ctx = sub_canvas.getContext('2d');
        sub_ctx.putImageData(subImgData,0,0);
        var imgString = sub_canvas.toDataURL("image/png");
        return imgString;
        }
    })
});
</script>
<div ></div>
<div class="blocks">
    <img src = "test_picture/model.jpg" id="testImg">
    <img src = "" id="testImg2">
    <img src = "test_picture/model.jpg" id="testImg3">
    <canvas id="myCanvas" style="border:1px solid #000000;"></canvas>
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
