$(document).ready(function () {
	//$('#tb tbody tr:even').css("backgroundColor","#EEEEFF");
	
	$("#tb tr").hover(
			function(){
				$(this).css("backgroundColor","#ecf9ff");
				//$(this).addClass("hover");
			},
			function(){
				//$(this).removeClass("hover");
				//$('#tb tbody tr:even').css("backgroundColor","#FFE4C4");
				$(this).css("backgroundColor","");
			});
	
	$('[name=ids]:checkbox').click(function(){
		var flag = true;
		$('[name=ids]:checkbox').each(function(){
			if(!this.checked){
				flag = false;
			}
		});
		$('#allChoose').attr('checked',flag);
	});
	
	$('#allChoose').click(function(){
		$('[name=ids]:checkbox').attr("checked",this.checked);
	});
	
});

//显示弹出层
function ShowDiv(show_div,bg_div){
	$("#"+show_div).css('display','block');
	$("#"+bg_div).css('display','block');
	var bgdiv = $(bg_div);
	bgdiv.style.width = document.body.scrollWidth;
	// bgdiv.style.height = $(document).height();
	$("#"+bg_div).height($(document).height());
};
//关闭弹出层
function CloseDiv(show_div,bg_div)
{
	$("#"+show_div).css('display','none');
	$("#"+bg_div).css('display','none');
};