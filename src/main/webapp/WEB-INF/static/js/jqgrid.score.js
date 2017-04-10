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
		url:'FUNC005/query',
    	datatype: "json",
    	mtype: 'GET',
        colNames: ['學號', '學年度', '姓名', '班代號', '系別', '檔案名稱'], //, '頁數'
        colModel: [
            {name: 'sId', index: 'sId', align: 'center', editable:true},
            {name: 'year', index: 'year', align: 'center', editable:true},
            {name: 'sName', index: 'sName', align: 'center', editable:true},
            {name: 'classId', index: 'classId', align: 'center', editable:true},
            {name: 'deptCode', index: 'deptCode', align: 'center', editable: true,
            	edittype:'select', formatter:'facultyCode',
            	editoptions: { 
            		dataUrl:"FUNC005/GETDEPT", 
                    buildSelect: function (data) {
                    	var response = jQuery.parseJSON(data);
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
            },
            {name: 'fileName', index: 'fileName', align: 'center', editable:true}
            //, {name: 'page', index: 'page', align: 'center', editable:true}
        ],
        loadonce: true,				//一次把所有資料載入
        jsonReader: {
            root:"rows",             
            page: "page",           
            total: "total",    
            records: "records",
            repeatitems : false                
            },
        editurl: "FUNC005/update",	//update資料的uri
        autowidth: true,
        firstsortorder: 'desc',
        gridview: true,             
        rownumbers: true,
        rowNum: 15,
        rowList: [10, 15, 30, 50],
        pager: '#gridPager',
        sortname: 'sId',
        //multiselect: true,			//開啟多筆選擇
        viewrecords: true,
        sortorder: 'desc',
        caption: '管理學生分數資料',			//表頭文字
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
				url: "FUNC005/update", 
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
						url : "FUNC005/add",
						method : "POST",
						data : formData	
					});
		        }				
			}, {	// del options
				reloadAfterSubmit: false, 
				onclickSubmit: function () {
					var value = $("#theGrid").jqGrid('getRowData', $("#theGrid").jqGrid('getGridParam', 'selrow')).account;
					$.ajax({
						url : "FUNC005/delete",
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
    	var id = $("#theGrid").jqGrid('getGridParam', 'selrow');
    	if (id != window.setMyGridSelectedId) {
	    	jQuery("#theGrid").jqGrid('saveRow', window.setMyGridSelectedId, checksave);
	    	delete window.setMyGridSelectedId;			//把id從window元件內刪除
    	} else {
    	}
    	var data = $("#theGrid").jqGrid('getRowData', id);
    	var formData = {
				year: data.year,
				fileName: data.fileName,
				sId: data.sId,
			};
      	$.ajax({
		      url: 'FUNC005/copyImage',
		      method: 'POST',
		      data: formData,
		      success: function(datas) {
		    	  var response = jQuery.parseJSON(datas);
		    	  if (response.result == 'done') {
		    		  window.open("FUNC005/loadImage?year="+data.year+"&fileName="+data.fileName+"&width="+response.width+"&height="+response.height+"&sId="+data.sId+"&type="+$("input[name='showType']:checked").val());
		    	  } else if (response.result == 'privilege') {
		    		  alert('抱歉，您沒有查詢影像的權限。');
		    		  return false;
		    	  } else if (response.result == 'null') {
		    		  alert('檔案不存在。');
		    		  return false;
		    	  } else {
		    		  alert('發生預期外的錯誤，請通知系統管理者。');
		    		  return false;
		    	  }
		      }
		});
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