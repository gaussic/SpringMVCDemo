$(document).ready(function() {
    function ElectricField() {
        ElectricField.prototype.title = "";
    }
   var electricFieldNames = ["A","B","C","D","A","B","C","D","A","B","C","D","A","B","C","D"];

    var electricFields = new Array(16);
    for (var i = 0; i < 16; i++) {
        var tempElectricField = new ElectricField();
        tempElectricField.title = electricFieldNames[i];
        electricFields[i] = tempElectricField;
    }

    var epCommunicationStateRectArray = new Array(20);
    var lowDeviceCommunicationStateRectArray = new Array(20);

    var eachCommunicationStateRectWidth = 45;
    var eachCommunicationStateRectHeight = 60;
    var eachCommunicationStateIntervalWidth = 40;
    var eachCommunicationStateIntervalHeight = 40;
    var epCommunicationStateMarginLeft = 150;
    var epCommunicationStateMargintop = 120;
    var lowDeviceCommunicationStateMarginLeft = 750;
    var espChannelIntervalHeight = 50;

    var ElectricFieldsArr = new Array(20);

    var electricFieldTitleSettings = {
        fontFamily : "sans-serif",
        fill : "#000000",
        fontSize : "15px",
        fontWeight : "bolder"
    };
    var eachElectricFiedSettings = {
        fill : "#d5d5d5",
        stroke : "#333333"
    };
    var electricFieldTextSettings = {
        fontFamily : "sans-serif",
        fontSize : "12px",
        fontWeight : "light"
    };
    var electricFieldValueSettings = {
        fontFamily : "sans-serif",
        fill : "#000000",
        fontSize : "13px",
        fontWeight : "bold",
        stroke : "",
        strokeWidth : "1px"
    };
    var electricFieldValueUnitSettings = {
        fontFamily : "sans-serif",
        fill : "#000000",
        fontSize : "13px",
        fontWeight : "bold"
    };
    var eachElectricValueRectSettings = {
        fill : "#FFFFFF"
    };
    // 电源运行状态
    var epRunStateLocationWidth = 40;
    var epRunStateLocationHeight = 50;
    var epRunStateFieldSettings = {
        fill : "#F0F0F0",
        stroke : "#000000",
        strokeWidth : 1
    };
    var epRunStateSettings = {
        fill : "Red",
        stroke : "#000000"
    };
    var epHopperLevelStateSettings = {
        // fill : "#5b5b5b",
        fill : "#d5d5d5",
        stroke : "#000000"
    };
    var epHopperLevelWithGrayStateSettings = {
        // fill : "#5b5b5b",
        fill : "#5b5b5b",
        stroke : "#000000"
    };
    var lineSettings = {
        stroke : "green",
        strokeWidth : "2px"
    };
    /** ***************************************画图************************************************************* */
    $("#linkStateSvg").svg({
        onLoad : drawIntro
    });
    function drawIntro(svg) {
        // 绘制矩形框
        drawEpCommunicationRect(svg);
        drawLowDeviceCommunicationRect(svg);

        drawLineLine(svg);
        drawText(svg);
        for (var i = 0; i < electricFields.length; i++) {
            var currentElectricFieldLocationX = parseInt($(epCommunicationStateRectArray[i]).attr("x"));
            var currentElectricFieldLocationY = parseInt($(epCommunicationStateRectArray[i]).attr("y"));
            drawEpCommunicationName(svg, currentElectricFieldLocationX, currentElectricFieldLocationY, electricFields[i].title);

            var locationX = parseInt($(lowDeviceCommunicationStateRectArray[i]).attr("x"));
            var locationY = parseInt($(lowDeviceCommunicationStateRectArray[i]).attr("y"));
            drawEpCommunicationName(svg, locationX, locationY, electricFields[i].title);
            // 绘制title、ep运行状态
            // drawRapperRunState(svg, currentElectricFieldLocationX,
            // currentElectricFieldLocationY, i);
        }
    }
    function drawText(svg) {
        var epLocationX = epCommunicationStateMarginLeft + (eachCommunicationStateRectWidth + eachCommunicationStateIntervalWidth) * 2;
        var epLocationY = epCommunicationStateMargintop - 50;
        svg.text(epLocationX, epLocationY, "高  压", {
            fontFamily : "微软雅黑",
            fill : "#000000",
            fontSize : "20px",
            fontWeight : "bolder"
        });

        var lowDeviceLocationX = lowDeviceCommunicationStateMarginLeft + (eachCommunicationStateRectWidth + eachCommunicationStateIntervalWidth) * 2;
        svg.text(lowDeviceLocationX, epLocationY, "低  压", {
            fontFamily : "微软雅黑",
            fill : "#000000",
            fontSize : "20px",
            fontWeight : "bolder"
        });
    }
    function drawEpCommunicationRect(svg) {
        for (var i = 0; i < 4; i++) {
            for (var j = 0; j < 2; j++) {
                var locationX = epCommunicationStateMarginLeft + j * (eachCommunicationStateRectWidth + eachCommunicationStateIntervalWidth);
                // if(i %2 == 0)
                // locationX = locationX + eachCommunicationStateRectWidth/2;
                var locationY = epCommunicationStateMargintop + i * (eachCommunicationStateRectHeight + eachCommunicationStateIntervalHeight);
                if (i >= 2)
                    locationY = locationY + espChannelIntervalHeight;
                var tempRectSvg = svg.rect(locationX, locationY, eachCommunicationStateRectWidth, eachCommunicationStateRectHeight, epRunStateFieldSettings);
                epCommunicationStateRectArray[j + i * 2] = tempRectSvg;

                var communicationLineLocationX = locationX + eachCommunicationStateRectWidth / 2;
                var communicationLineLocationBeginY = locationY + eachCommunicationStateRectHeight;
                var communicationLineLocationEndY = communicationLineLocationBeginY + eachCommunicationStateIntervalHeight / 2;
                if (i % 2 == 1) {
                    communicationLineLocationBeginY = locationY;
                    communicationLineLocationEndY = communicationLineLocationBeginY - eachCommunicationStateIntervalHeight / 2;
                }
                svg.line(communicationLineLocationX, communicationLineLocationBeginY, communicationLineLocationX, communicationLineLocationEndY, {
                    stroke : "green",
                    strokeWidth : "2px"
                });
            }
        }
    }

    function drawLowDeviceCommunicationRect(svg) {
        for (var i = 0; i < 4; i++) {
            for (var j = 0; j < 2; j++) {
                var locationX = lowDeviceCommunicationStateMarginLeft + j * (eachCommunicationStateRectWidth + eachCommunicationStateIntervalWidth);
                // if(i %2 == 0)
                // locationX = locationX + eachCommunicationStateRectWidth/2;
                var locationY = epCommunicationStateMargintop + i * (eachCommunicationStateRectHeight + eachCommunicationStateIntervalHeight);
                if (i >= 2)
                    locationY = locationY + espChannelIntervalHeight;
                var tempRectSvg = svg.rect(locationX, locationY, eachCommunicationStateRectWidth, eachCommunicationStateRectHeight, epRunStateFieldSettings);
                lowDeviceCommunicationStateRectArray[j + i * 5] = tempRectSvg;

                var communicationLineLocationX = locationX + eachCommunicationStateRectWidth / 2;
                var communicationLineLocationBeginY = locationY + eachCommunicationStateRectHeight;
                var communicationLineLocationEndY = communicationLineLocationBeginY + eachCommunicationStateIntervalHeight / 2;
                if (i % 2 == 1) {
                    communicationLineLocationBeginY = locationY;
                    communicationLineLocationEndY = communicationLineLocationBeginY - eachCommunicationStateIntervalHeight / 2;
                }
                svg.line(communicationLineLocationX, communicationLineLocationBeginY, communicationLineLocationX, communicationLineLocationEndY, lineSettings);
            }
        }
    }

    function drawLineLine(svg) {
        var line1BeginLocationX = epCommunicationStateMarginLeft + eachCommunicationStateRectWidth / 2;
        var line1BeginLocationY = epCommunicationStateMargintop + eachCommunicationStateRectHeight + eachCommunicationStateIntervalHeight / 2;

        var line1EndLocationX = epCommunicationStateMarginLeft + (eachCommunicationStateRectWidth + eachCommunicationStateIntervalWidth) * 5;

        svg.line(line1BeginLocationX, line1BeginLocationY, line1EndLocationX, line1BeginLocationY, lineSettings);

        var line2BeginLocationY = epCommunicationStateMargintop + eachCommunicationStateRectHeight * 3 + eachCommunicationStateIntervalHeight * 2.5 + espChannelIntervalHeight;
        svg.line(line1BeginLocationX, line2BeginLocationY, line1EndLocationX, line2BeginLocationY, lineSettings);

        svg.line(line1EndLocationX, line1BeginLocationY, line1EndLocationX, line2BeginLocationY, lineSettings);

        var line3BeginLocationX = lowDeviceCommunicationStateMarginLeft - eachCommunicationStateIntervalWidth;
        var line3EndLocationX = line3BeginLocationX + (eachCommunicationStateRectWidth + eachCommunicationStateIntervalWidth) * 5 - eachCommunicationStateRectWidth / 2;
        svg.line(line3BeginLocationX, line1BeginLocationY, line3EndLocationX, line1BeginLocationY, lineSettings);
        svg.line(line3BeginLocationX, line2BeginLocationY, line3EndLocationX, line2BeginLocationY, lineSettings);
        svg.line(line3BeginLocationX, line1BeginLocationY, line3BeginLocationX, line2BeginLocationY, lineSettings);

        var centerLineLocationY = line1BeginLocationY + (line2BeginLocationY - line1BeginLocationY) / 2;
        svg.line(line1EndLocationX, centerLineLocationY, line3BeginLocationX, centerLineLocationY, lineSettings);

        var centerLineBeginLocationX = line1EndLocationX + (line3BeginLocationX - line1EndLocationX) / 2;
        var centerLineEndY = epCommunicationStateMargintop + 30;
        svg.line(centerLineBeginLocationX, centerLineLocationY, centerLineBeginLocationX, centerLineEndY - 18, lineSettings);

        var computerLocationX = centerLineBeginLocationX - 40;
        var computerLocationY = centerLineEndY - 120;
        var computerWidth = 80;
        var computerHeight = 50;
        svg.rect(computerLocationX, computerLocationY, computerWidth, computerHeight, {
            stroke : "black",
            strokeWidth : "2px",
            fill : "none"
        });

        var computerLocation1X = computerLocationX;
        var computerLocation1Y = computerLocationY + computerHeight - 5;

        svg.rect(computerLocation1X, computerLocation1Y, computerWidth, 5, {
            stroke : "black",
            strokeWidth : "2px"
        });

        var computerViewSwitchLocationX = computerLocationX + computerWidth - 5;
        var computerViewSwitchLocationY = computerLocationY + computerHeight - 2;
        svg.circle(computerViewSwitchLocationX, computerViewSwitchLocationY, 1.5, {
            stroke : "white",
            strokeWidth : "1px",
            fill : "white"
        });

        var computerViewHolderWidth = 20;
        var computerViewHolderHeight = 10;
        var computerViewHolderLocationX = computerLocationX + computerWidth / 2 - computerViewHolderWidth / 2;
        var computerViewHolderLocationY = computerLocationY + computerHeight;
        svg.rect(computerViewHolderLocationX, computerViewHolderLocationY, computerViewHolderWidth, computerViewHolderHeight, {
            stroke : "black",
            fill : "black"
        });

        var computerViewHolderEllipseWidth = 20;
        var computerViewHolderEllipseHeight = 6;
        var computerViewHolderEllipseLocationX = computerLocationX + computerWidth / 2;
        var computerViewHolderEllipseLocationY = computerLocationY + computerHeight + computerViewHolderHeight;
        svg.ellipse(computerViewHolderEllipseLocationX, computerViewHolderEllipseLocationY, computerViewHolderEllipseWidth, computerViewHolderEllipseHeight, {
            fill : "black"
        });

        var computerCaseWidth = 80;
        var computerCaseHeight = 30;
        var computerCaseLocationX = computerLocationX;
        var computerCaseLocationY = computerViewHolderEllipseLocationY + computerViewHolderEllipseHeight + 5;
        svg.rect(computerCaseLocationX, computerCaseLocationY, computerCaseWidth, computerCaseHeight, 3, 3, {
            fill : "white",
            stroke : "black",
            strokeWidth : "2px"
        });

        var computerCase1Width = 10;
        var computerCase1Height = 25;
        var computerCase1LocationX = computerLocationX + computerCaseWidth - 15;
        var computerCase1LocationY = computerCaseLocationY + 2.5;
        svg.rect(computerCase1LocationX, computerCase1LocationY, computerCase1Width, computerCase1Height, {
            fill : "black"
        });

        var computerCase2Width = 4;
        var computerCase2LocationX = computerCase1LocationX - 8;
        svg.rect(computerCase2LocationX, computerCase1LocationY, computerCase2Width, computerCase1Height, {
            fill : "black"
        });

        var computerCase3LocationX = computerCase2LocationX - 8;
        svg.rect(computerCase3LocationX, computerCase1LocationY, computerCase2Width, computerCase1Height, {
            fill : "black"
        });
        var computerCase4LocationX = computerCase3LocationX - 8;
        svg.rect(computerCase4LocationX, computerCase1LocationY, computerCase2Width, computerCase1Height, {
            fill : "black"
        });

        var computerCaseSwitchWidth = 5;
        var computerCaseSwitchLocationX = computerCase4LocationX - 20;
        var computerCaseSwitchLocationY = computerCase1LocationY + 12;
        svg.circle(computerCaseSwitchLocationX, computerCaseSwitchLocationY, computerCaseSwitchWidth, {
            fill : "white",
            stroke : "black",
            strokeWidth : "2px"
        });
    }
    function drawEpCommunicationName(svg, currentElectricFieldLocationX, currentElectricFieldLocationY, name) {
        var textLocationX = currentElectricFieldLocationX + 2;
        var textLocationY = currentElectricFieldLocationY + eachCommunicationStateRectHeight + 15;
        svg.text(textLocationX, textLocationY, name, electricFieldTitleSettings);
    }
});
