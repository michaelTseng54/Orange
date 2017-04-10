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
		url:'FUNC007/query',
    	datatype: "json",
    	mtype: 'GET',
        colNames: ['帳號', '密碼', '登入權限', '管理權限', '列印權限', '查詢權限'],
        colModel: [
            {name: 'account', index: 'account', align: 'center', editable:true},
            {name: 'password', index: 'password', align: 'center', editable:true},
            {name: 'loginp', index: 'loginp', align: 'center', editable:true, 
            	edittype:"select", formatter:'loginp', editoptions:{value:"0:取消;1:啟用"}},
            {name: 'manager', index: 'manager', align: 'center', editable:true, 
            	edittype:"select", formatter:'manager', editoptions:{value:"0:取消;1:啟用"}},
            {name: 'print', index: 'print', align: 'center', editable:true, 
            	edittype:"select", formatter:'print', editoptions:{value:"0:取消;1:啟用"}},
            {name: 'query', index: 'query', align: 'center', editable:true, 
            	edittype:"select", formatter:'query', editoptions:{value:"0:取消;1:啟用"}}
        ],
        loadonce: true,				//一次把所有資料載入
        jsonReader: {
            root:"rows",             
            page: "page",           
            total: "total",    
            records: "records",
            repeatitems : false                
            },
        editurl: "FUNC007/update",	//update資料的uri
        autowidth: true,
        firstsortorder: 'desc',
        gridview: true,             
        rownumbers: true,
        rowNum: 15,
        rowList: [10, 15, 30, 50],
        pager: '#gridPager',
        sortname: 'account',
        //multiselect: true,			//開啟多筆選擇
        viewrecords: true,
        sortorder: 'desc',
        caption: '管理人員資料',			//表頭文字
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
				url: "FUNC007/update", 
				mtype: "POST",
				closeAfterEdit: true				
			}, {	// add options
				height:290, 
				reloadAfterSubmit: false, 
				closeAfterAdd: true,
				onclickSubmit: function () {
					
					var formData = {
						account: $('#account').val(),
						password: $('#password').val(), 
						loginp: $('#loginp').val(),
						manager: $('#manager').val(), 
						print: $('#print').val(),
						query: $('#query').val()
					};
					$.ajax({
						url : "FUNC007/add",
						method : "POST",
						data : formData	
					});
		        }				
			}, {	// del options
				reloadAfterSubmit: false, 
				onclickSubmit: function () {
					var value = $("#theGrid").jqGrid('getRowData', $("#theGrid").jqGrid('getGridParam', 'selrow')).account;
					$.ajax({
						url : "FUNC007/delete",
						data : "account=" + value,
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