var velocityChartTitle = "风速";
var densityChartTitle = "密度";
var absoluteChartSubtitle = "绝对值";
var relativeChartSubtitle = "相对值";

var millCurve;

var absoluteBarForDensity;
var relativeBarForDensity;
var curveForDensity ;

var absoluteBarForVelocity;
var relativeBarForVelocity ;
var curveForVelocity ;
$(document).ready(function () {

    //TODO 初始化锅炉负荷实时曲线图
    //TODO 锅炉负荷曲线图、密度曲线图、风速曲线图，三个曲线图实现点击同步。
    //初始化锅炉负荷
    //初始化曲线图，柱图
    //密度
    var chartOptions = {exporting:{enabled:false},legend:{align:"right",verticalAlign:"top",floating:true,y:40,itemDistance:5,itemStyle:{fontFamily:"宋体",fontWeight:'light'}}};

    var curveOptions = $.extend({},chartOptions);
    curveOptions.legend.y = 10;
    millCurve = getCurveChartWithOptionsAndSerieNames("container_Mill", "磨煤机", "",curveOptions,["磨煤量"]);
     absoluteBarForDensity = getAbsoluteBarWithOptions("container_absoluteBar", densityChartTitle+"("+ relativeChartSubtitle + ")" ,"",chartOptions);
     relativeBarForDensity = getRelativeBarWithOptions("container_relativeBar", densityChartTitle+"("+ relativeChartSubtitle + ")" ,"",chartOptions);
     curveForDensity = getCurveChartWithOptions("container", densityChartTitle, "",curveOptions);
    //风速
     absoluteBarForVelocity = getAbsoluteBarWithOptions("container_V_absoluteBar", velocityChartTitle + "(" + absoluteChartSubtitle+")", "",chartOptions);
     relativeBarForVelocity = getRelativeBarWithOptions("container_V_relativeBar", velocityChartTitle+ "(" + absoluteChartSubtitle+")", "",chartOptions);
     curveForVelocity = getCurveChartWithOptions("container_V", velocityChartTitle, "",curveOptions);

     // curveForVelocity.len
     //初始化曲线图数据
    //TODO 根据磨煤机ABCD进行数据查询
     var urlForCurveDensity = "../getInitTimeDataForDensity?mill=A";
    // initCurveChart(urlForCurveDensity,curveForDensity);
    // var urlForCurveVelocity = "../getInitTimeDataForVelocity?mill=A"
    // initCurveChart(urlForCurveVelocity,curveForVelocity);
    //TODO 初始化 磨煤机磨煤量曲线图
    // var urlForMillData = "../getMillRealTimeData?mill=A";
    // initCurveChart(urlForMillData,curveForVelocity);
  /*  var url = "../getInitTimeDataForDensity?mill=A";
    initCurveChartInRealTimePage(url);

    //加载完成后刷新一次
    freshMainPage();
    //定时刷新
    setInterval(freshMainPage,5000);*/

});
function initAndFresh(millLocation){

        var url = "../getInitTimeDataForDensity?mill=" + millLocation;
        initCurveChartInRealTimePage(url);
        //加载完成后刷新一次
        freshMainPage(millLocation);
        //定时刷新
        setInterval(function(){freshMainPage(millLocation);},5000);

}

/**
 * 初始化曲线chart
 * @param url
 */
function initCurveChartInRealTimePage(url) {
    $.getJSON(url, function (initData) {
        // var newData = getSeriesArray(initData);
        // chartForCurve.series[0].setData(newData[0].data);
        // chartForCurve.series[1].setData(newData[1].data);
        // chartForCurve.series[2].setData(newData[2].data);
        // chartForCurve.series[3].setData(newData[3].data);

        var DA =[],DB=[],DC=[],DD=[];
        var VA=[],VB=[],VC=[],VD=[];
        var millData = [];
        for(var i=0;i<initData.length;i++){
            var v = initData[i];
            var time = v.time;
            DA.push({x:time,y:v.AD});
            DB.push({x:time,y:v.BD});
            DC.push({x:time,y:v.CD});
            DD.push({x:time,y:v.DD});

            VA.push({x:time,y:v.AV});
            VB.push({x:time,y:v.BV});
            VC.push({x:time,y:v.CV});
            VD.push({x:time,y:v.DV});

            millData.push({x:time,y:v.m});

        }
        curveForDensity.series[0].setData(DA);
        curveForDensity.series[1].setData(DB);
        curveForDensity.series[2].setData(DC);
        curveForDensity.series[3].setData(DD);

        curveForVelocity.series[0].setData(VA);
        curveForVelocity.series[1].setData(VB);
        curveForVelocity.series[2].setData(VC);
        curveForVelocity.series[3].setData(VD);

        millCurve.series[0].setData(millData);
    })
}

function freshMainPage(millLocation){
    var latestTime = 100;
    var url = "../getMillRealTimeData?mill=" + millLocation;
    //todo 最新时间的刷新数据
    $.get(url, {"latestTime": latestTime}, function (result) {
        freshCurrentChartAndTable(result);
    }, "json");
}
/**
 * 刷新当前正在显示的图表
 * @param result
 */
function freshCurrentChartAndTable(result) {
    var time = result.time;
    var millA = result.millA;

    var millADensityData = getDensityDataFromMill(millA);
    var millAVelocityData = getVelocityDataFromMill(millA);
    var millADensityDataForRelative = getMillDataFromAbsoluteToRelative(millADensityData);
    var millAVelocityDataForRelative = getMillDataFromAbsoluteToRelative(millAVelocityData);


    freshCurveChart(curveForDensity, time, millADensityData);
    freshRelativeBar(relativeBarForDensity,millADensityDataForRelative);
    freshRelativeBar(absoluteBarForDensity,millADensityData);

    freshCurveChart(curveForVelocity, time, millAVelocityData);
    freshRelativeBar(relativeBarForVelocity,millAVelocityDataForRelative);
    freshRelativeBar(absoluteBarForVelocity,millAVelocityData);

//    freshAbsoluteBar(absoluteBarForDensity, millADensityData);

    //TODO 刷新锅炉负荷
    freshCurveChartForSingleLine(millCurve,time,result.millData);

    //刷新表格
    //TODO 刷新实时画面的窗口
    freshRealTimeMainPageTable(millADensityData,millAVelocityData,millADensityDataForRelative,millAVelocityDataForRelative,result.millData);



}

/**
 * 刷新实时画面的表格
 */
function freshRealTimeMainPageTable(millADensityData,millAVelocityData,millADensityDataForRelative,millAVelocityDataForRelative,mill){
    var $table = $("#realTimeTable");
    //运行状态
    $table.find("tr:eq(1) th:eq(0)").html("运行中");
    //磨煤机磨煤量
    $table.find("tr:eq(3) td:eq(0)").html(mill);

    $table.find("tr:eq(2) td:eq(1)").html(millAVelocityDataForRelative.pipe1Data);
    $table.find("tr:eq(2) td:eq(2)").html(millAVelocityDataForRelative.pipe2Data);
    $table.find("tr:eq(2) td:eq(3)").html(millAVelocityDataForRelative.pipe3Data);
    $table.find("tr:eq(2) td:eq(4)").html(millAVelocityDataForRelative.pipe4Data);

    $table.find("tr:eq(1) td:eq(1)").html(millAVelocityData.pipe1Data);
    $table.find("tr:eq(1) td:eq(2)").html(millAVelocityData.pipe2Data);
    $table.find("tr:eq(1) td:eq(3)").html(millAVelocityData.pipe3Data);
    $table.find("tr:eq(1) td:eq(4)").html(millAVelocityData.pipe4Data);

    $table.find("tr:eq(3) td:eq(2)").html(millADensityData.pipe1Data);
    $table.find("tr:eq(3) td:eq(3)").html(millADensityData.pipe2Data);
    $table.find("tr:eq(3) td:eq(4)").html(millADensityData.pipe3Data);
    $table.find("tr:eq(3) td:eq(5)").html(millADensityData.pipe4Data);

    $table.find("tr:eq(4) td:eq(1)").html(millADensityDataForRelative.pipe1Data);
    $table.find("tr:eq(4) td:eq(2)").html(millADensityDataForRelative.pipe2Data);
    $table.find("tr:eq(4) td:eq(3)").html(millADensityDataForRelative.pipe3Data);
    $table.find("tr:eq(4) td:eq(4)").html(millADensityDataForRelative.pipe4Data);
}