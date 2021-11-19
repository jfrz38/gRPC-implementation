document.getElementById("clear").addEventListener("click", clearCanvas);



const canvas = document.getElementById("canvas")
var context = canvas.getContext("2d");
var color = "#000000"
canvas.addEventListener('mousedown', onMouseDown, false)
canvas.addEventListener('mousemove', onMouseMove, false)
canvas.addEventListener('mouseup', onMouseUp, false)
const sizes = canvas.getBoundingClientRect();
canvasSizes = { width: sizes.width, height: sizes.height }
var mouseDown = 0, lastX, lastY;
var lastReceivedX, lastReceivedY;

["black", "red", "yellow", "blue", "green", "white"].forEach(color => {
    const element = document.getElementById(color)
    element.addEventListener("click", selectColor)
    element.style.width = "58px"
    element.style.height = "21px"
    element.style.border = "none"
    element.style.borderRadius = "4px"
    element.style.backgroundColor = element.id
})

function selectColor(e){
    const d = document.createElement("div")
    d.style.color = e.target.id
    var colors = window.getComputedStyle( document.body.appendChild(d) ).color.match(/\d+/g).map(function(a){ return parseInt(a,10); });
    document.body.removeChild(d);
    color = (colors.length >= 3) ? '#' + (((1 << 24) + (colors[0] << 16) + (colors[1] << 8) + colors[2]).toString(16).substr(1)) : false;

}

window.api.receive("paint", (data) => {
    if(!lastReceivedX) lastReceivedX = data.x
    if(!lastReceivedY) lastReceivedY = data.y
    context.beginPath();
    context.moveTo(lastReceivedX, lastReceivedY);
    context.lineTo(data.x, data.y);
    context.closePath();
    context.strokeStyle = data.color
    context.stroke();
    lastReceivedX = data.x;
    lastReceivedY = data.y;
});

function draw(context, x, y) {
    context.beginPath();
    context.moveTo(lastX, lastY);
    context.lineTo(x, y);
    context.closePath();
    context.strokeStyle = color
    context.stroke();
    window.api.send("transform", { mouse: { x: x, y: y }, canvas: canvasSizes, color: color })

}

function clearCanvas() {
    context.clearRect(0, 0, canvas.width, canvas.height)
    lastReceivedX = null
    lastReceivedY = null
}

function onMouseDown(e) {
    var xy = getMousePos(e);
    lastX = xy.mouseX;
    lastY = xy.mouseY;
    mouseDown = 1;
    lastReceivedX = null
    lastReceivedY = null
}

function onMouseUp() {
    mouseDown = 0
}

function onMouseMove(e) {
    if (mouseDown == 1) {
        var xy = getMousePos(e);
        draw(context, xy.mouseX, xy.mouseY);
        lastX = xy.mouseX, lastY = xy.mouseY;
    }
}

function getMousePos(e) {
    var o = {};
    if (!e)
        var e = event
    if (e.offsetX) {
        o.mouseX = e.offsetX
        o.mouseY = e.offsetY
    }
    else if (e.layerX) {
        o.mouseX = e.layerX
        o.mouseY = e.layerY
    }
    return o;
}
