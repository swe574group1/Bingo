
var PageName = 'Home';
var PageId = 'f09f4c09a92e4c1fb8d427cae504c02b'
var PageUrl = 'Home.html'
document.title = 'Home';
var PageNotes = 
{
"pageName":"Home",
"showNotesNames":"False"}
var $OnLoadVariable = '';

var $CSUM;

var hasQuery = false;
var query = window.location.hash.substring(1);
if (query.length > 0) hasQuery = true;
var vars = query.split("&");
for (var i = 0; i < vars.length; i++) {
    var pair = vars[i].split("=");
    if (pair[0].length > 0) eval("$" + pair[0] + " = decodeURIComponent(pair[1]);");
} 

if (hasQuery && $CSUM != 1) {
alert('Prototype Warning: The variable values were too long to pass to this page.\nIf you are using IE, using Firefox will support more data.');
}

function GetQuerystring() {
    return '#OnLoadVariable=' + encodeURIComponent($OnLoadVariable) + '&CSUM=1';
}

function PopulateVariables(value) {
    var d = new Date();
  value = value.replace(/\[\[OnLoadVariable\]\]/g, $OnLoadVariable);
  value = value.replace(/\[\[PageName\]\]/g, PageName);
  value = value.replace(/\[\[GenDay\]\]/g, '19');
  value = value.replace(/\[\[GenMonth\]\]/g, '3');
  value = value.replace(/\[\[GenMonthName\]\]/g, 'Mart');
  value = value.replace(/\[\[GenDayOfWeek\]\]/g, 'Pazartesi');
  value = value.replace(/\[\[GenYear\]\]/g, '2012');
  value = value.replace(/\[\[Day\]\]/g, d.getDate());
  value = value.replace(/\[\[Month\]\]/g, d.getMonth() + 1);
  value = value.replace(/\[\[MonthName\]\]/g, GetMonthString(d.getMonth()));
  value = value.replace(/\[\[DayOfWeek\]\]/g, GetDayString(d.getDay()));
  value = value.replace(/\[\[Year\]\]/g, d.getFullYear());
  return value;
}

function OnLoad(e) {

}

var u21 = document.getElementById('u21');

var u86 = document.getElementById('u86');
gv_vAlignTable['u86'] = 'center';
var u51 = document.getElementById('u51');
gv_vAlignTable['u51'] = 'center';
var u25 = document.getElementById('u25');

var u16 = document.getElementById('u16');

var u55 = document.getElementById('u55');

var u46 = document.getElementById('u46');

var u76 = document.getElementById('u76');
gv_vAlignTable['u76'] = 'top';
var u31 = document.getElementById('u31');
gv_vAlignTable['u31'] = 'top';
var u77 = document.getElementById('u77');
gv_vAlignTable['u77'] = 'top';
var u93 = document.getElementById('u93');
gv_vAlignTable['u93'] = 'center';
var u38 = document.getElementById('u38');

var u32 = document.getElementById('u32');

if (bIE) u32.attachEvent("onmouseover", MouseOveru32);
else u32.addEventListener("mouseover", MouseOveru32, true);
function MouseOveru32(e)
{
windowEvent = e;

if (!IsTrueMouseOver('u32',e)) return;
if (true) {

	SetPanelVisibility('u82','','none',500);

}

}

if (bIE) u32.attachEvent("onmouseout", MouseOutu32);
else u32.addEventListener("mouseout", MouseOutu32, true);
function MouseOutu32(e)
{
windowEvent = e;

if (!IsTrueMouseOut('u32',e)) return;
if (true) {

	SetPanelVisibility('u82','hidden','none',500);

}

}
gv_vAlignTable['u32'] = 'top';
var u23 = document.getElementById('u23');

var u62 = document.getElementById('u62');
gv_vAlignTable['u62'] = 'top';
var u53 = document.getElementById('u53');

u53.style.cursor = 'pointer';
if (bIE) u53.attachEvent("onclick", Clicku53);
else u53.addEventListener("click", Clicku53, true);
function Clicku53(e)
{
windowEvent = e;


if (true) {

	self.location.href="Search.html" + GetQuerystring();

}

}

var u87 = document.getElementById('u87');

if (bIE) u87.attachEvent("onmouseover", MouseOveru87);
else u87.addEventListener("mouseover", MouseOveru87, true);
function MouseOveru87(e)
{
windowEvent = e;

if (!IsTrueMouseOver('u87',e)) return;
if (true) {

	SetPanelVisibility('u91','','none',500);

}

}

if (bIE) u87.attachEvent("onmouseout", MouseOutu87);
else u87.addEventListener("mouseout", MouseOutu87, true);
function MouseOutu87(e)
{
windowEvent = e;

if (!IsTrueMouseOut('u87',e)) return;
if (true) {

	SetPanelVisibility('u91','hidden','none',500);

}

}
gv_vAlignTable['u87'] = 'top';
var u1 = document.getElementById('u1');
gv_vAlignTable['u1'] = 'center';
var u27 = document.getElementById('u27');

u27.style.cursor = 'pointer';
if (bIE) u27.attachEvent("onclick", u27Click);
else u27.addEventListener("click", u27Click, true);
InsertAfterBegin(document.body, "<DIV class='intcases' id='u27LinksClick'></DIV>")
var u27LinksClick = document.getElementById('u27LinksClick');
function u27Click(e) 
{
windowEvent = e;


	ToggleLinks(e, 'u27LinksClick');
}

InsertBeforeEnd(u27LinksClick, "<div class='intcaselink' onmouseout='SuppressBubble(event)' onclick='u27Clickub1648f506472485993fce533427c5514(event)'>Login OK</div>");
function u27Clickub1648f506472485993fce533427c5514(e)
{

	self.location.href="Member.html" + GetQuerystring();

	ToggleLinks(e, 'u27LinksClick');
}

InsertBeforeEnd(u27LinksClick, "<div class='intcaselink' onmouseout='SuppressBubble(event)' onclick='u27Clickub057998ee09b412a8fe6a98a8693ea9d(event)'>Login FAILED</div>");
function u27Clickub057998ee09b412a8fe6a98a8693ea9d(e)
{

	ToggleLinks(e, 'u27LinksClick');
}

var u7 = document.getElementById('u7');
gv_vAlignTable['u7'] = 'center';
var u66 = document.getElementById('u66');
gv_vAlignTable['u66'] = 'top';
var u30 = document.getElementById('u30');
gv_vAlignTable['u30'] = 'top';
var u8 = document.getElementById('u8');

var u60 = document.getElementById('u60');
gv_vAlignTable['u60'] = 'top';
var u89 = document.getElementById('u89');

var u34 = document.getElementById('u34');

var u17 = document.getElementById('u17');

var u64 = document.getElementById('u64');

var u19 = document.getElementById('u19');

var u49 = document.getElementById('u49');
gv_vAlignTable['u49'] = 'center';
var u79 = document.getElementById('u79');

var u81 = document.getElementById('u81');

if (bIE) u81.attachEvent("onmouseover", MouseOveru81);
else u81.addEventListener("mouseover", MouseOveru81, true);
function MouseOveru81(e)
{
windowEvent = e;

if (!IsTrueMouseOver('u81',e)) return;
if (true) {

	SetPanelVisibility('u88','','none',500);

}

}

if (bIE) u81.attachEvent("onmouseout", MouseOutu81);
else u81.addEventListener("mouseout", MouseOutu81, true);
function MouseOutu81(e)
{
windowEvent = e;

if (!IsTrueMouseOut('u81',e)) return;
if (true) {

	SetPanelVisibility('u88','hidden','none',500);

}

}
gv_vAlignTable['u81'] = 'top';
var u97 = document.getElementById('u97');

var u85 = document.getElementById('u85');

var u11 = document.getElementById('u11');
gv_vAlignTable['u11'] = 'center';
var u41 = document.getElementById('u41');
gv_vAlignTable['u41'] = 'top';
var u71 = document.getElementById('u71');
gv_vAlignTable['u71'] = 'top';
var u15 = document.getElementById('u15');

u15.style.cursor = 'pointer';
if (bIE) u15.attachEvent("onclick", Clicku15);
else u15.addEventListener("click", Clicku15, true);
function Clicku15(e)
{
windowEvent = e;


if (true) {

	self.location.href="Search.html" + GetQuerystring();

}

}

var u45 = document.getElementById('u45');
gv_vAlignTable['u45'] = 'center';
var u36 = document.getElementById('u36');

var u75 = document.getElementById('u75');
gv_vAlignTable['u75'] = 'top';
var u58 = document.getElementById('u58');

var u37 = document.getElementById('u37');
gv_vAlignTable['u37'] = 'top';
var u2 = document.getElementById('u2');

var u92 = document.getElementById('u92');

var u83 = document.getElementById('u83');

var u95 = document.getElementById('u95');
gv_vAlignTable['u95'] = 'center';
var u22 = document.getElementById('u22');

u22.style.cursor = 'pointer';
if (bIE) u22.attachEvent("onclick", Clicku22);
else u22.addEventListener("click", Clicku22, true);
function Clicku22(e)
{
windowEvent = e;


if (true) {

	self.location.href="Member.html" + GetQuerystring();

}

}

var u13 = document.getElementById('u13');
gv_vAlignTable['u13'] = 'center';
var u52 = document.getElementById('u52');

var u43 = document.getElementById('u43');
gv_vAlignTable['u43'] = 'center';
var u0 = document.getElementById('u0');

var u3 = document.getElementById('u3');
gv_vAlignTable['u3'] = 'center';
var u47 = document.getElementById('u47');
gv_vAlignTable['u47'] = 'center';
var u68 = document.getElementById('u68');
gv_vAlignTable['u68'] = 'top';
var u90 = document.getElementById('u90');
gv_vAlignTable['u90'] = 'center';
var u73 = document.getElementById('u73');
gv_vAlignTable['u73'] = 'top';
var u84 = document.getElementById('u84');
gv_vAlignTable['u84'] = 'center';
var u20 = document.getElementById('u20');

var u50 = document.getElementById('u50');

var u28 = document.getElementById('u28');

var u24 = document.getElementById('u24');
gv_vAlignTable['u24'] = 'center';
var u54 = document.getElementById('u54');

var u99 = document.getElementById('u99');
gv_vAlignTable['u99'] = 'top';
var u39 = document.getElementById('u39');
gv_vAlignTable['u39'] = 'top';
var u69 = document.getElementById('u69');
gv_vAlignTable['u69'] = 'top';
var u78 = document.getElementById('u78');
gv_vAlignTable['u78'] = 'top';
var u4 = document.getElementById('u4');

var u94 = document.getElementById('u94');

var u6 = document.getElementById('u6');

var u96 = document.getElementById('u96');
gv_vAlignTable['u96'] = 'top';
var u61 = document.getElementById('u61');

var u91 = document.getElementById('u91');

var u35 = document.getElementById('u35');
gv_vAlignTable['u35'] = 'top';
var u26 = document.getElementById('u26');

var u65 = document.getElementById('u65');
gv_vAlignTable['u65'] = 'top';
var u56 = document.getElementById('u56');

var u82 = document.getElementById('u82');

var u5 = document.getElementById('u5');
gv_vAlignTable['u5'] = 'center';
var u12 = document.getElementById('u12');

var u9 = document.getElementById('u9');
gv_vAlignTable['u9'] = 'center';
var u42 = document.getElementById('u42');

var u33 = document.getElementById('u33');

var u72 = document.getElementById('u72');
gv_vAlignTable['u72'] = 'top';
var u63 = document.getElementById('u63');

var u18 = document.getElementById('u18');

var u48 = document.getElementById('u48');

var u67 = document.getElementById('u67');
gv_vAlignTable['u67'] = 'top';
var u88 = document.getElementById('u88');

var u57 = document.getElementById('u57');
gv_vAlignTable['u57'] = 'center';
var u10 = document.getElementById('u10');

var u40 = document.getElementById('u40');

var u70 = document.getElementById('u70');
gv_vAlignTable['u70'] = 'top';
var u14 = document.getElementById('u14');

var u44 = document.getElementById('u44');

var u74 = document.getElementById('u74');
gv_vAlignTable['u74'] = 'top';
var u29 = document.getElementById('u29');
gv_vAlignTable['u29'] = 'bottom';
var u59 = document.getElementById('u59');

var u98 = document.getElementById('u98');
gv_vAlignTable['u98'] = 'center';
var u80 = document.getElementById('u80');
gv_vAlignTable['u80'] = 'center';
if (window.OnLoad) OnLoad();
