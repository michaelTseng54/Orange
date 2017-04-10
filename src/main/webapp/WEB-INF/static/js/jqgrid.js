$(document).ready(function () {
	'use strict';
	var theGrid = $("#theGrid"),
	horizontalScrollPosition = 10,
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
		url:'FUNC001/query',
    	datatype: "json",
    	mtype: 'GET',
        colNames: ['projId', 'projId', 'projNm', 'custId', 'startDate', 'endDate', 'projMgr', 'memo', 'warnMailList'],
        colModel: [
            {name: 'projId', index: 'projId', align: 'center', editable:true, hidden: true},
            {name: 'projId', index: 'projId', align: 'center'},
            {name: 'projNm', index: 'projNm', align: 'center', editable:true},
            {name: 'custId', index: 'custId', align: 'center', editable:true},
            {name: 'startDate', index: 'startDate', align: 'center', editable:true, editoptions: { dataInit: function (elem) { $(elem).datepicker({dateFormat:"yy-mm-dd"}); } }},
            {name: 'endDate', index: 'endDate', align: 'center', editable:true, editoptions: { dataInit: function (elem) { $(elem).datepicker({dateFormat:"yy-mm-dd"}); } }},
            {name: 'projMgr', index: 'projMgr', align: 'center', editable:true},
            {name: 'memo', index: 'memo', align: 'center', editable:true},
            {name: 'warnMailList', index: 'warnMailList', align: 'center', editable: true,
            	edittype:'select', formatter:'warnMailList',
            	editoptions: { 
            		dataUrl:"selectOptions", 
                    buildSelect: function (data) {
                    	var response = jQuery.parseJSON(data);
                    	var s = '<select>';
                        if (response && Object.keys(response).length) {
                        	 $.each(response, function(k, v) {
                        		 s += '<option value="' + v + '">' + k + '</option>';	 
                        	 });
                        }
                        s += "</select>";
                        return s;
                    } 
            	}
            }
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
        autowidth: true,
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
        loadComplete: function () {
            selectSavedRow.call(this);
        },
        onSelectRow: function () {}
    });
	
	// http://www.trirand.com/jqgridwiki/doku.php?id=wiki:form_editing
	theGrid.jqGrid('navGrid','#gridPager',
					//parameters options
			{ edit: true, add: true, del: true, search: false, view: false 
			}, {	// edit options
				height:290, 
				reloadAfterSubmit: false, 
				url: "FUNC001/update", 
				mtype: "POST",
				closeAfterEdit: true				
			}, {	// add options
				height:290, 
				reloadAfterSubmit: false, 
				closeAfterAdd: true,
				onclickSubmit: function () {
					
					var formData = {
						projNm: $('#projNm').val(), 
						custId: $('#custId').val(), 
						startDate: $('#startDate').val(),
						endDate: $('#endDate').val(),
						projMgr: $('#projMgr').val(),
						memo: $('#memo').val(),
						warnMailList: $('#warnMailList').val()
					};
					$.ajax({
						url : "FUNC001/add",
						method : "POST",
						data : formData	
					});
		        }				
			}, {	// del options
				reloadAfterSubmit: false, 
				onclickSubmit: function () {
					var value = $("#theGrid").jqGrid('getRowData', $("#theGrid").jqGrid('getGridParam', 'selrow')).projId;
					$.ajax({
						url : "FUNC001/delete",
						data : "projId=" + value,
						method : "POST"
					});
		        }
//			,
//		        serializeDelData: function (postdata) {
//		        	window.postdata2 = postdata;
//		        	postdata.event = 'del-test';
//		        	return postdata; 
//		        }
			}, 
			{} // search options
			);
	theGrid.click(function(){
    	var id = jQuery("#theGrid").jqGrid('getGridParam', 'selrow');
    	if (id != window.setMyGridSelectedId) {
	    	jQuery("#theGrid").jqGrid('saveRow', window.setMyGridSelectedId, checksave);
	    	delete window.setMyGridSelectedId;			//把id從window元件內刪除
    	} else {
    	}
    });
	theGrid.dblclick(function(){
    	var id = jQuery("#theGrid").jqGrid('getGridParam', 'selrow');
    	if (typeof window.setMyGridSelectedId == "undefined") {
    		window.setMyGridSelectedId = id;			//把id塞進window元件
    		jQuery('#theGrid').jqGrid('editRow', id, true, pickdates);
    	}
    });
    function checksave(result) {
    	if (result.responseText=="") {alert("Update is missing!"); return false;}
    	return true;
    }
    function pickdates(id){
//    	jQuery("#"+id+"_endDate", "#theGrid").datepicker({dateFormat:"yy-mm-dd"});
//    	jQuery("#"+id+"_startDate", "#theGrid").datepicker({dateFormat:"yy-mm-dd"});
    }
});