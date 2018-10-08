function MillData(pipe1Data, pipe2Data, pipe3Data, pipe4Data) {
    // this.pipe1Data = formatNumber(pipe1Data,6);
    // this.pipe2Data = formatNumber(pipe2Data,6);
    // this.pipe3Data = formatNumber(pipe3Data,6);
    // this.pipe4Data = formatNumber(pipe4Data,6);

    this.pipe1Data = handleData(pipe1Data);
    this.pipe2Data = handleData(pipe2Data);
    this.pipe3Data = handleData(pipe3Data);
    this.pipe4Data = handleData(pipe4Data);
}
function handleData(data){
    var result;
    if(data.toString().length > 8){
        result = Number(data.toString().substr(0 ,8));
    }else{
        result = data;
    }
    return result;
}
/**
 *
 * @param num
 * @param precision
 * @param separator
 * @returns {*}
 *=======================================================
 *     formatNumber(10000)="10,000"
 *     formatNumber(10000, 2)="10,000.00"
 *     formatNumber(10000.123456, 2)="10,000.12"
 *     formatNumber(10000.123456, 2, ' ')="10 000.12"
 *     formatNumber(.123456, 2, ' ')="0.12"
 *     formatNumber(56., 2, ' ')="56.00"
 *     formatNumber(56., 0, ' ')="56"
 *     formatNumber('56.')="56"
 *     formatNumber('56.a')=NaN
 *=======================================================
 */
function formatNumber(num, precision, separator) {
    var parts;
    // 判断是否为数字
    if (!isNaN(parseFloat(num)) && isFinite(num)) {
        // 把类似 .5, 5. 之类的数据转化成0.5, 5, 为数据精度处理做准, 至于为什么
        // 不在判断中直接写 if (!isNaN(num = parseFloat(num)) && isFinite(num))
        // 是因为parseFloat有一个奇怪的精度问题, 比如 parseFloat(12312312.1234567119)
        // 的值变成了 12312312.123456713
        num = Number(num);
        // 处理小数点位数
        num = (typeof precision !== 'undefined' ? num.toFixed(precision) : num).toString();
        // 分离数字的小数部分和整数部分
        parts = num.split('.');
        // 整数部分加[separator]分隔, 借用一个著名的正则表达式
        parts[0] = parts[0].toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1' + (separator || ','));

        return parts.join('.');
    }
    return NaN;
}

MillData.prototype.getPipe1DataFunction = function () {

    if (this.pipe1Data === undefined)
        return 0;
    return this.pipe1Data;
};
MillData.prototype.getPipe2DataFunction = function () {
    if (this.pipe2Data === undefined)
        return 0;
    return this.pipe2Data;
};
MillData.prototype.getPipe3DataFunction = function () {
    if (this.pipe3Data === undefined)
        return 0;
    return this.pipe3Data;
};
MillData.prototype.getPipe4DataFunction = function () {
    if (this.pipe4Data === undefined)
        return 0;
    return this.pipe4Data;
};

function getDensityDataFromMill(mill) {
    return new MillData(mill.pipe1.density, mill.pipe2.density, mill.pipe3.density, mill.pipe4.density);
}

function getVelocityDataFromMill(mill) {
    return new MillData(mill.pipe1.Velocity, mill.pipe2.Velocity, mill.pipe3.Velocity, mill.pipe4.Velocity);
}

function getMillDataFromAbsoluteToRelative(millData) {
    var avg = (millData.getPipe1DataFunction() + millData.getPipe2DataFunction() + millData.getPipe3DataFunction() + millData.getPipe4DataFunction()) / 4;
    return new MillData(millData.getPipe1DataFunction() - avg, millData.getPipe2DataFunction() - avg, millData.getPipe3DataFunction() - avg, millData.getPipe4DataFunction() - avg);
}