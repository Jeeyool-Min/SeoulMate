var i=0;
var original = document.getElementByid('basicInfo');
function duplicate() {
    var clone = original.coneNode(true);
    clone.id = "basicInfo" + ++i;
    original.parseNode.appendChild(clone);
}
