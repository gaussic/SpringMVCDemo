
function Group() {
    this.divJqueryDom = null;

    this.curveForDensity = null;
    this.curveForVelocity = null;
    this.barRelativeForDensity = null;
    this.barRelativeForVelocity = null;
    this.barAbsoluteForDensity = null;
    this.barAbsoluteForVelocity = null;

    this.currentChart = null;

    this.curveBtn = null;
    this.barRelativeBtn = null;
    this.barAbsoluteBtn = null;

    this.densityRadio = null;
    this.velocityRadio = null;

    this.ACheckBox = null;
    this.BCheckBox = null;
    this.CCheckBox = null;
    this.DCheckBox = null;

    //实时刷新数据
    this.realTime = null;
    this.densityRealData = null;
    this.densityRealDataForRelative = null;
    this.velocityRealData = null;
    this.velocityRealDataForRelative = null;

    //曲线初始化的地址
    this.densityCurveUrl = null;
    this.velocityCurveUrl = null;
}

/**
 * 根据当前选中的按钮与 radio的类型，返回应显示的DIV的jquery对象
 * @param currentBtn
 * @param radioValue
 * @param divArrs
 * @returns {*|jQuery|HTMLElement}
 */
Group.prototype.findTargetDivByBtn = function (currentBtn, radioValue, divArrs) {
    var targetDivId;
    var targetChart;
    var targetDiv;
    if (radioValue === velocityRadioValue) {
        if (currentBtn === this.curveBtn) {
            targetChart = this.curveForVelocity;
        } else if (currentBtn === this.barAbsoluteBtn) {
            targetChart = this.barAbsoluteForVelocity;
        } else if (currentBtn === this.barRelativeBtn) {
            targetChart = this.barRelativeForVelocity;
        }
    } else if (radioValue === densityRadioValue) {
        if (currentBtn === this.curveBtn) {
            targetChart = this.curveForDensity;
        } else if (currentBtn === this.barAbsoluteBtn) {
            targetChart = this.barAbsoluteForDensity;
        } else if (currentBtn === this.barRelativeBtn) {
            targetChart = this.barRelativeForDensity;
        }
    }
    targetDivId = getDivIdFromChartByChart(targetChart);
    if (divArrs) {
        for (var x in divArrs) {
            var tempDivId = divArrs[x].attr("id");
            if (tempDivId === targetDivId) {
                targetDiv = divArrs[x];
            }
        }
    }

    return targetDiv;
};
/**
 * 查找当前被选中的按钮
 * @returns {*}
 */
Group.prototype.findCurrentBtn = function () {
    var currentBtn;

    if (this.curveBtn) {
        if (this.curveBtn.hasClass("currentBtnClass")) {
            currentBtn = this.curveBtn;
        }
    }
    if (this.barRelativeBtn) {
        if (this.barRelativeBtn.hasClass("currentBtnClass")) {
            currentBtn = this.barRelativeBtn;
        }
    }
    if (this.barAbsoluteBtn) {
        if (this.barAbsoluteBtn.hasClass("currentBtnClass")) {
            currentBtn = this.barAbsoluteBtn;
        }
    }
    return currentBtn;
};
/**
 * 刷新当前正在显示的图表
 */
Group.prototype.freshCurrentChart = function () {
    /*1、寻找到当前正显示的DIV的ID
      2、根据DIVId，找到对应的chart
      3、根据divId，找到对应刷新的数据
      4、根据不同的chart，刷新chart
      */
    if (this.divJqueryDom) {
        var baseDivId = this.divJqueryDom.attr("id");
        /*var $currentDivDom = $("div[id^='" + baseDivId + "'][display='block']");*/
        var $currentDivDom;
        var divArrs = $("div[id^='" + baseDivId + "']");
        for(var x in divArrs){
            var $dom = $(divArrs[x]);
            if($dom.css("display")==="block"){
                $currentDivDom = $dom;
                break;
            }
        }
        if ($currentDivDom) {
            var currentDivId = $currentDivDom.attr("id");

            if (currentDivId === getDivIdFromChartByChart(this.curveForDensity)) {
                freshCurveChart(this.curveForDensity, this.realTime, this.densityRealData);
            } else if (currentDivId === getDivIdFromChartByChart(this.curveForVelocity)) {
                freshCurveChart(this.curveForVelocity,this.realTime,this.velocityRealData);
            }else if(currentDivId === getDivIdFromChartByChart(this.barRelativeForDensity)){
                freshRelativeBar(this.barRelativeForDensity,this.densityRealDataForRelative);
            }else if(currentDivId === getDivIdFromChartByChart(this.barRelativeForVelocity)){
                freshRelativeBar(this.barRelativeForVelocity,this.velocityRealDataForRelative);
            }else if(currentDivId === getDivIdFromChartByChart(this.barAbsoluteForDensity)){

                freshAbsoluteBar(this.barAbsoluteForDensity,this.densityRealData);
            }else if(currentDivId === getDivIdFromChartByChart(this.barAbsoluteForVelocity)){
                freshAbsoluteBar(this.barAbsoluteForVelocity,this.velocityRealData);
            }


        }
    }
};
/**
 * 如果当前视图为曲线视图，则给曲线加载15分钟数据
 */
Group.prototype.initCurveData = function(){
    /*查找当前显示的chart
    * 1、判断当前chart是否是曲线图
    * 2、如果是曲线图，根据group的曲线URL，重新加载数据
    * */
    if (this.divJqueryDom) {
        var baseDivId = this.divJqueryDom.attr("id");
        /*var $currentDivDom = $("div[id^='" + baseDivId + "'][display='block']");*/
        var $currentDivDom;
        var divArrs = $("div[id^='" + baseDivId + "']");
        for (var x in divArrs) {
            var $dom = $(divArrs[x]);
            if ($dom.css("display") === "block") {
                $currentDivDom = $dom;
                break;
            }
        }
    }
    if($currentDivDom) {
        var currentDomId = $currentDivDom.attr('id');
        var densityCurveId = getDivIdFromChartByChart(this.curveForDensity);
        var velocityCurveId = getDivIdFromChartByChart(this.curveForVelocity);
        if(currentDomId === densityCurveId){
            initCurveChart(this.densityCurveUrl,this.curveForDensity);
        }else if(currentDomId === velocityCurveId){
            initCurveChart(this.velocityCurveUrl,this.curveForVelocity);
        }


    }
};

Group.prototype.getChartArray = function(){
    return [this.curveForVelocity,this.curveForDensity,this.barAbsoluteForVelocity,this.barAbsoluteForDensity,this.barRelativeForVelocity,this.barRelativeForDensity];
};
