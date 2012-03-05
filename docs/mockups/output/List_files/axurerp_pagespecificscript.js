
var PageName = 'List';
var PageId = '4835cd29e1014211a10f051282ba1eb1'
var PageUrl = 'List.html'
document.title = 'List';
var PageNotes = 
{
"pageName":"List",
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
  value = value.replace(/\[\[GenDay\]\]/g, '5');
  value = value.replace(/\[\[GenMonth\]\]/g, '3');
  value = value.replace(/\[\[GenMonthName\]\]/g, 'March');
  value = value.replace(/\[\[GenDayOfWeek\]\]/g, 'Monday');
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

var u154 = document.getElementById('u154');

var u155 = document.getElementById('u155');

var u156 = document.getElementById('u156');

var u157 = document.getElementById('u157');

u157.style.cursor = 'pointer';
if (bIE) u157.attachEvent("onclick", Clicku157);
else u157.addEventListener("click", Clicku157, true);
function Clicku157(e)
{
windowEvent = e;


if (true) {

	self.location.href="Create.html" + GetQuerystring();

}

}

var u80 = document.getElementById('u80');

var u81 = document.getElementById('u81');
gv_vAlignTable['u81'] = 'top';
var u82 = document.getElementById('u82');

u82.style.cursor = 'pointer';
if (bIE) u82.attachEvent("onclick", Clicku82);
else u82.addEventListener("click", Clicku82, true);
function Clicku82(e)
{
windowEvent = e;


if (true) {

	self.location.href="Profile.html" + GetQuerystring();

}

}
gv_vAlignTable['u82'] = 'top';
var u83 = document.getElementById('u83');
gv_vAlignTable['u83'] = 'top';
var u84 = document.getElementById('u84');
gv_vAlignTable['u84'] = 'top';
var u85 = document.getElementById('u85');
gv_vAlignTable['u85'] = 'top';
var u86 = document.getElementById('u86');
gv_vAlignTable['u86'] = 'top';
var u87 = document.getElementById('u87');
gv_vAlignTable['u87'] = 'top';
var u88 = document.getElementById('u88');

var u89 = document.getElementById('u89');

var u90 = document.getElementById('u90');

var u91 = document.getElementById('u91');

var u92 = document.getElementById('u92');

var u10 = document.getElementById('u10');
gv_vAlignTable['u10'] = 'top';
var u11 = document.getElementById('u11');

var u12 = document.getElementById('u12');
gv_vAlignTable['u12'] = 'top';
var u13 = document.getElementById('u13');

var u14 = document.getElementById('u14');
gv_vAlignTable['u14'] = 'top';
var u15 = document.getElementById('u15');

var u16 = document.getElementById('u16');
gv_vAlignTable['u16'] = 'top';
var u17 = document.getElementById('u17');

var u18 = document.getElementById('u18');
gv_vAlignTable['u18'] = 'top';
var u19 = document.getElementById('u19');

var u110 = document.getElementById('u110');

var u111 = document.getElementById('u111');
gv_vAlignTable['u111'] = 'top';
var u112 = document.getElementById('u112');

var u113 = document.getElementById('u113');
gv_vAlignTable['u113'] = 'top';
var u114 = document.getElementById('u114');

var u115 = document.getElementById('u115');
gv_vAlignTable['u115'] = 'top';
var u20 = document.getElementById('u20');
gv_vAlignTable['u20'] = 'top';
var u21 = document.getElementById('u21');

var u22 = document.getElementById('u22');
gv_vAlignTable['u22'] = 'top';
var u23 = document.getElementById('u23');

var u24 = document.getElementById('u24');
gv_vAlignTable['u24'] = 'top';
var u25 = document.getElementById('u25');

var u26 = document.getElementById('u26');
gv_vAlignTable['u26'] = 'top';
var u27 = document.getElementById('u27');

var u28 = document.getElementById('u28');
gv_vAlignTable['u28'] = 'top';
var u29 = document.getElementById('u29');

var u100 = document.getElementById('u100');

var u101 = document.getElementById('u101');
gv_vAlignTable['u101'] = 'top';
var u102 = document.getElementById('u102');

var u103 = document.getElementById('u103');
gv_vAlignTable['u103'] = 'top';
var u104 = document.getElementById('u104');

var u105 = document.getElementById('u105');
gv_vAlignTable['u105'] = 'top';
var u106 = document.getElementById('u106');

var u107 = document.getElementById('u107');
gv_vAlignTable['u107'] = 'top';
var u108 = document.getElementById('u108');

var u109 = document.getElementById('u109');
gv_vAlignTable['u109'] = 'top';
var u127 = document.getElementById('u127');
gv_vAlignTable['u127'] = 'top';
var u128 = document.getElementById('u128');

var u129 = document.getElementById('u129');
gv_vAlignTable['u129'] = 'top';
var u30 = document.getElementById('u30');
gv_vAlignTable['u30'] = 'top';
var u31 = document.getElementById('u31');

var u32 = document.getElementById('u32');
gv_vAlignTable['u32'] = 'top';
var u33 = document.getElementById('u33');

var u34 = document.getElementById('u34');
gv_vAlignTable['u34'] = 'top';
var u35 = document.getElementById('u35');

var u36 = document.getElementById('u36');
gv_vAlignTable['u36'] = 'top';
var u37 = document.getElementById('u37');

var u38 = document.getElementById('u38');
gv_vAlignTable['u38'] = 'top';
var u39 = document.getElementById('u39');

var u93 = document.getElementById('u93');

var u94 = document.getElementById('u94');
gv_vAlignTable['u94'] = 'center';
var u95 = document.getElementById('u95');

var u96 = document.getElementById('u96');

var u97 = document.getElementById('u97');
gv_vAlignTable['u97'] = 'top';
var u98 = document.getElementById('u98');

var u99 = document.getElementById('u99');
gv_vAlignTable['u99'] = 'top';
var u116 = document.getElementById('u116');

var u117 = document.getElementById('u117');
gv_vAlignTable['u117'] = 'top';
var u118 = document.getElementById('u118');

var u119 = document.getElementById('u119');
gv_vAlignTable['u119'] = 'top';
var u40 = document.getElementById('u40');
gv_vAlignTable['u40'] = 'top';
var u41 = document.getElementById('u41');

var u42 = document.getElementById('u42');
gv_vAlignTable['u42'] = 'top';
var u43 = document.getElementById('u43');

var u44 = document.getElementById('u44');
gv_vAlignTable['u44'] = 'top';
var u45 = document.getElementById('u45');

var u46 = document.getElementById('u46');
gv_vAlignTable['u46'] = 'top';
var u47 = document.getElementById('u47');

var u48 = document.getElementById('u48');
gv_vAlignTable['u48'] = 'top';
var u49 = document.getElementById('u49');

var u120 = document.getElementById('u120');

var u121 = document.getElementById('u121');
gv_vAlignTable['u121'] = 'top';
var u122 = document.getElementById('u122');

var u123 = document.getElementById('u123');
gv_vAlignTable['u123'] = 'top';
var u124 = document.getElementById('u124');

var u125 = document.getElementById('u125');
gv_vAlignTable['u125'] = 'top';
var u126 = document.getElementById('u126');

var u0 = document.getElementById('u0');

var u1 = document.getElementById('u1');
gv_vAlignTable['u1'] = 'center';
var u2 = document.getElementById('u2');

var u3 = document.getElementById('u3');

var u4 = document.getElementById('u4');
gv_vAlignTable['u4'] = 'top';
var u5 = document.getElementById('u5');

var u6 = document.getElementById('u6');
gv_vAlignTable['u6'] = 'top';
var u7 = document.getElementById('u7');

var u8 = document.getElementById('u8');
gv_vAlignTable['u8'] = 'top';
var u9 = document.getElementById('u9');

var u50 = document.getElementById('u50');
gv_vAlignTable['u50'] = 'top';
var u51 = document.getElementById('u51');

var u52 = document.getElementById('u52');
gv_vAlignTable['u52'] = 'top';
var u53 = document.getElementById('u53');

var u54 = document.getElementById('u54');
gv_vAlignTable['u54'] = 'center';
var u55 = document.getElementById('u55');

var u56 = document.getElementById('u56');
gv_vAlignTable['u56'] = 'center';
var u57 = document.getElementById('u57');

u57.style.cursor = 'pointer';
if (bIE) u57.attachEvent("onclick", Clicku57);
else u57.addEventListener("click", Clicku57, true);
function Clicku57(e)
{
windowEvent = e;


if (true) {

	self.location.href="Profile.html" + GetQuerystring();

}

}
gv_vAlignTable['u57'] = 'top';
var u58 = document.getElementById('u58');

u58.style.cursor = 'pointer';
if (bIE) u58.attachEvent("onclick", Clicku58);
else u58.addEventListener("click", Clicku58, true);
function Clicku58(e)
{
windowEvent = e;


if (true) {

	SetPanelVisibility('u59','','none',500);

}

}

var u59 = document.getElementById('u59');

var u130 = document.getElementById('u130');

var u131 = document.getElementById('u131');
gv_vAlignTable['u131'] = 'top';
var u132 = document.getElementById('u132');

var u133 = document.getElementById('u133');
gv_vAlignTable['u133'] = 'top';
var u134 = document.getElementById('u134');

var u135 = document.getElementById('u135');
gv_vAlignTable['u135'] = 'top';
var u136 = document.getElementById('u136');

var u137 = document.getElementById('u137');
gv_vAlignTable['u137'] = 'top';
var u138 = document.getElementById('u138');

var u139 = document.getElementById('u139');
gv_vAlignTable['u139'] = 'top';
var u60 = document.getElementById('u60');

u60.style.cursor = 'pointer';
if (bIE) u60.attachEvent("onclick", Clicku60);
else u60.addEventListener("click", Clicku60, true);
function Clicku60(e)
{
windowEvent = e;


if (true) {

	self.location.href="Home.html" + GetQuerystring();

}

}
gv_vAlignTable['u60'] = 'top';
var u61 = document.getElementById('u61');

u61.style.cursor = 'pointer';
if (bIE) u61.attachEvent("onclick", Clicku61);
else u61.addEventListener("click", Clicku61, true);
function Clicku61(e)
{
windowEvent = e;


if (true) {

	self.location.href="Settings.html" + GetQuerystring();

}

}
gv_vAlignTable['u61'] = 'top';
var u62 = document.getElementById('u62');

var u63 = document.getElementById('u63');

u63.style.cursor = 'pointer';
if (bIE) u63.attachEvent("onclick", Clicku63);
else u63.addEventListener("click", Clicku63, true);
function Clicku63(e)
{
windowEvent = e;


if (true) {

	self.location.href="Search.html" + GetQuerystring();

}

}

var u64 = document.getElementById('u64');

u64.style.cursor = 'pointer';
if (bIE) u64.attachEvent("onclick", Clicku64);
else u64.addEventListener("click", Clicku64, true);
function Clicku64(e)
{
windowEvent = e;


if (true) {

	self.location.href="Inbox.html" + GetQuerystring();

}

}
gv_vAlignTable['u64'] = 'top';
var u65 = document.getElementById('u65');

u65.style.cursor = 'pointer';
if (bIE) u65.attachEvent("onclick", Clicku65);
else u65.addEventListener("click", Clicku65, true);
function Clicku65(e)
{
windowEvent = e;


if (true) {

	self.location.href="Notifications.html" + GetQuerystring();

}

}
gv_vAlignTable['u65'] = 'top';
var u66 = document.getElementById('u66');

var u67 = document.getElementById('u67');

var u68 = document.getElementById('u68');

u68.style.cursor = 'pointer';
if (bIE) u68.attachEvent("onclick", Clicku68);
else u68.addEventListener("click", Clicku68, true);
function Clicku68(e)
{
windowEvent = e;


if (true) {

	self.location.href="Member.html" + GetQuerystring();

}

}

var u69 = document.getElementById('u69');
gv_vAlignTable['u69'] = 'top';
var u140 = document.getElementById('u140');

var u141 = document.getElementById('u141');
gv_vAlignTable['u141'] = 'top';
var u142 = document.getElementById('u142');

var u143 = document.getElementById('u143');
gv_vAlignTable['u143'] = 'top';
var u144 = document.getElementById('u144');

var u145 = document.getElementById('u145');
gv_vAlignTable['u145'] = 'top';
var u146 = document.getElementById('u146');
gv_vAlignTable['u146'] = 'top';
var u147 = document.getElementById('u147');
gv_vAlignTable['u147'] = 'top';
var u148 = document.getElementById('u148');
gv_vAlignTable['u148'] = 'top';
var u149 = document.getElementById('u149');
gv_vAlignTable['u149'] = 'top';
var u70 = document.getElementById('u70');

u70.style.cursor = 'pointer';
if (bIE) u70.attachEvent("onclick", Clicku70);
else u70.addEventListener("click", Clicku70, true);
function Clicku70(e)
{
windowEvent = e;


if (true) {

	self.location.href="resources/reload.html#" + encodeURI(PageUrl + GetQuerystring());

}

}

var u71 = document.getElementById('u71');
gv_vAlignTable['u71'] = 'top';
var u72 = document.getElementById('u72');

u72.style.cursor = 'pointer';
if (bIE) u72.attachEvent("onclick", Clicku72);
else u72.addEventListener("click", Clicku72, true);
function Clicku72(e)
{
windowEvent = e;


if (true) {

	self.location.href="List_1.html" + GetQuerystring();

}

}

var u73 = document.getElementById('u73');
gv_vAlignTable['u73'] = 'top';
var u74 = document.getElementById('u74');

var u75 = document.getElementById('u75');
gv_vAlignTable['u75'] = 'top';
var u76 = document.getElementById('u76');

var u77 = document.getElementById('u77');
gv_vAlignTable['u77'] = 'top';
var u78 = document.getElementById('u78');

var u79 = document.getElementById('u79');
gv_vAlignTable['u79'] = 'center';
var u150 = document.getElementById('u150');
gv_vAlignTable['u150'] = 'top';
var u151 = document.getElementById('u151');
gv_vAlignTable['u151'] = 'top';
var u152 = document.getElementById('u152');

var u153 = document.getElementById('u153');

if (window.OnLoad) OnLoad();
