
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

    //ʵʱˢ������
    this.realTime = null;
    this.densityRealData = null;
    this.densityRealDataForRelative = null;
    this.velocityRealData = null;
    this.velocityRealDataForRelative = null;

    //���߳�ʼ���ĵ�ַ
    this.densityCurveUrl = null;
    this.velocityCurveUrl = null;
}

/**
 * ���ݵ�ǰѡ�еİ�ť�� radio�����ͣ�����Ӧ��ʾ��DIV��jquery����
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
 * ���ҵ�ǰ��ѡ�еİ�ť
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
 * ˢ�µ�ǰ������ʾ��ͼ��
 */
Group.prototype.freshCurrentChart = function () {
    /*1��Ѱ�ҵ���ǰ����ʾ��DIV��ID
      2������DIVId���ҵ���Ӧ��chart
      3������divId���ҵ���Ӧˢ�µ�����
      4�����ݲ�ͬ��chart��ˢ��chart
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
                freshRelativeBar(this.barAbsoluteForDensity,this.densityRealData);
            }else if(currentDivId === getDivIdFromChartByChart(this.barAbsoluteForVelocity)){
                freshRelativeBar(this.barAbsoluteForVelocity,this.velocityRealData);
            }


        }
    }
};
/**
 * �����ǰ��ͼΪ������ͼ��������߼���15��������
 */
Group.prototype.initCurveData = function(){
  /*���ҵ�ǰ��ʾ��chart
  * 1���жϵ�ǰchart�Ƿ�������ͼ
  * 2�����������ͼ������group������URL�����¼�������
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
