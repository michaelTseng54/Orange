$(document).ready(function () {
	'use strict';
	var theGrid = $("#theGrid"),
	// numberTemplate = {formatter: 'number', align: 'right', sorttype: 'number'},
	horizontalScrollPosition = 0,
	selectedRow = null,
	saveSelectedRow = function () {
		var $grid = $(this); 
		selectedRow = $grid.jqGrid('getGridParam', 'selrow');
		horizontalScrollPosition = this.grid.bDiv.scrollLeft;
	},
	selectSavedRow = function () {
		var $grid = $(this); 
        if (selectedRow) {
        	$grid.jqGrid("setSelection", selectedRow, false);
            selectedRow = null;
        }
        this.grid.bDiv.scrollLeft = horizontalScrollPosition;
	};
	theGrid.jqGrid({
		url:'FUNC001/jqGrid',
    	datatype: "json",
    	mtype: 'GET',
        colNames: ['projId', 'projNm', 'custId', 'endDate', 'projMgr', 'memo', 'warnMailList'],
        colModel: [
            {name: 'projId', index: 'projId', width: 40, align: 'center', editable:true, sortable:true, sorttype:"int"},
            {name: 'projNm', index: 'projNm', width: 40, align: 'center', editable:true},
            {name: 'custId', index: 'custId', width: 40, align: 'center', editable:true},
            {name: 'endDate', index: 'endDate', width: 70, align: 'center', editable:true},
            {name: 'projMgr', index: 'projMgr', width: 42, align: 'center', editable:true},
            {name: 'memo', index: 'memo', width: 50, align: 'center', editable:true},
            {name: 'warnMailList', index: 'warnMailList', width: 50, align: 'center', editable:true}
        ],
        loadonce: true,				//一次把所有資料載入
        jsonReader: {
            root:"rows",             
            page: "page",           
            total: "total",    
            records: "records",
            repeatitems : false                
            },
        editurl: "FUNC001/update",	//update資料的uri
        firstsortorder: 'desc',
        gridview: true,             
        rownumbers: true,
        rowNum: 15,
        rowList: [10, 15, 30, 50, 100],
        pager: '#gridPager',
        sortname: 'projId',
        //multiselect: true,			//開啟多筆選擇
        viewrecords: true,
        sortorder: 'desc',
        caption: '測試中文',				//表頭文字
        height: '100%',
        width: $(window).width() - 80,	//表單寬度
        onSortCol: function () {
            saveSelectedRow.call(this);
        },
        loadComplete: function (xhr, stat) {
            selectSavedRow.call(this);
        }
    });
    jQuery("#theGrid").click(function(){
    	var id = jQuery("#theGrid").jqGrid('getGridParam','selrow');
    	if (id != window.setMyGridSelectedId) {
	    	jQuery("#theGrid").jqGrid('saveRow', window.setMyGridSelectedId, checksave);
	    	delete window.setMyGridSelectedId;
    	}
    });
    jQuery("#theGrid").dblclick(function(){
    	var id = jQuery("#theGrid").jqGrid('getGridParam','selrow');
    	if (typeof window.setMyGridSelectedId == "undefined") {
    		console.log("window.setMyGridSelectedId == undefined");
    		window.setMyGridSelectedId = id;
    		jQuery("#theGrid").jqGrid('editRow', id);
    	}
//    	if (window.setMyGridSelectedId == id)	{
//			//var row = jQuery("#theGrid").jqGrid('getRowData', id);
//    	}
//    	else { 
//    		jQuery("#theGrid").jqGrid('saveRow', window.setMyGridSelectedId, checksave);
//    		window.setMyGridSelectedId = 'undefined';
//    		jQuery("#theGrid").jqGrid('editRow', id);
//    	}
    });
    function checksave(result) {
    	if (result.responseText=="") {alert("Update is missing!"); return false;}
    	return true;
    }
    function pickdates(id){
    	jQuery("#"+id+"_sdate","#rowed6").datepicker({dateFormat:"yy-mm-dd"});
    }
//    jQuery("#ed4").click( function() {
//    	jQuery("#theGrid").jqGrid('editRow',"13");
//    	this.disabled = 'true';
//    	jQuery("#sved4").attr("disabled", false);
//    });
//    jQuery("#sved4").click( function() {
//    	jQuery("#theGrid").jqGrid('saveRow', "13", checksave);
//    	jQuery("#sved4").attr("disabled", true);
//    	jQuery("#ed4").attr("disabled", false);
//    });
//    jQuery("#m1").click( function(){
//    	var id = jQuery("#theGrid").jqGrid('getGridParam','selrow');
//    	if (id)	{
//    		var ret = jQuery("#theGrid").jqGrid('getRowData',id);
//    		alert("projId="+ret.projId+" projNm="+ret.projNm+"...");
//    	} else { alert("Please select row");}
//    });
});