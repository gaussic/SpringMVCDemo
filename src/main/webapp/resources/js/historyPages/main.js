$(document).ready(function () {
    //日期插件
    var currentDate = new Date();
    var beginDate = new Date(currentDate.getTime() - 24 * 60 * 60 * 1000);
    initDateTimePicker("startInputTime", beginDate);
    initDateTimePicker("endInputTime", currentDate);

    initQueryTime();


    $queryBtn = $("#queryBtn");
    var queryBtnLeft = $queryBtn.position().left;
    $("#velocityRadioLabel").css("left", queryBtnLeft - 200);
    $("#densityRadioLabel").css("left", queryBtnLeft - 100);


    //给所有的单选按钮点击添加处理
    // language=JQuery-CSS
    $("input[type='radio']").click(function () {
        //找出和当前name一样的单选按钮对应的label，并去除选中的样式的class
        $("input[type='radio'][name='" + $(this).attr('name') + "']").parent().removeClass("checked");
        //给自己对应的label
        $(this).parent().addClass("checked");
    });
    $("#densityRadio").click();

    $("[name='densityOrVelocityRadio']").bind("change", function () {
        var densityOrVelocityValue = $(this).val();
        if (densityOrVelocityValue === "density") {
            //显示密度曲线
            changeChartDataToDensity();
        } else if (densityOrVelocityValue === "velocity") {
            //显示风速曲线
            changeChartDataToVelocity();
        }
    })
});

/**
 * 初始化时间框，结束时间默认时间为当前时间，开始时间为一小时前
 */
function initQueryTime() {
    var now = new Date();
    var endText = dateFtt("yyyy-MM-dd hh:mm", now);
    var begin = new Date(now.getTime() - 1 * 60 * 60 * 1000);
    var beginText = dateFtt("yyyy-MM-dd hh:mm", begin);
    $("#startInputTime").val(beginText);
    $("#endInputTime").val(endText);
    // alert($("#startInputTime").text());
    $("#queryBtn").click();

    // initTable();
}

/**************************************时间格式化处理************************************/
function dateFtt(fmt, date) { //author: meizz
    var o = {
        "M+": date.getMonth() + 1,                 //月份
        "d+": date.getDate(),                    //日
        "h+": date.getHours(),                   //小时
        "m+": date.getMinutes(),                 //分
        "s+": date.getSeconds(),                 //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function initDateTimePicker(inputId, initDate) {
    $("#" + inputId).datetimepicker({
        language: 'zh_CN',
        format: 'yyyy-mm-dd hh:ii',//显示格式
        //todayHighlight: 1,//今天高亮
        //minView: "month",//设置只显示到月份
        startView: 2,
        forceParse: true,
        showMeridian: 1,
        initialDate: initDate,
        autoclose: 1//选择后自动关闭
    });
}