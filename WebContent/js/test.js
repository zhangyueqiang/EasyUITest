
$(function(){
	$("#mytree").tree({
		onClick:function(node){
			var isExists=$("mytab").tabs("exists",node.text);
			if (isExists==true){
				$("#mytab").tabs("select",node.text);
			}else{
				$("#mytab").tabs("add",{
				title:node.text,
				content:"<iframe id='myframe' src="+node.attribute.url+"></iframe>",
				closable:true
			  });
			}
		}
	});
});