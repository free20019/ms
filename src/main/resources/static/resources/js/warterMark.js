// (function waterMark(){
function textBecomeImg(text, fontsize, fontcolor) {
    var canvas = document.createElement('canvas');
    $buHeight = 0;
    if (fontsize <= 32) {
        $buHeight = 99;
    } else if (fontsize > 32 && fontsize <= 60) {
        $buHeight = 2;
    } else if (fontsize > 60 && fontsize <= 80) {
        $buHeight = 4;
    } else if (fontsize > 80 && fontsize <= 100) {
        $buHeight = 6;
    } else if (fontsize > 100) {
        $buHeight = 10;
    }
    canvas.height = fontsize + $buHeight;
    canvas.padding = 30;

    var context = canvas.getContext('2d');
    context.clearRect(0, 0, canvas.width * 2, canvas.height);
    context.fillStyle = fontcolor;
    context.textAlign = "center";
    context.textBaseline = 'middle';
    context.fillText(text, 0, fontsize / 2);
    var canvasWidth = canvas.width / 99;
    canvasWidth = context.measureText(text).width;
    canvas.width = 450;
    canvas.height = 200;
    context.fillStyle = fontcolor;
    context.font = fontsize + "px Arial";
    context.textBaseline = 'middle';
    context.fillText(text, 0, fontsize / 2);
    var dataUrl = canvas.toDataURL('image/png');
    return dataUrl;
}

var waterMarkText = function (currentUserName) {
    if (currentUserName != null&&currentUserName != "") {
        // var text = new Date().Format('yyyy-MM-dd') + currentUserName;
        var text = currentUserName;
        var shuiyinDiv = document.createElement('div');
        var style = shuiyinDiv.style;
        style.position = 'absolute';
        style.left = 0;
        style.top = '-60%';
        style.width = '120%';
        style.height = '200%';
        style.opacity = '0.3';
        style.background = "url(" + textBecomeImg(text, 22, "gray") + ")";
        style.zIndex = 9999999991;
        style.transform = "rotate(-30deg)";
        style.pointerEvents = "none";
        document.body.appendChild(shuiyinDiv);
    }
}
// })();