$(document).ready(function () {
    /** ***************************************画图************************************************************* */

    $("#linkStateSvg").svg({
        onLoad: drawIntro
    });
    var url = "../../getAllDeviceState";
    setInterval(function(){
        $.getJSON(url,function(resultData){
           communication.freshData(resultData);
        });
    },5000);

});
/**rect 连接正常与异常颜色
 * */
var rectFillNormal = "#2bff00",rectFillAbnormal="#F0F0F0";
var rectNormalSettings = {
    fill: rectFillAbnormal,
    stroke: "#000000",
    strokeWidth: 1
};


var location_top = "top", location_bottom = "bottom", location_left = "left", location_right = "right";


/**
 * @Author jiangyong xia
 * @Description 绘制两个矩形之间的折线，并返回svg的line对象
 * @Date 16:07 18/8/30
 * @param svg
 * @param rectFrom
 * @param fromLocation
 * @param rectTo
 * @param toLocation
 * @returns {*} svg的line对象
 */
function drawLinesBetweenMillAndComputer(svg, rectFrom, fromLocation, rectTo, toLocation){
    var location = drawLineBetweenRect(svg, rectFrom, fromLocation, rectTo, toLocation);
    var fromHeight = 20;
    var point1 = [location.fromX, location.fromY];
    var point2 = [location.fromX, location.fromY - fromHeight];
    var point3 = [location.toX, location.fromY - fromHeight];
    var point4 = [location.toX, location.toY];

    return drawCommonPolyLine(svg,[point1,point2,point3,point4]);
}
/**
 * @Author jiangyong xia
 * @Description 绘制折线
 * @Date 16:08 18/8/30
 * @param svg
 * @param pointsArr 例如：[[100,100],[100,200]]
 * @returns {void|*}
 */
function drawCommonPolyLine(svg,pointsArr){
    return svg.polyline(pointsArr, {
        "fill": "white",
        "fill-opacity": 0,
        "stroke": "green",
        "strokeWidth": "2px"
    });
}

/**
 * @Author jiangyong xia
 * @Description 绘制电脑与DCS的RECT之间的连线
 * @Date 16:10 18/8/30
 * @param svg
 * @param rectFrom
 * @param fromLocation
 * @param rectTo
 * @param toLocation
 * @returns {void|*}
 */
function drawLinesBetweenDCSAndComputer(svg, rectFrom, fromLocation, rectTo, toLocation){
    var location = drawLineBetweenRect(svg, rectFrom, fromLocation, rectTo, toLocation);
    var point1 = [location.fromX,location.fromY];
    var point2 = [location.toX,location.fromY];
    var point3 = [location.toX,toLocation.toY];
    return drawCommonPolyLine(svg,[point1,point2,point3]);

}
/**
 * @Author jiangyong xia
 * @Description 绘制所有管道的rect与 目标 rect之间的连线
 * @Date 16:11 18/8/30
 * @param svg
 * @param millRects
 * @param targetRect
 */
function drawLines(svg, millRects, targetRect) {
    for (var i = 0; i < millRects.length; i++) {
        var fromRect = millRects[i];
        drawLinesBetweenMillAndComputer(svg, fromRect, location_top, targetRect, location_bottom);
    }
}
var rectHeight = 120, rectWidth = 80, rectInterval = 30;
/**
 * @Author jiangyong xia
 * @Description 绘制一台磨煤机对应的四个粉管的rect，返回一个rect的SVG数组对象
 * @Date 15:59 18/8/30
 * @Param
 * @return
 **/
function drawMillPiping(svg, initX, initY) {
    var millAPipe1Rect = drawRect(svg, initX, initY, rectWidth, rectHeight);
    var millAPipe2Rect = drawRect(svg, initX + rectInterval + rectWidth, initY, rectWidth, rectHeight);
    var millAPipe3Rect = drawRect(svg, initX + (rectInterval + rectWidth) * 2, initY, rectWidth, rectHeight);
    var millAPipe4Rect = drawRect(svg, initX + (rectInterval + rectWidth) * 3, initY, rectWidth, rectHeight);

    return [millAPipe1Rect, millAPipe2Rect, millAPipe3Rect, millAPipe4Rect];
}

/***
 * @Author jiangyong xia
 * @Description 根据管道的位置，绘制管道的rect，并返回
 * @Date 17:07 18/8/30
 * @param svg
 * @param initX
 * @param initY
 * @param location  A  B  C   D
 */
function drawPipeRect(svg,initX,initY,location,pipeName){
    var left=initX,top=initY;
    if(location === "A"){

    }else if(location === "B"){
        left = initX + (rectInterval + rectWidth) * 1;
    }else if(location === "C"){
        left = initX + (rectInterval + rectWidth) * 2;
    }else if(location === "D"){
        left = initX + (rectInterval + rectWidth) * 3;
    }
    generatorText(svg,pipeName,left,top);
    return drawRect(svg, left, top, rectWidth, rectHeight);

}


var epCommunicationStateMargintop = 120;
/**
 * 粉管的rect的基本位置
 * @type {number}
 */
var millABLeft = 150,
    millACTop = 250,
    millCDLeft = 700,
    millBDTOP = 450;
/**
 * DCS的基本位置
 * @type {number}
 */
var dcsX = millCDLeft + (rectWidth + rectInterval) * 2 ,
    dcsY = epCommunicationStateMargintop  + 10,
    dcsWidth = 120,
    dcsHeight = 50;

function Communication(svg,millA,millB,millC,millD,DCS){
    this.svg = svg;
    this.millA = millA;
    this.millB = millB;
    this.millC = millC;
    this.millD = millD;
    this.DCS = DCS;
    this.freshData = function(data){
        this.millA.freshData(data.millA);
        this.millB.freshData(data.millB);
        this.millC.freshData(data.millC);
        this.millD.freshData(data.millD);
        this.DCS.freshData(data.dcs);
    };
}
function Mill(id,name,pipeA,pipeB,pipeC,pipeD){
    this.id = id;
    this.name = name;
    this.pipeA = pipeA;
    this.pipeB = pipeB;
    this.pipeC = pipeC;
    this.pipeD = pipeD;
    this.freshData = function(data){
        this.pipeA.freshData(data.pipeA);
        this.pipeB.freshData(data.pipeB);
        this.pipeC.freshData(data.pipeC);
        this.pipeD.freshData(data.pipeD);
    };
    this.getRects = function(){
        return [this.pipeA.rect,this.pipeB.rect,this.pipeC.rect,this.pipeD.rect];
    }
}
function PipeOrDcs(id,name,rect){
    this.id = id;
    this.name = name;
    this.rect = rect;
    this.freshData = function(data){
        //TODO 刷新数据
        if(data.state === 0){
            $(this.rect).attr({"fill":rectFillNormal});
        }else if(data.state === 1){
            $(this.rect).attr({"fill":rectFillAbnormal});
        }
    }
}
function generatorMillPipes(svg, pipe, millLocation,millLeft,millTop){
    var pipeId = pipe.id;
    var pipeName = pipe.name;
    var state = pipe.state;
    var left=millLeft,top = millTop;


    // if(millLocation === "A"){
    //     left = millLeft;
    // }else if(millLocation === "B"){
    //     left = millLeft + (rectInterval + rectWidth);
    // }else if(millLocation === "C"){
    //     left = millLeft + (rectInterval + rectWidth) * 2;
    // }else if(millLocation === "D"){
    //     left = millLeft + (rectInterval + rectWidth) * 3;
    // }



    var pipeRect = drawPipeRect(svg,left,top,millLocation,pipeName);
    return new PipeOrDcs(pipeId,pipeName,pipeRect);
}
function generatorText(svg,textValue,baseLeft,baseTop){
    var x = baseLeft + rectWidth /3;
    var y = baseTop + rectHeight + 20;
    return drawText(svg,textValue,x,y);
}
function drawText(svg,value,x,y){
    var text = svg.text(value);
    $(text).attr("x",x);
    $(text).attr("y",y);
    return $(text);
}
function generatorMill(svg,mill,millLocation){
    var millId = mill.id;
    var millName = mill.name;
    var millLeft,millTop;
    if(millLocation === "A"){
        millLeft = millABLeft;millTop = millACTop;
    }else if(millLocation === "B"){
        millLeft = millABLeft;millTop = millBDTOP;
    }else if(millLocation === "C"){
        millLeft = millCDLeft;millTop = millACTop;
    }else if(millLocation === "D"){
        millLeft = millCDLeft;millTop = millBDTOP;
    }


    var pipeA = generatorMillPipes(svg,mill.pipeA,"A",millLeft,millTop);
    var pipeB = generatorMillPipes(svg,mill.pipeB,"B",millLeft,millTop);
    var pipeC = generatorMillPipes(svg,mill.pipeC,"C",millLeft,millTop);
    var pipeD = generatorMillPipes(svg,mill.pipeD,"D",millLeft,millTop);




    return new Mill(millId,millName,pipeA,pipeB,pipeC,pipeD);
}
function drawMillBetweenComputerLines(svg,communication,computerRect){
    var millARects = communication.millA.getRects;
    drawLines(svg, communication.millA.getRects(), computerRect);
    drawLines(svg, communication.millB.getRects(), computerRect);
    drawLines(svg, communication.millC.getRects(), computerRect);
    drawLines(svg, communication.millD.getRects(), computerRect);
}
/**
 * 整体的通讯对象
 */
var communication;
/**
 * @Author jiangyong xia
 * @Description 绘制通讯中断的基本信息
 * @Date 16:14 18/8/30
 * @param svg
 */
function drawIntro(svg) {
    //TODO 传递后台查询的数据
    var url = "../getAllDeviceState";
    $.getJSON(url,function(resultData){
        // var resultData = {dcs:{id:"",name:"",state:""},millA:{name:"",id:"",pipeA:{name:"",state:"",id:""}}};
        var millAObj = generatorMill(svg,resultData.millA,"A");
        var millBObj = generatorMill(svg,resultData.millB,"B");
        var millCObj = generatorMill(svg,resultData.millC,"C");
        var millDObj = generatorMill(svg,resultData.millD,"D");
        var dcs = resultData.dcs;
        var DCSRect = drawRect(svg,dcsX,dcsY,dcsWidth,dcsHeight);
        var DcsText = drawText(svg,dcs.name,dcsX + dcsWidth/3,dcsY + dcsHeight + 20);
        var DCSObj = new PipeOrDcs(dcs.id,dcs.name,DCSRect);

        communication = new Communication(svg,millAObj,millBObj,millCObj,millDObj,DCSObj);
        communication.freshData(resultData);
        var computerRect = drawComputer(svg);
        drawMillBetweenComputerLines(svg,communication,computerRect);

        drawLinesBetweenDCSAndComputer(svg,DCSRect,location_left,computerRect,location_bottom);


    });
    drawLegend(svg);

/*
    var millARects = drawMillPiping(svg, millABLeft, millACTop);
    var millBRects = drawMillPiping(svg, millABLeft, millBDTOP);
    var millCRects = drawMillPiping(svg, millCDLeft, millACTop);
    var millDRects = drawMillPiping(svg, millCDLeft, millBDTOP);
    var DCSRect = drawRect(svg,dcsX,dcsY,dcsWidth,dcsHeight);
    var computerRect = drawComputer(svg);
    drawLines(svg, millARects, computerRect);
    drawLines(svg, millBRects, computerRect);
    drawLines(svg, millCRects, computerRect);
    drawLines(svg, millDRects, computerRect);
    drawLinesBetweenDCSAndComputer(svg,DCSRect,location_left,computerRect,location_bottom);
*/


}
/**
 * @Description 绘制图例
 * @Author jiangyong xia
 * @Date 18:05 18/9/5
 * @Param
 * @return
 **/
function drawLegend(svg){
    var legendTop = millBDTOP + rectHeight + 100;
    var legendLeft = millCDLeft + (rectWidth + rectInterval) * 3;
     legendTop = 50;
    legendLeft = 100;
    var normarlRect = drawRect(svg, legendLeft, legendTop, 90, 40) ;
    var noNormarlRect = drawRect(svg, legendLeft, legendTop + 60, 90, 40) ;
    $(normarlRect).attr({"fill":rectFillNormal});
    drawText(svg,"正常",legendLeft - 50,legendTop + 25);
    drawText(svg,"中断",legendLeft - 50,legendTop  + 25 +60);
}


// function drawText(svg) {
//     var epLocationX = epCommunicationStateMarginLeft + (eachCommunicationStateRectWidth + eachCommunicationStateIntervalWidth) * 2;
//     var epLocationY = epCommunicationStateMargintop - 50;
//     svg.text(epLocationX, epLocationY, "高  压", {
//         fontFamily : "微软雅黑",
//         fill : "#000000",
//         fontSize : "20px",
//         fontWeight : "bolder"
//     });
//
//     var lowDeviceLocationX = lowDeviceCommunicationStateMarginLeft + (eachCommunicationStateRectWidth + eachCommunicationStateIntervalWidth) * 2;
//     svg.text(lowDeviceLocationX, epLocationY, "低  压", {
//         fontFamily : "微软雅黑",
//         fill : "#000000",
//         fontSize : "20px",
//         fontWeight : "bolder"
//     });
// }

/**
 * @Description 指定RECT的连线位置，返回两个坐标点
 * @Author jiangyong xia
 *
 * @Date 16:01 18/8/30
 * @param svg
 * @param rectFrom
 * @param fromLocation  指定参数 location_top = "top", location_bottom = "bottom", location_left = "left", location_right =
 * "right"
 * @param rectTo
 * @param toLocation   指定参数 location_top = "top", location_bottom = "bottom", location_left = "left", location_right =
 * "right"
 * @returns {{fromX: *, fromY: *, toX: *, toY: *}}
 */
function drawLineBetweenRect(svg, rectFrom, fromLocation, rectTo, toLocation) {
    var fromX, fromY, toX, toY;
    var rectFromAttr = rectFrom.attributes,
        rectToAttr = rectTo.attributes;
    var rectFromX = parseInt(rectFromAttr.x.value),
        rectFromY = parseInt(rectFromAttr.y.value),
        rectFromWidth = parseInt(rectFromAttr.width.value),
        rectFromHeight = parseInt(rectFromAttr.height.value);
    var rectToX = parseInt(rectToAttr.x.value),
        rectToY = parseInt(rectToAttr.y.value),
        rectToWidth = parseInt(rectToAttr.width.value),
        rectToHight = parseInt(rectToAttr.height.value);
    if (fromLocation === location_top) {
        fromX = rectFromX + rectFromWidth / 2;
        fromY = rectFromY;
    } else if (fromLocation === location_bottom) {
        fromX = rectFromX + rectFromWidth / 2;
        fromY = rectFromY + rectFromHeight;
    } else if (fromLocation === location_left) {
        fromX = rectFromX;
        fromY = rectFromY + rectFromHeight / 2;
    } else if (fromLocation === location_right) {
        fromX = rectFromX + rectFromWidth;
        fromY = rectFromY + rectFromHeight / 2;
    }
    if (toLocation === location_top) {
        toX = rectToX + rectFromWidth / 2;
        toY = rectToY;
    } else if (toLocation === location_bottom) {
        toX = rectToX + rectToWidth / 2;
        toY = rectToY + rectToHight;
    } else if (toLocation === location_left) {
        toX = rectToX;
        toY = rectToY + rectFromHeight / 2;
    } else if (toLocation === location_right) {
        toX = rectToX + rectToWidth;
        toY = rectToY + rectToHight / 2;
    }
    // var svgLine =  svg.line(fromX   , fromY, toX, toY, {
    //     stroke : "green",
    //     strokeWidth : "2px"
    // });
    return {"fromX":fromX,"fromY":fromY,"toX":toX,"toY":toY};

}

/**
 * @Author jiangyong xia
 * @Description 绘制矩形
 * @Date 16:16 18/8/30
 * @param svg
 * @param locationX
 * @param locationY
 * @param width
 * @param height
 */
function drawRect(svg, locationX, locationY, width, height) {
    var tempRectSvg = svg.rect(locationX, locationY, width, height, rectNormalSettings);
    return tempRectSvg;
}

/**
 * @Author jiangyong xia
 * @Description 绘制电脑
 * @Date 16:13 18/8/30
 * @param svg
 */
function drawComputer(svg) {
    var rectSettingWhite = {
        fill: "white",
        stroke: "black",
        strokeWidth: "2px"
    };
    var rectSetting = {fill: "black"};

    var computerLocationX = (millCDLeft + (rectWidth + rectInterval) * 4 - millABLeft) / 2 + millABLeft - 40;
    var computerLocationY = epCommunicationStateMargintop + 30 - 120;
    var computerWidth = 80;
    var computerHeight = 50;
    svg.rect(computerLocationX, computerLocationY, computerWidth, computerHeight, {
        stroke: "black",
        strokeWidth: "2px",
        fill: "none"
    });

    var computerLocation1X = computerLocationX;
    var computerLocation1Y = computerLocationY + computerHeight - 5;
    svg.rect(computerLocation1X, computerLocation1Y, computerWidth, 5, {
        stroke: "black",
        strokeWidth: "2px"
    });

    var computerViewSwitchLocationX = computerLocationX + computerWidth - 5;
    var computerViewSwitchLocationY = computerLocationY + computerHeight - 2;
    svg.circle(computerViewSwitchLocationX, computerViewSwitchLocationY, 1.5, {
        stroke: "white",
        strokeWidth: "1px",
        fill: "white"
    });

    var computerViewHolderWidth = 20;
    var computerViewHolderHeight = 10;
    var computerViewHolderLocationX = computerLocationX + computerWidth / 2 - computerViewHolderWidth / 2;
    var computerViewHolderLocationY = computerLocationY + computerHeight;
    svg.rect(computerViewHolderLocationX, computerViewHolderLocationY, computerViewHolderWidth, computerViewHolderHeight, {
        stroke: "black",
        fill: "black"
    });

    var computerViewHolderEllipseHeight = 6;
    var computerViewHolderEllipseLocationX = computerLocationX + computerWidth / 2;
    var computerViewHolderEllipseLocationY = computerLocationY + computerHeight + computerViewHolderHeight;
    svg.ellipse(computerViewHolderEllipseLocationX, computerViewHolderEllipseLocationY, 20, computerViewHolderEllipseHeight, rectSetting);

    var computerCaseWidth = 80;
    var computerCaseLocationX = computerLocationX;
    var computerCaseLocationY = computerViewHolderEllipseLocationY + computerViewHolderEllipseHeight + 5;
    var rect = svg.rect(computerCaseLocationX, computerCaseLocationY, computerCaseWidth, 30, 3, 3, rectSettingWhite);

    var computerCase1Height = 25;
    var computerCase1LocationX = computerLocationX + computerCaseWidth - 15;
    var computerCase1LocationY = computerCaseLocationY + 2.5;
    svg.rect(computerCase1LocationX, computerCase1LocationY, 10, computerCase1Height, rectSetting);
    svg.rect(computerCase1LocationX - 8, computerCase1LocationY, 4, computerCase1Height, rectSetting);
    svg.rect(computerCase1LocationX - 8 * 2, computerCase1LocationY, 4, computerCase1Height, rectSetting);
    svg.rect(computerCase1LocationX - 8 * 3, computerCase1LocationY, 4, computerCase1Height, rectSetting);

    var computerCaseSwitchLocationX = computerCase1LocationX - 40;
    var computerCaseSwitchLocationY = computerCase1LocationY + 12;
    svg.circle(computerCaseSwitchLocationX, computerCaseSwitchLocationY, 5, rectSettingWhite);

    return rect;
}