$(document).ready(function () {
	//$('#tb tbody tr:even').css("backgroundColor","#EEEEFF");
	
	$("#tb tr").hover(
			function(){
				$(this).css("backgroundColor","#FFE4C4");
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
