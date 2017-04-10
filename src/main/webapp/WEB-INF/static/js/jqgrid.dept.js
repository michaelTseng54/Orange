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
		url:'query/dept',
    	datatype: "json",
    	mtype: 'GET',
        colNames: ['系所別代號', '系所別中文名稱', '所屬學院別'],
        colModel: [
            {name: 'deptCode', index: 'account', align: 'center', editable:true},
            {name: 'deptCname', index: 'password', align: 'center', editable:true},
            {name: 'facultyCode', index: 'facultyCode', align: 'center', editable: true,
            	edittype:'select', formatter:'facultyCode',
            	editoptions: { 
            		dataUrl:"GETFACULTY", 
                    buildSelect: function (data) {
                    	var response = jQuery.parseJSON(data);
                    	console.log("response : "+ response);
                    	var s = '<select>';
                        if (response && Object.keys(response).length) {
                        	 $.each(response, function(k, v) {
                        		 s += '<option value="' + v + '">' + k +'('+ v + ')' + '</option>';	 
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
        editurl: "update/dept",	//update資料的uri
        autowidth: true,
        firstsortorder: 'desc',
        gridview: true,             
        rownumbers: true,
        rowNum: 15,
        rowList: [10, 15, 30, 50],
        pager: '#gridPager',
        sortname: 'deptCode',
        //multiselect: true,			//開啟多筆選擇
        viewrecords: true,
        sortorder: 'desc',
        caption: '系所別資料設定',			//表頭文字
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
				url: "update/dept", 
				mtype: "POST",
				closeAfterEdit: true				
			}, {	// add options
				height:290, 
				reloadAfterSubmit: false, 
				closeAfterAdd: true,
				onclickSubmit: function () {
					
					var formData = {
						deptCode: $('#deptCode').val(), 
						deptCname: $('#deptCname').val(),
						facultyCode: $('#facultyCode').val()
					};
					$.ajax({
						url : "add/dept",
						method : "POST",
						data : formData	
					});
		        }				
			}, {	// del options
				reloadAfterSubmit: false, 
				onclickSubmit: function () {
					var value = $("#theGrid").jqGrid('getRowData', $("#theGrid").jqGrid('getGridParam', 'selrow')).account;
					$.ajax({
						url : "delete/dept",
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